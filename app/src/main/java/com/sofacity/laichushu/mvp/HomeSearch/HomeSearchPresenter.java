package com.sofacity.laichushu.mvp.HomeSearch;

import android.database.sqlite.SQLiteDatabase;

import com.sofacity.laichushu.bean.netbean.HomeSearch_Paramet;
import com.sofacity.laichushu.db.DaoMaster;
import com.sofacity.laichushu.db.DaoSession;
import com.sofacity.laichushu.db.Search_HistoryDao;
import com.sofacity.laichushu.mvp.home.HomeHotModel;
import com.sofacity.laichushu.retrofit.ApiCallback;
import com.sofacity.laichushu.ui.activity.HomeSearchActivity;
import com.sofacity.laichushu.ui.base.BasePresenter;
import com.sofacity.laichushu.utils.SharePrefManager;

/**
 * home 搜索
 * Created by wangtong on 2016/10/31.
 */
public class HomeSearchPresenter extends BasePresenter<HomeSearchView> {
    private HomeSearchActivity mActivity;
    private String pageSize = "10";
    private String pageNo = "1";
    private String userId = SharePrefManager.getUserId();
    private HomeSearch_Paramet paramet = new HomeSearch_Paramet("",pageSize,pageNo,userId);
    private Search_HistoryDao search_historyDao;

    //初始化构造
    public HomeSearchPresenter(HomeSearchView view) {
        attachView(view);
        mActivity = (HomeSearchActivity) view;
    }
    public void LoadData(String name){
        mvpView.showLoading();
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
                mvpView.hideLoading();
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
        /**
         *  通过 DaoMaster 的内部类 DevOpenHelper创建数据库
         *  注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表。
         *  所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级（先备份数据再升级）。
         *
         * 参数1：Context
         * 参数2: 要创建的数据库名字
         * 参数3 : CursorFactroy
         */
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mActivity, "laichushu.db", null);
//        // 获取数据库
        SQLiteDatabase database = helper.getWritableDatabase();
//        // 获取DaoMaster
        DaoMaster daoMaster = new DaoMaster(database);
//        // 获取Session
        DaoSession daoSession = daoMaster.newSession();
//        // 获取对应的表的DAO对象
        search_historyDao = daoSession.getSearch_HistoryDao();
    }

    public Search_HistoryDao getSearch_historyDao() {
        return search_historyDao;
    }
}
