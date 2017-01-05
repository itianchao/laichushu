package com.laichushu.book.bean.netbean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PCPC on 2017/1/4.
 */

public class FindLessonListResult implements Parcelable {

    /**
     * success : true
     * data : {"lessonList":[{"id":"14","name":"璇剧▼娴嬭瘯3","lessonType":"1","status":"1","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQ6geAZ6bCAAAyc1Prfmc269.jpg","thumbName":"4eeca8a7e4409a06b39be795dc8ed3f1.jpg","downNum":21,"collectNum":22,"clickNum":15,"speakerName":"寮犺�佸笀","remarks":""},{"id":"15","name":"璇剧▼娴嬭瘯4","lessonType":"1","fileType":"1","status":"1","baseUrl":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiG1eAQXWHAAHtis6W7XE71.docx","baseName":"QQ鐭棰�20161214144041.mp4","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiGDKAZjLkAAwhqcZFfLs334.jpg","thumbName":"star (6).jpg","downNum":2,"collectNum":2,"clickNum":3,"speakerName":"寮犺�佸笀","remarks":"qq鐭棰戙�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘��","ccVideoId":"33FDDB28A2EE0CA29C33DC5901307461"}],"totalNum":2}
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
         * lessonList : [{"id":"14","name":"璇剧▼娴嬭瘯3","lessonType":"1","status":"1","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQ6geAZ6bCAAAyc1Prfmc269.jpg","thumbName":"4eeca8a7e4409a06b39be795dc8ed3f1.jpg","downNum":21,"collectNum":22,"clickNum":15,"speakerName":"寮犺�佸笀","remarks":""},{"id":"15","name":"璇剧▼娴嬭瘯4","lessonType":"1","fileType":"1","status":"1","baseUrl":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiG1eAQXWHAAHtis6W7XE71.docx","baseName":"QQ鐭棰�20161214144041.mp4","thumbUrl":"http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiGDKAZjLkAAwhqcZFfLs334.jpg","thumbName":"star (6).jpg","downNum":2,"collectNum":2,"clickNum":3,"speakerName":"寮犺�佸笀","remarks":"qq鐭棰戙�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘�傘��","ccVideoId":"33FDDB28A2EE0CA29C33DC5901307461"}]
         * totalNum : 2
         */

        private int totalNum;
        private List<LessonListBean> lessonList;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public List<LessonListBean> getLessonList() {
            return lessonList;
        }

        public void setLessonList(List<LessonListBean> lessonList) {
            this.lessonList = lessonList;
        }

        public static class LessonListBean {
            /**
             * id : 14
             * name : 璇剧▼娴嬭瘯3
             * lessonType : 1
             * status : 1
             * thumbUrl : http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQ6geAZ6bCAAAyc1Prfmc269.jpg
             * thumbName : 4eeca8a7e4409a06b39be795dc8ed3f1.jpg
             * downNum : 21
             * collectNum : 22
             * clickNum : 15
             * speakerName : 寮犺�佸笀
             * remarks :
             * fileType : 1
             * baseUrl : http://101.254.183.67:9980/group1/M00/00/2C/wKiTPlhiG1eAQXWHAAHtis6W7XE71.docx
             * baseName : QQ鐭棰�20161214144041.mp4
             * ccVideoId : 33FDDB28A2EE0CA29C33DC5901307461
             */

            private String id;
            private String name;
            private String lessonType;
            private String status;
            private String thumbUrl;
            private String thumbName;
            private int downNum;
            private int collectNum;
            private int clickNum;
            private String speakerName;
            private String remarks;
            private String fileType;
            private String baseUrl;
            private String baseName;
            private String ccVideoId;

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

            public String getLessonType() {
                return lessonType;
            }

            public void setLessonType(String lessonType) {
                this.lessonType = lessonType;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getThumbUrl() {
                return thumbUrl;
            }

            public void setThumbUrl(String thumbUrl) {
                this.thumbUrl = thumbUrl;
            }

            public String getThumbName() {
                return thumbName;
            }

            public void setThumbName(String thumbName) {
                this.thumbName = thumbName;
            }

            public int getDownNum() {
                return downNum;
            }

            public void setDownNum(int downNum) {
                this.downNum = downNum;
            }

            public int getCollectNum() {
                return collectNum;
            }

            public void setCollectNum(int collectNum) {
                this.collectNum = collectNum;
            }

            public int getClickNum() {
                return clickNum;
            }

            public void setClickNum(int clickNum) {
                this.clickNum = clickNum;
            }

            public String getSpeakerName() {
                return speakerName;
            }

            public void setSpeakerName(String speakerName) {
                this.speakerName = speakerName;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getFileType() {
                return fileType;
            }

            public void setFileType(String fileType) {
                this.fileType = fileType;
            }

            public String getBaseUrl() {
                return baseUrl;
            }

            public void setBaseUrl(String baseUrl) {
                this.baseUrl = baseUrl;
            }

            public String getBaseName() {
                return baseName;
            }

            public void setBaseName(String baseName) {
                this.baseName = baseName;
            }

            public String getCcVideoId() {
                return ccVideoId;
            }

            public void setCcVideoId(String ccVideoId) {
                this.ccVideoId = ccVideoId;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.totalNum);
            dest.writeList(this.lessonList);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.totalNum = in.readInt();
            this.lessonList = new ArrayList<LessonListBean>();
            in.readList(this.lessonList, LessonListBean.class.getClassLoader());
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

    public FindLessonListResult() {
    }

    protected FindLessonListResult(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FindLessonListResult> CREATOR = new Parcelable.Creator<FindLessonListResult>() {
        @Override
        public FindLessonListResult createFromParcel(Parcel source) {
            return new FindLessonListResult(source);
        }

        @Override
        public FindLessonListResult[] newArray(int size) {
            return new FindLessonListResult[size];
        }
    };
}
