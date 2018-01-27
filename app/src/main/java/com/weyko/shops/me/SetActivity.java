package com.weyko.shops.me;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.weyko.shops.CommonWebActivity;
import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.base.BaseDialog;
import com.weyko.shops.config.Constant;
import com.weyko.shops.databinding.ActivitySetBinding;
import com.weyko.shops.login.ResetPasswordActivity;
import com.weyko.shops.manager.CacheManager;
import com.weyko.shops.manager.PermissionManager;
import com.weyko.shops.manager.VersionManager;
import com.weyko.shops.util.CommonUtils;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.util.ThemeUtil;
/**
 * Description: 设置页面
 * Created  by: weyko on 2017/6/16.
 */

public class SetActivity extends BaseActivity<ActivitySetBinding> implements View.OnClickListener {
    private BaseDialog dialogExit;
    private BaseDialog dialogPhone;
    private PermissionManager permissionManager;
    private VersionManager versionManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_set;
    }
    @Override
    protected void initData() {
        showBack();
        binding.tvResetPwdSet.setOnClickListener(this);
        binding.tvCheckversionSet.setOnClickListener(this);
        binding.tvRebackSet.setOnClickListener(this);
        binding.tvConnectSet.setOnClickListener(this);
        binding.tvExitSet.setOnClickListener(this);
        binding.tvServiceSet.setOnClickListener(this);
        binding.tvVersionSet.setText(CommonUtils.getVersionName());
    }

    @Override
    protected boolean onBackIntercept() {
        finish();
        return false;
    }

    @Override
    protected String headerTitle() {
        return getString(R.string.me_setting);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_reset_pwd_set://重置密码
                startActivity(ResetPasswordActivity.class,false);
                break;
            case R.id.tv_checkversion_set://检查版本
                if(versionManager==null)versionManager=new VersionManager();
                   versionManager.checkVersion(this);
                break;
            case R.id.tv_reback_set://意见反馈
                startActivity(RebackActivity.class,false);
                break;
            case R.id.tv_connect_set://联系客服
                showPhoneDialog();
                break;
            case R.id.tv_exit_set://退出当前账号
                showExitDialog();
                break;
            case R.id.tv_service_set://服务条款
                CommonWebActivity.openWeb(SetActivity.this,"service_agreement",getString(R.string.set_service));
                break;
            default:
                break;
        }
    }
    private void showExitDialog() {
        if(dialogExit==null) {
            dialogExit = new BaseDialog(this);
            dialogExit.setMsg(getString(R.string.set_exit_hint));
            dialogExit.setOnSureClick(new PerfectClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    CacheManager.getInstance().exitLogin(SetActivity.this);
                }
            });
        }
        if(!dialogExit.isShowing())
           dialogExit.show();
    }

    private void showPhoneDialog() {
        if(dialogPhone==null) {
            dialogPhone = new BaseDialog(this);
            dialogPhone.setMsg(String.format(getString(R.string.set_connect_msg),Constant.SERVICE_TEL));
            dialogPhone.setHint(getString(R.string.set_connect_hint));
            dialogPhone.setSureText(getString(R.string.set_connect_sure));
            dialogPhone.setOnSureClick(new PerfectClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if(permissionManager==null){
                        permissionManager=new PermissionManager();
                    }
                    permissionManager.checkPermisson(SetActivity.this, PermissionManager.RequestPermisson.PERMISSION_PHONE, new PermissionManager.OnPermissionListener() {
                        @Override
                        public void onPermissionCheckResult(boolean isAllow) {
                            if(isAllow){
                                CommonUtils.callPhone(Constant.SERVICE_TEL);
                            }else{
                                ShowUtil.d("showPhoneDialog----->isAllow="+isAllow);
                            }
                        }
                    });
                }
            });
        }
        if(!dialogPhone.isShowing())
            dialogPhone.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dialogExit!=null){
            dialogExit.dismiss();
        }
        if(dialogPhone!=null){
            dialogPhone.dismiss();
        }
        if(versionManager!=null)versionManager.onDestory();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ShowUtil.d("onRequestPermissionsResult-------->");
        if(requestCode== PermissionManager.RequestPermisson.PERMISSION_PHONE)
           permissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        else if(requestCode==PermissionManager.RequestPermisson.PERMISSION_SDCARD_WRITE){
            versionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }
}
