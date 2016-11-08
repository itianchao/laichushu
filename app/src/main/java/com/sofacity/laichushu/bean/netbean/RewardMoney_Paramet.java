package com.sofacity.laichushu.bean.netbean;

/**
 * 打赏参数
 * Created by wangtong on 2016/11/8.
 */

public class RewardMoney_Paramet {

    /**
     * awarderId : 112
     * accepterId : 112
     * articleId : 112
     * money : 112
     */

    private String awarderId;
    private String accepterId;
    private String articleId;
    private String money;

    public RewardMoney_Paramet(String awarderId, String accepterId, String articleId, String money) {
        this.awarderId = awarderId;
        this.accepterId = accepterId;
        this.articleId = articleId;
        this.money = money;
    }

    public String getAwarderId() {
        return awarderId;
    }

    public void setAwarderId(String awarderId) {
        this.awarderId = awarderId;
    }

    public String getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(String accepterId) {
        this.accepterId = accepterId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
