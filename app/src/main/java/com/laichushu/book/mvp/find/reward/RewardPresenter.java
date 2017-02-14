package com.laichushu.book.mvp.find.reward;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.Balance_Paramet;
import com.laichushu.book.bean.netbean.LiveRewardMoney_Paramet;
import com.laichushu.book.bean.netbean.RewardMoney_Paramet;
import com.laichushu.book.event.RewardEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

/**
 * 打赏
 * Created by wangtong on 2017/2/10.
 */

public class RewardPresenter extends BasePresenter<RewardView> {
    private MvpFragment2 mFragment;
    private String userId = ConstantValue.USERID;

    public RewardPresenter(RewardView view) {
        attachView(view);
        mFragment = (MvpFragment2) view;
    }
    public void getBalace2() {
        mvpView.showLoading();
        Balance_Paramet paramet = new Balance_Paramet(userId);
        Logger.e("余额参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getBalance(paramet), new ApiCallback<BalanceBean>() {
            @Override
            public void onSuccess(BalanceBean model) {
                mvpView.getBalance2Data(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
    /**
     * 打赏 对话框
     */
    public void openReward(String balance, final String accepterId, final String articleId, final double maxLimit, final double minLimit) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mFragment.mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_reward);
        final EditText payEt = (EditText) customerView.findViewById(R.id.et_pay);
        TextView balanceTv = (TextView) customerView.findViewById(R.id.tv_balance);
        payEt.setHint("只能打赏" + minLimit + "-" + maxLimit + "金额");
        balanceTv.setText("当前余额：" + balance);
        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pay = payEt.getText().toString();

                if (TextUtils.isEmpty(pay)) {
                    ToastUtil.showToast("请输入打赏金额");
                } else {
                    try {
                        if (Double.parseDouble(pay) >= minLimit && Double.parseDouble(pay) <= maxLimit) {
                            if (pay.contains(".") && pay.substring(pay.indexOf(".") + 1, pay.length()).length() > 2) {
                                ToastUtil.showToast("不能超过小数点后两位");
                            } else {
                                // TODO: 2016/11/8 请求打赏
                                rewardMoney(userId, accepterId, articleId, pay);
                                dialogBuilder.dismiss();
                            }

                        } else {
                            ToastUtil.showToast("只能打赏" + minLimit + "-" + maxLimit + "金额");
                        }
                    } catch (NumberFormatException e) {
                        ToastUtil.showToast("请输入正确的价格");
                    }
                }
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mFragment.mActivity)                // 添加自定义View
                .show();
    }
    /**
     * 打赏请求
     *
     * @param money
     * @param sourceId
     * @param accepterId
     * @param awarderId
     */
    public void rewardMoney(String awarderId, String accepterId, String sourceId, final String money) {
        mvpView.showLoading();
        LiveRewardMoney_Paramet paramet = new LiveRewardMoney_Paramet(awarderId, accepterId, sourceId, money);
        Logger.e("打赏参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.rewardMoney(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getRewardMoneyData(model,money);
                EventBus.getDefault().postSticky(new RewardEvent(money));
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
