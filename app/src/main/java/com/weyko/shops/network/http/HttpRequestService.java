package com.weyko.shops.network.http;

import com.weyko.shops.share.ShareBean;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Description:
 * Created  by: weyko on 2017/7/17.
 */

public interface HttpRequestService {
    @FormUrlEncoded
    @POST("sms/verify")
    Observable<ShareBean> getSMS(@Field("appkey") String key, @Field("phone") String phone, @Field("zone") String zone, @Field("code") String code);
    @Streaming
    @GET
    Observable<ResponseBody> downLoadFile(@Url String url);
//    @Streaming
//    @GET("v2/app/install/595c5959959d6901ca0004ac?download_token=1a9dfa8f248b6e45ea46bc5ed96a0a9e&source=update")
//    Observable<ResponseBody> downLoadFile();
}
