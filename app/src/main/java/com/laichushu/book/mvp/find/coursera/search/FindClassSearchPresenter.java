package com.laichushu.book.mvp.find.coursera.search;

import com.laichushu.book.bean.netbean.LessonList_Paramet;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.db.Search_History;
import com.laichushu.book.db.Search_HistoryDao;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindCourseSearchActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

import java.util.List;

import de.greenrobot.dao.query.Query;

/**
 * 发现 - 课程 - 搜索 Presenter
 * Created by wangtong on 2017/1/6.
 */

public class FindClassSearchPresenter extends BasePresenter<FindClassSearchView> {
    private FindCourseSearchActivity mActivity;

    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE1;
    private Search_HistoryDao search_historyDao;
    private String lessonType = ConstantValue.VIDEO_PLAY_ON_DEMAND;
    private LessonList_Paramet paramet = new LessonList_Paramet(lessonType, "", pageNo, pageSize);

    public FindClassSearchPresenter(FindClassSearchView view) {
        attachView(view);
        mActivity = (FindCourseSearchActivity) view;
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
    public List<Search_History> getHistoryList(String type) {
        Query<Search_History> build = search_historyDao.queryBuilder().where(Search_HistoryDao.Properties.Type.eq(type)).build();
        return build.list();
    }

    /**
     * 搜索
     *
     * @param search
     */
    public void loadSearchResultData(String search, String lessonType) {
        LoggerUtil.e("发现 - 课程 - 视频列表");
        paramet.setLessonType(lessonType);
        paramet.setLessonName(search);
        addSubscription(apiStores.getLessonList(paramet), new ApiCallback<CourseraModle>() {
            @Override
            public void onSuccess(CourseraModle modle) {
                mvpView.getVideoListDataSuccess(modle);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getVideoListDataFail("code:" + code + " msg:" + msg);
            }

            @Override
            public void onFinish() {
            }
        });
    }

    public LessonList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(LessonList_Paramet paramet) {
        this.paramet = paramet;
    }
}
