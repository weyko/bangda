package com.weyko.shops.task.get;

import com.weyko.shops.base.BaseBean;

/**
 * Description:承接任务（抢单）
 * Created  by: weyko on 2017/7/4.
 */

public class GetTaskBean extends BaseBean {
    private GetTaskData data;

    public GetTaskData getData() {
        return data;
    }

    public void setData(GetTaskData data) {
        this.data = data;
    }

    public class  GetTaskData{
        private String recvUserId;
        private String recvPhone;
        private int taskId;
        private String taskName;

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

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }
    }
    public static class GetTaskSubmit{
        private int  instruct;
        private String recvUserId;
        private String recvPhone;
        private String issueUserId;//发布任务用户ID
        private String taskId;
        private String taskName;

        public int getInstruct() {
            return instruct;
        }

        public void setInstruct(int instruct) {
            this.instruct = instruct;
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

        public String getIssueUserId() {
            return issueUserId;
        }

        public void setIssueUserId(String issueUserId) {
            this.issueUserId = issueUserId;
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
    }
}
