package com.laichushu.book.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.laichushu.book.ui.widget.LoadingPager;

/**
 * Created by Wangtong on 2016/10/11.
 * mvpFragment抽取
 */
public abstract class MvpFragment2<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;
    protected LoadingPager mPage;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
        initData();
    }

    protected void initData() {

    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        mPage = new LoadingPager(mActivity) {
            @Override
            public View createSuccessView() {
                return MvpFragment2.this.createSuccessView();
            }
        };
        mPage.mTitle.setVisibility(View.GONE);
        return mPage;
    }
    public abstract View createSuccessView();

    public void refreshPage(LoadingPager.PageState type){
        mPage.refreshPage(type);
    }
}
