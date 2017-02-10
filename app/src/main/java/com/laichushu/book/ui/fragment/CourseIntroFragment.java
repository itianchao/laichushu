package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 课程 - 简介
 * Created by wangtong on 2017/1/9.
 */

public class CourseIntroFragment extends MvpFragment2 {

    private TextView briefTv;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_courseintro);
        briefTv = (TextView) mSuccessView.findViewById(R.id.tv_brief);
        return mSuccessView;
    }

    @Override
    public void initData() {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 30);
        String brief = getArguments().getString("brief");
        briefTv.setText(brief);
    }
}
