package com.laichushu.book.mvp.find.coursera.mine;

import android.text.TextUtils;

import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MineCourseList_Paramet;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.fragment.FindClassMineFragment;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;

/**
 * 发现 - 课程 - 我的 Presenter
 * Created by wangtong on 2017/1/6.
 */

public class FindClassMinePresenter extends BasePresenter<FindClassMineView> {

    private FindClassMineFragment mFragment;
    private String pageNo = "1";
    private java.lang.String pageSize = ConstantValue.PAGESIZE1;
    private String userId = ConstantValue.USERID;

    private MineCourseList_Paramet paramet = new MineCourseList_Paramet(userId, "", pageNo, pageSize);

    public MineCourseList_Paramet getParamet() {
        return paramet;
    }

    public void setParamet(MineCourseList_Paramet paramet) {
        this.paramet = paramet;
    }

    public FindClassMinePresenter(FindClassMineView view) {
        attachView(view);
        mFragment = (FindClassMineFragment) view;
    }

    /**
     * 获取我的列表
     */
    public void loadVideoList(String operateType, boolean isRefrush) {
        if (getParamet().getPageNo().equals("1") && !isRefrush) {
            mFragment.refreshPage(LoadingPager.PageState.STATE_LOADING);
        }
        LoggerUtil.e("发现 - 课程 - 我的列表");
        getParamet().setOperateType(operateType);
        addSubscription(apiStores.getMineCourseList(paramet), new ApiCallback<CourseraModle>() {
            @Override
            public void onSuccess(CourseraModle modle) {
                mvpView.getMineListDataSuccess(modle);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getMineListDataFail("code:" + code + " msg:" + msg);
            }

            @Override
            public void onFinish() {
            }
        });
    }
}
