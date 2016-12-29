package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/29.
 */

public class FindMyServerList_Paramet implements Serializable {
    private String loginUserId, userId, pageNo, pangeSize;

    public FindMyServerList_Paramet(String loginUserId, String userId, String pageNo, String pangeSize) {
        this.loginUserId = loginUserId;
        this.userId = userId;
        this.pageNo = pageNo;
        this.pangeSize = pangeSize;
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

    public String getPangeSize() {
        return pangeSize;
    }

    public void setPangeSize(String pangeSize) {
        this.pangeSize = pangeSize;
    }
}
