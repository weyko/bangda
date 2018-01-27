package com.weyko.shops.bean;

import com.weyko.shops.base.BaseBean;

/**
 * Created by zhong.xiwei on 2017/8/8.
 */

public class VersionBean extends BaseBean {
    private int versionCode;
    private String versionName;
    private String downUrl;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }
    public static class VersionSubmit{
        private int instruct;
        private int versionCode;
        private String platName;

        public int getInstruct() {
            return instruct;
        }

        public void setInstruct(int instruct) {
            this.instruct = instruct;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getPlatName() {
            return platName;
        }

        public void setPlatName(String platName) {
            this.platName = platName;
        }
    }
}
