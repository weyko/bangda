package com.weyko.shops.config;

/**
 * Description:
 * Created  by: weyko on 2017/6/6.
 */

public class URLConfig {
    public static final String POST_GET_SIGN="";
    public static final String POST_REQUEST_UNIFIED_ORDER="";
    /**
     * 网络超时
     */
    public static final long TIME_OUT = 10;
    public static final String APP_KEY = "1f788de568517";
    public static String HTTP_HOST ;
    public static String BASE_URL ="http://download.fir.im/";
    public static String BASE_URL_ONESHARE="https://webapi.sms.mob.com/" ;

    public static int conditionFlag=1;
    public static String baseUrlSpalce(String url) {
        if (url != null && url.indexOf("http") != -1) {
            return url;
        } else {
            url = URLConfig.HTTP_HOST + url;
        }
        return url;
    }
}
