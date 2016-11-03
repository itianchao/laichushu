package com.sofacity.laichushu.bean.netbean;

/**
 * 保存评论
 * Created by wangtong on 2016/11/3.
 */
public class SaveComment_Paramet {

    /**
     * sourceId : 1
     * userId : 2
     * content : 3
     */

    private String sourceId;
    private String userId;
    private String content;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
