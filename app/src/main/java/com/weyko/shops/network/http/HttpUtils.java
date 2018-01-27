package com.weyko.shops.network.http;

import com.weyko.shops.config.URLConfig;
import com.weyko.shops.network.http.download.ProgressInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 */
public class HttpUtils {
    private static HttpUtils instance;
    private Retrofit retrofit;
    private Retrofit retrofit_oneshare;
    public static HttpUtils getInstance(){
        if(instance==null){
            synchronized (HttpUtils.class){
                if(instance==null)instance=new HttpUtils();
            }
        }
        return instance;
    }
    private OkHttpClient getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return  new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new ProgressInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(URLConfig.TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    private Retrofit init(String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public <T> T getService(Class<T> clzz){
        if(retrofit==null){
            retrofit=init(URLConfig.BASE_URL);
        }
        T result=retrofit.create(clzz);
        return result;
    }
    public <T> T getOneShareService(Class<T> clzz){
        if(retrofit_oneshare==null){
            retrofit_oneshare=init(URLConfig.BASE_URL_ONESHARE);
        }
        T result=retrofit_oneshare.create(clzz);
        return result;
    }
}
