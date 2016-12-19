package com.laichushu.book.mvp;

import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.write.FindView;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.fragment.FindFragment;

/**
 * Created by PCPC on 2016/12/19.
 */

public class FindPresenter extends BasePresenter<FindView> {
    private String pageSize = ConstantValue.PAGESIZE;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    private FindFragment findFragment;

    public FindPresenter(FindView view) {
        attachView(view);
        findFragment = (FindFragment) view;
    }
    //加载数据
}
