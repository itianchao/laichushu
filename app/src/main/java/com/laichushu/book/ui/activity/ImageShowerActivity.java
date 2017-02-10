package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.MyZoomImageView;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

public class ImageShowerActivity extends MvpActivity2 implements View.OnClickListener {

    private String url;
    private int window_width, window_height;
    private MyZoomImageView divShow;
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
        tvTitle = ((TextView) mSuccessView.findViewById(R.id.tv_title));
        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        url = getIntent().getStringExtra("path");
        tvTitle.setText("用户头像");

        ivBack.setOnClickListener(this);

        window_width = UIUtil.getScreenWidth();
        window_height = UIUtil.getScreenHeight();
        GlideUitl.loadImg(mActivity, url, divShow);


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
