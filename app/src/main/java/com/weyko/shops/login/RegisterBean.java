package com.weyko.shops.login;

import com.weyko.shops.base.BaseBean;

/**
 * Description:
 * Created  by: weyko on 2017/6/23.
 */

public class RegisterBean extends BaseBean {
    private RegsiterData data;

    public RegsiterData getData() {
        return data;
    }

    public void setData(RegsiterData data) {
        this.data = data;
    }

    /**
     * 注册返回内容类
     */
    public class RegsiterData{
        /**
         * 用户ID
         */
        private String userId;
        /**
         * 昵称
         */
        private String petName;
        private String userKey;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }
    }

    /**
     * 注册提交内容类
     */
    public static class RestiterSubmit{
        /**
         * 报文指令
         */
        private int instruct;
        /**
         *昵称
         */
        private String petName;
        /**
         * 验证码
         */
        private String securityCode;
        /**
         * 手机号
         */
        private String phone;
        /**
         * 密码
         */
        private String password;
        /**
         * 推荐码
         */
        private String inviteCode;
        /**
         * 用户类型，(所有用户都可以发任务，0:标识只发不接，1：标识接发任务)
         */
        private int userType=1;

        public int getInstruct() {
            return instruct;
        }

        public void setInstruct(int instruct) {
            this.instruct = instruct;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public String getPetName() {
            return petName;
        }

        public String getSecurityCode() {
            return securityCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }
    }
}
