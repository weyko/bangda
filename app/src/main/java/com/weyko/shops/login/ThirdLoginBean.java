package com.weyko.shops.login;

import com.weyko.shops.base.BaseBean;

/**
 * Description: 第三方登录
 * Created  by: weyko on 2017/7/26.
 */

public class ThirdLoginBean extends BaseBean{
    private ThirdLoginData data;

    public ThirdLoginData getData() {
        return data;
    }

    public void setData(ThirdLoginData data) {
        this.data = data;
    }

    public class ThirdLoginData{
        private String userId ;
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
    public static class ThirdLoginSubmit{
        private int instruct;
        private String sharePlat;
        private String account;
        private String nickName;
        public int getInstruct() {
            return instruct;
        }

        public void setInstruct(int instruct) {
            this.instruct = instruct;
        }

        public String getSharePlat() {
            return sharePlat;
        }

        public void setSharePlat(String sharePlat) {
            this.sharePlat = sharePlat;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
