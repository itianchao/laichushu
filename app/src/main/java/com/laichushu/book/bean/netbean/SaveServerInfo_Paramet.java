package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/12/29.
 */

public class SaveServerInfo_Paramet {
    private String loginUserId, userId, nickName, companyName, jobTitle, email, idProve, serviceType,
            visitingCard, serviceIntroduce;

    public SaveServerInfo_Paramet(String loginUserId, String userId, String nickName, String companyName, String jobTitle, String email, String idProve, String serviceType, String visitingCard, String serviceIntroduce) {
        this.loginUserId = loginUserId;
        this.userId = userId;
        this.nickName = nickName;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.email = email;
        this.idProve = idProve;
        this.serviceType = serviceType;
        this.visitingCard = visitingCard;
        this.serviceIntroduce = serviceIntroduce;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdProve() {
        return idProve;
    }

    public void setIdProve(String idProve) {
        this.idProve = idProve;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getVisitingCard() {
        return visitingCard;
    }

    public void setVisitingCard(String visitingCard) {
        this.visitingCard = visitingCard;
    }

    public String getServiceIntroduce() {
        return serviceIntroduce;
    }

    public void setServiceIntroduce(String serviceIntroduce) {
        this.serviceIntroduce = serviceIntroduce;
    }
}
