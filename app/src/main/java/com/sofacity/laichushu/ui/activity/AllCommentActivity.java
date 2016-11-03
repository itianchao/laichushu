package com.sofacity.laichushu.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.allcomment.AllCommentMoudle;
import com.sofacity.laichushu.mvp.allcomment.AllCommentPresenter;
import com.sofacity.laichushu.mvp.allcomment.AllCommentView;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 全部评论
 * Created by wangtong on 2016/10/27.
 */
public class AllCommentActivity extends MvpActivity<AllCommentPresenter> implements View.OnClickListener, AllCommentView {

    private PullLoadMoreRecyclerView commentRyv;

    @Override
    protected AllCommentPresenter createPresenter() {
        return new AllCommentPresenter(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_allcomment);
        initTitleBar("全部评论");
        commentRyv = (PullLoadMoreRecyclerView)findViewById(R.id.ryv_comment);

    }

    /**
     * 初始化标题
     *
     * @param title 标题名称
     */
    private void initTitleBar(String title) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) findViewById(R.id.iv_title_finish);
        titleTv.setText(title);
        finishIv.setOnClickListener(this);
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
        }
    }

    @Override
    public void getDataSuccess(AllCommentMoudle model) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
