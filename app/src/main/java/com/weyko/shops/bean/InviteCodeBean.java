package com.weyko.shops.bean;

import com.weyko.shops.base.BaseBean;

/**
 * Description: 邀请好友实体类
 * Created  by: weyko on 2017/7/26.
 */

public class InviteCodeBean extends BaseBean {
    private InviteCodeData data;

    public InviteCodeData getData() {
        return data;
    }

    public void setData(InviteCodeData data) {
        this.data = data;
    }

    public class InviteCodeData{
        private String inviteCode;
        public String getInviteCode() {
            return inviteCode;
        }
        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }
    }
    public static class InviteCodeSubmit{
        private int instruct;
        private String userId;

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
    }
}
