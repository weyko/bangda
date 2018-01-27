package com.weyko.shops.base;

import android.support.v4.app.FragmentActivity;

import com.mob.MobApplication;
import com.weyko.shops.util.ShowUtil;

import java.util.HashMap;

import cn.smssdk.SMSSDK;

/**
 * Description:
 * Created  by: weyko on 2017/5/27.
 */

public class BaseApplication extends MobApplication {
    private static BaseApplication instance;
    private HashMap<String,FragmentActivity>activities;
    public static boolean isPayPage=false;
//
//    private DevOpenHelper mHelper;
//    private SQLiteDatabase db;
//    private DaoMaster mDaoMaster;
//    private DaoSession mDaoSession;
    private int screenWith;
    private int screenHeight;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        activities=new HashMap<>();
        SMSSDK.setInitFlag(SMSSDK.InitFlag.DEFAULT);
    }
    public static BaseApplication getInstance(){
        return instance;
    }
    public void addActivity(BaseActivity activity) {
        activities.put(activity.getClass().getSimpleName(),activity);
    }
    public void removeActivity(String classTag){
        FragmentActivity currentActivity = getActivity(classTag);
        if(currentActivity!=null){
           currentActivity.finish();
           activities.remove(classTag);
        }
    }
    public FragmentActivity getActivity(String clzzName){
        return activities.get(clzzName);
    }
    public void removeAll(){
        activities.clear();
    }
    public int getScreenWith(){
      if(screenWith==0)screenWith= ShowUtil.getScreenSize(this, ShowUtil.ScreenEnum.WIDTH);
        return screenWith;
    }
    public int getScreenHeight(){
        if(screenHeight==0)screenHeight=ShowUtil.getScreenSize(this, ShowUtil.ScreenEnum.HEIGHT);
       return  screenHeight;
    }
//    /**
//     * 设置greenDao
//     */
//    private void setDatabase() {
//        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
//        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
//        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
//        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
//        mHelper = new DevOpenHelper(this, "notes-db");
//        db = mHelper.getWritableDatabase();
//        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
//        mDaoMaster = new DaoMaster(db);
//        mDaoSession = mDaoMaster.newSession();
//    }
//    public DaoSession getDaoSession() {
//        return mDaoSession;
//    }
//    public SQLiteDatabase getDb() {
//        return db;
//    }
}
