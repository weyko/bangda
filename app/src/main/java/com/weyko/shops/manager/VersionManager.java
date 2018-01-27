package com.weyko.shops.manager;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.base.BaseDialog;
import com.weyko.shops.base.BaseManager;
import com.weyko.shops.bean.VersionBean;
import com.weyko.shops.config.Constant;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.network.http.download.DownLoadBrocastReceiver;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.util.CommonUtils;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;

/**
 * 版本管理工具类
 * Created by zhong.xiwei on 2017/8/10.
 */

public class VersionManager implements BaseManager {
    private VersionBean.VersionSubmit submit;
    private BaseDialog dialogVersion;
    private PermissionManager permissionManager;
    private int versionCode;
    public void checkVersion(final BaseActivity activity) {
        if(submit==null){
            versionCode = CommonUtils.getVersionCode();
            submit=new VersionBean.VersionSubmit();
            submit.setInstruct(UDPConfig.ACTION_CHECK_VERSION);
            submit.setPlatName("android");
            submit.setVersionCode(versionCode);
        }
        SendPackageToServer.getInstance().sendData(activity, ConvertTool.toJsonStr(submit), VersionBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result instanceof VersionBean){
                    VersionBean bean= (VersionBean) result;
                    if(!bean.isOk()){
                        ShowUtil.showToast(activity,bean.getMsg());
                    }else {
                        showDownDialog(activity,bean.getVersionName());
                    }
                }
            }
        });
    }
    @Override
    public void onDestory() {
        if(dialogVersion!=null&&dialogVersion.isShowing()){
            dialogVersion.dismiss();
            dialogVersion=null;
        }
        SendPackageToServer.getInstance().cancal(UDPConfig.ACTION_CHECK_VERSION);
    }
    private void showDownDialog(final BaseActivity activity, final String versionName) {
        final boolean isDown = checkIsDown(versionName);
        if(dialogVersion==null){
            dialogVersion = new BaseDialog(activity);
            dialogVersion.setSureText(activity.getString(R.string.set_version_update_sure));
            dialogVersion.setOnSureClick(new PerfectClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if(permissionManager==null){
                        permissionManager=new PermissionManager();
                    }
                    permissionManager.checkPermisson(activity, PermissionManager.RequestPermisson.PERMISSION_SDCARD_WRITE, new PermissionManager.OnPermissionListener() {
                        @Override
                        public void onPermissionCheckResult(boolean isAllow) {
                            if(isAllow){
                                if(isDown){
                                    installApk(activity);
                                }else {
                                    startDown(activity,versionName);
                                }
                            }
                        }
                    });
                }
            });
        }
        dialogVersion.setMsg(String.format(activity.getString(isDown?R.string.set_version_update_down_msg:R.string.set_version_update_msg),versionName));
        if(!dialogVersion.isShowing()){
            dialogVersion.show();
        }
    }

    private boolean checkIsDown(String versionName){
        String apkPath = SaveDataUtil.getInstance().getString(Constant.KEY_APKPATH);
        ShowUtil.d("checkIsDown------>"+apkPath+" versionName="+versionName);
        if(!TextUtils.isEmpty(apkPath)&&apkPath.endsWith(versionName+".apk")){
            return true;
        }
        return false;
    }
    private void startDown(BaseActivity activity,String versionName){
        Intent intent=new Intent(activity,DownLoadBrocastReceiver.class);
        intent.setAction(DownLoadBrocastReceiver.DOWN_ACTION_START);
        intent.putExtra("apkUrl",Constant.APK_URL);
        intent.putExtra("versionName",versionName);
        activity.sendBroadcast(intent);
    }
    private void installApk(BaseActivity activity){
        Intent intent=new Intent(activity,DownLoadBrocastReceiver.class);
        intent.setAction(DownLoadBrocastReceiver.DOWN_ACTION_COMPLETE);
        intent.putExtra(DownloadManager.EXTRA_DOWNLOAD_ID,SaveDataUtil.getInstance().getLong("downloadId"));
        activity.sendBroadcast(intent);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(permissionManager==null)return;
          permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
