package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/11/26.
 */

public class WalletBalanceReward implements Serializable {

    /**
     * balance : 90.0
     * data : [{"accountId":"1","tradeName":"微信充值","type":"充值","sourceId":"2","tradeTime":1477549788000,"tradeMoney":0.01,"status":"交易成功"},{"accountId":"1","tradeName":"保护费","type":"充值","sourceId":"2","tradeTime":1477255017000,"tradeMoney":400,"status":"交易成功"}]
     * success : true
     */

    private double balance;
    private boolean success;
    private List<DataBean> data;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * accountId : 1
         * tradeName : 微信充值
         * type : 充值
         * sourceId : 2
         * tradeTime : 1477549788000
         * tradeMoney : 0.01
         * status : 交易成功
         */

        private String accountId;
        private String tradeName;
        private String type;
        private String sourceId;
        private String tradeTime;
        private double tradeMoney;
        private String status;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getTradeName() {
            return tradeName;
        }

        public void setTradeName(String tradeName) {
            this.tradeName = tradeName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSourceId() {
            return sourceId;
        }

        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getTradeTime() {
            return tradeTime;
        }

        public void setTradeTime(String tradeTime) {
            this.tradeTime = tradeTime;
        }

        public double getTradeMoney() {
            return tradeMoney;
        }

        public void setTradeMoney(double tradeMoney) {
            this.tradeMoney = tradeMoney;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
