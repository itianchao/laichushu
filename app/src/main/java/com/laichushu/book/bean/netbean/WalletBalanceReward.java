package com.laichushu.book.bean.netbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCPC on 2016/11/26.
 */

public class WalletBalanceReward extends BaseModel implements Parcelable {

    /**
     * balance : 90.0
     * data : [{"accountId":"1","tradeName":"微信充值","type":"充值","sourceId":"2","tradeTime":1477549788000,"tradeMoney":0.01,"status":"交易成功"},{"accountId":"1","tradeName":"保护费","type":"充值","sourceId":"2","tradeTime":1477255017000,"tradeMoney":400,"status":"交易成功"}]
     * success : true
     */

    private double balance;
    private String errMsg;
    private boolean success;
    private String status;
    private List<DataBean> data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public static class DataBean implements Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.accountId);
            dest.writeString(this.tradeName);
            dest.writeString(this.type);
            dest.writeString(this.sourceId);
            dest.writeString(this.tradeTime);
            dest.writeDouble(this.tradeMoney);
            dest.writeString(this.status);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.accountId = in.readString();
            this.tradeName = in.readString();
            this.type = in.readString();
            this.sourceId = in.readString();
            this.tradeTime = in.readString();
            this.tradeMoney = in.readDouble();
            this.status = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.balance);
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeList(this.data);
    }

    public WalletBalanceReward() {
    }

    protected WalletBalanceReward(Parcel in) {
        this.balance = in.readDouble();
        this.success = in.readByte() != 0;
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<WalletBalanceReward> CREATOR = new Parcelable.Creator<WalletBalanceReward>() {
        @Override
        public WalletBalanceReward createFromParcel(Parcel source) {
            return new WalletBalanceReward(source);
        }

        @Override
        public WalletBalanceReward[] newArray(int size) {
            return new WalletBalanceReward[size];
        }
    };
}
