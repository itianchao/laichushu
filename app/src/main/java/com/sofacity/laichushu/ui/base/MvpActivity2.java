package com.sofacity.laichushu.ui.base;

import android.os.Bundle;
import android.view.View;

import com.sofacity.laichushu.ui.widget.LoadingPager;


/**
 * Created by Wangtong on 2016/10/11.
 * mvpActivity抽取
 */
public abstract class MvpActivity2<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;
    private LoadingPager mPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        mPage = new LoadingPager(this) {
            @Override
            public View createSuccessView() {
                return MvpActivity2.this.createSuccessView();
            }
        };
        setContentView(mPage);
    }

    protected abstract P createPresenter();
    protected abstract View createSuccessView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
    public void refreshPage(LoadingPager.PageState type){
        mPage.refreshPage(type);
    }
}
