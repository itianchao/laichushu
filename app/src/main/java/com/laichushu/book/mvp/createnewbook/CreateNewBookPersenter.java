package com.laichushu.book.mvp.createnewbook;

import com.laichushu.book.ui.activity.CreateNewBookActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.SharePrefManager;
import com.orhanobut.logger.Logger;


/**
 * 评价详情
 * Created by wangtong on 2016/11/4.
 */
public class CreateNewBookPersenter extends BasePresenter<CreateNewBookView> {

    private CreateNewBookActivity mActivity;

    private String userId = SharePrefManager.getUserId();

    public CreateNewBookPersenter(CreateNewBookView view) {
        attachView(view);
        this.mActivity = (CreateNewBookActivity) view;
    }
    /**
     * 获取分类信息
     */
    public void loadCategoryData(){
        Logger.e("获取分类信息");
    }
    /**
     * 提交服务器创建新书
     */
    public void commitNewBook(){
        Logger.e("创建新书");
    }
}
