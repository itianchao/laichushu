package com.laichushu.book.bean.netbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PCPC on 2017/1/17.
 */

public class AliPayResult implements Parcelable {

    /**
     * success : true
     * data : {"orderCode":"20170113102921932324383365238359","money":0.01,"isSuccess":true,"notifyUrl":"levn2002cn.55555.io/book-app/recharge/alipaycheck","productName":"来出书-支付宝充值"}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * orderCode : 20170113102921932324383365238359
         * money : 0.01
         * isSuccess : true
         * notifyUrl : levn2002cn.55555.io/book-app/recharge/alipaycheck
         * productName : 来出书-支付宝充值
         */

        private String orderCode;
        private double money;
        private boolean isSuccess;
        private String notifyUrl;
        private String productName;

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public boolean isIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public String getNotifyUrl() {
            return notifyUrl;
        }

        public void setNotifyUrl(String notifyUrl) {
            this.notifyUrl = notifyUrl;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.orderCode);
            dest.writeDouble(this.money);
            dest.writeByte(this.isSuccess ? (byte) 1 : (byte) 0);
            dest.writeString(this.notifyUrl);
            dest.writeString(this.productName);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.orderCode = in.readString();
            this.money = in.readDouble();
            this.isSuccess = in.readByte() != 0;
            this.notifyUrl = in.readString();
            this.productName = in.readString();
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
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.data, flags);
    }

    public AliPayResult() {
    }

    protected AliPayResult(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<AliPayResult> CREATOR = new Parcelable.Creator<AliPayResult>() {
        @Override
        public AliPayResult createFromParcel(Parcel source) {
            return new AliPayResult(source);
        }

        @Override
        public AliPayResult[] newArray(int size) {
            return new AliPayResult[size];
        }
    };
}
