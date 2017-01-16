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
import com.laichushu.book.bean.netbean.WalletBalanceReward;
import com.laichushu.book.event.RefrushWalletEvent;
import com.laichushu.book.mvp.mine.wallet.WalletPresener;
import com.laichushu.book.mvp.mine.wallet.WalletView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
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
    private String money = null, defPlate = "1", payPlate = "1";
    private WalletBalanceReward bean;
    private Handler  handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
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
                    payPlate = "1";
                }

            }
        });
        rbWechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rbAlipay.setChecked(false);
                    rbWechat.setChecked(true);
                    payPlate = "2";
                }

            }
        });

        bean = getIntent().getParcelableExtra("bean");
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
                if (Integer.parseInt(money) <= 0) {
                    edMoney.setText(null);
                    ToastUtil.showToast("请输入正确充值金额");
                    return;
                }
                btnSubmit.setClickable(false);
                mvpPresenter.loadRechargeData(money, defPlate);
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
           handler.sendEmptyMessageDelayed(1,1700);
        } else {
            ToastUtil.showToast("充值失败！");
            btnSubmit.setClickable(true);
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void getDataFail(String msg) {

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
