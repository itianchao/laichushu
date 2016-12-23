package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.findeditpage.FindEditPagePresenter;
import com.laichushu.book.mvp.findeditpage.FindEditPageView;
import com.laichushu.book.ui.adapter.TotalRanKingAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

public class FindEditPageActivity extends MvpActivity2<FindEditPagePresenter> implements FindEditPageView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener, RadioGroup.OnCheckedChangeListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private PullLoadMoreRecyclerView mVideoRecyclerView, mMineRecyclerView;
    private TotalRanKingAdapter rangeAdapter;
    private RadioGroup rgTitle;
    private RadioButton rbRanking,rbCity;
    @Override
    protected FindEditPagePresenter createPresenter() {
        return new FindEditPagePresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_find_edit_page);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        rgTitle = (RadioGroup) inflate.findViewById(R.id.rg_find);
        rbRanking = (RadioButton) inflate.findViewById(R.id.rb_total_ranking);
        rbCity = (RadioButton) inflate.findViewById(R.id.rb_city);
        mVideoRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_total_ranking);
        mMineRecyclerView = (PullLoadMoreRecyclerView) inflate.findViewById(R.id.ryv_city);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("编辑");

        ivBack.setOnClickListener(this);
        rgTitle.setOnCheckedChangeListener(this);
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_total_ranking:
                //全部排行
                mvpPresenter.showRankingDialog(mActivity,rbRanking);
                break;
            case R.id.rb_city:
                //城市

                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void showDialog() {
        showProgressDialog();
    }

    @Override
    public void dismissDialog() {
        dismissProgressDialog();
    }
}
