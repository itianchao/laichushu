package com.laichushu.book.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.AliPayResult;
import com.laichushu.book.bean.netbean.WalletBalanceReward;
import com.laichushu.book.bean.wechatpay.WxInfo;
import com.laichushu.book.event.RefrushWalletEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.mine.wallet.WalletPresener;
import com.laichushu.book.mvp.mine.wallet.WalletView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.PayUtils;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * 充值界面
 */
public class RechargeDetailsActivity extends MvpActivity2<WalletPresener> implements WalletView, View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private RadioGroup rbPay;
    private CheckBox rbAlipay, rbWechat;
    private Button btnSubmit;
    private EditText edMoney;
    private String money = null, payPlate = ConstantValue.ALIPAY_PLATE;
    private WalletBalanceReward bean;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                finish();
            }
        }
    };
    @Override
    protected WalletPresener createPresenter() {

        return new WalletPresener(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_recharge_details);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        rbPay = (RadioGroup) inflate.findViewById(R.id.rg_pay);
        rbAlipay = (CheckBox) inflate.findViewById(R.id.rb_aliPay);
        rbWechat = (CheckBox) inflate.findViewById(R.id.rb_wxPay);
        btnSubmit = (Button) inflate.findViewById(R.id.btn_Recharge);
        edMoney = (EditText) inflate.findViewById(R.id.ed_inputMoney);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("充值");
        tvTitle.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        rbAlipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rbAlipay.setChecked(true);
                    rbWechat.setChecked(false);
                    payPlate = ConstantValue.ALIPAY_PLATE;
                }

            }
        });
        rbWechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rbAlipay.setChecked(false);
                    rbWechat.setChecked(true);
                    payPlate = ConstantValue.WXPAY_PLATE;
                }

            }
        });

        bean = getIntent().getParcelableExtra("bean");
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 30);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.btn_Recharge:
                money = edMoney.getText().toString().trim();
                if (TextUtils.isEmpty(money)) {
                    ToastUtil.showToast("请输入金额");
                    return;
                }
                try {

                    if (Double.parseDouble(money) > 0) {
                        if (money.contains(".") && money.substring(money.indexOf(".") + 1, money.length()).length() > 2) {
                            ToastUtil.showToast("不能超过小数点后两位");
                        } else {
                            btnSubmit.setClickable(false);
                            if (payPlate.equals(ConstantValue.ALIPAY_PLATE)) {
                                mvpPresenter.loadRechargeData(money, payPlate);
                            } else {
                                mvpPresenter.loadRechargeWXData(money, payPlate);
                            }

                        }
                    }

                } catch (NumberFormatException e) {
                    ToastUtil.showToast("输入格式错区");
                    edMoney.setText("");
                }


                break;
        }
    }


    @Override
    public void getWalletRecordDateSuccess(WalletBalanceReward modle) {

    }

    @Override
    public void getWithdrawalsApplayDateSuccess(RewardResult model) {

    }

    @Override
    public void getRechargePayDateSuccess(AliPayResult model) {
        if (model.isSuccess()) {
            if (payPlate.equals(ConstantValue.ALIPAY_PLATE)) {
//                ConstantValue.ALIPAY_CALLBACK_URL = model.getData().getNotifyUrl();
//                PayUtils.getInstance(mActivity).alipay(mActivity, money, model.getData().getOrderCode());
                ToastUtil.showToast("充值成功！");
                handler.sendEmptyMessageDelayed(1, 3700);
            }
        } else {
            ToastUtil.showToast("充值失败！");
            btnSubmit.setClickable(true);
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getRechargePayDateSuccess(WxInfo model) {
        if (model.isSuccess()) {
            if (payPlate.equals(ConstantValue.WXPAY_PLATE)) {
//                PayUtils.getInstance(mActivity).wechatPay(model);
                ToastUtil.showToast("充值成功！");
                handler.sendEmptyMessageDelayed(1, 3700);
            }
        } else {
            ToastUtil.showToast("充值失败！");
            btnSubmit.setClickable(true);
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);

    }

    @Override
    public void getDataFail(String msg) {
        LoggerUtil.e(msg);
    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }

    @Override
    public void finish() {
        super.finish();
        EventBus.getDefault().postSticky(new RefrushWalletEvent(true));
    }
}
