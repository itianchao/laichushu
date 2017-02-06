package com.laichushu.book.mvp.mine.bookcast;


import com.laichushu.book.bean.netbean.BaseModel;

import java.util.List;

/**
 * Created by PCPC on 2016/11/21.
 */

public class BookCastModle extends BaseModel {

    /**
     * success : true
     * data : {"collectList":[{"id":"53","userId":"112","targetId":"74","collectName":"乖,摸摸头","collectCount":3,"collectType":"1","artCoverUrl":"http://101.254.183.67:9980/group1/M00/00/03/wKiTPlgpdXSAT7ohAABqYh718yY787.jpg","artCoverName":"wKiTPlgWui2AfU8TAABqYh718yY506.jpg"},{"id":"52","userId":"112","targetId":"114","collectName":"777","collectCount":1,"collectType":"1","artCoverUrl":"http://101.254.183.67:9980/group1/M00/00/0A/wKiTPlgyakWAV3GLAAC_5dIvu_E261.jpg","artCoverName":"20161121113005.jpg"},{"id":"51","userId":"112","targetId":"81","collectName":"天龙八部","collectCount":2,"collectType":"1","artCoverUrl":"http://101.254.183.67:9980/group1/M00/00/00/wKiTPlgYTveARJ69AALixhE4LjM854.jpg","artCoverName":"219049-12092510305493.jpg"}],"totalNum":3}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * collectList : [{"id":"53","userId":"112","targetId":"74","collectName":"乖,摸摸头","collectCount":3,"collectType":"1","artCoverUrl":"http://101.254.183.67:9980/group1/M00/00/03/wKiTPlgpdXSAT7ohAABqYh718yY787.jpg","artCoverName":"wKiTPlgWui2AfU8TAABqYh718yY506.jpg"},{"id":"52","userId":"112","targetId":"114","collectName":"777","collectCount":1,"collectType":"1","artCoverUrl":"http://101.254.183.67:9980/group1/M00/00/0A/wKiTPlgyakWAV3GLAAC_5dIvu_E261.jpg","artCoverName":"20161121113005.jpg"},{"id":"51","userId":"112","targetId":"81","collectName":"天龙八部","collectCount":2,"collectType":"1","artCoverUrl":"http://101.254.183.67:9980/group1/M00/00/00/wKiTPlgYTveARJ69AALixhE4LjM854.jpg","artCoverName":"219049-12092510305493.jpg"}]
         * totalNum : 3
         */

        private int totalNum;
        private List<CollectListBean> collectList;

        public int getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(int totalNum) {
            this.totalNum = totalNum;
        }

        public List<CollectListBean> getCollectList() {
            return collectList;
        }

        public void setCollectList(List<CollectListBean> collectList) {
            this.collectList = collectList;
        }

        public static class CollectListBean {
            /**
             * id : 53
             * userId : 112
             * targetId : 74
             * collectName : 乖,摸摸头
             * collectCount : 3
             * collectType : 1
             * artCoverUrl : http://101.254.183.67:9980/group1/M00/00/03/wKiTPlgpdXSAT7ohAABqYh718yY787.jpg
             * artCoverName : wKiTPlgWui2AfU8TAABqYh718yY506.jpg
             */

            private String id;
            private String userId;
            private String targetId;
            private String collectName;
            private int collectCount;
            private String collectType;
            private String artCoverUrl;
            private String artCoverName;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getTargetId() {
                return targetId;
            }

            public void setTargetId(String targetId) {
                this.targetId = targetId;
            }

            public String getCollectName() {
                return collectName;
            }

            public void setCollectName(String collectName) {
                this.collectName = collectName;
            }

            public int getCollectCount() {
                return collectCount;
            }

            public void setCollectCount(int collectCount) {
                this.collectCount = collectCount;
            }

            public String getCollectType() {
                return collectType;
            }

            public void setCollectType(String collectType) {
                this.collectType = collectType;
            }

            public String getArtCoverUrl() {
                return artCoverUrl;
            }

            public void setArtCoverUrl(String artCoverUrl) {
                this.artCoverUrl = artCoverUrl;
            }

            public String getArtCoverName() {
                return artCoverName;
            }

            public void setArtCoverName(String artCoverName) {
                this.artCoverName = artCoverName;
            }
        }
    }
}