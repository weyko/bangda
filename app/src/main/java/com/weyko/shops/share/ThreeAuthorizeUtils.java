package com.weyko.shops.share;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.mob.tools.utils.UIHandler;
import com.weyko.shops.MainActivity;
import com.weyko.shops.R;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.login.ThirdLoginBean;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Set;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * 第三方授权
 */
public class ThreeAuthorizeUtils {
    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;
    private final Context mContext;
    private PlatfromActionListener platfromActionListener = null;
    public interface ThreeAuthorizeInterface {
        void onValid(Platform plat);//已授权

        void onComplete(Platform platform, HashMap<String, Object> res);//授权成功

        void onCancel();//授权取消

        void onError();//授权失败
    }
    public ThreeAuthorizeUtils() {
        this.mContext = BaseApplication.getInstance();
    }

    /**
     * 授权
     *
     * @param plat 第三方平台来源
     */
    private void authorize(final Platform plat, final ThreeAuthorizeInterface threeAuthorizeCallBack) {
        if (plat.isAuthValid()) {//已授权
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message arg0) {
                        if(threeAuthorizeCallBack!=null)threeAuthorizeCallBack.onValid(plat);
                        return false;
                    }
                });
                return;
            }
        }
        if (platfromActionListener == null) {
            platfromActionListener = new PlatfromActionListener(threeAuthorizeCallBack);
        }
        plat.setPlatformActionListener(platfromActionListener);
        plat.SSOSetting(false);
        plat.showUser(null);
    }

    private class PlatfromActionListener implements PlatformActionListener {
        private ThreeAuthorizeInterface threeAuthorizeCallBack;

        public PlatfromActionListener(ThreeAuthorizeInterface threeAuthorizeCallBack) {
            this.threeAuthorizeCallBack = threeAuthorizeCallBack;
        }

        @Override
        public void onError(Platform platform, int action, Throwable t) {
            if (action == Platform.ACTION_USER_INFOR) {
                UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message arg0) {
                        ShowUtil.showToast(mContext,
                                mContext.getApplicationContext().getString(R.string.msg_auth_error));
                        if(threeAuthorizeCallBack!=null)threeAuthorizeCallBack.onError();
                        return false;
                    }
                });
            }
            t.printStackTrace();
        }

        @Override
        public void onComplete(final Platform platform, int action,
                               final HashMap<String, Object> res) {
            if (action == Platform.ACTION_USER_INFOR) {
                UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE,
                        new Handler.Callback() {
                            @Override
                            public boolean handleMessage(Message arg0) {
                                //授权成功。执行登录操作
                                if (res != null && res.keySet() != null && res.keySet().size() > 0&&threeAuthorizeCallBack!=null) {
                                    threeAuthorizeCallBack.onComplete(platform, res);
                                }
                                return false;
                            }
                        });
            }
        }

        @Override
        public void onCancel(Platform platform, int action) {
            if (action == Platform.ACTION_USER_INFOR) {
                UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message arg0) {
                        ShowUtil.showToast(mContext, mContext.getApplicationContext().getString(R.string.msg_auth_cancel));
                        if(threeAuthorizeCallBack!=null)threeAuthorizeCallBack.onCancel();
                        return false;
                    }
                });
            }
        }
    }

    /**
     * 第三方登录
     * @param sharePlat
     */
    public void thirdLogin(final BaseActivity activity, String sharePlat){
        authorize(ShareSDK.getPlatform(sharePlat),new ThreeAuthorizeUtils.ThreeAuthorizeInterface() {
            @Override
            public void onValid(Platform plat) {
                ShowUtil.d("onValid-------->");
                doThirdLogin(activity,plat);
            }

            @Override
            public void onComplete(Platform platform, HashMap<String, Object> res) {
                if (res == null)
                    return;
                Set<String> keys = res.keySet();
                JSONObject jsonObject = new JSONObject();
                for (String key : keys) {
                    try {
                        jsonObject.put(key, res.get(key)
                                .toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                ShowUtil.d("onComplete.thirdPartyDb="+jsonObject.toString());
                SaveDataUtil.getInstance().setThirdData(jsonObject.toString());
                doThirdLogin(activity,platform);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError() {
            }
        });
    }
    private void doThirdLogin(final BaseActivity activity, Platform platform){
        String thirdPartyDb = SaveDataUtil.getInstance().getThirdData();
        String thirdPartyAccount = platform.getDb().getUserId();
        String iconUrl="";
        String platName=platform.getName();
        String nickname="";
        if (Wechat.NAME.equals(platform.getName())) {
            thirdPartyAccount = platform.getDb().get("unionid");
            iconUrl = platform.getDb().getUserIcon();
        }else if(QQ.NAME.equals(platform.getName())){
            JSONObject jsonObject = null;
            try {
                if(!TextUtils.isEmpty(thirdPartyDb)){
                    ShowUtil.d("thirdPartyDb="+thirdPartyDb);
                    jsonObject = new JSONObject(thirdPartyDb);
                    iconUrl = jsonObject.getString("figureurl_qq_2");
                    nickname = jsonObject.getString("nickname");
                }else{
                    platform.removeAccount(true);
                    thirdLogin(activity,platform.getName());
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            iconUrl = platform.getDb().getUserIcon();
        }
        String thirdPartyKey = platform.getDb().getToken();
        ShowUtil.d("platform.thirdPartyAccount="+thirdPartyAccount+" name="+platform.getName()+" thirdPartyKey="+thirdPartyKey+"\niconUrl="+iconUrl);
        ThirdLoginBean.ThirdLoginSubmit thirdLoginSubmit=new ThirdLoginBean.ThirdLoginSubmit();
        thirdLoginSubmit.setInstruct(UDPConfig.ACTION_THIRD_LOGIN);
        thirdLoginSubmit.setAccount(thirdPartyAccount);
        thirdLoginSubmit.setSharePlat(platName);
        thirdLoginSubmit.setNickName(nickname);
        SendPackageToServer.getInstance().sendData(activity, ConvertTool.toJsonStr(thirdLoginSubmit), ThirdLoginBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                if(result!=null){
                    ThirdLoginBean bean= (ThirdLoginBean) result;
                    if(bean.isOk()) {
                        SaveDataUtil.getInstance().setSSOUserId(bean.getData().getUserId());
                        SaveDataUtil.getInstance().setUserKey(bean.getData().getUserKey());
                        activity.startActivity(MainActivity.class,true);
                    }else{
                        ShowUtil.showToast(activity,bean.getMsg());
                    }
                }
            }
        });
    }
    public void clearMemory() {
        if (platfromActionListener != null)
            platfromActionListener = null;
    }
}
