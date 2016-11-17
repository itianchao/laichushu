package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

/**
 * 我的
 * Created by wangtong on 2016/10/17.
 */
public class MineFragment extends MvpFragment2 implements View.OnClickListener {
    private TextView tvTitle;
    private ImageView ivMineHead;

    @Override
    protected BasePresenter createPresenter() {

        return null;
    }

    @Override
    public View createSuccessView() {
        View mRootView = UIUtil.inflate(R.layout.fragment_mine);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        ivMineHead = (ImageView) mRootView.findViewById(R.id.iv_minaHead);
        tvTitle.setText("编辑个人信息");
        return mRootView;
    }

    @Override
    protected void initData() {
        super.initData();
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {

    }
}
