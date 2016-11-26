package com.laichushu.book.bean.netbean;

/**
 * 话题详情评论保存
 * Created by wangtong on 2016/11/26.
 */

public class TopicDetailCommentSave_Paramet {

    private String userId;
    private String content;
    private String topicId;   //话题id

    public TopicDetailCommentSave_Paramet(String userId, String content, String topicId) {
        this.userId = userId;
        this.content = content;
        this.topicId = topicId;
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

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
}
