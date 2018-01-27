package com.weyko.shops.manager;

import com.weyko.shops.base.BaseManager;
import com.weyko.shops.bean.TokenBean;
import com.weyko.shops.util.SaveDataUtil;

/**
 * Description: 缓存管理类
 * Created  by: weyko on 2017/6/16.
 */

public class UserCacheManager implements BaseManager {
    private static UserCacheManager instance;
    public static UserCacheManager getInstance(){
        if(instance==null){
            synchronized (UserCacheManager.class){
                if(instance==null)instance=new UserCacheManager();
            }
        }
        return instance;
    }

    /**
     * 存储用户登录信息
     * @param tokenBean
     */
    public void saveUserLoginInfo(TokenBean tokenBean){
        SaveDataUtil.getInstance().setSSOUserId(tokenBean==null?"":tokenBean.getUseId());
        SaveDataUtil.getInstance().setUserKey(tokenBean==null?"":tokenBean.getToken());
        SaveDataUtil.getInstance().setPhone(tokenBean==null?"":tokenBean.getPhone());
    }
    @Override
    public void onDestory() {
        if(instance!=null){
            instance=null;
        }
    }
}
