package com.laichushu.book.bean.JsonBean;

/**
 * 查询余额
 * Created by wangtong on 2016/11/8.
 */

public class BalanceBean {

    /**
     * success : true
     * data : 0.0
     */

    private boolean success;
    private String errMsg;

    /**
     * data : {"userId":"175","money":149780.36,"minLimit":1,"maxLimit":100}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 175
         * money : 149780.36
         * minLimit : 1.0
         * maxLimit : 100.0
         */

        private String userId;
        private String money;
        private double minLimit;
        private double maxLimit;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public double getMinLimit() {
            return minLimit;
        }

        public void setMinLimit(double minLimit) {
            this.minLimit = minLimit;
        }

        public double getMaxLimit() {
            return maxLimit;
        }

        public void setMaxLimit(double maxLimit) {
            this.maxLimit = maxLimit;
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
