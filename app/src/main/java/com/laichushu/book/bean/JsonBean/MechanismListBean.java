package com.laichushu.book.bean.JsonBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 机构列表modle
 * Created by wt on 2016/11/24.
 */

public class MechanismListBean {
    private boolean success;
    private String errMsg;
    private ArrayList<DataBean> data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 1
         * name : 涓浗涔︾睄鍑虹増绀�
         * grade : 1
         * address : 鍖椾含甯傛捣娣�鍖鸿嫃宸炶52鍙�
         * logoUrl : http://192.168.147.62/group1/M00/00/00/wKiTPlgRoDqAMg8jAABHZg_FRL8660.jpg
         */

        private String id;
        private String name;
        private String grade;
        private String address;
        private String logoUrl;
        private int collectCount;
        private boolean isCollect;
        private String introduce;

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLogoUrl() {
            return logoUrl;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.grade);
            dest.writeString(this.address);
            dest.writeString(this.logoUrl);
            dest.writeInt(this.collectCount);
            dest.writeByte(this.isCollect ? (byte) 1 : (byte) 0);
            dest.writeString(this.introduce);
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.grade = in.readString();
            this.address = in.readString();
            this.logoUrl = in.readString();
            this.collectCount = in.readInt();
            this.isCollect = in.readByte() != 0;
            this.introduce = in.readString();
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
}
