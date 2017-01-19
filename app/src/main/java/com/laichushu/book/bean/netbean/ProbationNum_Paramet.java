package com.laichushu.book.bean.netbean;

/**
 * 试读 参数
 * Created by wangtong on 2017/1/17.
 */

public class ProbationNum_Paramet {
    private String articleId;

    public ProbationNum_Paramet(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
