package com.laichushu.book.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    @Override
    protected BasePresenter createPresenter() {

        return null;
    }

    @Override
    public View createSuccessView() {
        View createSuccessView = UIUtil.inflate(R.layout.fragment_mine);
        return createSuccessView;
    }

    @Override
    protected void initData() {
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }
}
