package com.weyko.shops.util;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.weyko.shops.R;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.manager.CheckManager;
import com.weyko.shops.network.http.RequestImpl;
import com.weyko.shops.view.DrawableClickableEditText;

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Description:验证码工具类
 * Created  by: weyko on 2017/7/26.
 */

public class RecodeUtil implements TextWatcher {
    private DrawableClickableEditText phoneView,recodeView;
    private TextView recodeGetView;
    private String codeGet,codeSecond;
    private CheckManager checkManager;
    private EventHandler eventHandler;
    public RecodeUtil() {
        codeGet= BaseApplication.getInstance().getString(R.string.register_code_get);
        codeSecond=BaseApplication.getInstance().getString(R.string.register_scond);
    }
    public void bindView(DrawableClickableEditText phoneView,DrawableClickableEditText recodeView,TextView recodeGetView){
        this.phoneView=phoneView;
        this.recodeView=recodeView;
        this.recodeGetView=recodeGetView;
        phoneView.addTextChangedListener(this);
    }
    public void registerRecode(){
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result!=SMSSDK.RESULT_COMPLETE||data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    String msg = throwable.getMessage();
                    ShowUtil.d("weyko","afterEvent--msg="+msg);
                    JSONObject object = null;
                    try {
                        object = new JSONObject(throwable.getMessage());
                        String des = object.optString("detail");
                        handler.sendMessage(handler.obtainMessage(-100,des));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                        handler.sendEmptyMessage(60);
                    }else if(event==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        handler.sendEmptyMessage(-200);
                    }
                }
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
        recodeGetView.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(checkManager==null)checkManager=new CheckManager();
                if(phoneView==null||!checkManager.checkCodeData(phoneView.getText().toString()))return;
                SMSSDK.getVerificationCode("86",phoneView.getText().toString());
            }
        });
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(recodeGetView==null)return;
            int time = msg.what;
           if(time==-100){
                Toast.makeText(BaseApplication.getInstance(),String.valueOf(msg.obj),Toast.LENGTH_SHORT).show();
                return;
            }else if(time==-200){
                if(requestImpl!=null){
                    requestImpl.loadSuccess(null);
                    return;
                }
            }else  if(time==0){
               recodeGetView.setEnabled(true);
               recodeGetView.setClickable(true);
               recodeGetView.setText(codeGet);
               recodeGetView.setTextColor(recodeGetView.getResources().getColor(R.color.colorPrimary));
               removeCallbacksAndMessages(null);
               return;
           }
            recodeGetView.setEnabled(false);
            recodeGetView.setClickable(false);
            recodeGetView.setTextColor(recodeGetView.getResources().getColor(android.R.color.white));
            recodeGetView.setText(time+codeSecond);
            time--;
            ShowUtil.d("weyko","time="+time);
            handler.sendEmptyMessageDelayed(time,1000);
        }
    };
//    private ShareModel shareModel;
    private RequestImpl requestImpl;
    public void checkRecode(FragmentActivity activity, RequestImpl requestImpl){
        if(phoneView==null||recodeView==null)return;
        this.requestImpl=requestImpl;
//        if(shareModel==null){
//            shareModel=new ShareModel();
//        }
//        shareModel.setData(phoneView.getText().toString(),recodeView.getText().toString());
//        shareModel.checkCode(activity,requestImpl);
        SMSSDK.submitVerificationCode("86", phoneView.getText().toString(), recodeView.getText().toString());
    }
    public void removeCallbacksAndMessages(){
        handler.removeCallbacksAndMessages(null);
    }
    public void unregisterRecode(){
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int len = s.length();
        recodeGetView.setEnabled(len > 0);
        if (len > 0) {
            recodeGetView.setBackgroundResource(R.drawable.recode_btn_bg_selector);
        } else {
            recodeGetView.setBackgroundColor(recodeGetView.getResources().getColor(R.color.colorPage));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
