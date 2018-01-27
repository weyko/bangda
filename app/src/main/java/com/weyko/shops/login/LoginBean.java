package com.weyko.shops.login;

import com.weyko.shops.base.BaseBean;

/**
 * Description:
 * Created  by: weyko on 2017/6/14.
 */

public class LoginBean extends BaseBean{
    private String userId;
    private String userKey;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserKey() {
        return userKey;
    }
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
