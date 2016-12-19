package com.laichushu.book.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.WalletBalanceReward;
import com.laichushu.book.mvp.wallet.WalletPresener;
import com.laichushu.book.mvp.wallet.WalletView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

public class RechargeDetailsActivity extends MvpActivity2<WalletPresener> implements WalletView, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private RadioGroup rbPay;
    private RadioButton rbAlipay, rbWechat;
    private Button btnSubmit;
    private EditText edMoney;
    private String money = "1", payPlate = null;

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
        rbAlipay = (RadioButton) inflate.findViewById(R.id.rb_aliPay);
        rbWechat = (RadioButton) inflate.findViewById(R.id.rb_wxPay);
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
        rbPay.setOnCheckedChangeListener(this);
        btnSubmit.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
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
                mvpPresenter.loadRechargeData(money, payPlate);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_aliPay:
                rbAlipay.setChecked(true);
                rbWechat.setChecked(false);
                payPlate = "1";
                break;
            case R.id.rb_wxPay:
                rbAlipay.setChecked(false);
                rbWechat.setChecked(true);
                payPlate = "2";
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
    public void getRechargePayDateSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("充值成功！");
        } else {
            ToastUtil.showToast("充值失败！");
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getDataFail(String msg) {

    }
}
