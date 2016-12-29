package com.laichushu.book.bean.netbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCPC on 2016/12/29.
 */

public class FindServiceInfoModel implements Parcelable {

    /**
     * success : true
     * data : [{"userId":"150","jobTitle":"卖火柴","idProve":"路人甲乙丙","serviceType":2,"auditStatus":1,"serviceIntroduce":"卖火柴的小女孩开始卖火柴一镜二次。 action","companyName":"大街","cooperateNum":0,"name":"卖火柴的小女孩","email":"123@qq.com","nickName":"无尽之夏","cityId":"0101","photo":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhh3M2AeuqXAADCsbGO7uw091.jpg","score":0,"partyId":"2"}]
     */

    private boolean success;
    private String errMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * userId : 150
         * jobTitle : 卖火柴
         * idProve : 路人甲乙丙
         * serviceType : 2
         * auditStatus : 1
         * serviceIntroduce : 卖火柴的小女孩开始卖火柴一镜二次。 action
         * companyName : 大街
         * cooperateNum : 0
         * name : 卖火柴的小女孩
         * email : 123@qq.com
         * nickName : 无尽之夏
         * cityId : 0101
         * photo : http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhh3M2AeuqXAADCsbGO7uw091.jpg
         * score : 0
         * partyId : 2
         */

        private String userId;
        private String jobTitle;
        private String idProve;
        private int serviceType;
        private int auditStatus;
        private String serviceIntroduce;
        private String companyName;
        private int cooperateNum;
        private String name;
        private String email;
        private String nickName;
        private String cityId;
        private String photo;
        private int score;
        private String partyId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getIdProve() {
            return idProve;
        }

        public void setIdProve(String idProve) {
            this.idProve = idProve;
        }

        public int getServiceType() {
            return serviceType;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public int getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(int auditStatus) {
            this.auditStatus = auditStatus;
        }

        public String getServiceIntroduce() {
            return serviceIntroduce;
        }

        public void setServiceIntroduce(String serviceIntroduce) {
            this.serviceIntroduce = serviceIntroduce;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getCooperateNum() {
            return cooperateNum;
        }

        public void setCooperateNum(int cooperateNum) {
            this.cooperateNum = cooperateNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getPartyId() {
            return partyId;
        }

        public void setPartyId(String partyId) {
            this.partyId = partyId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.userId);
            dest.writeString(this.jobTitle);
            dest.writeString(this.idProve);
            dest.writeInt(this.serviceType);
            dest.writeInt(this.auditStatus);
            dest.writeString(this.serviceIntroduce);
            dest.writeString(this.companyName);
            dest.writeInt(this.cooperateNum);
            dest.writeString(this.name);
            dest.writeString(this.email);
            dest.writeString(this.nickName);
            dest.writeString(this.cityId);
            dest.writeString(this.photo);
            dest.writeInt(this.score);
            dest.writeString(this.partyId);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.userId = in.readString();
            this.jobTitle = in.readString();
            this.idProve = in.readString();
            this.serviceType = in.readInt();
            this.auditStatus = in.readInt();
            this.serviceIntroduce = in.readString();
            this.companyName = in.readString();
            this.cooperateNum = in.readInt();
            this.name = in.readString();
            this.email = in.readString();
            this.nickName = in.readString();
            this.cityId = in.readString();
            this.photo = in.readString();
            this.score = in.readInt();
            this.partyId = in.readString();
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
        dest.writeList(this.data);
    }

    public FindServiceInfoModel() {
    }

    protected FindServiceInfoModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FindServiceInfoModel> CREATOR = new Parcelable.Creator<FindServiceInfoModel>() {
        @Override
        public FindServiceInfoModel createFromParcel(Parcel source) {
            return new FindServiceInfoModel(source);
        }

        @Override
        public FindServiceInfoModel[] newArray(int size) {
            return new FindServiceInfoModel[size];
        }
    };
}
