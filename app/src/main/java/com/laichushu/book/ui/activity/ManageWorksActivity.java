package com.laichushu.book.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

public class ManageWorksActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mRecyclerView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View manageView = UIUtil.inflate(R.layout.activity_manage_works);
        ivBack = ((ImageView) manageView.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) manageView.findViewById(R.id.tv_title));
        mRecyclerView = (PullLoadMoreRecyclerView) manageView.findViewById(R.id.ryv_book);
        return manageView;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("作品管理");
        ivBack.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
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
