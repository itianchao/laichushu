package com.laichushu.book.ui.activity;

import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.WalletBalanceReward;
import com.laichushu.book.mvp.wallet.WalletPresener;
import com.laichushu.book.mvp.wallet.WalletView;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.CashierInputFilter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

public class WithdrawalsDetails extends MvpActivity2<WalletPresener> implements WalletView, View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle, tvAccountNum;
    private WalletBalanceReward bean;
    private EditText edInputNum, edInputAcc;
    private Button btnWithdrawals;

    @Override
    protected WalletPresener createPresenter() {
        return new WalletPresener(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_withdrawals_details);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvAccountNum = ((TextView) inflate.findViewById(R.id.tv_accountMoney));
        edInputNum = ((EditText) inflate.findViewById(R.id.ed_inputNum));
        edInputAcc = ((EditText) inflate.findViewById(R.id.ed_inputAcc));
        btnWithdrawals = ((Button) inflate.findViewById(R.id.btn_withdrawals));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("提现");
        tvTitle.setVisibility(View.VISIBLE);
        edInputNum.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        InputFilter[] filters = {new CashierInputFilter()};
        edInputNum.setFilters(filters);

        ivBack.setOnClickListener(this);
        btnWithdrawals.setOnClickListener(this);

        bean =getIntent().getParcelableExtra("bean");
        if (bean != null) {
            tvAccountNum.setText(bean.getBalance() + " 元");
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.btn_withdrawals:
                if (TextUtils.isEmpty(edInputNum.getText().toString())) {
                    ToastUtil.showToast("请输入金额");
                    return;
                }
                //不能大于
                if (Double.valueOf(edInputNum.getText().toString()) > bean.getBalance()) {
                    ToastUtil.showToast("输入金额超额，请重新输入");
                    edInputNum.setText("");
                    return;
                }
                if (TextUtils.isEmpty(edInputAcc.getText().toString())) {
                    ToastUtil.showToast("请输入支付宝账户");
                    return;
                }
                showProgressDialog();
                mvpPresenter.loadWithdrawalsData(edInputAcc.getText().toString(), edInputNum.getText().toString());
                break;
        }
    }

    @Override
    public void getWalletRecordDateSuccess(WalletBalanceReward modle) {

    }

    @Override
    public void getWithdrawalsApplayDateSuccess(RewardResult model) {
        dismissProgressDialog();
        if (model.isSuccess()) {
            edInputNum.setText(null);
            edInputAcc.setText(null);
            ToastUtil.showToast("提现申请成功！");
            mActivity.finish();
        } else {
            ToastUtil.showToast("操作失败！");
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getRechargePayDateSuccess(RewardResult model) {

    }

    @Override
    public void getDataFail(String msg) {
        LoggerUtil.e(msg);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }


}
