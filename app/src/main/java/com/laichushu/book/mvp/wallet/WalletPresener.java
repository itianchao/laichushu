package com.laichushu.book.mvp.wallet;

import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.bean.netbean.WalletBalanceRecord_Paramet;
import com.laichushu.book.bean.netbean.WalletBalanceReward;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.homepage.HomePageView;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MyWalletDetailsActivity;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * Created by PCPC on 2016/11/26.
 */

public class WalletPresener extends BasePresenter<WalletView> {
    private MyWalletDetailsActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE4;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public WalletPresener(WalletView view) {
        attachView(view);
        mActivity = (MyWalletDetailsActivity) view;
    }

    public MyWalletDetailsActivity getmActivity() {
        return mActivity;
    }

    public void setmActivity(MyWalletDetailsActivity mActivity) {
        this.mActivity = mActivity;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public WalletBalanceRecord_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(WalletBalanceRecord_Paramet paramet) {
        this.paramet = paramet;
    }

    //查询我的钱包基本信息+交易记录
    WalletBalanceRecord_Paramet paramet = new WalletBalanceRecord_Paramet(userId, pageNo, pageSize);

    public void LoadWalletRecordData() {
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getBalanceRecordDetails(paramet), new ApiCallback<WalletBalanceReward>() {
            @Override
            public void onSuccess(WalletBalanceReward model) {
                mvpView.getWalletRecordDateSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
    //
}
