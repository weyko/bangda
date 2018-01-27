package com.weyko.shops.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.weyko.shops.MainActivity;
import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.bean.TokenBean;
import com.weyko.shops.config.Constant;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.ActivityRegisterBinding;
import com.weyko.shops.manager.CheckManager;
import com.weyko.shops.manager.InputManager;
import com.weyko.shops.manager.UserCacheManager;
import com.weyko.shops.network.http.RequestImpl;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.RecodeUtil;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.util.ThemeUtil;

import rx.Subscription;

/**
 * Description:注册页面
 * Created  by: weyko on 2017/6/21.
 */

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> implements SendPackageToServer.OnSendDataListener {
    private CheckManager checkManager;
    private RecodeUtil recodeUtil;
    private boolean isRecoded=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this);
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setContent() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        showBack();
        binding.setModel(new RegisterBean.RestiterSubmit());
        final RegisterBean.RestiterSubmit model = binding.getModel();
        model.setInstruct(UDPConfig.ACTION_REGISTER);
        model.setPetName("");
        checkManager=new CheckManager();
        recodeUtil=new RecodeUtil();
        recodeUtil.bindView(binding.cetAccountRegister,binding.cetCodeRegister,binding.tvCodeRegister);
        recodeUtil.registerRecode();
        binding.tvRegisterRegister.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(!checkManager.checkRegisterData(RegisterActivity.this,binding.getModel()))return;
                if(isRecoded){
                    SendPackageToServer.getInstance().sendData(RegisterActivity.this, ConvertTool.toJsonStr(model), RegisterBean.class,RegisterActivity.this);
                    return;
                }
                recodeUtil.checkRecode(RegisterActivity.this, new RequestImpl() {
                    @Override
                    public void loadSuccess(Object object) {
                        isRecoded=true;
                        recodeUtil.removeCallbacksAndMessages();
                        SendPackageToServer.getInstance().sendData(RegisterActivity.this, ConvertTool.toJsonStr(model), RegisterBean.class,RegisterActivity.this);
                    }
                    @Override
                    public void loadFailed() {
                    }
                    @Override
                    public void addSubscription(Subscription subscription) {
                        RegisterActivity.this.addSubscription(subscription);
                    }
                });
            }
        });
        InputManager inputManager=new InputManager();
        inputManager.manageInputView(binding.cetAccountRegister,InputManager.INPUT_TYPE_CLEAR,0);
        inputManager.manageInputView(binding.cetPwdRegister,InputManager.INPUT_TYPE_PWD,0);
        binding.cetAccountRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(charSequence))binding.getModel().setPhone("");
                else
                binding.getModel().setPhone(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @Override
    protected boolean onBackIntercept() {
        finish();
        return false;
    }

    @Override
    protected String headerTitle() {
        return getString(R.string.register);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        recodeUtil.unregisterRecode();
    }

    @Override
    public void onSendBackResult(Object result) {
        if(result!=null){
            RegisterBean data= (RegisterBean) result;
            ShowUtil.showToast(BaseApplication.getInstance(),data.getMsg());
            if(data.isOk()){
                TokenBean tokenBean=new TokenBean(data.getData().getUserKey(),data.getData().getUserId(),binding.getModel().getPhone());
                UserCacheManager.getInstance().saveUserLoginInfo(tokenBean);
                BaseApplication.getInstance().removeActivity(LoginActivity.class.getSimpleName());
                startActivity(MainActivity.class,true);
            }
        }
    }
}
