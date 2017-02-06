package com.laichushu.book.mvp.msg.notice;

import com.laichushu.book.bean.netbean.BaseModel;

import java.util.ArrayList;

/**
 * 公告数据模型
 * Created by wangtong on 2016/10/12.
 */
public class NoticeModle extends BaseModel {
    private boolean success;
    private String errMsg;
    private ArrayList<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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
        /**
         * id : 31
         * partyId : 1
         * partyName : 中国书籍出版社
         * title : 服务器升级
         * content : 由于服务器升级，2016年12月1日凌晨1：00-3：00服务停用
         * createUserId : 1
         * updateUserId : 1
         * releaseTime : 1479697873000
         */

        private String id;
        private String partyId;
        private String partyName;
        private String title;
        private String content;
        private String createUserId;
        private String updateUserId;
        private String releaseTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPartyId() {
            return partyId;
        }

        public void setPartyId(String partyId) {
            this.partyId = partyId;
        }

        public String getPartyName() {
            return partyName;
        }

        public void setPartyName(String partyName) {
            this.partyName = partyName;
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

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }
    }
}
