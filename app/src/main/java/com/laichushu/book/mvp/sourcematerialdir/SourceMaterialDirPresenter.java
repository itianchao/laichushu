package com.laichushu.book.mvp.sourcematerialdir;

import com.laichushu.book.bean.netbean.SourceMaterialDirList_Paramet;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.SourceMaterialDirActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 素材文件夹 presenter
 * Created by wangtong on 2016/11/16.
 */
public class SourceMaterialDirPresenter extends BasePresenter<SourceMaterialDirView> {
    private SourceMaterialDirActivity mActivity;
    //初始化构造
    public SourceMaterialDirPresenter(SourceMaterialDirView view) {
        attachView(view);
        mActivity = (SourceMaterialDirActivity)view;
    }

    /**
     * 获取素材文件夹列表
     * @param articleId
     */
    public void getSourceMaterialList(String articleId){
        LoggerUtil.e("获取素材文件夹列表");
        SourceMaterialDirList_Paramet paramet = new SourceMaterialDirList_Paramet(articleId);
        addSubscription(apiStores.getSourceMaterialDirList(paramet), new ApiCallback<SourceMaterialDirModle>() {
            @Override
            public void onSuccess(SourceMaterialDirModle model) {
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
