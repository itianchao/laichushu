package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.WalletBalanceReward;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

public class WithdrawalsDetails extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle, tvAccountNum;
    private WalletBalanceReward bean;
    private EditText edInputNum, edInputAcc;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_withdrawals_details);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvAccountNum = ((TextView) inflate.findViewById(R.id.tv_accountMoney));
        edInputNum = ((EditText) inflate.findViewById(R.id.ed_inputNum));
        tvAccountNum = ((TextView) inflate.findViewById(R.id.ed_inputAcc));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("提现");
        tvTitle.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        bean = (WalletBalanceReward) getIntent().getSerializableExtra("bean");
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
        }
    }
}
