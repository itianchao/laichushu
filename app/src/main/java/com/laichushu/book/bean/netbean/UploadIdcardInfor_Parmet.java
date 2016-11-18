package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/18.
 */

public class UploadIdcardInfor_Parmet implements Serializable {
    private String userId, idCard, name;

    public UploadIdcardInfor_Parmet(String userId, String idCard, String name) {
        this.userId = userId;
        this.idCard = idCard;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
