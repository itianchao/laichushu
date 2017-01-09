package com.laichushu.book.mvp.write.coverdir;


import com.laichushu.book.bean.JsonBean.PreviewCoverBean;
import com.laichushu.book.bean.netbean.CoverDir_Paramet;
import com.laichushu.book.bean.netbean.CoverMake_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.CoverListActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 模版列表
 * Created by wangtong on 2016/11/23.
 */

public class CoverDirPresenter extends BasePresenter<CoverDirView> {

    private CoverListActivity mActivity;
    private String userId = ConstantValue.USERID;

    public CoverDirPresenter(CoverDirView view) {
        attachView(view);
        mActivity = (CoverListActivity) view;
    }

    /**
     * 获取收藏列表
     *
     * @param type 模版类型
     */
    public void loadCoverListData(String type) {

        LoggerUtil.e("获取收藏列表");
        CoverDir_Paramet paramet = new CoverDir_Paramet(type);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.getCoverList(paramet), new ApiCallback<CoverDirModle>() {
            @Override
            public void onSuccess(CoverDirModle model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 创建预览图片
     *
     */
    public void makePreviewCover(String id, String name, String type, String url) {
        mActivity.showProgressDialog();
        LoggerUtil.e("创建预览图片");
        CoverMake_Paramet paramet = new CoverMake_Paramet(id,userId,name,type,url);
        LoggerUtil.toJson(paramet);
        addSubscription(apiStores.makeCover(paramet), new ApiCallback<PreviewCoverBean>() {
            @Override
            public void onSuccess(PreviewCoverBean model) {
                mvpView.getMakePreviewCover(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail2("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
