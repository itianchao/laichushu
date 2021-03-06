package com.laichushu.book.mvp.home.homesearch;

import com.laichushu.book.bean.netbean.HomeSearch_Paramet;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.bean.netbean.PerInformation_Paramet;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.db.Search_HistoryDao;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.home.homelist.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.HomeSearchActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

/**
 * home 搜索
 * Created by wangtong on 2016/10/31.
 */
public class HomeSearchPresenter extends BasePresenter<HomeSearchView> {
    private HomeSearchActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    private HomeSearch_Paramet paramet = new HomeSearch_Paramet("", pageSize, pageNo, userId);
    private Search_HistoryDao search_historyDao;

    //初始化构造
    public HomeSearchPresenter(HomeSearchView view) {
        attachView(view);
        mActivity = (HomeSearchActivity) view;
    }

    /**
     * 模糊查询
     *
     * @param name 关键字
     */
    public void LoadData(String name) {
        getParamet().setComplexName(name);
        addSubscription(apiStores.homeSearch(paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getDataSuccess(model);
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

    public HomeSearch_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(HomeSearch_Paramet paramet) {
        this.paramet = paramet;
    }

    public void setupDatabase() {
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        search_historyDao = daoSession.getSearch_HistoryDao();
    }

    public Search_HistoryDao getSearch_historyDao() {
        return search_historyDao;
    }

    /**
     * 热门搜索
     */
    public void loadHotSearchData() {
        addSubscription(apiStores.getHotSearch(), new ApiCallback<HomeSearchModel>() {
            @Override
            public void onSuccess(HomeSearchModel model) {
                mvpView.getHotSearchDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail2("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    public PerInformation_Paramet getMsgParamet() {
        return msgParamet;
    }

    public void setMsgParamet(PerInformation_Paramet msgParamet) {
        this.msgParamet = msgParamet;
    }

    PerInformation_Paramet msgParamet = new PerInformation_Paramet(userId, pageNo, pageSize, "");

    /**
     * 搜索私信
     * @param senderName
     */
    public void LoadPerInfoDetailsData(String senderName) {
        //私信列表
        getMsgParamet().setSenderName(senderName);
        LoggerUtil.toJson(msgParamet);
        addSubscription(apiStores.getPerInformation(msgParamet), new ApiCallback<MessageCommentResult>() {
            @Override
            public void onSuccess(MessageCommentResult model) {
                mvpView.getPerInfoListDateSuccess(model);
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
}