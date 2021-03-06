package com.laichushu.book.bean.netbean;


/**
 * Created by PCPC on 2016/11/22.
 */

public class HomeUserResult extends BaseModel{

    /**
     * photo :
     * nickName : test
     * grade : 1
     * success : true
     */

    private String photo;
    private String nickName, userId;
    private String grade;
    private boolean status;
    private boolean success;
    private boolean beFocused;
    private String levelType;
    private String type;
    private String introduce;

    public HomeUserResult(String photo, String nickName, String userId, String grade, boolean status, boolean success, boolean beFocused, String levelType, String type, String introduce) {
        this.photo = photo;
        this.nickName = nickName;
        this.userId = userId;
        this.grade = grade;
        this.status = status;
        this.success = success;
        this.beFocused = beFocused;
        this.levelType = levelType;
        this.type = type;
        this.introduce = introduce;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isBeFocused() {
        return beFocused;
    }

    public void setBeFocused(boolean beFocused) {
        this.beFocused = beFocused;
    }

    public String getLevelType() {
        return levelType;
    }

    public void setLevelType(String levelType) {
        this.levelType = levelType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
