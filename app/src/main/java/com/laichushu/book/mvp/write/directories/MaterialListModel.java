package com.laichushu.book.mvp.write.directories;

import java.util.ArrayList;

/**
 *  素材列表
 * Created by wangtong on 2016/11/7.
 */
public class MaterialListModel {

    /**
     * data : [{"articleId":"74","id":"175","name":"绱犳潗涓�","parentId":"173"},{"articleId":"74","id":"177","name":"绱犳潗浜�","parentId":"173"}]
     * success : true
     */

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * articleId : 74
     * id : 175
     * name : 绱犳潗涓�
     * parentId : 173
     */

    private String errMsg;
    /**
     * parentId : 175
     * name : 绱犳潗涓�
     * count : 1
     * data : [{"id":"176","name":"绱犳潗鑺備竴","articleId":"74","parentId":"175"}]
     */

    private ArrayList<DataBean> data;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }


    public static class DataBean {
        private String id;
        private String name;
        private String count;
        private String contentUrlPc;    	// pc端素材路径
        private String contentUrlApp;   	// app 端素材路径
        /**
         * id : 176
         * name : 绱犳潗鑺備竴
         * articleId : 74
         * parentId : 175
         */

        private ArrayList<InDataBean> data;

        public String getContentUrlPc() {
            return contentUrlPc;
        }

        public void setContentUrlPc(String contentUrlPc) {
            this.contentUrlPc = contentUrlPc;
        }

        public String getContentUrlApp() {
            return contentUrlApp;
        }

        public void setContentUrlApp(String contentUrlApp) {
            this.contentUrlApp = contentUrlApp;
        }

        /**
         * isSection : false
         */

        private boolean isSection;

        public boolean isSection() {
            return isSection;
        }

        public void setSection(boolean section) {
            isSection = section;
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

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public ArrayList<InDataBean> getData() {
            return data;
        }

        public void setData(ArrayList<InDataBean> data) {
            this.data = data;
        }

        public boolean isIsSection() {
            return isSection;
        }

        public void setIsSection(boolean isSection) {
            this.isSection = isSection;
        }

        public static class InDataBean {
            private String id;
            private String name;
            private String articleId;
            private String parentId;
            private String imageName;
            private String imageUrl;
            private String contentUrlPc;    	// pc端素材路径
            private String contentUrlApp;   	// app 端素材路径

            public String getContentUrlPc() {
                return contentUrlPc;
            }

            public void setContentUrlPc(String contentUrlPc) {
                this.contentUrlPc = contentUrlPc;
            }

            public String getContentUrlApp() {
                return contentUrlApp;
            }

            public void setContentUrlApp(String contentUrlApp) {
                this.contentUrlApp = contentUrlApp;
            }

            public String getImageName() {
                return imageName;
            }

            public void setImageName(String imageName) {
                this.imageName = imageName;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
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

            public String getArticleId() {
                return articleId;
            }

            public void setArticleId(String articleId) {
                this.articleId = articleId;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }
        }
    }
}
