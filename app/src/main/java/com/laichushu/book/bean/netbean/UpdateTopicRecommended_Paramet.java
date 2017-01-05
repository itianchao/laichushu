package com.laichushu.book.bean.netbean;

/**
 * 话题设置推荐
 * Created by wangtong on 2017/1/5.
 */

public class UpdateTopicRecommended_Paramet {
    private String id,userId;

    public UpdateTopicRecommended_Paramet(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
