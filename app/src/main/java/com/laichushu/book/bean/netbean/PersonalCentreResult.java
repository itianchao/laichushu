package com.laichushu.book.bean.netbean;

import java.io.Serializable;
import java.util.Date;

/**
 * 个人主页通知类
 * 用于封装返回给手机APP端的json对象
 *
 * @author 施大勇
 * @Class Name PersonalHomeNotify
 * @Create In 2016年10月13日
 */
public class PersonalCentreResult implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8717635352536903519L;

    /*
     *  id
     */
    private String id;
    /*
     *  用户姓名
     */
    private String name;
    /*
     *  昵称
     */
    private String nickName;
    /*
     *  作者等级
     */
    private String grade;
    /*
     * 头像
     */
    private String photo;
    /*
     *  成功或失败
     */
    private Boolean success;
    /*
     *  错误码对应常量:ErrorCode
     */
    private String errCode;
    /*
     *  错误信息
     */
    private String errMsg;
    /*
     * 生日
     */
    private String birthday;
    /*
     * 地区（城市）
     */
    private String city;
    /*
     * 性别
     */
    private String sex;
    /*
     *  身份认证状态  1未认证 2  认证中 3认证失败   4.认证通过
     */
    private String atteStatus;
    /*
     * 个性签名
     */
    private String sign;

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    private String articleCount;

    /**
     * id
     *
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 用户姓名
     *
     * @return 用户姓名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 用户姓名
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 昵称
     *
     * @return 昵称
     */
    public String getNickName() {
        return this.nickName;
    }


    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public String getGrade() {
        return this.grade;
    }


    public void setGrade(String grade) {
        this.grade = grade;
    }


    public String getPhoto() {
        return this.photo;
    }

    /**
     * 头像
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 成功或失败
     *
     * @return 成功或失败
     */
    public Boolean getSuccess() {
        return this.success;
    }

    /**
     * 成功或失败
     * <p>
     * <p>
     * public void setSuccess(Boolean success) {
     * this.success = success;
     * }
     * <p>
     * /**
     * 错误码对应常量:ErrorCode
     *
     * @return 错误码对应常量:ErrorCode
     */
    public String getErrCode() {
        return this.errCode;
    }

    /**
     * 错误码对应常量:ErrorCode
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * 错误信息
     *
     * @return 错误信息
     */
    public String getErrMsg() {
        return this.errMsg;
    }

    /**
     * 错误信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * 身份认证状态
     *
     * @return 身份认证状态
     */
    public String getAtteStatus() {
        return this.atteStatus;
    }

    /**
     * 身份认证状态
     */
    public void setAtteStatus(String atteStatus) {
        this.atteStatus = atteStatus;

    }

    /**
     * 生日
     *
     * @return 生日
     */
    public String getBirthday() {
        return this.birthday;
    }

    /**
     * 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 地区（城市）
     *
     * @return 地区（城市）
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 地区（城市）
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 性别
     *
     * @return 性别
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 个性签名
     *
     * @return 个性签名
     */
    public String getSign() {
        return this.sign;
    }

    /**
     * 个性签名
     */
    public void setSign(String sign) {
        this.sign = sign;

    }
}
