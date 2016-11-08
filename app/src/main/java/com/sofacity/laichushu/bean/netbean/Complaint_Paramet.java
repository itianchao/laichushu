package com.sofacity.laichushu.bean.netbean;

/**
 * 举报参数
 * Created by wangtong on 2016/11/8.
 */

public class Complaint_Paramet {
    private String articleId;
    private String userId;
    private String reason;
    private String position;
    private String linkUrl;
    private String remarks;

    public Complaint_Paramet(String articleId, String userId, String reason, String position, String linkUrl, String remarks) {
        this.articleId = articleId;
        this.userId = userId;
        this.reason = reason;
        this.position = position;
        this.linkUrl = linkUrl;
        this.remarks = remarks;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
