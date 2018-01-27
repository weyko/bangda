package com.weyko.shops.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.weyko.shops.base.BaseApplication;

/**
 * Description:数据存储工具类
 * Created  by: weyko on 2017/6/1.
 */

public class SaveDataUtil {
    public static final String KEY_MAP_LOCATION="lat_lon";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static SaveDataUtil instance;
    public static SaveDataUtil getInstance(){
        if(instance==null){
            synchronized (SaveDataUtil.class){
                if(instance==null)instance=new SaveDataUtil(BaseApplication.getInstance().getApplicationContext());
            }
        }
        return instance;
    }
    public SaveDataUtil(Context context) {
        preferences=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor=preferences.edit();
    }
    public void putString(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }
    public String getString(String key){
        return preferences.getString(key,"");
    }
    public void putInt(String key,int value){
        editor.putInt(key,value);
        editor.commit();
    }
    public void putLong(String key,long value){
        editor.putLong(key,value);
        editor.commit();
    }
    public int getInt(String key){
        return preferences.getInt(key,0);
    }
    public long getLong(String key){
        return preferences.getLong(key,0);
    }
    public void putBoolean(String key,boolean value){
        editor.putBoolean(key,value);
        editor.commit();
    }
    public void put(String key,String value){
        editor.putString(key, value);
        editor.commit();
    }
    public boolean getBoolean(String key,boolean defaultValue){
        return preferences.getBoolean(key,defaultValue);
    }
    public String getBalance(){
        return preferences.getString("balance","");
    }
    public void setBalance(String balance){
        editor.putString("balance", balance);
        editor.commit();
    }
    public void setSSOUserId(String userId){
        editor.putString("userId", userId);
        editor.commit();
    }
    public void setUserKey(String userKey){
        editor.putString("userKey",userKey);
        editor.commit();
    }
    public void setPhone(String phone){
        editor.putString("phone",phone);
        editor.commit();
    }
    public String getSSOUserId(){
     return  preferences.getString("userId","");
    }
    public String getUserKey(){
        return preferences.getString("userKey","");
    }
    public String getPhone(){
        return preferences.getString("phone","");
    }
    public void setThirdData(String thirdPartyDb){
        editor.putString("thirdPartyDb",thirdPartyDb);
        editor.commit();
    }
    public String getThirdData(){
        return preferences.getString("thirdPartyDb","");
    }
    public void setLatitude(double latitude) {
        editor.putString("latitude",String.valueOf(latitude));
        editor.commit();
    }
    public double getLatitude(){
      return Double.valueOf(preferences.getString("latitude","0"));
    }
    public void setLgitude(double longitude) {
        editor.putString("longitude",String.valueOf(longitude));
        editor.commit();
    }
    public double getLgitude(){
        return Double.valueOf(preferences.getString("longitude","0"));
    }
}
