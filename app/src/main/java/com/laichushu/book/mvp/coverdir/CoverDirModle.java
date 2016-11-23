package com.laichushu.book.mvp.coverdir;

import java.util.ArrayList;
import java.util.List;

/**
 * 模版modle
 * Created by wangtong on 2016/11/23.
 */

public class CoverDirModle {

    /**
     * success : true
     * data : [{"id":"31","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv6-eAXelUAACAQn9J_WA952.jpg","imgName":"褰╁甫.jpg"},{"id":"36","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7FmAYsCwAADT0NgWCuI384.jpg","imgName":"绌轰腑.jpg"},{"id":"43","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7pmAGMUDAABs36BxR04622.jpg","imgName":"鐭㈤噺褰�.jpg"},{"id":"52","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv8V-AdfUFAAFNCGO-wAM910.jpg","imgName":"绠�绾�.jpg"},{"id":"56","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv8uWAMWtPAAFENBzGvxU638.jpg","imgName":"鐜勫够.jpg"}]
     */

    private boolean success;
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

    public static class DataBean {
        /**
         * id : 31
         * imgUrl : http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv6-eAXelUAACAQn9J_WA952.jpg
         * imgName : 褰╁甫.jpg
         */

        private String id;
        private String imgUrl;
        private String imgName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgName() {
            return imgName;
        }

        public void setImgName(String imgName) {
            this.imgName = imgName;
        }
    }
}
