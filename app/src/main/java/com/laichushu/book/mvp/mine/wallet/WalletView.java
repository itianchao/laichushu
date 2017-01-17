package com.laichushu.book.mvp.mine.wallet;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.AliPayResult;
import com.laichushu.book.bean.netbean.WalletBalanceReward;
import com.laichushu.book.bean.wechatpay.WxInfo;

/**
 * Created by PCPC on 2016/11/26.
 */

public interface WalletView {
    void getWalletRecordDateSuccess(WalletBalanceReward modle);

    void getWithdrawalsApplayDateSuccess(RewardResult model);

    void getRechargePayDateSuccess(AliPayResult model);

    void getRechargePayDateSuccess(WxInfo model);

    void getDataFail(String msg);

    void showDialog();

    void dismissDialog();
}
