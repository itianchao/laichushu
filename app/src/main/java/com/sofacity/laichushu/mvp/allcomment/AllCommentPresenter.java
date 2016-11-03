package com.sofacity.laichushu.mvp.allcomment;

import com.sofacity.laichushu.ui.activity.AllCommentActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;

/**
 * 全部评论
 * Created by wangtong on 2016/11/3.
 */
public class AllCommentPresenter extends BasePresenter<AllCommentView> {
    private AllCommentActivity mActivity;
    //初始化构造
    public AllCommentPresenter(AllCommentView view) {
        attachView(view);
        mActivity = (AllCommentActivity) view;
    }

}
