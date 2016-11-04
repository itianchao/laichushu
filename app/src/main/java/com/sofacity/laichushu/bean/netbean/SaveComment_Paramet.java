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

    private String articleId;
    private String userId;
    private String content;
    private String starLevel;
    public SaveComment_Paramet(String articleId, String userId, String content,String starLevel) {
        this.articleId = articleId;
        this.userId = userId;
        this.content = content;
        this.starLevel = starLevel;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
