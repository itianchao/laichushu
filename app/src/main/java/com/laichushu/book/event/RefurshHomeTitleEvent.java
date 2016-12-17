package com.laichushu.book.event;

/**
 * 刷新活动列表
 * Created by wangtong on 2016/11/9.
 */

public class RefurshHomeTitleEvent {
    public boolean participate;
    public int applyAmount;
    public int position;

    public RefurshHomeTitleEvent(boolean participate, int applyAmount, int position) {
        this.participate = participate;
        this.applyAmount = applyAmount;
        this.position = position;
    }

    public boolean isParticipate() {
        return participate;
    }

    public void setParticipate(boolean participate) {
        this.participate = participate;
    }

    public int getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(int applyAmount) {
        this.applyAmount = applyAmount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
