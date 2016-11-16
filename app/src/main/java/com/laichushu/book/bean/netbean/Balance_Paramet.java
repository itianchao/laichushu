package com.laichushu.book.bean.netbean;

/**
 * 查询余额 参数
 * Created by wangtong on 2016/11/8.
 */

public class Balance_Paramet {

    /**
     * userId : 112
     */

    private String userId;

    public Balance_Paramet(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
