package com.laichushu.book.mvp.part;

import java.util.ArrayList;

/**
 * 节 modle
 * Created by wangtong on 2016/11/9.
 */

public class PartModel {

    /**
     * success : true
     * data : [{"id":"182","name":"绗竴绔� 椤轰赴","content":"","isSection":false}]
     */

    private boolean success;
    private String errMsg;
    /**
     * id : 182
     * name : 绗竴绔� 椤轰赴
     * content :
     * isSection : false
     */

    private ArrayList<DataBean> data;

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
        private String id;
        private String name;
        private String content;
        private boolean isSection;
        private String contentUrlPc;        // pc端素材路径
        private String contentUrlApp;    // app 端素材路径
        private String imageUrl;            // 上传图片路径
        private String imageName;        // 上传图片名称
        private String articleId;            // 作品id
        private String parentId;            // 父ID

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
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

        public boolean isSection() {
            return isSection;
        }

        public void setSection(boolean section) {
            isSection = section;
        }

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isIsSection() {
            return isSection;
        }

        public void setIsSection(boolean isSection) {
            this.isSection = isSection;
        }
    }
}
