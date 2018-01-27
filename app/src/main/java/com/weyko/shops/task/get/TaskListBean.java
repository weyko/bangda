package com.weyko.shops.task.get;

import com.weyko.shops.base.BaseBean;

import java.util.List;

/**
 * Description:
 * Created  by: weyko on 2017/8/3.
 */

public class TaskListBean extends BaseBean {
    private List<TaskItemBean>data;

    public List<TaskItemBean> getData() {
        return data;
    }

    public void setData(List<TaskItemBean> data) {
        this.data = data;
    }
    public class TaskItemBean{
        private String issueUserId;
        private String taskName;
        private String taskDescribe;
        private double moneyReward;
        private String startPosition;
        private String endPosition;
        private String taskId;
        private int taskState;
        private String time;

         public String getIssueUserId() {
             return issueUserId;
         }

         public void setIssueUserId(String issueUserId) {
             this.issueUserId = issueUserId;
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

         public String getEndPosition() {
             return endPosition;
         }

         public void setEndPosition(String endPosition) {
             this.endPosition = endPosition;
         }

         public String getTaskId() {
             return taskId;
         }

         public void setTaskId(String taskId) {
             this.taskId = taskId;
         }

         public int getTaskState() {
             return taskState;
         }

         public void setTaskState(int taskState) {
             this.taskState = taskState;
         }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
    public static class TaskListSubmit{
        private int instruct;
        private String userId;
        private double lgitude;
        private double latitude;
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

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }
    }
    public static class MyTaskSubmit{
        private int instruct;
        private String userId;
        private int taskType;
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

        public int getTaskType() {
            return taskType;
        }

        public void setTaskType(int taskType) {
            this.taskType = taskType;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }
    }
}
