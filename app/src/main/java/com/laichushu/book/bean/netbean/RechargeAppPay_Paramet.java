package com.laichushu.book.bean.netbean;

import java.io.Serializable;

/**
 * Created by PCPC on 2016/12/17.
 */

public class RechargeAppPay_Paramet implements Serializable {
    private String userId, money, payPlate;//(1:微信，2:支付宝)

    public RechargeAppPay_Paramet(String userId, String money, String payPlate) {
        this.userId = userId;
        this.money = money;
        this.payPlate = payPlate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPayPlate() {
        return payPlate;
    }

    public void setPayPlate(String payPlate) {
        this.payPlate = payPlate;
    }
}
