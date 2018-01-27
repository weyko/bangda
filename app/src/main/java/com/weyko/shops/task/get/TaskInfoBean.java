package com.weyko.shops.task.get;

import com.weyko.shops.base.BaseBean;

import java.io.Serializable;

/**
 * Description:
 * Created  by: weyko on 2017/6/28.
 */

public class TaskInfoBean extends BaseBean{
    private TaskInfoData data;

    public TaskInfoData getData() {
        return data;
    }

    public void setData(TaskInfoData data) {
        this.data = data;
    }

    public class TaskInfoData implements Serializable{
        private String recvUserId;//承接任务ID
        private String recvPhone;//承接任务ID
        private String issueUserId;//发布任务ID
        private String issuePhone;//发布任务电话
        private String taskId;//任务编码
        private String taskName;//任务名称
        private String taskDescribe;//任务描述
        private double moneyReward;//任务赏金
        private double taskMoney;//商品购买金额
        private String startPosition;//任务起点
        private String destination;//任务目的地
        private int taskState;//任务状态
        private boolean isCrosstown;//是否跨区
        private String sendTime;//发布时间

        public String getRecvUserId() {
            return recvUserId;
        }

        public void setRecvUserId(String recvUserId) {
            this.recvUserId = recvUserId;
        }

        public String getRecvPhone() {
            return recvPhone;
        }

        public void setRecvPhone(String recvPhone) {
            this.recvPhone = recvPhone;
        }

        public String getIssueUserId() {
            return issueUserId;
        }

        public void setIssueUserId(String issueUserId) {
            this.issueUserId = issueUserId;
        }

        public String getIssuePhone() {
            return issuePhone;
        }

        public void setIssuePhone(String issuePhone) {
            this.issuePhone = issuePhone;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskDescribe() {
            return taskDescribe;
        }

        public void setTaskDescribe(String taskDescribe) {
            this.taskDescribe = taskDescribe;
        }

        public double getMoneyReward() {
            return moneyReward;
        }

        public void setMoneyReward(double moneyReward) {
            this.moneyReward = moneyReward;
        }

        public double getTaskMoney() {
            return taskMoney;
        }

        public void setTaskMoney(double taskMoney) {
            this.taskMoney = taskMoney;
        }

        public String getStartPosition() {
            return startPosition;
        }

        public void setStartPosition(String startPosition) {
            this.startPosition = startPosition;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public int getTaskState() {
            return taskState;
        }

        public void setTaskState(int taskState) {
            this.taskState = taskState;
        }

        public boolean isCrosstown() {
            return isCrosstown;
        }

        public void setCrosstown(boolean crosstown) {
            isCrosstown = crosstown;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }
    }
    public static class  TaskInfoSubmit{
        private int instruct;
        private String userId;
        private String taskID;

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

        public String getTaskID() {
            return taskID;
        }
        public void setTaskID(String taskID) {
            this.taskID = taskID;
        }
    }
}
