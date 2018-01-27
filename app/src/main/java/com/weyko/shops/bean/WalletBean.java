package com.weyko.shops.bean;

import com.weyko.shops.base.BaseBean;

/**
 * Description:钱包
 * Created  by: weyko on 2017/7/31.
 */

public class WalletBean extends BaseBean {
    private WalletData data;
    public WalletData getData() {
        return data;
    }

    public void setData(WalletData data) {
        this.data = data;
    }

    public class WalletData{
        private double balanec;//余额
        private double integrals;//积分

        public double getBalanec() {
            return balanec;
        }

        public void setBalanec(double balanec) {
            this.balanec = balanec;
        }

        public double getIntegrals() {
            return integrals;
        }

        public void setIntegrals(double integrals) {
            this.integrals = integrals;
        }
    }
    public static class WalletSubmit{
        private int instruct;//指令
        private String userId;//用户ID

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
