package com.weyko.shops.login;

/**
 * Description:
 * Created  by: weyko on 2017/6/14.
 */

public class LoginModel {
    private int instruct;
    private String phone;
    private String password;
    private int userType=1;//用户类型(必填,默认填1)

    public int getInstruct() {
        return instruct;
    }

    public void setInstruct(int instruct) {
        this.instruct = instruct;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
