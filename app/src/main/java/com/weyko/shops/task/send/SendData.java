package com.weyko.shops.task.send;

import com.weyko.shops.base.BaseBean;

/**
 * Description:发送任务实体类
 * Created  by: weyko on 2017/6/28.
 */

public class SendData extends BaseBean {
    private String userId;
    private String taskName;
    private String taskDescribe;
    private double moneyReward=-1;
    private String startPosition;
    private double startLgitude;
    private double startLatitude;
    private String destination;
    private double destLgitude;
    private double destLatitude;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public double getStartLgitude() {
        return startLgitude;
    }

    public void setStartLgitude(double startLgitude) {
        this.startLgitude = startLgitude;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getDestLgitude() {
        return destLgitude;
    }

    public void setDestLgitude(double destLgitude) {
        this.destLgitude = destLgitude;
    }

    public double getDestLatitude() {
        return destLatitude;
    }

    public void setDestLatitude(double destLatitude) {
        this.destLatitude = destLatitude;
    }
}
