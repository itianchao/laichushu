package com.laichushu.book.mvp.find.reward;

import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;

/**
 * 打赏
 * Created by wangtong on 2017/2/10.
 */
public interface RewardView {
    void showLoading();

    void hideLoading();

    void getRewardMoneyData(RewardResult model, String money);

    void getDataFail(String msg);

    void getBalance2Data(BalanceBean model);
}
