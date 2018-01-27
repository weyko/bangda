package com.weyko.shops.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.weyko.shops.MainActivity;
import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.LayoutBindPhoneBinding;
import com.weyko.shops.manager.InputManager;
import com.weyko.shops.network.http.RequestImpl;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.RecodeUtil;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.util.ThemeUtil;

import rx.Subscription;

/**
 * Description:重置密码
 * Created  by: weyko on 2017/7/26.
 */

public class ResetPasswordActivity extends BaseActivity<LayoutBindPhoneBinding>{
    private RecodeUtil recodeUtil;
    private InputManager inputManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.layout_bind_phone;
    }

    @Override
    protected void initData() {
        showBack();
        inputManager=new InputManager();
        recodeUtil=new RecodeUtil();
        recodeUtil.bindView(binding.cetAccountBindphone,binding.cetCodeBindphone,binding.tvCodeBindphone);
        recodeUtil.registerRecode();
        binding.tvNextBindphone.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if(TextUtils.isEmpty(binding.cetCodeBindphone.getText().toString())){
                    ShowUtil.showToast(view.getContext(),getString(R.string.check_securitycode_hint));
                    return;
                }
                recodeUtil.checkRecode(ResetPasswordActivity.this, new RequestImpl() {
                    @Override
                    public void loadSuccess(Object object) {
                        recodeUtil.removeCallbacksAndMessages();
                        binding.tvNextBindphone.setEnabled(false);
                        showSecondStep();
                    }

                    @Override
                    public void loadFailed() {

                    }

                    @Override
                    public void addSubscription(Subscription subscription) {
                        ResetPasswordActivity.this.addSubscription(subscription);
                    }
                });
            }
        });
        inputManager.manageInputView(binding.cetAccountBindphone,InputManager.INPUT_TYPE_CLEAR,0);
        inputManager.manageInputView(binding.cetCodeBindphone,InputManager.INPUT_TYPE_CLEAR,0);
        inputManager.manageInputView(binding.cetPwdBindphone,InputManager.INPUT_TYPE_PWD,0);
        inputManager.manageInputView(binding.cetPwdAgainBindphone,InputManager.INPUT_TYPE_PWD,0);
    }

    @Override
    protected boolean onBackIntercept() {
        finish();
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recodeUtil.unregisterRecode();
    }

    @Override
    protected String headerTitle() {
        return getString(R.string.pwd_reset);
    }
    private void showSecondStep(){
        binding.llStepFirst.setVisibility(View.GONE);
        binding.llStepSecond.setVisibility(View.VISIBLE);
        binding.tvSubmitBindphone.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                String pwd=binding.cetPwdBindphone.getText().toString();
                String pwdAgree=binding.cetPwdAgainBindphone.getText().toString();
                if(TextUtils.isEmpty(pwd)){
                    ShowUtil.showToast(view.getContext(),getString(R.string.check_pwd_hint));
                    return;
                }
                if(!pwd.equals(pwdAgree)){
                    ShowUtil.showToast(view.getContext(),getString(R.string.pwd_fail_agreed));
                    return;
                }
                SaveDataUtil.getInstance().setSSOUserId(binding.cetAccountBindphone.getText().toString());
                resetPwd(pwdAgree);
            }
        });
    }
    private void resetPwd(String pwdAgree){
        ResetPwdBean resetPwdBean=new ResetPwdBean();
        resetPwdBean.setInstruct(UDPConfig.ACTION_RESET_PWD);
        resetPwdBean.setUserId(SaveDataUtil.getInstance().getSSOUserId());
        resetPwdBean.setNewPassword(pwdAgree);
        SendPackageToServer.getInstance().sendData(this, ConvertTool.toJsonStr(resetPwdBean), ThirdLoginBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result!=null){
                    ThirdLoginBean thirdLoginBean= (ThirdLoginBean) result;
                    ShowUtil.showToast(ResetPasswordActivity.this,thirdLoginBean.getMsg());
                    if(thirdLoginBean.isOk()){
                        startActivity(MainActivity.class,true);
                    }
                }
            }
        });
    }
}
