package com.laichushu.book.mvp.draftmodle;

import java.util.ArrayList;

/**
 * 草稿模式数据模型
 * Created by wangtong on 2016/10/12.
 */
public class DraftModle {

    /**
     * success : true
     * data : [{"id":"172","name":"绗竴绔�","content":"","isSection":true},{"id":"173","name":"绗簩绔�","content":"http://101.254.183.67/group1/M00/00/02/wKiTPlgkACOAN-u4AAAAsGFqsTg24.html","isSection":false},{"id":"174","name":"绗笁绔�","content":"","isSection":false},{"id":"210","name":"绗洓绔�","isSection":false},{"id":"211","name":"绗簲绔�","isSection":false}]
     */

    private boolean success;
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
         * id : 172
         * name : 绗竴绔�
         * content :
         * isSection : true
         */

        private String id;
        private String name;
        private String content;
        private boolean isSection;

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
