package com.laichushu.book.mvp.wallet;

import com.laichushu.book.bean.netbean.WalletBalanceReward;

/**
 * Created by PCPC on 2016/11/26.
 */

public interface WalletView {
    void getWalletRecordDateSuccess(WalletBalanceReward modle);

    void getDataFail(String msg);
}
