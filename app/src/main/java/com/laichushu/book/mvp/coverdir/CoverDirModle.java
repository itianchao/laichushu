package com.laichushu.book.mvp.coverdir;

import java.util.List;

/**
 * 模版modle
 * Created by wangtong on 2016/11/23.
 */

public class CoverDirModle {

    /**
     * success : true
     * data : [{"id":"29","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv61WAOMT-AAB_giHF064935.jpg","imgName":"褰╁甫.jpg"},{"id":"30","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv67-AMRMUAACAs5MB3rM800.jpg","imgName":"褰╁甫.jpg"},{"id":"31","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv6-eAXelUAACAQn9J_WA952.jpg","imgName":"褰╁甫.jpg"},{"id":"32","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv6_SADqhqAAB-kqDzTTQ012.jpg","imgName":"褰╁甫.jpg"},{"id":"33","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7BGATDqpAADPvpyOUFM898.jpg","imgName":"绌轰腑.jpg"},{"id":"34","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7C-AAfgKAADPjfsPrhI964.jpg","imgName":"绌轰腑.jpg"},{"id":"36","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7FmAYsCwAADT0NgWCuI384.jpg","imgName":"绌轰腑.jpg"},{"id":"37","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7HWAbUnVAADQenfv0Os997.jpg","imgName":"绌轰腑.jpg"},{"id":"41","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7oqABvMZAABrlKgJeaA658.jpg","imgName":"鐭㈤噺褰�.jpg"},{"id":"42","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7pOAdALnAABp6ylkpUo993.jpg","imgName":"鐭㈤噺褰�.jpg"},{"id":"43","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7pmAGMUDAABs36BxR04622.jpg","imgName":"鐭㈤噺褰�.jpg"},{"id":"44","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv7qKAbkxKAABrB16HMyw596.jpg","imgName":"鐭㈤噺褰�.jpg"},{"id":"50","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv8UqAZhxzAAFMwPCHvdI163.jpg","imgName":"绠�绾�.jpg"},{"id":"51","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv8VmAM_nuAAFLjOtXsxE396.jpg","imgName":"绠�绾�.jpg"},{"id":"52","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv8V-AdfUFAAFNCGO-wAM910.jpg","imgName":"绠�绾�.jpg"},{"id":"53","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv8WSAeQSjAAFK5h5Y2y0713.jpg","imgName":"绠�绾�.jpg"},{"id":"54","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv8s6ANKA_AAFEsJfkBJw028.jpg","imgName":"鐜勫够.jpg"},{"id":"55","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv8t2AEi1NAAFAggL7LlI566.jpg","imgName":"鐜勫够.jpg"},{"id":"56","imgUrl":"http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv8uWAMWtPAAFENBzGvxU638.jpg","imgName":"鐜勫够.jpg"},{"id":"57","imgUrl":"http://101.254.183.67:9980/group1/M00/00/09/wKiTPlgv8u-AAiGrAAFBAOzFG4g149.jpg","imgName":"鐜勫够.jpg"},{"id":"58","imgUrl":"http://101.254.183.67:9980/group1/M00/00/0B/wKiTPlgzs0mARdbfAAFZKFV_TlA076.jpg","imgName":"Tulips.jpg"}]
     */

    private boolean success;
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

    public static class DataBean {
        /**
         * id : 29
         * imgUrl : http://101.254.183.67:9980/group1/M00/00/08/wKiTPlgv61WAOMT-AAB_giHF064935.jpg
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
