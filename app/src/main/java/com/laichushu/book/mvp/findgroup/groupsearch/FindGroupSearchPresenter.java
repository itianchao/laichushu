package com.laichushu.book.mvp.findgroup.groupsearch;

import com.laichushu.book.ui.base.BasePresenter;
import com.orhanobut.logger.Logger;

/**
 * 搜索 Presenter
 * Created by wangtong on 2016/12/27.
 */

public class FindGroupSearchPresenter extends BasePresenter<FindGroupSearchView> {
    public FindGroupSearchPresenter(FindGroupSearchView view) {
        attachView(view);
    }

    /**
     * 获取搜索结果
     * @param search 关键字
     */
    public void loadSearchResultData(String search) {
        Logger.e("搜索");
//        addSubscription();
    }
}
