package com.sofacity.laichushu.ui.base;

import android.os.Bundle;

import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;


/**
 * Created by Wangtong on 2016/10/11.
 * mvpActivity抽取
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

}
