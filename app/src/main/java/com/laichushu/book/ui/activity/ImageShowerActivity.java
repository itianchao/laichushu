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
import com.laichushu.book.ui.widget.MyZoomImageView;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;

public class ImageShowerActivity extends MvpActivity2 implements View.OnClickListener {

    private String url;
    private int window_width, window_height;
//    private DragImageView divShow;
    private MyZoomImageView divShow;
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
        divShow = (MyZoomImageView) mSuccessView.findViewById(R.id.div_show);
        ivBack = ((ImageView) mSuccessView.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) mSuccessView.findViewById(R.id.tv_middleLeft));
        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        url = getIntent().getStringExtra("path");
        tvTitle.setText("头像展示");

        ivBack.setOnClickListener(this);

        window_width = UIUtil.getScreenWidth();
        window_height = UIUtil.getScreenHeight();
        GlideUitl.loadImg(mActivity, url, divShow);
//        divShow.setmActivity(mActivity);
//        viewTreeObserver = divShow.getViewTreeObserver();
//        viewTreeObserver
//                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//
//                    @Override
//                    public void onGlobalLayout() {
//                        if (state_height == 0) {
//                            Rect frame = new Rect();
//                            getWindow().getDecorView()
//                                    .getWindowVisibleDisplayFrame(frame);
//                            state_height = frame.top;
//                            divShow.setScreen_H(window_height - state_height);
//                            divShow.setScreen_W(window_width);
//                        }
//
//                    }
//                });


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
