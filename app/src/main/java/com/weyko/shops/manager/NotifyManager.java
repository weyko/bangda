package com.weyko.shops.manager;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.weyko.shops.R;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.config.Constant;
import com.weyko.shops.task.get.TaskInfoBean;
import com.weyko.shops.task.send.TaskInfoActivity;
import com.weyko.shops.util.SaveDataUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 推送工具类
 */
public class NotifyManager {
    private Context mContext;
    private final int lowInt=1000;
    private int num=1;
    private NotificationManager manager;
    public int pushType=0;
    public NotifyManager() {
        mContext= BaseApplication.getInstance();
    }
    private static NotifyManager instance;
    public static NotifyManager getInstance(){
        if(instance==null){
            synchronized (NotifyManager.class){
                if(instance==null)instance=new NotifyManager();
            }
        }
        return instance;
    }
    private Intent setIntentDatas(TaskInfoBean infoBean) {
        Intent intent = new Intent(mContext, TaskInfoActivity.class);
        intent.putExtra(Constant.KEY_TASK_INFO,infoBean);
        intent.putExtra(Constant.KEY_TASK_INFO_NEW,true);
        return intent;
    }

    private boolean isRuning() {
        ActivityManager am = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        String MY_PKG_NAME = mContext.getPackageName();
        for (RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(MY_PKG_NAME)
                    || info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {
                isAppRunning = true;
                break;
            }
        }
        return isAppRunning;
    }

    public void setNotifyDatas(String content, TaskInfoBean infoBean) {
        if(manager==null) {
            manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        int notifyId=pushType+lowInt;
        Notification.Builder builder = new Notification.Builder(mContext);
        builder.setSmallIcon(R.mipmap.app_logo);
        builder.setTicker(mContext.getString(R.string.nofity_new_msg));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setPriority(Notification.PRIORITY_MAX);
        }
        builder.setWhen(System.currentTimeMillis());

        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
                setIntentDatas(infoBean), PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentTitle(mContext.getString(R.string.app_name));
        builder.setContentText(content);
        builder.setContentIntent(contentIntent);
        builder.setDefaults(0);
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
            try {
                Field field = notification.getClass().getDeclaredField("extraNotification");
                Object extraNotification = field.get(notification);
                Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
                method.invoke(extraNotification, num);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!SaveDataUtil.getInstance().getBoolean(Constant.KEY_TASK_INFO_NEW,false)){//如果已经有新消息，但是用户没有点开，就不需要重复通知
            // 声音是否打开
            if (SetManager.isSoundRemindOpen()) {
                notification.defaults |= Notification.DEFAULT_SOUND;
            }
            // 震动是否打开
            if (SetManager.isVibrationRemindOpen()) {
                notification.defaults |= Notification.DEFAULT_VIBRATE;
            }
        }
        manager.notify(notifyId, notification);
        SaveDataUtil.getInstance().putBoolean(Constant.KEY_TASK_INFO_NEW,true);
    }
}
