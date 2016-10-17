package com.sofacity.laichushu.ui.fragment;

import android.os.Bundle;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.home.HomeModel;
import com.sofacity.laichushu.mvp.home.HomePresenter;
import com.sofacity.laichushu.mvp.home.HomeView;
import com.sofacity.laichushu.ui.base.MvpFragment;
import com.sofacity.laichushu.utils.UIUtil;

/**
 * 首页
 * Created by wangtong on 2016/10/17.
 */
public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView{
    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtil.inflate(R.layout.fragment_home);
    }

    @Override
    public void getDataSuccess(HomeModel model) {

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }
}
