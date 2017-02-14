package com.laichushu.book.bean.otherbean;

import java.util.ArrayList;
import java.util.List;

/**
 * 想法数据模型
 * Created by wangtong on 2017/2/13.
 */

public class BookMarkIdeaJsonBean {

    private ArrayList<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : 1001
         * hear : Java8 简明教程
         * name : 45
         * time : 卡特烈德叶维奇
         */

        private String content;
        private String hear;
        private String name;
        private String time;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHear() {
            return hear;
        }

        public void setHear(String hear) {
            this.hear = hear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
