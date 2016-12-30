package com.laichushu.book.mvp.mineservice;

import com.laichushu.book.bean.netbean.AuthorWorksByBookId_Paramet;
import com.laichushu.book.bean.netbean.BookDetailsModle;
import com.laichushu.book.bean.netbean.FindMyServerList_Paramet;
import com.laichushu.book.bean.netbean.MyArticBooklist_paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MineServicePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * Created by PCPC on 2016/12/30.
 */

public class MineServicePresenter extends BasePresenter<MineServiceView> {
    private MineServicePageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE4;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public MineServicePresenter(MineServiceView view) {
        attachView(view);
        mActivity = (MineServicePageActivity) view;
    }

    public MyArticBooklist_paramet getColl_paramet() {
        return coll_paramet;
    }

    public void setColl_paramet(MyArticBooklist_paramet coll_paramet) {
        this.coll_paramet = coll_paramet;
    }

    //服务收藏
    private MyArticBooklist_paramet coll_paramet = new MyArticBooklist_paramet(userId, pageNo, pageSize, ConstantValue.COLLECTSERVICE_TYPE, ConstantValue.BOOKCOMMENTTYPE);

    public void LoadCollectionData() {
        LoggerUtil.toJson(coll_paramet);
        addSubscription(apiStores.getCollectList(coll_paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getCollectionDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public FindMyServerList_Paramet getCoop_paramet() {
        return coop_paramet;
    }

    public void setCoop_paramet(FindMyServerList_Paramet coop_paramet) {
        this.coop_paramet = coop_paramet;
    }

    //服务合作
    private FindMyServerList_Paramet coop_paramet = new FindMyServerList_Paramet(userId, "", pageNo, pageSize);

    public void LoadFindMyServerListData(String userID) {
        LoggerUtil.toJson(coop_paramet);
        addSubscription(apiStores.getFindMyServerListDetails(coop_paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getCollectionDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 获取图书详情
     *
     * @param articleId
     */
    public void loadBookDetailsByid(String articleId) {
        mvpView.showDialog();
        AuthorWorksByBookId_Paramet bookParamet = new AuthorWorksByBookId_Paramet(userId, articleId);
        LoggerUtil.e("获取图书详情");
        addSubscription(apiStores.getAuthorWorksByBookId(bookParamet), new ApiCallback<BookDetailsModle>() {
            @Override
            public void onSuccess(BookDetailsModle model) {
                mvpView.getBookDetailsByIdDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code" + code + "msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
}
