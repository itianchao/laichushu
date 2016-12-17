package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PCPC on 2016/11/22.
 */

public class HomeUseDyrResult implements Serializable {

    /**
     * success : true
     * data : [{"id":"28","teamId":"112","teamName":"","title":"逗你玩","content":"逗你玩逗你玩","replyNum":3,"createDate":"2天前","topFlag":"0","starterId":"112","creatUserName":"哦的的哦","creatUserId":"112","type":1,"createrPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg"},{"id":"29","teamId":"112","teamName":"","title":"i旅游哦突兀压神州婆婆","content":"侯loss6艘我是无所谓，，你，你","createDate":"2天前","topFlag":"0","starterId":"112","creatUserName":"哦的的哦","creatUserId":"112","type":1,"createrPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg"},{"id":"23","teamId":"112","teamName":"","title":"旅途通知我","content":"图我想问一下","browseNum":63,"createDate":"31天前","topFlag":"0","starterId":"112","creatUserName":"哦的的哦","creatUserId":"112","type":2,"createrPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg"},{"id":"24","teamId":"112","teamName":"","title":"啦啦啦啦","content":"额得得得得得得","browseNum":88,"createDate":"60天前","topFlag":"0","starterId":"112","creatUserName":"哦的的哦","creatUserId":"112","type":2,"createrPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg"},{"id":"25","teamId":"112","teamName":"","title":"兔兔在在在在在在","content":"龙门默默魔王","createDate":"6天前","topFlag":"0","starterId":"112","creatUserName":"哦的的哦","creatUserId":"112","type":1,"createrPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg"},{"id":"26","teamId":"112","teamName":"","title":"可以找我呜呜呜呜呜呜呜呜","content":"唐知我者谓我心忧","createDate":"6天前","topFlag":"0","starterId":"112","creatUserName":"哦的的哦","creatUserId":"112","type":1,"createrPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg"},{"id":"27","teamId":"112","teamName":"","title":"看了一下休息休息","content":"啦啦啦啦龙","createDate":"6天前","topFlag":"0","starterId":"112","creatUserName":"哦的的哦","creatUserId":"112","type":1,"createrPhoto":"http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg"}]
     */

    private boolean success;
    private String errMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 28
         * teamId : 112
         * teamName :
         * title : 逗你玩
         * content : 逗你玩逗你玩
         * replyNum : 3
         * createDate : 2天前
         * topFlag : 0
         * starterId : 112
         * creatUserName : 哦的的哦
         * creatUserId : 112
         * type : 1
         * createrPhoto : http://101.254.183.67:9980/group1/M00/00/0F/wKiTPlg4FM2ANV-AAACe5hadIhg819.jpg
         * browseNum : 63
         */

        private String id;
        private String teamId;
        private String teamName;
        private String title;
        private String content;
        private String createDate;
        private String topFlag;
        private String starterId;
        private String creatUserName;
        private String creatUserId;
        private int type;
        private String createrPhoto;

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

        private boolean isCollect;


        /*
             *  已回复数
             */
        private int replyNum;
        /*
         *  已收藏数
         */
        private int collectNum;
        /*
         *  已浏览数
         */
        private int browseNum;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTeamId() {
            return teamId;
        }

        public void setTeamId(String teamId) {
            this.teamId = teamId;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public int getBrowseNum() {
            return browseNum;
        }

        public void setBrowseNum(int browseNum) {
            this.browseNum = browseNum;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getTopFlag() {
            return topFlag;
        }

        public void setTopFlag(String topFlag) {
            this.topFlag = topFlag;
        }

        public String getStarterId() {
            return starterId;
        }

        public void setStarterId(String starterId) {
            this.starterId = starterId;
        }

        public String getCreatUserName() {
            return creatUserName;
        }

        public void setCreatUserName(String creatUserName) {
            this.creatUserName = creatUserName;
        }

        public String getCreatUserId() {
            return creatUserId;
        }

        public void setCreatUserId(String creatUserId) {
            this.creatUserId = creatUserId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreaterPhoto() {
            return createrPhoto;
        }

        public void setCreaterPhoto(String createrPhoto) {
            this.createrPhoto = createrPhoto;
        }
    }
}
