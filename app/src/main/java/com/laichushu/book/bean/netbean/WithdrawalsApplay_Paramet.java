package com.laichushu.book.bean.netbean;

/**
 * Created by PCPC on 2016/12/8.
 */

public class WithdrawalsApplay_Paramet {
    private String userId, accountName, applyMoney;

    public WithdrawalsApplay_Paramet(String userId, String accountName, String applyMoney) {
        this.userId = userId;
        this.accountName = accountName;
        this.applyMoney = applyMoney;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getApplyMoney() {
        return applyMoney;
    }

    public void setApplyMoney(String applyMoney) {
        this.applyMoney = applyMoney;
    }
}
