package com.weyko.shops.login;

import com.weyko.shops.base.BaseBean;

/**
 * Description:
 * Created  by: weyko on 2017/6/29.
 */

public class LoginCacheBean extends BaseBean {
    private LoginCache data;

    public LoginCache getData() {
        return data;
    }
    public void setData(LoginCache data) {
        this.data = data;
    }

    public class LoginCache{
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
    public static class LoginCacheSubmit{
        private int instruct;
        private String userId;
        private String userKey;

        public int getInstruct() {
            return instruct;
        }

        public void setInstruct(int instruct) {
            this.instruct = instruct;
        }

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
}
