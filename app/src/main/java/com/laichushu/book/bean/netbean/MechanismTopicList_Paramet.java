package com.laichushu.book.bean.netbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 获取话题列表 参数
 * Created by wangtong on 2016/11/26.
 */

public class MechanismTopicList_Paramet implements Parcelable {

    /**
     * id : 2
     * userId  机构管理员id
     * sourceId 机构id
     * pageNo : 1
     * pageSize : 10
     */
    private String userId;
    private String sourceId;
    private String id;
    private String pageNo;
    private String pageSize;

    public MechanismTopicList_Paramet(String userId, String sourceId, String id, String pageNo, String pageSize) {
        this.userId = userId;
        this.sourceId = sourceId;
        this.id = id;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.sourceId);
        dest.writeString(this.id);
        dest.writeString(this.pageNo);
        dest.writeString(this.pageSize);
    }

    protected MechanismTopicList_Paramet(Parcel in) {
        this.userId = in.readString();
        this.sourceId = in.readString();
        this.id = in.readString();
        this.pageNo = in.readString();
        this.pageSize = in.readString();
    }

    public static final Parcelable.Creator<MechanismTopicList_Paramet> CREATOR = new Parcelable.Creator<MechanismTopicList_Paramet>() {
        @Override
        public MechanismTopicList_Paramet createFromParcel(Parcel source) {
            return new MechanismTopicList_Paramet(source);
        }

        @Override
        public MechanismTopicList_Paramet[] newArray(int size) {
            return new MechanismTopicList_Paramet[size];
        }
    };
}
