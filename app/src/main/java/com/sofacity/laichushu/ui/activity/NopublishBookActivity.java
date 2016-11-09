package com.sofacity.laichushu.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.ui.widget.LoadDialog;

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
        error_btn_retry.setOnClickListener(this);
        finishIv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        titleTv.setText("");
        url = "";
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
}
