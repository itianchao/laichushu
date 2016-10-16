package com.sofacity.laichushu.ui.base;


import android.os.Bundle;
import android.view.View;

import com.sofacity.laichushu.ui.base.BaseFragment;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * Created by Wangtong on 2016/10/11.
 * mvpFragment抽取
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
