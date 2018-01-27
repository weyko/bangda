package com.weyko.shops.manager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.WindowManager;

import com.weyko.shops.MainActivity;
import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.base.BaseManager;
import com.weyko.shops.bean.TokenBean;
import com.weyko.shops.config.Constant;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.databinding.ActivityLoginBinding;
import com.weyko.shops.databinding.ActivityRebackBinding;
import com.weyko.shops.login.LoginActivity;
import com.weyko.shops.login.LoginBean;
import com.weyko.shops.login.LoginCacheBean;
import com.weyko.shops.login.LoginModel;
import com.weyko.shops.login.RegisterBean;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.task.send.SendData;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;
import com.weyko.shops.view.CircularProgress.CircularProgressButton;
import com.weyko.shops.view.DrawableClickableEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:检查工具类
 * Created  by: weyko on 2017/6/16.
 */

public class CheckManager implements BaseManager {
    /**
     * 检查是否第一次登录
     */
    public void checkFirstLogin(final BaseActivity activity) {
        if (activity == null) return;
        final Intent intent = new Intent();
        if (SaveDataUtil.getInstance().getBoolean(Constant.KEY_APP_FIRST, true)) {
            intent.setClass(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
            activity.finish();
        } else {
            if (!TextUtils.isEmpty(SaveDataUtil.getInstance().getUserKey())) {
//                loginByCachePwd(activity, intent);
                gotoHome(activity);
            } else {
                intent.setClass(activity, LoginActivity.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                activity.finish();
            }
        }
    }

    /**
     * 免密登录
     *
     * @param activity
     * @param intent
     */
    private void loginByCachePwd(final BaseActivity activity, final Intent intent) {
        LoginCacheBean.LoginCacheSubmit submit = new LoginCacheBean.LoginCacheSubmit();
        submit.setInstruct(UDPConfig.ACTION_LOGIN_CACHE);
        submit.setUserId(SaveDataUtil.getInstance().getSSOUserId());
        submit.setUserKey(SaveDataUtil.getInstance().getUserKey());
        SendPackageToServer.getInstance().sendData(activity, ConvertTool.toJsonStr(submit), LoginCacheBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if (result == null) {
                    intent.setClass(activity, LoginActivity.class);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                    activity.finish();
                    return;
                }
                LoginCacheBean bean = (LoginCacheBean) result;
                if (bean.isOk()) {
                    intent.setClass(activity, MainActivity.class);
                } else {
                    intent.setClass(activity, LoginActivity.class);
                }
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
                activity.finish();
            }
        });
    }

    private void gotoHome(final BaseActivity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        final Intent intent = new Intent();
        intent.setClass(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        activity.finish();
    }

    /**
     * 检查登录信息
     *
     * @param loginBinding
     * @return
     */
    public void checkLoginData(final BaseActivity activity, final ActivityLoginBinding loginBinding) {
        boolean isOk = false;
        final LoginModel loginModel = loginBinding.getModel();
        String showMsg = "";
        if (TextUtils.isEmpty(loginModel.getPhone())) {
            showMsg = activity.getString(R.string.check_phone_hint);
        } else if (!isPhone(String.valueOf(loginModel.getPhone()))) {
            showMsg = activity.getString(R.string.check_phone_right_hint);
        } else if (TextUtils.isEmpty(loginModel.getPassword())) {
            showMsg = activity.getString(R.string.check_pwd_hint);
        } else {
            isOk = true;
        }
        if (isOk) {
            int progress = loginBinding.cpbLogin.getProgress();
            if(progress==CircularProgressButton.SUCCESS_STATE_PROGRESS||progress==CircularProgressButton.ERROR_STATE_PROGRESS){
                progress=CircularProgressButton.IDLE_STATE_PROGRESS;
                loginBinding.cpbLogin.setProgress(progress);
                return;
            }else{
                progress=CircularProgressButton.INDETERMINATE_STATE_PROGRESS;
                loginBinding.cpbLogin.setProgress(progress);
            }
            loginModel.setInstruct(UDPConfig.ACTION_LOGIN);
            SendPackageToServer.getInstance().sendData(activity, ConvertTool.toJsonStr(loginModel), LoginBean.class, new SendPackageToServer.OnSendDataListener() {
                @Override
                public void onSendBackResult(Object result) {
                    if (result != null) {
                        LoginBean loginBean = (LoginBean) result;
                        ShowUtil.showToast(activity, loginBean.getMsg());
                        if (loginBean.isOk()) {
                            loginBinding.cpbLogin.setProgress(CircularProgressButton.SUCCESS_STATE_PROGRESS);
                            TokenBean tokenBean = new TokenBean(loginBean.getUserKey(), loginBean.getUserId(), loginModel.getPhone());
                            UserCacheManager.getInstance().saveUserLoginInfo(tokenBean);
                            activity.startActivity(MainActivity.class, true);
                        }
                    }else{
                        loginBinding.cpbLogin.setProgress(CircularProgressButton.ERROR_STATE_PROGRESS);
                    }

                }
            });
        } else ShowUtil.showToast(activity, showMsg);
    }

    /**
     * 检查注册信息
     *
     * @param restiterSubmit
     * @return
     */
    public boolean checkRegisterData(BaseActivity activity, RegisterBean.RestiterSubmit restiterSubmit) {
        boolean isOk = false;
        String showMsg = "";
        if (TextUtils.isEmpty(restiterSubmit.getPhone())) {
            showMsg = activity.getString(R.string.check_phone_hint);
        } else if (!isPhone(String.valueOf(restiterSubmit.getPhone()))) {
            showMsg = activity.getString(R.string.check_phone_right_hint);
        } else if (TextUtils.isEmpty(restiterSubmit.getPassword())) {
            showMsg = activity.getString(R.string.check_pwd_hint);
        } else if (TextUtils.isEmpty(restiterSubmit.getSecurityCode())) {
            showMsg = activity.getString(R.string.check_securitycode_hint);
        } else {
            isOk = true;
        }
        if (!isOk) {
            ShowUtil.showToast(activity, showMsg);
        }
        return isOk;
    }

    /**
     * 检查验证码
     *
     * @param phone
     * @return
     */
    public boolean checkCodeData(String phone) {
        boolean isOk = false;
        String showMsg = "";
        Context context = BaseApplication.getInstance();
        if (TextUtils.isEmpty(phone)) {
            showMsg = context.getString(R.string.check_phone_hint);
        } else if (!isPhone(String.valueOf(phone))) {
            showMsg = context.getString(R.string.check_phone_right_hint);
        } else {
            isOk = true;
        }
        if (!isOk) {
            ShowUtil.showToast(context, showMsg);
        }
        return isOk;
    }

    public boolean checkViewData(DrawableClickableEditText inputView) {
        if (TextUtils.isEmpty(inputView.getText().toString())) {
            inputView.setError(inputView.getHint().toString());
            return false;
        }
        return true;
    }

    /**
     * 检查发布任务
     *
     * @param activity
     * @param sendData
     * @return
     */
    public boolean checkSendTaskData(BaseActivity activity, SendData sendData, boolean isAreaModel) {
        boolean isOk = false;
        String showMsg = "";
        if (TextUtils.isEmpty(sendData.getTaskName())) {
            showMsg = activity.getString(R.string.send_taskname);
        } else if (TextUtils.isEmpty(sendData.getTaskDescribe())) {
            showMsg = activity.getString(R.string.send_mark);
        } else if (sendData.getMoneyReward() < 0) {
            showMsg = activity.getString(R.string.send_reward);
        } else if (isAreaModel) {
            if (TextUtils.isEmpty(sendData.getStartPosition())) {
                showMsg = activity.getString(R.string.send_start_address);
            } else if (TextUtils.isEmpty(sendData.getDestination())) {
                showMsg = activity.getString(R.string.send_address);
            } else {
                isOk = true;
            }
        } else if (TextUtils.isEmpty(sendData.getDestination())) {
            showMsg = activity.getString(R.string.send_address);
        } else {
            isOk = true;
        }
        if (!isOk) {
            ShowUtil.showToast(activity, showMsg);
        }
        return isOk;
    }

    /**
     * 检查是否可以意见反馈
     *
     * @return
     */
    public boolean checkIsRebackable(FragmentActivity activity, ActivityRebackBinding binding) {
        if (TextUtils.isEmpty(binding.cetInputReback.getText().toString())) {
            ShowUtil.showToast(activity, activity.getString(R.string.reback_input_hinit_toast));
            return false;
        }
        String connect = binding.cetConnectReback.getText().toString();
        if (TextUtils.isEmpty(connect)) {
            ShowUtil.showToast(activity, activity.getString(R.string.reback_connect_toast));
            return false;
        }
        if (!isPhone(connect) && !isEmail(connect)) {
            ShowUtil.showToast(activity, activity.getString(R.string.reback_connect_format_toast));
            return false;
        }
        return true;
    }

    /**
     * 是否为手机号
     *
     * @return
     */
    public boolean isPhone(String phone) {
        String regex = "1[3,4,5,6,7,8,9]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * 检查邮箱地址格式
     */
    public boolean isEmail(String text) {
        if (Pattern.compile("\\w[\\w.-]*@[\\w.]+\\.\\w+").matcher(text).matches()) {
            return true;
        }
        return false;
    }

    @Override
    public void onDestory() {
    }
}
