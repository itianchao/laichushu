package com.laichushu.book.mvp.mechanismsearch;

import com.laichushu.book.ui.activity.MechanismSearchActivity;
import com.laichushu.book.ui.base.BasePresenter;

/**
 * 机构 presenter
 * Created by wangtong on 2016/10/12.
 */
public class MechanismSearchPresenter extends BasePresenter<MechanismSearchView> {
    private MechanismSearchActivity mActivity;

    //初始化构造
    public MechanismSearchPresenter(MechanismSearchView view) {
        attachView(view);
        mActivity = (MechanismSearchActivity) view;
    }
}
