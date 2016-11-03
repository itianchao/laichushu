package com.sofacity.laichushu.bean.netbean;

/**
 * //图书详情-喜欢本书的也喜欢 参数
 * Created by wangtong on 2016/11/3.
 */
public class BestLike_Paramet {


    /**
     * 图书Id
     * articleId : 1
     */
    private String pageSize;
    private String pageNo;
    private String articleId;
    private String userId;

    public BestLike_Paramet(String articleId, String pageSize, String pageNo,String userId) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.articleId = articleId;
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }
}
