package com.laichushu.book.mvp.findgroup.topicsearch;

import com.laichushu.book.bean.netbean.MyPublishTopicList_Paramet;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.db.Search_History;
import com.laichushu.book.db.Search_HistoryDao;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindSearchGroupTopicActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.orhanobut.logger.Logger;

import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * 小组话题搜索
 * Created by wangtong on 2017/1/3.
 */

public class SearchGroupTopicPresenter extends BasePresenter<SearchGroupTopicView> {

    private String userId = ConstantValue.USERID;
    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE1;
    private String type = ConstantValue.SEARCH_TYPE_GROUP;
    private String teamId = ConstantValue.STRING_NULL;
    private MyPublishTopicList_Paramet paramet = new MyPublishTopicList_Paramet(userId, type, pageNo, pageSize, teamId, "");
    private FindSearchGroupTopicActivity mActivity;
    private Search_HistoryDao search_historyDao;

    public SearchGroupTopicPresenter(SearchGroupTopicView view) {
        mActivity = (FindSearchGroupTopicActivity) view;
        attachView(view);
    }

    /**
     * 获取搜索话题结果
     *
     * @param search 关键字
     */
    public void loadSearchResultData(String search,String teamId) {
        mActivity.showProgressDialog();
        Logger.e("搜索话题");
        getParamet().setTitle(search);//设置搜索关键字
        paramet.setTeamId(teamId);
        addSubscription(apiStores.getSearchTopicList(paramet), new ApiCallback<MechanismTopicListModel>() {
            @Override
            public void onSuccess(MechanismTopicListModel model) {
                mActivity.dismissProgressDialog();
                mvpView.searchTopicDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mActivity.dismissProgressDialog();
                mvpView.searchTopicDataFail(code + "|" + msg);
            }

            @Override
            public void onFinish() {
                mActivity.dismissProgressDialog();
            }
        });
    }

    public MyPublishTopicList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(MyPublishTopicList_Paramet paramet) {
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
        String type = ConstantValue.SEARCH_TYPE_TOPIC;//搜索小组
        Query<Search_History> build = search_historyDao.queryBuilder().where(Search_HistoryDao.Properties.Type.eq(type)).build();
        return build.list();
    }
}
