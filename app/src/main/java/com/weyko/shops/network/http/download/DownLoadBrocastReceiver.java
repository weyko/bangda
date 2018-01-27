package com.weyko.shops.network.http.download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.weyko.shops.R;
import com.weyko.shops.config.Constant;
import com.weyko.shops.util.CommonUtils;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;

import java.io.File;

/**
 * Created by zhong.xiwei on 2017/8/10.
 */

public class DownLoadBrocastReceiver extends BroadcastReceiver {
    public static final String DOWN_ACTION_START="com.weyko.shops.down.start";
    public static final String DOWN_ACTION_COMPLETE="android.intent.action.DOWNLOAD_COMPLETE";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent==null)return;
        String action = intent.getAction();
        if(DOWN_ACTION_START.equals(action)){
            startDownload(context,intent.getStringExtra("apkUrl"),intent.getStringExtra("versionName"));
        }else if(DOWN_ACTION_COMPLETE.equals(action)){
            long appDownLoadId = -1;
            try {
                appDownLoadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            }catch (Exception e){
                e.printStackTrace();
            }
            ShowUtil.d("installing---------->");
            installApk(context,appDownLoadId);
        }
    }
    private void startDownload(Context context,String downloadUrl,String versionName) {
        String fileName=Constant.APP_NAME+"_"+versionName+".apk";
        DownloadManager downloadManager=(DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        try {
            Uri uri = Uri.parse(downloadUrl);
            DownloadManager.Request request = new DownloadManager.Request(uri);
//            //删除原文件
            if(!deleteFile(context,fileName)){
                Toast.makeText(context, context.getString(R.string.down_fail), Toast.LENGTH_SHORT).show();
                return;
            }
            ShowUtil.d("startDownload---------->");
            //设置下载路径和文件名
            request.setDestinationInExternalPublicDir(Constant.DIR_NAME, fileName);
            request.setTitle(context.getString(R.string.down_title));
            request.setDescription(String.format(context.getString(R.string.down_msg),versionName));
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setMimeType("application/vnd.android.package-archive");
            // 设置为可被媒体扫描器找到
            request.allowScanningByMediaScanner();
            // 设置为可见和可管理
            request.setVisibleInDownloadsUi(true);
            //设置可下载的网络类型
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
            long downloadId = downloadManager.enqueue(request);
            SaveDataUtil.getInstance().putLong("downloadId",downloadId);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, context.getString(R.string.down_fail), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @DESCRIPTION 删除文件夹中原本存在的apk文件
     */
    public boolean deleteFile(Context context,String fileName) {
        try {
            String filePath = getStorageDirectory(context);
            File dir=new File(filePath);
            if(!dir.exists()){
                dir.mkdirs();
            }
           String  apkPath=filePath + File.separator + fileName;
            ShowUtil.d("deleteFile-->apkPath="+apkPath);
            SaveDataUtil.getInstance().put(Constant.KEY_APKPATH,apkPath);
            File file = new File(apkPath);
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    /**
     * @DESCRIPTION 获取下载路径
     */
    public String getStorageDirectory(Context context) {
        String mDataRootPath = context.getCacheDir().getPath();
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ? Constant.BASE_DIR : mDataRootPath + Constant.DIR_NAME;
    }
    private void installApk(Context context,long appDownLoadId){
        long downloadId = SaveDataUtil.getInstance().getLong("downloadId");
        if(appDownLoadId == downloadId){
            CommonUtils.installApk(context,SaveDataUtil.getInstance().getString(Constant.KEY_APKPATH));
            /*try {
                DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                Uri downloadFileUri = dManager.getUriForDownloadedFile(downloadId);
                ShowUtil.d("installApk-------->downloadFileUri="+downloadFileUri+" downloadId="+downloadId);
                installIntent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(installIntent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, context.getString(R.string.down_fail), Toast.LENGTH_SHORT).show();
            }*/
        }
    }
}
