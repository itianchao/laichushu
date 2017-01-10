package com.laichushu.book.mvp.find.group.groupsearch;

import com.laichushu.book.mvp.find.group.GroupListModle;

import java.util.ArrayList;

/**
 * 发现 - 小组 - 搜索小组 模型
 * Created by wangtong on 2016/12/28.
 */

public class FindGroupModle {

    /**
     * data : {"teamList":[{"code":"100002","id":"278","joinNum":1,"joinStatus":"3","leaderId":"175","name":"盗墓组","photo":"http://101.254.183.67:9980/group1/M00/00/2D/wKiTPlhjPcGAdqnDAAGYxkrTmC8271.jpg","remarks":"盗一波墓","status":"0"},{"id":"144","joinNum":1,"joinStatus":"-1","leaderId":"152","name":"盗墓笔记","photo":"http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQ_yiAZOwsAAAhreXm-XY808.jpg","status":"1"}],"totalPage":2}
     * success : true
     */

    private DataBean data;
    private boolean success;
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * teamList : [{"code":"100002","id":"278","joinNum":1,"joinStatus":"3","leaderId":"175","name":"盗墓组","photo":"http://101.254.183.67:9980/group1/M00/00/2D/wKiTPlhjPcGAdqnDAAGYxkrTmC8271.jpg","remarks":"盗一波墓","status":"0"},{"id":"144","joinNum":1,"joinStatus":"-1","leaderId":"152","name":"盗墓笔记","photo":"http://101.254.183.67:9980/group1/M00/00/1E/wKiTPlhQ_yiAZOwsAAAhreXm-XY808.jpg","status":"1"}]
         * totalPage : 2
         */

        private int totalPage;
        private ArrayList<GroupListModle.DataBean> teamList;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public ArrayList<GroupListModle.DataBean> getTeamList() {
            return teamList;
        }

        public void setTeamList(ArrayList<GroupListModle.DataBean> teamList) {
            this.teamList = teamList;
        }
    }
}
