package com.laichushu.book.bean.netbean;

/**
 * 获取机构列表
 * Created by wt on 2016/11/24.
 */

public class MechanismList_Paramet {
    private String type,pageNo,pageSize;

    public MechanismList_Paramet(String type, String pageSize, String pageNo) {
        this.type = type;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
