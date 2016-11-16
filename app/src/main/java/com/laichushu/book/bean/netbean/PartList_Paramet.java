package com.laichushu.book.bean.netbean;

/**
 * 节 参数
 * Created by wangtong on 2016/11/10.
 */

public class PartList_Paramet {
    private String parentId;
    private String pageNo;
    private String pageSize;

    public PartList_Paramet(String parentId, String pageNo, String pageSize) {
        this.parentId = parentId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
