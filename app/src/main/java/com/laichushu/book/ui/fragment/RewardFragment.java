package com.laichushu.book.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.find.reward.RewardPresenter;
import com.laichushu.book.mvp.find.reward.RewardView;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

/**
 * 打赏
 * Created by wangtong on 2017/2/10.
 */

public class RewardFragment extends MvpFragment2<RewardPresenter> implements RewardView, View.OnClickListener {

    private ImageView rewardIv;
    private String sourceId;
    private String accepterId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        accepterId = getArguments().getString("accepterId");
        sourceId = getArguments().getString("sourceId");
    }

    @Override
    protected RewardPresenter createPresenter() {
        return new RewardPresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_reward);
        rewardIv = (ImageView) mSuccessView.findViewById(R.id.iv_reward);
        return mSuccessView;
    }

    @Override
    public void initData() {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        },20);
        rewardIv.setOnClickListener(this);
        super.initData();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_reward:
                //弹出对话框确认
                mvpPresenter.getBalace2();
                break;
        }
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
    }

    /**
     * 查询余额后打赏
     */
    @Override
    public void getBalance2Data(BalanceBean model) {
        if (model.isSuccess()) {
            String balance = model.getData().getMoney();
            double maxLimit = model.getData().getMaxLimit();
            double minLimit = model.getData().getMinLimit();
            mvpPresenter.openReward(balance + "", accepterId, sourceId, maxLimit, minLimit);
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    /**
     * 打赏回调
     *
     * @param model
     * @param money
     */
    @Override
    public void getRewardMoneyData(RewardResult model, String money) {
        if (model.isSuccess()) {
            ToastUtil.showToast("打赏成功，感谢支持");
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }
}
