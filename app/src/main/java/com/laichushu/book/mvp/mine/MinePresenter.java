package com.laichushu.book.mvp.mine;

import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.PersonalCentre_Parmet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;

/**
 * 个人中心
 * Created by wangtong on 2016/12/24.
 */

public class MinePresenter extends BasePresenter<MineView> {
    public MinePresenter(MineView view) {
        attachView(view);
    }

    /**
     * 请求个人信息接口
     */
    public void loadData() {
        addSubscription(apiStores.getPersonalDetails(new PersonalCentre_Parmet(ConstantValue.USERID)), new ApiCallback<PersonalCentreResult>() {
            @Override
            public void onSuccess(PersonalCentreResult result) {
                mvpView.getDataSuccess(result);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("");
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
