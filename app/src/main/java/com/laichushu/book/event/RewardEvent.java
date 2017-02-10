package com.laichushu.book.event;

/**
 * 打赏
 * Created by wangtong on 2017/2/10.
 */

public class RewardEvent {

    private String money;
    public RewardEvent(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
