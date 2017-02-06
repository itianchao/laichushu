package com.laichushu.book.mvp.write.directories;

import android.os.Parcel;
import android.os.Parcelable;

import com.laichushu.book.bean.netbean.BaseModel;

import java.util.ArrayList;

/**
 * Created by wangtong on 2016/11/7.
 */
public class BookMoudle extends BaseModel {

    /**
     * success : true
     * data : [{"id":"172","name":"绗竴绔�","isSection":true},{"id":"173","name":"绗簩绔�","isSection":false},{"id":"174","name":"绗笁绔�","isSection":false}]
     */

    private boolean success;
    /**
     * id : 172
     * name : 绗竴绔�
     * isSection : true
     */

    private ArrayList<DataBean> data;
    private String errMsg;

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
        private String id;
        private String name;
        private boolean isSection;
        private String content;
        private String contentUrlPc;    	// pc端素材路径
        private String contentUrlApp;   	// app 端素材路径

        public String getContentUrlPc() {
            return contentUrlPc;
        }

        public void setContentUrlPc(String contentUrlPc) {
            this.contentUrlPc = contentUrlPc;
        }

        public String getContentUrlApp() {
            return contentUrlApp;
        }

        public void setContentUrlApp(String contentUrlApp) {
            this.contentUrlApp = contentUrlApp;
        }

        public boolean isSection() {
            return isSection;
        }

        public void setSection(boolean section) {
            isSection = section;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public boolean isIsSection() {
            return isSection;
        }

        public void setIsSection(boolean isSection) {
            this.isSection = isSection;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeByte(this.isSection ? (byte) 1 : (byte) 0);
            dest.writeString(this.content);
            dest.writeString(this.contentUrlPc);
            dest.writeString(this.contentUrlApp);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.isSection = in.readByte() != 0;
            this.content = in.readString();
            this.contentUrlPc = in.readString();
            this.contentUrlApp = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
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
