package com.sofacity.laichushu.mvp.directories;

import java.util.List;

/**
 * Created by wangtong on 2016/11/7.
 */
public class BookMoudle {

    /**
     * success : true
     * data : [{"id":"172","name":"绗竴绔�","isSection":true},{"id":"173","name":"绗簩绔�","isSection":false},{"id":"174","name":"绗笁绔�","isSection":false}]
     */

    private boolean success;
    /**
     * id : 172
     * name : 绗竴绔�
     * isSection : true
     */

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

        public boolean isIsSection() {
            return isSection;
        }

        public void setIsSection(boolean isSection) {
            this.isSection = isSection;
        }
    }
}
