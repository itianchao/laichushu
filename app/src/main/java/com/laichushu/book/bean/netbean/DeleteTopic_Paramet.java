package com.laichushu.book.bean.netbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PCPC on 2016/12/21.
 */

public class DeleteTopic_Paramet implements Parcelable {
    private String id;

    public DeleteTopic_Paramet(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    protected DeleteTopic_Paramet(Parcel in) {
        this.id = in.readString();
    }

    public static final Parcelable.Creator<DeleteTopic_Paramet> CREATOR = new Parcelable.Creator<DeleteTopic_Paramet>() {
        @Override
        public DeleteTopic_Paramet createFromParcel(Parcel source) {
            return new DeleteTopic_Paramet(source);
        }

        @Override
        public DeleteTopic_Paramet[] newArray(int size) {
            return new DeleteTopic_Paramet[size];
        }
    };

    private String userId;

    public DeleteTopic_Paramet(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
