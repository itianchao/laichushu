package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PCPC on 2016/11/17.
 */

public class UpdatePersonalInfor_Parmet implements Serializable {
    private String userId, nickName, sex, city, sign;
    private String birthday;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public UpdatePersonalInfor_Parmet(String userId, String nickName, String sex, String city, String sign, String birthday) {
        this.userId = userId;
        this.nickName = nickName;
        this.sex = sex;
        this.city = city;
        this.sign = sign;
        this.birthday = birthday;

    }
}
