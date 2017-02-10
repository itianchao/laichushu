package com.laichushu.book.bean.netbean;

/**
 * Created by wangtong on 2017/2/10.
 */

public class LiveRewardMoney_Paramet  {
    /**
     * awarderId : 112
     * accepterId : 112
     * sourceId : 112
     * money : 112
     */

    private String awarderId;
    private String accepterId;
    private String sourceId;
    private String money;

    public LiveRewardMoney_Paramet(String awarderId, String accepterId, String sourceId, String money) {
        this.awarderId = awarderId;
        this.accepterId = accepterId;
        this.sourceId = sourceId;
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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
