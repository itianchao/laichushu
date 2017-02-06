package com.laichushu.book.mvp.write.directories;

import com.laichushu.book.bean.netbean.BaseModel;

import java.util.List;

/**
 * 素材目录内容
 * Created by wangtong on 2016/11/7.
 */
public class MaterialContentModel extends BaseModel {

    /**
     * success : true
     * data : [{"id":"175","name":"绱犳潗涓�","articleId":"74","parentId":"173"},{"id":"177","name":"绱犳潗浜�","articleId":"74","parentId":"173"}]
     */

    private boolean success;
    /**
     * id : 175
     * name : 绱犳潗涓�
     * articleId : 74
     * parentId : 173
     */
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

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
        private String id;
        private String name;
        private String articleId;
        private String parentId;
        private String imageUrl;
        private String imageName;

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
