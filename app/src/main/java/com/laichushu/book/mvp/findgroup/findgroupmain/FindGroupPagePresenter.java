package com.laichushu.book.mvp.findgroup.findgroupmain;

import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.activity.FindGroupDetailActivity;
import com.laichushu.book.ui.base.BasePresenter;

import java.util.ArrayList;

/**
 * 小组详情
 * Created by PCPC on 2016/12/26.
 */

public class FindGroupPagePresenter extends BasePresenter<FindGroupPageView> {
    private FindGroupDetailActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;
    private ArrayList<String> leaderList = new ArrayList<>();
    private ArrayList<String> memberList = new ArrayList<>();
    //初始化构造
    public FindGroupPagePresenter(FindGroupPageView view) {
        attachView(view);
        mActivity = (FindGroupDetailActivity) view;
        leaderList.clear();
        leaderList.add("待处理申请");
        leaderList.add("成员管理");
        leaderList.add("修改资料");
        leaderList.add("分享");
        leaderList.add("解散");
        memberList.clear();
        memberList.add("分享");
        if (mActivity.isJoin()){
            memberList.add("退出小组");
        }else {
            memberList.add("加入小组");
        }
    }

    public ArrayList<String> getLeaderList() {
        return leaderList;
    }

    public ArrayList<String> getMemberList() {
        return memberList;
    }
}
