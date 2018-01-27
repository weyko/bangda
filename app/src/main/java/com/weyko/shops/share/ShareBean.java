package com.weyko.shops.share;

import java.io.Serializable;

/**
 * Description:
 * Created  by: weyko on 2017/7/17.
 */

public class ShareBean implements Serializable {
    private int status;
    private String error;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
