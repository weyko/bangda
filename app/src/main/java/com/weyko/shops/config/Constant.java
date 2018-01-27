package com.weyko.shops.config;

import android.os.Environment;

import java.io.File;

/**
 * Description:
 * Created  by: weyko on 2017/6/5.
 */

public class Constant {
    public static boolean isDebug=true;
    public final static String KEY_APP_FIRST ="KEY_APP_FIRST";
    public final static String KEY_WX_ORDERID ="KEY_WX_ORDERID";
    public final static String KEY_ALI_ORDERID ="KEY_ALI_ORDERID";
    public final static String KEY_TASK_INFO ="KEY_TASK_INFO";
    public final static String KEY_TASK_ID ="KEY_TASK_ID";
    public final static String KEY_TASK_INFO_NEW ="KEY_TASK_INFO_NEW";
    public static String publicKey="bangdaraspublick";
    public static String SERVICE_TEL="10086";
    public static String APP_NAME= "Bangda";
    public static String DIR_NAME= File.separator+APP_NAME+File.separator;
    public static String BASE_DIR= Environment.getExternalStorageDirectory().getAbsolutePath()+ DIR_NAME;
    public static String APK_URL = "http://download.fir.im/v2/app/install/595c5959959d6901ca0004ac?download_token=1a9dfa8f248b6e45ea46bc5ed96a0a9e&source=update";
    public static String KEY_APKPATH = "apkPath";
}
