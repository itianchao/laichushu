package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/28.
 */

public class UserFocusBe_parmet implements Serializable {
    private String loginUserId, userId, pageNo, pageSize;

    public UserFocusBe_parmet(String loginUserId, String userId, String pageNo, String pageSize) {
        this.loginUserId = loginUserId;
        this.userId = userId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
