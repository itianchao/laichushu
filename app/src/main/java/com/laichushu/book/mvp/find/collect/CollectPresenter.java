package com.laichushu.book.mvp.find.collect;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CollectSave_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.fragment.CollectFragment;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 收藏
 * Created by wangtong on 2017/2/10.
 */

public class CollectPresenter extends BasePresenter<CollectView>{
    private String userId = ConstantValue.USERID;
    private String sourceType = ConstantValue.COLLECTCOURSE_TYPE;
    private CollectFragment mFragment;
    public CollectPresenter(CollectView view) {
        attachView(view);
        mFragment = (CollectFragment) view;
    }

    public void collectSave(String sourceId, final String type){
        mFragment.mActivity.showProgressDialog();
        LoggerUtil.e("收藏");
        CollectSave_Paramet paramet = new CollectSave_Paramet(userId,sourceId, sourceType,type);
        addSubscription(apiStores.collectSave(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mFragment.mActivity.dismissProgressDialog();
                mvpView.loadCollectDataSuccess(model,type);
            }

            @Override
            public void onFailure(int code, String msg) {
                mFragment.mActivity.dismissProgressDialog();
                mvpView.loadCollectDataFail(code + "|" + msg,type);
            }

            @Override
            public void onFinish() {
                mFragment.mActivity.dismissProgressDialog();
            }
        });
    }
}
