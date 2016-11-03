package com.sofacity.laichushu.bean.netbean;

/**
 * home 全部图书
 * Created by wangtong on 2016/11/2.
 */
public class HomeAllBook_Paramet {

    /**
     * sortWay : 1
     * pageSize : 2
     * pageNo : 10
     */

    private String sortWay;
    private String pageSize;
    private String pageNo;
    private String userId;

    public HomeAllBook_Paramet(String sortWay, String pageSize, String pageNo, String userId) {
        this.sortWay = sortWay;
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

    public String getSortWay() {
        return sortWay;
    }

    public void setSortWay(String sortWay) {
        this.sortWay = sortWay;
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
