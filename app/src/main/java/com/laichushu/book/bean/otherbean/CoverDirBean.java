package com.laichushu.book.bean.otherbean;

import java.util.ArrayList;


/**
 * 封面模版
 * Created by wangtong on 2016/11/23.
 */

public class CoverDirBean {

    /**
     * success : true
     * data : [{"id":"29","mouldImg":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv61WAOMT-AAB_giHF064935.jpg","imgCount":"4","typeName":"模板一"},{"id":"42","mouldImg":"http://101.254.183.67:9980/group1/M00/00/0E/wKiTPlg2qRyAVFcTAAFAggL7LlI061.jpg","imgCount":"4","typeName":"模板二"},{"id":"43","mouldImg":"http://101.254.183.67:9980/group1/M00/00/0E/wKiTPlg2qTOAcyKlAAFENBzGvxU506.jpg","imgCount":"5","typeName":"模板三"},{"id":"44","mouldImg":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv6_SADqhqAAB-kqDzTTQ012.jpg","imgCount":"4","typeName":"模板四"},{"mouldImg":"http://101.254.183.67:9980/group1/M00/00/10/wKiTPlg5XxeAZ5qzAAAUN0xQnnk899.jpg","typeName":"模板五"}]
     */

    private boolean success;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

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

    public static class DataBean {
        /**
         * id : 29
         * mouldImg : http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv61WAOMT-AAB_giHF064935.jpg
         * imgCount : 4
         * typeName : 模板一
         */

        private String id;
        private String mouldImg;
        private String imgCount;
        private String typeName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
