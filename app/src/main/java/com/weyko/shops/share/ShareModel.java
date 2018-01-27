package com.weyko.shops.share;

import android.support.v4.app.FragmentActivity;

import com.weyko.shops.R;
import com.weyko.shops.config.URLConfig;
import com.weyko.shops.network.http.HttpRequestService;
import com.weyko.shops.network.http.HttpUtils;
import com.weyko.shops.network.http.RequestImpl;
import com.weyko.shops.util.ShowUtil;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description: 验证码统一管理接口类
 * Created  by: weyko on 2017/7/17.
 */

public class ShareModel {
    private String phone;
    private String code;
    public void setData(String phone,String code){
        this.phone=phone;
        this.code=code;
    }

    /**
     * 检查验证码
     * @param activity
     * @param listener
     */
    public void checkCode(final FragmentActivity activity,final RequestImpl listener){
        if(activity==null||listener==null)return;
        Subscription subscription = HttpUtils.getInstance().getOneShareService(HttpRequestService.class).getSMS(URLConfig.APP_KEY,phone,"86",code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShareBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFailed();
                    }

                    @Override
                    public void onNext(ShareBean shareBean) {
                        if(shareBean!=null){
                            if(shareBean.getStatus()!=200){
                                ShowUtil.showToast(activity,activity.getString(R.string.check_code_error));
                            }else {
                                listener.loadSuccess(shareBean);
                            }
                        }
                    }
                });
        listener.addSubscription(subscription);
    }
}
