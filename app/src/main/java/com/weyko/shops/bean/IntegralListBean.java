package com.weyko.shops.bean;

import com.weyko.shops.base.BaseBean;

import java.util.List;

/**
 * Description:积分记录
 * Created  by: weyko on 2017/7/27.
 */

public class IntegralListBean extends BaseBean{
    private List<IntegralItemBean>data;

    public List<IntegralItemBean> getData() {
        return data;
    }

    public void setData(List<IntegralItemBean> data) {
        this.data = data;
    }

    public class IntegralItemBean{
        private String date;
        private String time;
        private int taskID;
        private String taskName;
        private double integral;

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

        public double getIntegral() {
            return integral;
        }

        public void setIntegral(double integral) {
            this.integral = integral;
        }

        public int getTaskID() {
            return taskID;
        }

        public void setTaskID(int taskID) {
            this.taskID = taskID;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }
    }
}
