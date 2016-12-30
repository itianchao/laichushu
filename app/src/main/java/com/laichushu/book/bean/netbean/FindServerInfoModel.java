package com.laichushu.book.bean.netbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PCPC on 2016/12/30.
 */

public class FindServerInfoModel implements Parcelable {

    /**
     * success : true
     * data : {"userId":"150","jobTitle":"鍗栫伀鏌�","idProve":"璺汉鐢蹭箼涓�","serviceType":2,"auditStatus":1,"serviceIntroduce":"鍗栫伀鏌寸殑灏忓コ瀛╁紑濮嬪崠鐏煷涓�闀滀簩娆°�� action","companyName":"澶ц","cooperateNum":1,"name":"鍗栫伀鏌寸殑灏忓コ瀛�","email":"123@qq.com","nickName":"鏃犲敖涔嬪","cityId":"0101","photo":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhh3M2AeuqXAADCsbGO7uw091.jpg","isCollect":false,"score":0,"partyId":"2"}
     */

    private boolean success;
    private String errMsg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * userId : 150
         * jobTitle : 鍗栫伀鏌�
         * idProve : 璺汉鐢蹭箼涓�
         * serviceType : 2
         * auditStatus : 1
         * serviceIntroduce : 鍗栫伀鏌寸殑灏忓コ瀛╁紑濮嬪崠鐏煷涓�闀滀簩娆°�� action
         * companyName : 澶ц
         * cooperateNum : 1
         * name : 鍗栫伀鏌寸殑灏忓コ瀛�
         * email : 123@qq.com
         * nickName : 鏃犲敖涔嬪
         * cityId : 0101
         * photo : http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhh3M2AeuqXAADCsbGO7uw091.jpg
         * isCollect : false
         * score : 0.0
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
        private boolean isCollect;
        private double score;
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

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
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
            dest.writeByte(this.isCollect ? (byte) 1 : (byte) 0);
            dest.writeDouble(this.score);
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
            this.isCollect = in.readByte() != 0;
            this.score = in.readDouble();
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
        dest.writeParcelable(this.data, flags);
    }

    public FindServerInfoModel() {
    }

    protected FindServerInfoModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FindServerInfoModel> CREATOR = new Parcelable.Creator<FindServerInfoModel>() {
        @Override
        public FindServerInfoModel createFromParcel(Parcel source) {
            return new FindServerInfoModel(source);
        }

        @Override
        public FindServerInfoModel[] newArray(int size) {
            return new FindServerInfoModel[size];
        }
    };
}
