package com.laichushu.book.mvp.find.group.detail;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ApplyJoinGroupMember_Paramet;
import com.laichushu.book.bean.netbean.DeleteGroupMember_Paramet;
import com.laichushu.book.bean.netbean.DismissGroup_Paramet;
import com.laichushu.book.bean.netbean.MyPublishTopicList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.find.mechanism.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindGroupDetailActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 小组详情
 * Created by PCPC on 2016/12/26.
 */

public class FindGroupDetailPresenter extends BasePresenter<FindGroupDetailView> {
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

    public FindGroupDetailPresenter(FindGroupDetailView view) {
        attachView(view);
        mActivity = (FindGroupDetailActivity) view;
        initData();
    }

    /**
     * 更多 设置数据
     */
    public void initData() {
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
     * 解散小组对话框
     */
    public void openDismissGroupDialog(final String teamId) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_ok);
        TextView msgTitleTv = (TextView) customerView.findViewById(R.id.tv_msg_title);
        msgTitleTv.setText("确认是否解散小组？");
        //取消
        Button cancelBtn = (Button) customerView.findViewById(R.id.btn_cancel);
        cancelBtn.setText("取消");
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissGroup(teamId);
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mActivity)                // 添加自定义View
                .show();
    }

    /**
     * 解散小组接口
     */
    public void dismissGroup(String teamId){
        mActivity.showProgressDialog();
        LoggerUtil.e("解散小组");
        DismissGroup_Paramet paramet = new DismissGroup_Paramet(teamId,userId);
        LoggerUtil.toJson(new Gson().toJson(paramet));
        addSubscription(apiStores.dismissGroup(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mActivity.dismissProgressDialog();
                mvpView.dismissGroupSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mActivity.dismissProgressDialog();
                mvpView.dismissGroupFail(code + "|" + msg);
            }

            @Override
            public void onFinish() {
                mActivity.dismissProgressDialog();
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

    /**
     * 申请加入小组 对话框
     */
    public void openJoinGroupDialog(final String teamId) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_ok);
        TextView msgTitleTv = (TextView) customerView.findViewById(R.id.tv_msg_title);
        msgTitleTv.setText("确认是否申请加入小组？");
        //取消
        Button cancelBtn = (Button) customerView.findViewById(R.id.btn_cancel);
        cancelBtn.setText("取消");
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinGroup(teamId);
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mActivity)                // 添加自定义View
                .show();
    }

    /**
     * 退出小组 对话框
     */
    public void openLeaveGroupDialog(final String memberId) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_ok);
        TextView msgTitleTv = (TextView) customerView.findViewById(R.id.tv_msg_title);
        msgTitleTv.setText("确认是否退出小组？");
        //取消
        Button cancelBtn = (Button) customerView.findViewById(R.id.btn_cancel);
        cancelBtn.setText("取消");
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveGroup(memberId);
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mActivity)                // 添加自定义View
                .show();
    }

    /**
     * 退出小组
     */
    public void leaveGroup(String memberId){
        mActivity.showProgressDialog();
        LoggerUtil.e("退出小组");
        DeleteGroupMember_Paramet paramet = new DeleteGroupMember_Paramet(memberId,userId);
        addSubscription(apiStores.deleteGroupMember(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult modle) {
                mActivity.dismissProgressDialog();
                mvpView.getLeaveGroupSuccess(modle);
            }

            @Override
            public void onFailure(int code, String msg) {
                mActivity.dismissProgressDialog();
                mvpView.getLeaveGroupFail(code+"|"+msg);
            }

            @Override
            public void onFinish() {
                mActivity.dismissProgressDialog();
            }
        });
    }

    /**
     * 申请加入小组
     */
    public void joinGroup(String teamId){
        mActivity.showProgressDialog();
        LoggerUtil.e("申请加入小组");
        ApplyJoinGroupMember_Paramet paramet = new ApplyJoinGroupMember_Paramet(userId,teamId);
        addSubscription(apiStores.applyJoinGroup(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult modle) {
                mActivity.dismissProgressDialog();
                mvpView.getJoinGroupSuccess(modle);
            }

            @Override
            public void onFailure(int code, String msg) {
                mActivity.dismissProgressDialog();
                mvpView.getJoinGroupFail(code+"|"+msg);
            }

            @Override
            public void onFinish() {
                mActivity.dismissProgressDialog();
            }
        });
    }
}
