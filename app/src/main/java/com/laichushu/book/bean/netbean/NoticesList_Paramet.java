package com.laichushu.book.bean.netbean;

/**
 * 机构公告列表参数
 * Created by wangtong on 2016/11/26.
 */

public class NoticesList_Paramet {
    private String id; //机构id
    private String pageNo;
    private String pageSize;

    public NoticesList_Paramet(String id, String pageNo, String pageSize) {
        this.id = id;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNO) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}
