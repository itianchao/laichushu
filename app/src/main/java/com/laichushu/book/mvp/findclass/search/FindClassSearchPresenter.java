package com.laichushu.book.mvp.findclass.search;

import com.laichushu.book.db.DaoSession;
import com.laichushu.book.db.Search_History;
import com.laichushu.book.db.Search_HistoryDao;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.activity.FindClassSearchActivity;
import com.laichushu.book.ui.base.BasePresenter;

import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * 发现 - 课程 - 搜索 Presenter
 * Created by wangtong on 2017/1/6.
 */

public class FindClassSearchPresenter extends BasePresenter<FindClassSearchView> {
    private FindClassSearchActivity mActivity;
    private String userId = ConstantValue.USERID;
    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE1;
    private Search_HistoryDao search_historyDao;
    public FindClassSearchPresenter(FindClassSearchView view) {
        attachView(view);
        mActivity = (FindClassSearchActivity) view;
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
