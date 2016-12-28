package com.laichushu.book.mvp.findgroup.groupsearch;

import com.laichushu.book.bean.netbean.SearchGroupList_Paramet;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.db.Search_History;
import com.laichushu.book.db.Search_HistoryDao;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.findgroup.groupmain.GroupListModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindGroupSearchActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.orhanobut.logger.Logger;

import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * 搜索 Presenter
 * Created by wangtong on 2016/12/27.
 */

public class FindGroupSearchPresenter extends BasePresenter<FindGroupSearchView> {

    private String userId = ConstantValue.USERID;
    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE1;
    private SearchGroupList_Paramet paramet = new SearchGroupList_Paramet(userId,"",pageNo,pageSize);
    private FindGroupSearchActivity mActivity;
    private Search_HistoryDao search_historyDao;

    public FindGroupSearchPresenter(FindGroupSearchView view) {
        attachView(view);
        mActivity = (FindGroupSearchActivity)view;
    }

    /**
     * 获取搜索小组结果
     * @param search 关键字
     */
    public void loadSearchResultData(String search) {
        Logger.e("搜索小组");
        getParamet().setName(search);//设置搜索关键字
        addSubscription(apiStores.searchGroupList(paramet), new ApiCallback<FindGroupModle>() {
            @Override
            public void onSuccess(FindGroupModle model) {
                mvpView.searchGroupDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.searchGroupDataFail(code+"|"+msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 搜索小组接口参数 获取
     * @return 搜索接口参数
     */
    public SearchGroupList_Paramet getParamet() {
        return paramet;
    }

    /**
     * 搜索小组接口参数 设置
     * @param paramet 搜索接口参数
     */
    public void setParamet(SearchGroupList_Paramet paramet) {
        this.paramet = paramet;
    }

    /**
     * 设置搜索历史数据库 dao 类
     */
    public void setupDatabase() {
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        search_historyDao = daoSession.getSearch_HistoryDao();
    }

    /**
     * @return 搜索历史数据库 dao 类
     */
    public Search_HistoryDao getSearch_historyDao() {
        return search_historyDao;
    }

    /**
     * @return 搜索小组历史
     */
    public List<Search_History> getHistoryList() {
        String type = ConstantValue.SEARCH_TYPE_GROUP;//搜索小组
        Query<Search_History> build = search_historyDao.queryBuilder().where(Search_HistoryDao.Properties.Type.eq(type)).build();
        return build.list();
    }
}
