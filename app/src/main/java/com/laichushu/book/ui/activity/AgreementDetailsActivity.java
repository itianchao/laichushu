package com.laichushu.book.ui.activity;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

/**
 * 协议界面
 */
public class AgreementDetailsActivity extends MvpActivity2 implements View.OnClickListener {

    private TextView titleTv;
    private ImageView finishIv;
    private WebView wvContent;
    private String type;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_agreement_details);
        titleTv = (TextView) inflate.findViewById(R.id.tv_title);
        finishIv = (ImageView) inflate.findViewById(R.id.iv_title_finish);
        titleTv.setText("注册协议");
        wvContent = (WebView) inflate.findViewById(R.id.wv_content);
        return inflate;
    }

    @Override
    protected void initData() {
        String type = getIntent().getStringExtra("type");
        finishIv.setOnClickListener(this);

        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvContent.getSettings().setLoadWithOverviewMode(true);
        if (type.equals("1")) {
            wvContent.loadUrl("file:///android_asset/copyright.html");
            titleTv.setText("著作权保护声明");
        } else if (type.equals("user")) {
            wvContent.loadUrl("file:///android_asset/regist.html");
            titleTv.setText("用户注册协议");
        }

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
        }
    }
}
