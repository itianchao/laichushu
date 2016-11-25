package com.laichushu.book.bean.otherbean;

import java.util.ArrayList;

/**
 * 封面模版
 * Created by wangtong on 2016/11/23.
 */

public class CoverDirBean {
    /**
     * type : 1
     * mouldImg : /group1/M00/00/08/wKiTPlgv61WAOMT-AAB_giHF064935.jpg
     * imgCount : 5
     * typeName : 模板1
     */
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    private ArrayList<DateBean> data;

    public ArrayList<DateBean> getData() {
        return data;
    }

    public void setData(ArrayList<DateBean> data) {
        this.data = data;
    }

    public static class DateBean {
        private String type;
        private String mouldImg;
        private String imgCount;
        private String typeName;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMouldImg() {
            return mouldImg;
        }

        public void setMouldImg(String mouldImg) {
            this.mouldImg = mouldImg;
        }

        public String getImgCount() {
            return imgCount;
        }

        public void setImgCount(String imgCount) {
            this.imgCount = imgCount;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
}
