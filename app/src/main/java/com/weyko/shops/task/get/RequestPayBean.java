package com.weyko.shops.task.get;

/**
 * Description: 请求付款
 * Created  by: weyko on 2017/7/7.
 */

public class RequestPayBean {
    private int instruct;
    private String issueUserId;
    private String recvUserId;
    private String recvPhone;
    private String taskId;
    private String taskName;
    private double taskMoney;//任务金额(不包含赏金)
    private double moneyReward;//任务赏金
    private double totalMoney;

    public int getInstruct() {
        return instruct;
    }

    public void setInstruct(int instruct) {
        this.instruct = instruct;
    }

    public String getIssueUserId() {
        return issueUserId;
    }

    public void setIssueUserId(String issueUserId) {
        this.issueUserId = issueUserId;
    }

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

    public double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public double getMoneyReward() {
        return moneyReward;
    }

    public void setMoneyReward(double moneyReward) {
        this.moneyReward = moneyReward;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }
}
