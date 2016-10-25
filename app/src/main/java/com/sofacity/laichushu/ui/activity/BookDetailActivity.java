package com.sofacity.laichushu.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.base.BaseActivity;

/**
 * 图书详情
 * Created by wangtong on 2016/10/25.
 */
public class BookDetailActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView() {
        setContentView(R.layout.activity_bookdetail);
        initTitleBar("图书详情");
    }

    /**
     * 初始化标题
     *
     * @param title 标题名称
     */
    private void initTitleBar(String title) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) findViewById(R.id.iv_title_finish);
        ImageView shareIv = (ImageView) findViewById(R.id.iv_title_other);
        ImageView comentIv = (ImageView) findViewById(R.id.iv_title_another);
        titleTv.setText(title);
        shareIv.setImageResource(R.drawable.activity_share);
        comentIv.setImageResource(R.drawable.activity_keep);
        finishIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        comentIv.setOnClickListener(this);
    }

    /**
     * 点击事件
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.iv_title_other://分享
                break;
            case R.id.iv_title_another://收藏
                break;
        }
    }
}
