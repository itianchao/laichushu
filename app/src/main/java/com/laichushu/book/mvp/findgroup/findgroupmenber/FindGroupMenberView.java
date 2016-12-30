package com.laichushu.book.mvp.findgroup.findgroupmenber;

import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.HomeFocusResult;

/**
 * 发现 - 小组 - 成员管理列表or成员列表or审核成员列表
 * Created by wangtong on 2016/12/30.
 */

public interface FindGroupMenberView {

    void getDataSuccess(FindGroupMenberModle modle);//获取成员列表

    void getDataFali(String msg);

    void showProgress();

    void hideProgress();

    void getApplyMemberSuccess(RewardResult model, int position, String result);//成员申请

    void getApplyMemberFail(String msg);

    void deleteGroupMemberSuccess(RewardResult model, int position);//删除成员

    void deleteGroupMemberFail(String msg);

    void getAddFollowDataSuccess(HomeFocusResult model, boolean isFocus, int position);//添加取消 /关注

    void getAddFollowDataFail(String msg, boolean isFocus);
}
