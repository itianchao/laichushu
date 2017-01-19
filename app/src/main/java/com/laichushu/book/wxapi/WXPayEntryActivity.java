package com.laichushu.book.wxapi;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.event.RefrushWalletEvent;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.activity.MyWalletDetailsActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends MvpActivity2 implements IWXAPIEventHandler, View.OnClickListener {
    private IWXAPI api;
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvResult;
    private Button btnReturn;
    private int result = 0;
    private int TIME = 10;
    private static final int TIME_START = 1;
    private static final int TIME_END = 2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIME_START:// 开始倒计时
                    btnReturn.setText(--TIME + "s后自动返回钱包首页");
                    break;
                case TIME_END:// 结束，返回首页
                    UIUtil.openActivity(WXPayEntryActivity.this, MyWalletDetailsActivity.class);
                    break;
            }
        }
    };

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_wxpay_entry);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvResult = ((TextView) inflate.findViewById(R.id.tv_rechargeResult));
        btnReturn = ((Button) inflate.findViewById(R.id.btn_return));
        return inflate;

    }

    @Override
    protected void initData() {
        super.initData();
        api = WXAPIFactory.createWXAPI(this, ConstantValue.WECHAT_APPID);
        api.handleIntent(getIntent(), this);

        tvTitle.setText("充值结果");
        tvTitle.setVisibility(View.VISIBLE);

        ivBack.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
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
            case R.id.btn_return:
                UIUtil.openActivity(WXPayEntryActivity.this, MyWalletDetailsActivity.class);
                break;

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = R.string.errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                result = R.string.errcode_unsupported;
                break;
            default:
                result = R.string.errcode_unknown;
                break;
        }
        tvResult.setText(result);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = TIME; i > 0; i--) {
                    handler.sendEmptyMessage(TIME_START);
                    if (i <= 0) break;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(TIME_END);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(TIME_START);
        EventBus.getDefault().postSticky(new RefrushWalletEvent(true));
    }
}
