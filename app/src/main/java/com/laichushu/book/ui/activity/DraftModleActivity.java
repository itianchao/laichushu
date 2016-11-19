package com.laichushu.book.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.draftmodle.DraftModle;
import com.laichushu.book.mvp.draftmodle.DraftModlePresenter;
import com.laichushu.book.mvp.draftmodle.DraftModleView;
import com.laichushu.book.ui.adapter.DraftListAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 草稿模式
 * Created by wangtong on 2016/11/18.
 */

public class DraftModleActivity extends MvpActivity2<DraftModlePresenter> implements View.OnClickListener, DraftModleView {

    private TextView addTv;
    private ImageView finishIv;
    private TextView titleTv;
    private PullLoadMoreRecyclerView draftRyv;
    private TextView titleRightTv;

    @Override
    protected DraftModlePresenter createPresenter() {
        return new DraftModlePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccseeView = UIUtil.inflate(R.layout.activity_creatdraft);
        addTv = (TextView) mSuccseeView.findViewById(R.id.tv_add);
        titleTv = (TextView) mSuccseeView.findViewById(R.id.tv_title);
        titleRightTv = (TextView) mSuccseeView.findViewById(R.id.tv_title_right);
        finishIv = (ImageView) mSuccseeView.findViewById(R.id.iv_title_finish);
        draftRyv = (PullLoadMoreRecyclerView)mSuccseeView.findViewById(R.id.ryv_draft);

        draftRyv.setLinearLayout();
        titleRightTv.setVisibility(View.VISIBLE);
        titleRightTv.setText("管理");
        titleRightTv.setTextColor(Color.WHITE);
        draftRyv.setAdapter(new DraftListAdapter());
        titleTv.setText("草稿模式");
        addTv.setText("添加草稿");
        finishIv.setOnClickListener(this);
        return mSuccseeView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }

    @Override
    public void getDataSuccess(DraftModle model) {

    }

    @Override
    public void getDataFail(String msg) {
        Logger.e(msg);
        refreshPage(LoadingPager.PageState.STATE_ERROR);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }
}
