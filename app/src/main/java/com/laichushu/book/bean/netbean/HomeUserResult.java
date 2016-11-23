package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/11/22.
 */

public class HomeUserResult implements Serializable {

    /**
     * photo :
     * nickName : test
     * grade : 1
     * success : true
     */

    private String photo;
    private String nickName;
    private String grade;
    private boolean success;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
