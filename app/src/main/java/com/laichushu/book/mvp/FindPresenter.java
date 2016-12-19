package com.laichushu.book.mvp;

import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeModel;
import com.laichushu.book.mvp.write.FindView;
import com.laichushu.book.retrofit.ApiCallback;
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

    /**
     * 加载轮番图
     */
    public void loadFindCarouseData() {
//        mvpView.showLoading();
        addSubscription(apiStores.homeCarouselData(),
                new ApiCallback<HomeModel>() {
                    @Override
                    public void onSuccess(HomeModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail("code+" + code + "/msg:" + msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

}
