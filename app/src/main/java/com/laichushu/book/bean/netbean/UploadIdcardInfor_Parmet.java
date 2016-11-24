package com.laichushu.book.bean.netbean;

import java.io.File;
import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/18.
 */

public class UploadIdcardInfor_Parmet implements Serializable {
    private String userId, name, idCard;
    private File idCardFront, idCardOppsite;

    public UploadIdcardInfor_Parmet(String userId, String name, String idCard, File idCardFront, File idCardOppsite) {
        this.userId = userId;
        this.name = name;
        this.idCard = idCard;
        this.idCardFront = idCardFront;
        this.idCardOppsite = idCardOppsite;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public File getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(File idCardFront) {
        this.idCardFront = idCardFront;
    }

    public File getIdCardOppsite() {
        return idCardOppsite;
    }

    public void setIdCardOppsite(File idCardOppsite) {
        this.idCardOppsite = idCardOppsite;
    }
}
