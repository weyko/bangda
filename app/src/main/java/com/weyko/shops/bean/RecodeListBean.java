package com.weyko.shops.bean;

import com.weyko.shops.base.BaseBean;

import java.util.List;

/**
 * Description:钱包记录
 * Created  by: weyko on 2017/7/27.
 */

public class RecodeListBean extends BaseBean{
    private List<RecodeItemBean>data;

    public List<RecodeItemBean> getData() {
        return data;
    }

    public void setData(List<RecodeItemBean> data) {
        this.data = data;
    }

    public class RecodeItemBean{
        private String date;
        private String time;
        private int type;
        private double money;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }
    }
    public static class RecodeSubmit{
        private int instruct;
        private String userId;
        private int pageIndex;

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

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }
    }
}
