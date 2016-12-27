package com.laichushu.book.bean.netbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PCPC on 2016/12/27.
 */

public class FindEditorInfoModel implements Parcelable {

    /**
     * success : true
     * data : {"id":"209","name":"马大哈","workingYears":0,"cityId":"0201","introduction":"","level":"","levelName":"","press":"河北教育出版社","pressId":"9","cooperateNum":0,"photo":"http://101.254.183.67:9980/group1/M00/00/2A/wKiTPlhc8UGAVkxwAAEpvVkWswE362.jpg","score":0,"scoreNum":0}
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
         * id : 209
         * name : 马大哈
         * workingYears : 0
         * cityId : 0201
         * introduction :
         * level :
         * levelName :
         * press : 河北教育出版社
         * pressId : 9
         * cooperateNum : 0
         * photo : http://101.254.183.67:9980/group1/M00/00/2A/wKiTPlhc8UGAVkxwAAEpvVkWswE362.jpg
         * score : 0
         * scoreNum : 0
         */

        private String id;
        private String name;
        private int workingYears;
        private String cityId;
        private String introduction;
        private String level;
        private String levelName;
        private String press;
        private String pressId;
        private int cooperateNum;
        private String photo;
        private int score;
        private int scoreNum;

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

        public int getWorkingYears() {
            return workingYears;
        }

        public void setWorkingYears(int workingYears) {
            this.workingYears = workingYears;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public String getPress() {
            return press;
        }

        public void setPress(String press) {
            this.press = press;
        }

        public String getPressId() {
            return pressId;
        }

        public void setPressId(String pressId) {
            this.pressId = pressId;
        }

        public int getCooperateNum() {
            return cooperateNum;
        }

        public void setCooperateNum(int cooperateNum) {
            this.cooperateNum = cooperateNum;
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

        public int getScoreNum() {
            return scoreNum;
        }

        public void setScoreNum(int scoreNum) {
            this.scoreNum = scoreNum;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeInt(this.workingYears);
            dest.writeString(this.cityId);
            dest.writeString(this.introduction);
            dest.writeString(this.level);
            dest.writeString(this.levelName);
            dest.writeString(this.press);
            dest.writeString(this.pressId);
            dest.writeInt(this.cooperateNum);
            dest.writeString(this.photo);
            dest.writeInt(this.score);
            dest.writeInt(this.scoreNum);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.workingYears = in.readInt();
            this.cityId = in.readString();
            this.introduction = in.readString();
            this.level = in.readString();
            this.levelName = in.readString();
            this.press = in.readString();
            this.pressId = in.readString();
            this.cooperateNum = in.readInt();
            this.photo = in.readString();
            this.score = in.readInt();
            this.scoreNum = in.readInt();
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

    public FindEditorInfoModel() {
    }

    protected FindEditorInfoModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FindEditorInfoModel> CREATOR = new Parcelable.Creator<FindEditorInfoModel>() {
        @Override
        public FindEditorInfoModel createFromParcel(Parcel source) {
            return new FindEditorInfoModel(source);
        }

        @Override
        public FindEditorInfoModel[] newArray(int size) {
            return new FindEditorInfoModel[size];
        }
    };
}

