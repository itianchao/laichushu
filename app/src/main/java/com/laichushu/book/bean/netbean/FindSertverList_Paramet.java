package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/29.
 */

public class FindSertverList_Paramet implements Serializable {
    private String loginUserId, cityId, serviceType, orderBy, pageNo, pageSize;

    public FindSertverList_Paramet(String loginUserId, String cityId, String serviceType, String orderBy, String pageNo, String pageSize) {
        this.loginUserId = loginUserId;
        this.cityId = cityId;
        this.serviceType = serviceType;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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
