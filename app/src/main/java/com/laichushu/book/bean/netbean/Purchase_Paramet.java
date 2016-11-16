package com.laichushu.book.bean.netbean;

/**
 * 购买参数
 * Created by wangtong on 2016/11/8.
 */

public class Purchase_Paramet {
    private String articleId;
    private String buyerId;
    private String payMoney;

    public Purchase_Paramet(String articleId, String buyerId, String payMoney) {
        this.articleId = articleId;
        this.buyerId = buyerId;
        this.payMoney = payMoney;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }
}
