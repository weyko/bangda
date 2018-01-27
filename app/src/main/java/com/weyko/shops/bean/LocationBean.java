package com.weyko.shops.bean;

import com.weyko.shops.base.BaseBean;

/**
 * Description: 位置实体类
 * Created  by: weyko on 2017/6/26.
 */

public class LocationBean extends BaseBean {
    private String userId;
    private double lgitude;
    private double latitude;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLgitude() {
        return lgitude;
    }

    public void setLgitude(double lgitude) {
        this.lgitude = lgitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
