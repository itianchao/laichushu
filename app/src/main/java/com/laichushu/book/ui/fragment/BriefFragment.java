package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.activity.MechanismDetailActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

/**
 * 获取机构简介
 * Created by wangtong on 2016/11/26.
 */

public class BriefFragment extends MvpFragment2 {

    private TextView briefTv;
    private String introduce;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_brief);
        briefTv = (TextView) mSuccessView.findViewById(R.id.tv_brief);
        introduce = ((MechanismDetailActivity) getActivity()).getBean().getIntroduce();
        briefTv.setText(introduce);
        return mSuccessView;
    }

    @Override
    public void initData() {
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }
}
