package com.laichushu.book.bean.netbean;

/**
 * home 分类
 * Created by wangtong on 2016/11/2.
 */
public class HomeCategroyListBook_Paramet {

    /**
     * sortWay : 1
     * pageSize : 2
     * pageNo : 10
     */

    private String categoryId;
    private String pageSize;
    private String pageNo;
    private String userId;

    public HomeCategroyListBook_Paramet(String categoryId, String pageSize, String pageNo, String userId) {
        this.categoryId = categoryId;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
