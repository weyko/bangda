package com.weyko.shops.bean;

/**
 * Description:
 * Created  by: weyko on 2017/6/23.
 */

public class TokenBean {
    private String useId;
    private String token;
    private String phone;

    public TokenBean(String token, String useId,String phone) {
        this.token = token;
        this.useId = useId;
        this.phone=phone;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
