package com.laichushu.book.bean.netbean;

/**
 * 话题详情评论列表参数
 * Created by wangtong on 2016/11/26.
 */

public class TopicDetailCommentList_Paramet {
    private String topicId;  //话题 id
    private String pageNo;
    private String pageSize;

    public TopicDetailCommentList_Paramet(String topicId, String pageNo, String pageSize) {
        this.topicId = topicId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
