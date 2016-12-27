package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.findgroupmain.FindGroupPagePresenter;
import com.laichushu.book.mvp.findgroupmain.FindGroupPageView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 小组主页
 * Created by wangtong on 2016/12/26.
 */

public class FindGroupMainActivity extends MvpActivity2<FindGroupPagePresenter> implements FindGroupPageView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mVideoRecyclerView, mMineRecyclerView;
    private CheckBox rbRanking, rbCity;

    @Override
    protected FindGroupPagePresenter createPresenter() {
        return new FindGroupPagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_groupmain);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        mVideoRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_total_ranking);
        mMineRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_city);
        rbRanking = (CheckBox) inflate.findViewById(R.id.rb_total_ranking);
        rbCity = (CheckBox) inflate.findViewById(R.id.rb_city);
        mVideoRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_find);
        mMineRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_recommend);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("小组主页");

        ivBack.setOnClickListener(this);
        rbRanking.setOnClickListener(this);
        rbCity.setOnClickListener(this);

        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.rb_find:
//                发现
                break;
            case R.id.rb_recommend:
//                推荐
                break;
        }

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
