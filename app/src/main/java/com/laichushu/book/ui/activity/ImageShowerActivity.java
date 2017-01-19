package com.laichushu.book.ui.activity;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.DragImageView;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;

public class ImageShowerActivity extends MvpActivity2 implements View.OnClickListener {

    private WebView mWebView;
    private String url;
    private int window_width, window_height;
    private DragImageView divShow;
    private int state_height;
    private ViewTreeObserver viewTreeObserver;
    private ImageView ivBack;
    private TextView tvTitle;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_image_shower);
        mWebView = (WebView) mSuccessView.findViewById(R.id.webadd);
        divShow = (DragImageView) mSuccessView.findViewById(R.id.div_show);
        ivBack = ((ImageView) mSuccessView.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) mSuccessView.findViewById(R.id.tv_middleLeft));
        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        url = getIntent().getStringExtra("path");
        tvTitle.setText("头像展示");
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
        mWebView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.requestFocus();
        mWebView.setWebViewClient(wvc);
        //设置支持缩放
        mWebView.getSettings().setBuiltInZoomControls(true);
        // 设置出现缩放工具
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        window_width = UIUtil.getScreenWidth();
        window_height = UIUtil.getScreenHeight();
        GlideUitl.loadImg(mActivity, url, divShow);
        divShow.setmActivity(mActivity);
        viewTreeObserver = divShow.getViewTreeObserver();
        viewTreeObserver
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (state_height == 0) {
                            Rect frame = new Rect();
                            getWindow().getDecorView()
                                    .getWindowVisibleDisplayFrame(frame);
                            state_height = frame.top;
                            divShow.setScreen_H(window_height - state_height);
                            divShow.setScreen_W(window_width);
                        }

                    }
                });


        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 30);
    }

    private String mErrorUrl;
    private WebViewClient wvc = new WebViewClient() {


        @Override
        public void onPageFinished(WebView view, String url) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // 加载失败
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            LoggerUtil.e("failingUrl = " + failingUrl);
            mErrorUrl = failingUrl;
            refrushWebView();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    public void refrushWebView() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mWebView.loadUrl(mErrorUrl);
            }
        });
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
