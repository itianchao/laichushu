package com.laichushu.book.mvp.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * 首页 轮播图
 * Created by wangtong on 2016/10/17.
 */
public class HomeModel implements Parcelable {

    /**
     * success : true
     * data : [{"id":"15","name":"timg.jpg","url":"http://192.168.147.62/group1/M00/00/00/wKiTPlgRjeSACPAGAAKnxAFh1bE577.jpg","sort":1,"status":0,"statusName":"鍚敤"},{"id":"17","name":"寰俊鎴浘_20161027132130.png","url":"http://192.168.147.62/group1/M00/00/00/wKiTPlgRjvOAFvbBAAA_wdV6nR0638.png","sort":2,"status":0,"statusName":"鍚敤"},{"id":"21","name":"QQ鎴浘20161027185717.png","url":"http://192.168.147.62/group1/M00/00/00/wKiTPlgR3aWAdpv-AAFdlMmocTI690.png","sort":3,"status":0,"statusName":"鍚敤"},{"id":"22","name":"涔�,鎽告懜澶�.jpg","url":"http://192.168.147.62/group1/M00/00/00/wKiTPlgW7UqAN-jgAABqYh718yY994.jpg","sort":4,"status":0,"statusName":"鍚敤"}]
     */

    private boolean success;
    /**
     * id : 15
     * name : timg.jpg
     * url : http://192.168.147.62/group1/M00/00/00/wKiTPlgRjeSACPAGAAKnxAFh1bE577.jpg
     * sort : 1
     * status : 0
     * statusName : 鍚敤
     */
    private String errMsg;

    private ArrayList<DataBean> data;

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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static class DataBean implements Parcelable {
        private String id;
        private String name;
        private String url;
        private int sort;
        private int status;
        private String statusName;
        private String type;
        private String sourceId;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
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
            dest.writeString(this.url);
            dest.writeInt(this.sort);
            dest.writeInt(this.status);
            dest.writeString(this.statusName);
            dest.writeString(this.type);
            dest.writeString(this.sourceId);
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.url = in.readString();
            this.sort = in.readInt();
            this.status = in.readInt();
            this.statusName = in.readString();
            this.type = in.readString();
            this.sourceId = in.readString();
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
        dest.writeString(this.errMsg);
        dest.writeList(this.data);
    }

    public HomeModel() {
    }

    protected HomeModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.errMsg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HomeModel> CREATOR = new Parcelable.Creator<HomeModel>() {
        @Override
        public HomeModel createFromParcel(Parcel source) {
            return new HomeModel(source);
        }

        @Override
        public HomeModel[] newArray(int size) {
            return new HomeModel[size];
        }
    };
}
