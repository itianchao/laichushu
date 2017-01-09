package com.laichushu.book.mvp.write.createnewmaterialdir;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.CreateSourceMaterialDir_Paramet;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.CreateMaterialDirActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 创建素材 presenter
 * Created by wangtong on 2016/11/22.
 */
public class CreateNewMaterialDirPresenter extends BasePresenter<CreateNewMaterialDirView> {
    private CreateMaterialDirActivity mActivity;

    //初始化构造
    public CreateNewMaterialDirPresenter(CreateNewMaterialDirView view) {
        attachView(view);
        mActivity = (CreateMaterialDirActivity) view;
    }

    /**
     * 创建素材文件夹
     * @param articleId
     * @param foldName
     */
    public void createSourceMaterialDir(String articleId, String foldName){

        LoggerUtil.e("创建素材文件夹");
        CreateSourceMaterialDir_Paramet paramet = new CreateSourceMaterialDir_Paramet(articleId,foldName);
        LoggerUtil.toJson(paramet);
        mvpView.showLoading();
        addSubscription(apiStores.createSourceMaterialDir(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getCreateSourceMaterialDir(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code："+code+"\nmsg:"+msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}
