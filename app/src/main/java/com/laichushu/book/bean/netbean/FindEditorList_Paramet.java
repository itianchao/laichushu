package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/12/26.
 */

public class FindEditorList_Paramet {
    private String loginUserId, cityId, orderBy, pageNo, pageSize;

    public FindEditorList_Paramet(String loginUserId, String cityId, String orderBy, String pageNo, String pageSize) {
        this.loginUserId = loginUserId;
        this.cityId = cityId;
        this.orderBy = orderBy;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
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
