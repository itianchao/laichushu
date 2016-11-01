package com.sofacity.laichushu.mvp.home;

import java.util.List;

/**
 * 首页 轮播图
 * Created by wangtong on 2016/10/17.
 */
public class HomeModel {

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
    private String errorMsg;

    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        private String id;
        private String name;
        private String url;
        private int sort;
        private int status;
        private String statusName;

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

    }
}
