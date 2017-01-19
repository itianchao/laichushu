package com.laichushu.book.bean.netbean;

/**
 * 首页 - 同城 参数
 * Created by PCPC on 2017/1/9.
 */

public class ActivityListByCity_Paramet {
    private String userId, cityId, pageNo, pageSize;

    public ActivityListByCity_Paramet(String userId, String cityId, String pageNo, String pageSize) {
        this.userId = userId;
        this.cityId = cityId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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
