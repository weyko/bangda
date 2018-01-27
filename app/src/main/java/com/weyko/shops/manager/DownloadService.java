package com.weyko.shops.manager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import com.weyko.shops.R;
import com.weyko.shops.config.Constant;
import com.weyko.shops.network.http.HttpRequestService;
import com.weyko.shops.network.http.HttpUtils;
import com.weyko.shops.network.http.download.FileCallBack;
import com.weyko.shops.network.http.download.FileLoadEvent;
import com.weyko.shops.network.http.download.FileSubscriber;
import com.weyko.shops.util.CommonUtils;
import com.weyko.shops.util.ShowUtil;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by zhong.xiwei on 2017/8/9.
 */

public class DownloadService extends IntentService {
    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int notifyId=10101;
    private int mProgress=0;
    public DownloadService() {
        super("DownloadService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.app_logo)
                .setContentTitle("更新")
                .setContentText("下载安装包")
                .setAutoCancel(true);

        notificationManager.notify(notifyId, notificationBuilder.build());
        String versionName=intent.getStringExtra("versionName");
        downLoadFile(versionName);
    }

    private void downLoadFile(String versionName) {
        final String fileName= Constant.APP_NAME+"_"+versionName+".apk";
        final FileCallBack callBack=new FileCallBack(Constant.BASE_DIR,fileName) {
            @Override
            public void onSuccess(Object o) {
                ShowUtil.d("onSuccess---->");
                downloadCompleted("下载完成");
            }

            @Override
            public void progress(FileLoadEvent fileLoadEvent) {
               int progress=fileLoadEvent.getProgress();
                ShowUtil.d("total="+fileLoadEvent.getTotal()+" read="+fileLoadEvent.getBytesLoaded());
                int dotProgress=progress-mProgress;
                if(dotProgress>5) {
                    mProgress=progress;
                    sendNotification(fileLoadEvent);
                }
            }

            @Override
            public void onStart() {
                ShowUtil.d("onStart---->");
            }

            @Override
            public void onCompleted() {
                ShowUtil.d("onCompleted---->");
                installApk(Constant.BASE_DIR+fileName);
            }

            @Override
            public void onError(Throwable e) {
                ShowUtil.d("onError---->");
                downloadCompleted("下载失败");
            }
        };
        HttpUtils.getInstance().getService(HttpRequestService.class).downLoadFile(Constant.APK_URL)
                .subscribeOn(Schedulers.io())//请求网络 在调度者的io线程
                .observeOn(Schedulers.io()) //指定线程保存文件
                .doOnNext(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody body) {
                        callBack.saveFile(body);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中更新ui
                .subscribe(new FileSubscriber<ResponseBody>(callBack));
    }

    private void installApk(String fileName) {
        notificationManager.cancel(notifyId);
        CommonUtils.installApk(this,fileName);
    }

    private void downloadCompleted(String msg) {
        notificationBuilder.setProgress(0, 0, false);
        notificationBuilder.setContentText(msg);
        notificationManager.notify(notifyId, notificationBuilder.build());
    }

    private void sendNotification(FileLoadEvent download) {
        notificationBuilder.setProgress(100, (int)(download.getBytesLoaded()*100/download.getTotal()), false);
        notificationBuilder.setContentText(String.valueOf(download.getBytesLoaded()));
        notificationManager.notify(notifyId, notificationBuilder.build());
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {
        notificationManager.cancel(notifyId);
    }
}
