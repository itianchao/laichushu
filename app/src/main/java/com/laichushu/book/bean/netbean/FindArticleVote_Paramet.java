package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/28.
 */

public class FindArticleVote_Paramet implements Serializable {
    //    id 图书id
//    userId
//    cooperaterId  编辑或者服务者id
//    cooperateType 1-编辑 2-服务者
//     remarks  备注
    private String id, userId, cooperaterId, cooperateType, remarks;

    public FindArticleVote_Paramet(String id, String userId, String cooperaterId, String cooperateType, String remarks) {
        this.id = id;
        this.userId = userId;
        this.cooperaterId = cooperaterId;
        this.cooperateType = cooperateType;
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCooperaterId() {
        return cooperaterId;
    }

    public void setCooperaterId(String cooperaterId) {
        this.cooperaterId = cooperaterId;
    }

    public String getCooperateType() {
        return cooperateType;
    }

    public void setCooperateType(String cooperateType) {
        this.cooperateType = cooperateType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
