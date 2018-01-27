package com.weyko.shops.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.config.Constant;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.ActivityLoginBinding;
import com.weyko.shops.manager.CheckManager;
import com.weyko.shops.manager.InputManager;
import com.weyko.shops.share.ThreeAuthorizeUtils;
import com.weyko.shops.util.PerfectClickListener;
import com.weyko.shops.util.ThemeUtil;

import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Description: 登录页面
 * Created  by: weyko on 2017/6/14.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {
    private ThreeAuthorizeUtils threeAuthorizeUtils;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeUtil.getInstance().setWindow(this).setStatusBarColor(getResources().getColor(R.color.colorPage));
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_login;
    }
    @Override
    protected void initData() {
        binding.setModel(new LoginModel());
        InputManager inputManager=new InputManager();
        inputManager.manageInputView(binding.cetInputAccount,InputManager.INPUT_TYPE_CLEAR,0);
        binding.cetInputPwd.setDrawableRight(R.mipmap.icon_eye);
        inputManager.manageInputView(binding.cetInputPwd,InputManager.INPUT_TYPE_PWD,0);
        binding.tvRegisterLogin.setOnClickListener(this);
        binding.ivQqLogin.setOnClickListener(this);
        binding.ivWexinLogin.setOnClickListener(this);
        binding.tvFpwdLogin.setOnClickListener(this);
        binding.cetInputAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(TextUtils.isEmpty(charSequence.toString()))binding.getModel().setPhone("");
                else
                binding.getModel().setPhone(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.cpbLogin.setIndeterminateProgressMode(true);
        binding.cpbLogin.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                if(checkManager==null)
                    checkManager=new CheckManager();
                checkManager.checkLoginData(LoginActivity.this,binding);
            }
        });
    }

    @Override
    protected boolean onBackIntercept() {
        return false;
    }

    @Override
    protected String headerTitle() {
        return null;
    }
    private CheckManager checkManager;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_register_login:
                startActivity(RegisterActivity.class,false);
                break;
            case R.id.iv_qq_login:
                if(threeAuthorizeUtils==null)threeAuthorizeUtils=new ThreeAuthorizeUtils();
                threeAuthorizeUtils.thirdLogin(this,QQ.NAME);
                break;
            case R.id.iv_wexin_login:
                if(threeAuthorizeUtils==null)threeAuthorizeUtils=new ThreeAuthorizeUtils();
                threeAuthorizeUtils.thirdLogin(this, Wechat.NAME);
                break;
            case R.id.tv_fpwd_login:
                startActivity(ResetPasswordActivity.class,false);
                break;
        }
    }
}
