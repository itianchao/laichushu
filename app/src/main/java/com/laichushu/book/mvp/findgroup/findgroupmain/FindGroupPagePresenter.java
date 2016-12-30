package com.laichushu.book.mvp.findgroup.findgroupmain;

import com.google.gson.Gson;
import com.laichushu.book.bean.netbean.MyPublishTopicList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindGroupDetailActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;

import java.util.ArrayList;

/**
 * 小组详情
 * Created by PCPC on 2016/12/26.
 */

public class FindGroupPagePresenter extends BasePresenter<FindGroupPageView> {
    private FindGroupDetailActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo1 = "1";
    private String pageNo2 = "1";
    private String userId = ConstantValue.USERID;
    private String type = ConstantValue.SEARCH_TYPE_GROUP;
    private String teamId = ConstantValue.STRING_NULL;
    private ArrayList<String> leaderList = new ArrayList<>();
    private ArrayList<String> memberList = new ArrayList<>();
    private MyPublishTopicList_Paramet paramet1 = new MyPublishTopicList_Paramet(userId, type, pageNo1, pageSize, teamId);
    private MyPublishTopicList_Paramet paramet2 = new MyPublishTopicList_Paramet(userId, type, pageNo2, pageSize, teamId);

    /**
     * //初始化构造
     *
     * @param view
     */

    public FindGroupPagePresenter(FindGroupPageView view) {
        attachView(view);
        mActivity = (FindGroupDetailActivity) view;
        initData();
    }

    /**
     * 更多 设置数据
     */
    private void initData() {
        leaderList.clear();
        leaderList.add("待处理申请");
        leaderList.add("成员管理");
        leaderList.add("修改资料");
        leaderList.add("分享");
        leaderList.add("解散");
        memberList.clear();
        memberList.add("分享");
        if (mActivity.isJoin()) {
            memberList.add("退出小组");
        } else {
            memberList.add("加入小组");
        }
    }

    /**
     * @return 组长的列表
     */
    public ArrayList<String> getLeaderList() {
        return leaderList;
    }

    /**
     * @return 成员的列表
     */
    public ArrayList<String> getMemberList() {
        return memberList;
    }

    /**
     * 获取小组话题
     */
    public void getGroupTopicList(String teamId) {
        mActivity.briefRbn.setEnabled(false);
        mActivity.recommendRbn.setEnabled(false);
        LoggerUtil.e("获取小组话题");
        getParamet1().setTeamId(teamId);
        LoggerUtil.toJson(new Gson().toJson(getParamet1()));
        addSubscription(apiStores.getGroupTopicList(paramet1), new ApiCallback<MechanismTopicListModel>() {
            @Override
            public void onSuccess(MechanismTopicListModel model) {
                mvpView.getGroupTopicListDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getGroupTopicListDataFail(code + "|" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 获取推荐话题
     */
    public void getGroupSuggestTopicList(String teamId) {
        mActivity.briefRbn.setEnabled(false);
        mActivity.findRbn.setEnabled(false);
        getParamet2().setTeamId(teamId);
        LoggerUtil.e("获取小组话题");
        LoggerUtil.toJson(new Gson().toJson(getParamet2()));
        addSubscription(apiStores.getGroupSuggestTopicList(paramet2), new ApiCallback<MechanismTopicListModel>() {
            @Override
            public void onSuccess(MechanismTopicListModel model) {
                mvpView.getGroupSuggestTopicListSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getGroupSuggestTopicListFail(code + "|" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * @return 小组话题
     */
    public MyPublishTopicList_Paramet getParamet1() {
        return paramet1;
    }

    /**
     * @return 小组推荐话题
     */
    public MyPublishTopicList_Paramet getParamet2() {
        return paramet2;
    }
}
