package com.laichushu.book.mvp.sourcematerial;

import com.laichushu.book.bean.netbean.SourceMaterialList_Paramet;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.SourceMaterialActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 素材 presenter
 * Created by wangtong on 2016/11/16.
 */
public class SourceMaterialPresenter extends BasePresenter<SourceMaterialView> {
    private SourceMaterialActivity mActivity;
    //初始化构造
    public SourceMaterialPresenter(SourceMaterialView view) {
        attachView(view);
        mActivity = (SourceMaterialActivity)view;
    }

    /**
     * 获取文件夹列表
     * @param articleId
     */
    public void getSourceMaterialList(String articleId){
        LoggerUtil.e("获取文件夹列表");
        SourceMaterialList_Paramet paramet = new SourceMaterialList_Paramet(articleId);
        addSubscription(apiStores.getSourceMaterialList(paramet), new ApiCallback<SourceMaterialModle>() {
            @Override
            public void onSuccess(SourceMaterialModle model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code:"+code+"\nmsg:"+msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
