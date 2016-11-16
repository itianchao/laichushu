package com.laichushu.book.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

/**
 * 我的
 * Created by wangtong on 2016/10/17.
 */
public class MineFragment extends MvpFragment2 {
    private TextView tvTitle;
    @Override
    protected BasePresenter createPresenter() {

        return null;
    }

    @Override
    public View createSuccessView() {
        View mRootView = UIUtil.inflate(R.layout.fragment_mine);
        tvTitle =(TextView) mRootView.findViewById(R.id.tv_title);
        tvTitle.setText("个人中心");
        return mRootView;
    }

    @Override
    protected void initData() {
        super.initData();
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }
}
