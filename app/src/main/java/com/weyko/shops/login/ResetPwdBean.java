package com.weyko.shops.login;

import java.io.Serializable;

/**
 * Description:重置密码
 * Created  by: weyko on 2017/7/26.
 */

public class ResetPwdBean implements Serializable {
    private int instruct;
    private String userId;
    private String newPassword;

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
