package com.sofacity.laichushu.mvp.regist2;

import com.sofacity.laichushu.mvp.regist.RegistView;
import com.sofacity.laichushu.ui.activity.Regist2Activity;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * 注册 presenter
 * Created by wangtong on 2016/10/12.
 */
public class RegistPresenter2 extends BasePresenter<RegistView2> {
    private Regist2Activity mActivity;

    //初始化构造
    public RegistPresenter2(RegistView2 view) {
        attachView(view);
        mActivity = (Regist2Activity) view;
    }
}
