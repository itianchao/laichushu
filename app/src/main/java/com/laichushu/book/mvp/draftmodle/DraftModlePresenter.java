package com.laichushu.book.mvp.draftmodle;

import com.laichushu.book.mvp.regist2.RegistView2;
import com.laichushu.book.ui.activity.DraftModleActivity;
import com.laichushu.book.ui.base.BasePresenter;

/**
 * 草稿模式 presenter
 * Created by wangtong on 2016/11/18.
 */
public class DraftModlePresenter extends BasePresenter<DraftModleView> {
    private DraftModleActivity mActivity;

    //初始化构造
    public DraftModlePresenter(DraftModleView view) {
        attachView(view);
        mActivity = (DraftModleActivity) view;
    }
}
