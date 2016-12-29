package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 我的--服务
 */
public class MineServicePageActivity extends MvpActivity2 implements View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private ImageView ivBack;
    private TextView tvTitle, tvRight;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_mine_service_page);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRight = ((TextView) inflate.findViewById(R.id.tv_title_right));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("我的服务");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("成为服务者");

        ivBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.tv_title_right:
               //成为服务者
                UIUtil.openActivity(mActivity, MineBeServantActivity.class);
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
