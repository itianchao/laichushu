package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.Balance_Paramet;
import com.laichushu.book.bean.netbean.RewardMoney_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.ui.widget.LoadDialog;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import org.geometerplus.android.fbreader.FBReader;

/**
 * 未出版图书
 * Created by wangtong on 2016/11/9.
 */

public class NopublishBookActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_loading;
    private LinearLayout ll_error_pager;
    private Button error_btn_retry;
    private WebView mWebView;
    private TextView titleTv;
    private ImageView finishIv;
    private String url;
    private String mErrorUrl;
    private LoadDialog mLoadDialog;
    private ImageView selectIv;
    private ImageView rewardMoneyIv;
    private String articleId;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_nopublishbook);
        mLoadDialog = new LoadDialog(this);
        titleTv = (TextView) findViewById(R.id.tv_title);
        finishIv = (ImageView) findViewById(R.id.iv_title_finish);
        rl_loading = (RelativeLayout) findViewById(R.id.rl_loading);
        rl_loading.setVisibility(View.GONE);
        ll_error_pager = (LinearLayout) findViewById(R.id.ll_error_pager);
        ll_error_pager.setVisibility(View.GONE);
        error_btn_retry = (Button) findViewById(R.id.error_btn_retry);
        mWebView = (WebView) findViewById(R.id.webadd);
        //打赏举报
        selectIv = (ImageView) findViewById(R.id.iv_title_other);//选择弹窗 举报or书签
        rewardMoneyIv = (ImageView) findViewById(R.id.iv_title_another);//打赏
        selectIv.setScaleType(ImageView.ScaleType.CENTER);
        rewardMoneyIv.setScaleType(ImageView.ScaleType.CENTER);
        GlideUitl.loadImg2(this, R.drawable.icon_report, selectIv);
        GlideUitl.loadImg2(this, R.drawable.reward, rewardMoneyIv);
        //点击事件
        selectIv.setOnClickListener(this);
        rewardMoneyIv.setOnClickListener(this);
        error_btn_retry.setOnClickListener(this);
        finishIv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String title = getIntent().getStringExtra("title");
        titleTv.setText(title);
        url = getIntent().getStringExtra("path");
        articleId = getIntent().getStringExtra("articleId");
        // 设置可以访问文件
        mWebView.getSettings().setAllowFileAccess(true);
        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAppCacheEnabled(true); // 0722
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.loadUrl(url);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.requestFocus();
        mWebView.setWebViewClient(wvc);
        mWebView.setWebChromeClient(wcc);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.error_btn_retry:
                if (mErrorUrl != null)
                {
                    ll_error_pager.setVisibility(View.GONE);
                    rl_loading.setVisibility(View.VISIBLE);
                    mWebView.loadUrl(mErrorUrl);
                }
                break;
            case R.id.iv_title_other://举报
                Bundle bundle = new Bundle();
                bundle.putString("articleId",articleId);
                com.laichushu.book.utils.UIUtil.openActivity(this, ReportActivity.class,bundle);
                break;
            case R.id.iv_title_another://打赏
                getBalace();
                break;
        }
    }

    private WebChromeClient wcc = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mLoadDialog.dismiss();
            } else {
                mLoadDialog.show();
            }
            super.onProgressChanged(view, newProgress);
        }
    };

    private WebViewClient wvc = new WebViewClient() {

        @Override
        public void onPageFinished(WebView view, String url) {
            rl_loading.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // 加载失败
            ll_error_pager.setVisibility(View.VISIBLE);
            rl_loading.setVisibility(View.GONE);
            System.out.println("failingUrl = " + failingUrl);
            mErrorUrl = failingUrl;
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };
    /**
     * 查询余额
     */
    public void getBalace() {
        showProgressDialog("加载中请稍候");
        Balance_Paramet paramet = new Balance_Paramet(ConstantValue.USERID);
        Logger.e("余额参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getBalance(paramet), new ApiCallback<BalanceBean>() {
            @Override
            public void onSuccess(BalanceBean model) {
                dismissProgressDialog();
                if (model.isSuccess()) {
                    double balance = model.getData();
                    String accepterId = getIntent().getStringExtra("authorId");
                    String articleId = getIntent().getStringExtra("articleId");
                    openReward(balance + "", accepterId, articleId);
                } else {
                    ToastUtil.showToast(model.getErrMsg());
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                dismissProgressDialog();
                LoggerUtil.e("code+" + code + "/msg:" + msg);
                ToastUtil.showToast("打赏失败");
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }

    /**
     * 打赏对话框
     * @param balance   余额
     * @param accepterId  被打赏者
     * @param articleId  书id
     */
    private void openReward(String balance, final String accepterId, final String articleId){
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        final View customerView = com.laichushu.book.utils.UIUtil.inflate(R.layout.dialog_reward);
        final EditText payEt = (EditText) customerView.findViewById(R.id.et_pay);
        TextView balanceTv = (TextView) customerView.findViewById(R.id.tv_balance);
        balanceTv.setText(balance);
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
                    if (Integer.parseInt(pay) > 0 || Integer.parseInt(pay) < 100) {
                        // TODO: 2016/11/8 请求打赏
                        rewardMoney(ConstantValue.USERID, accepterId, articleId, pay);
                        dialogBuilder.dismiss();
                    } else {
                        ToastUtil.showToast("只能打赏1-100金额");
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
                .setCustomView(customerView, this)                // 添加自定义View
                .show();
    }

    /**
     * 打赏请求
     */
    /**
     * 打赏请求
     *
     * @param money
     * @param articleId
     * @param accepterId
     * @param awarderId
     */
    public void rewardMoney(String awarderId, String accepterId, String articleId, String money) {
        showProgressDialog("加载中");
        RewardMoney_Paramet paramet = new RewardMoney_Paramet(awarderId, accepterId, articleId, money);
        Logger.e("打赏参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.rewardMoney(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                dismissProgressDialog();
                if (model.isSuccess()) {
                    ToastUtil.showToast("打赏成功，感谢支持");
                } else {
                    ToastUtil.showToast(model.getErrMsg());
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                dismissProgressDialog();
                LoggerUtil.e("code+" + code + "/msg:" + msg);
                ToastUtil.showToast("打赏失败");
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }
}
