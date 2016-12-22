package com.laichushu.book.ui.activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.event.RefurshPhotoPathEvent;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.AppManager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * 预览模版
 * Created by wangtong on 2016/11/23.
 */

public class PreviewCoverActivity extends MvpActivity2 implements View.OnClickListener {

    private TextView titleTv,preDetails;
    private ImageView finishIv,coverIv;
    private Button createBtn;
    private WebView mWebView;
    private String url;
    private String title;
    private String bookType;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_previewcover);
        titleTv = (TextView)mSuccessView.findViewById(R.id.tv_title);
        preDetails = (TextView)mSuccessView.findViewById(R.id.tv_perDetails);
        finishIv = (ImageView)mSuccessView.findViewById(R.id.iv_title_finish);
        createBtn = (Button)mSuccessView.findViewById(R.id.btn_create);
        coverIv = (ImageView) mSuccessView.findViewById(R.id.webadd);
        finishIv.setOnClickListener(this);
        createBtn.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("path");
        title = getIntent().getStringExtra("title");
        bookType = getIntent().getStringExtra("bookType");
        titleTv.setText(title);
        preDetails.setText(bookType+"\n"+ SharePrefManager.getNickName());
        GlideUitl.loadImg(mActivity,url,270,350,coverIv);
        // 设置可以访问文件
//        mWebView.getSettings().setAllowFileAccess(true);
//        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
//        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        mWebView.getSettings().setAllowFileAccess(true);
//        mWebView.getSettings().setAppCacheEnabled(true); // 0722
//        mWebView.getSettings().setDomStorageEnabled(true);
//        mWebView.getSettings().setDatabaseEnabled(true);
//        mWebView.loadUrl(url);
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//        mWebView.requestFocus();
//        mWebView.setWebViewClient(wvc);
////        mWebView.setWebChromeClient(wcc);LayoutAlgorithm.SINGLE_COLUMN
//
//        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.btn_create:
                EventBus.getDefault().postSticky(new RefurshPhotoPathEvent(url));
                AppManager.getInstance().killActivity(CoverListActivity.class);
                AppManager.getInstance().killActivity(CoverDirActivity.class);
                finish();
                break;
        }
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
    public void refrushWebView(){
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mWebView.loadUrl(mErrorUrl);
            }
        });
    }
}
