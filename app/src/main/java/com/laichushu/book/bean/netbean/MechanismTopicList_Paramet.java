package com.laichushu.book.bean.netbean;

/**
 * 获取话题列表 参数
 * Created by wangtong on 2016/11/26.
 */

public class MechanismTopicList_Paramet {

    /**
     * id : 2
     * pageNo : 1
     * pageSize : 10
     */

    private String id;
    private String pageNo;
    private String pageSize;

    public MechanismTopicList_Paramet(String id, String pageNo, String pageSize) {
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
