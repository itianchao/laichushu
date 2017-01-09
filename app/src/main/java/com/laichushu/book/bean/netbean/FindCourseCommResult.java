package com.laichushu.book.bean.netbean;

import com.laichushu.book.mvp.find.group.groupmain.GroupListModle;

import java.util.ArrayList;

/**
 * Created by PCPC on 2016/12/19.
 */

public class FindCourseCommResult {

    /**
     * success : true
     * data : [{"id":"103","name":"鎰挎棤宀佹湀鍙洖澶�","photo":"group1/M00/00/1A/wKiTPlhKmfCAWIm6AAEuwbmelvg817.jpg","joinNum":1},{"id":"104","name":"sllA001a--selenium 3.0鍙戝竷","photo":"group1/M00/00/1A/wKiTPlhLnXSAEOhZAAAhZZffC38213.jpg","joinNum":1},{"id":"105","name":"瀹岀編涓栫晫","photo":"group1/M00/00/1A/wKiTPlhLn4eAchKLAAFFP-4zZSM034.jpg","joinNum":1},{"id":"106","name":"123","photo":"group1/M00/00/1A/wKiTPlhLoIuAOoTdAAAdTgEClQU352.jpg","joinNum":1},{"id":"107","joinNum":1},{"id":"108","name":"鍖荤粺澶╀笅","photo":"group1/M00/00/1A/wKiTPlhLpSaAW0AnAAFKVyPNanU154.jpg","joinNum":1},{"id":"109","name":"澶忎簡涓�涓澶�","photo":"group1/M00/00/1A/wKiTPlhLpx2AYe1BAAFKKofbLpY127.jpg","joinNum":1},{"id":"110","name":"鐙備汉","photo":"group1/M00/00/1A/wKiTPlhLp9mAf2yUAAFD0X-eY9E998.jpg","joinNum":1},{"id":"111","name":"TakeAPhoto","photo":"group1/M00/00/1B/wKiTPlhLsbuAXMk_AAA64o_YKjI362.jpg","joinNum":1}]
     */

    private boolean success;
    private ArrayList<GroupListModle.DataBean> data;

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

    public ArrayList<GroupListModle.DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<GroupListModle.DataBean> data) {
        this.data = data;
    }

//    public static class DataBean implements Parcelable {
//        /**
//         * id : 103
//         * name : 鎰挎棤宀佹湀鍙洖澶�
//         * photo : group1/M00/00/1A/wKiTPlhKmfCAWIm6AAEuwbmelvg817.jpg
//         * joinNum : 1
//         */
//
//        private String id;
//        private String name;
//        private String photo;
//        private int joinNum;
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getPhoto() {
//            return photo;
//        }
//
//        public void setPhoto(String photo) {
//            this.photo = photo;
//        }
//
//        public int getJoinNum() {
//            return joinNum;
//        }
//
//        public void setJoinNum(int joinNum) {
//            this.joinNum = joinNum;
//        }
//
//        public DataBean() {
//        }
//
//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeString(this.id);
//            dest.writeString(this.name);
//            dest.writeString(this.photo);
//            dest.writeInt(this.joinNum);
//        }
//
//        protected DataBean(Parcel in) {
//            this.id = in.readString();
//            this.name = in.readString();
//            this.photo = in.readString();
//            this.joinNum = in.readInt();
//        }
//
//        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
//            @Override
//            public DataBean createFromParcel(Parcel source) {
//                return new DataBean(source);
//            }
//
//            @Override
//            public DataBean[] newArray(int size) {
//                return new DataBean[size];
//            }
//        };
//    }
//
//    public FindCourseCommResult() {
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeByte(this.success ? (byte) 1 : (byte) 0);
//        dest.writeTypedList(this.data);
//        dest.writeString(this.errMsg);
//    }
//
//    protected FindCourseCommResult(Parcel in) {
//        this.success = in.readByte() != 0;
//        this.data = in.createTypedArrayList(DataBean.CREATOR);
//        this.errMsg = in.readString();
//    }
//
//    public static final Creator<FindCourseCommResult> CREATOR = new Creator<FindCourseCommResult>() {
//        @Override
//        public FindCourseCommResult createFromParcel(Parcel source) {
//            return new FindCourseCommResult(source);
//        }
//
//        @Override
//        public FindCourseCommResult[] newArray(int size) {
//            return new FindCourseCommResult[size];
//        }
//    };
}
