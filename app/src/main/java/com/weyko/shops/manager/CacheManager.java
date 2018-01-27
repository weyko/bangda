package com.weyko.shops.manager;
import com.weyko.shops.MainActivity;
import com.weyko.shops.base.BaseActivity;
import com.weyko.shops.base.BaseApplication;
import com.weyko.shops.base.BaseBean;
import com.weyko.shops.config.UDPConfig;
import com.weyko.shops.login.LoginActivity;
import com.weyko.shops.login.LoginOutBean;
import com.weyko.shops.network.udp.SendPackageToServer;
import com.weyko.shops.util.ConvertTool;
import com.weyko.shops.util.SaveDataUtil;
import com.weyko.shops.util.ShowUtil;

/**
 * Description:缓存管理
 * Created  by: weyko on 2017/6/27.
 */

public class CacheManager {
    private static CacheManager instance;
    public static CacheManager getInstance(){
        if(instance==null){
            synchronized (CacheManager.class){
                if(instance==null)instance=new CacheManager();
            }
        }
        return instance;
    }
    public void exitLogin(final BaseActivity activity){
        LoginOutBean outBean=new LoginOutBean();
        outBean.setInstruct(UDPConfig.ACTION_LOGINOUT);
        outBean.setUserId(SaveDataUtil.getInstance().getSSOUserId());
        ShowUtil.d("weyko","exitLogin--->sendData");
        SendPackageToServer.getInstance().sendData(activity,ConvertTool.toJsonStr(outBean), BaseBean.class, new SendPackageToServer.OnSendDataListener() {
            @Override
            public void onSendBackResult(Object result) {
                ShowUtil.d("weyko","exitLogin------>onSendBackResult");
                if(result==null)return;
                BaseBean baseBean= (BaseBean) result;
                ShowUtil.showToast(activity,baseBean.getMsg());
                if(baseBean.isOk()){
                    ShowUtil.d("weyko","exitLogin------>isOk");
                    onDestory();
                    SaveDataUtil.getInstance().setSSOUserId("");
                    SaveDataUtil.getInstance().setUserKey("");
                    SaveDataUtil.getInstance().setPhone("");
                    activity.startActivity(LoginActivity.class,true);
                    BaseApplication.getInstance().removeActivity(MainActivity.class.getSimpleName());
                }
            }
        });
    }
    /**
     * 资源释放
     */
    public void onDestory(){
        SendPackageToServer.getInstance().onDestory();
        LocationManager.getInstance().onDestory();
        UserCacheManager.getInstance().onDestory();
        HearManager.doHeart(true);
        if(instance!=null)instance=null;
    }
}
