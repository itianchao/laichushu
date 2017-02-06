package com.laichushu.book.bean.JsonBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.laichushu.book.bean.netbean.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构列表modle
 * Created by wt on 2016/11/24.
 */

public class MechanismListBean extends BaseModel implements Parcelable {


    /**
     * success : true
     * data : [{"id":"9","name":"河北教育出版社","grade":"1","address":"","logoUrl":"http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhLmxiAQH6TAAAUSAXikHs018.jpg","introduce":"哈哈哈 有简介啦","collectCount":81,"isCollect":true,"admin":""},{"id":"10","name":"山东人民出版社","grade":"1","address":"","logoUrl":"http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhLmziAQoP9AAAe1xWkkeA373.jpg","introduce":"山东人民出版社简介","collectCount":81,"isCollect":false,"admin":""},{"id":"11","name":"云南人民出版社","grade":"2","address":"云南","logoUrl":"http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQuG-ADDsHAABax4kt4BA617.jpg","introduce":"云南人民出版社简介信息啊啊","collectCount":81,"isCollect":false,"admin":""},{"id":"1","name":"中国书籍出版社","grade":"1","address":"北京市海淀区苏州街52号","logoUrl":"http://101.254.183.67:9980/group1/M00/00/1F/wKiTPlhSCeaAF5P9AABHZg_FRL8811.jpg","introduce":"想出书,来中国书籍出版社。。。。","collectCount":81,"isCollect":true,"admin":"160"},{"id":"2","name":"读者用户组","grade":"1","address":"","logoUrl":"http://101.254.183.67:9980/group1/M00/00/00/wKiTPlgRoDqAMg8jAABHZg_FRL8660.jpg","introduce":"啦啦啦啦啦啦啦","collectCount":81,"isCollect":false,"admin":""}]
     */

    private boolean success;
    private List<DataBean> data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private String errMsg;

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
         * id : 9
         * name : 河北教育出版社
         * grade : 1
         * address :
         * logoUrl : http://101.254.183.67:9980/group1/M00/00/1A/wKiTPlhLmxiAQH6TAAAUSAXikHs018.jpg
         * introduce : 哈哈哈 有简介啦
         * collectCount : 81
         * isCollect : true
         * admin :
         */

        private String id;
        private String name;
        private String grade;
        private String address;
        private String logoUrl;
        private String introduce;
        private int collectCount;
        private boolean isCollect;
        private String admin;

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

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public String getAdmin() {
            return admin;
        }

        public void setAdmin(String admin) {
            this.admin = admin;
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
            dest.writeString(this.introduce);
            dest.writeInt(this.collectCount);
            dest.writeByte(this.isCollect ? (byte) 1 : (byte) 0);
            dest.writeString(this.admin);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.grade = in.readString();
            this.address = in.readString();
            this.logoUrl = in.readString();
            this.introduce = in.readString();
            this.collectCount = in.readInt();
            this.isCollect = in.readByte() != 0;
            this.admin = in.readString();
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

    public MechanismListBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
        dest.writeList(this.data);
        dest.writeString(this.errMsg);
    }

    protected MechanismListBean(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
        this.errMsg = in.readString();
    }

    public static final Creator<MechanismListBean> CREATOR = new Creator<MechanismListBean>() {
        @Override
        public MechanismListBean createFromParcel(Parcel source) {
            return new MechanismListBean(source);
        }

        @Override
        public MechanismListBean[] newArray(int size) {
            return new MechanismListBean[size];
        }
    };
}
