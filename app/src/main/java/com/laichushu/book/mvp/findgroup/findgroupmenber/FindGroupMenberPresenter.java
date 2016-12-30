package com.laichushu.book.mvp.findgroup.findgroupmenber;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ChangeFocusState_Paramet;
import com.laichushu.book.bean.netbean.DeleteGroupMember_Paramet;
import com.laichushu.book.bean.netbean.GroupApplyMemberList_Paramet;
import com.laichushu.book.bean.netbean.GroupApplyMemberResult_Paramet;
import com.laichushu.book.bean.netbean.HomeFocusResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindGroupMemberListActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.UIUtil;

/**
 * 发现 - 小组 - 成员管理列表or成员列表or审核成员列表
 * Created by wangtong on 2016/12/30.
 */

public class FindGroupMenberPresenter extends BasePresenter<FindGroupMenberView> {

    private FindGroupMemberListActivity mActivity;
    private String userId = ConstantValue.USERID;

    public FindGroupMenberPresenter(FindGroupMenberView view) {
        attachView(view);
        this.mActivity = (FindGroupMemberListActivity) view;
    }

    /**
     * 获取小组成员列表
     */
    public void getGroupMemberList(String teamId) {
        GroupApplyMemberList_Paramet paramet = new GroupApplyMemberList_Paramet(teamId, userId);
        addSubscription(apiStores.getGroupMemberList(paramet), new ApiCallback<FindGroupMenberModle>() {
            @Override
            public void onSuccess(FindGroupMenberModle model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFali(code + "|" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 获取待审核小组成员列表
     */
    public void getGroupApplyMemberList(String teamId) {
        GroupApplyMemberList_Paramet paramet = new GroupApplyMemberList_Paramet(teamId, userId);
        addSubscription(apiStores.getGroupApplyMemberList(paramet), new ApiCallback<FindGroupMenberModle>() {
            @Override
            public void onSuccess(FindGroupMenberModle model) {
                mvpView.getDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFali(code + "|" + msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 审核成员对话框
     *
     * @param position
     * @param dataBean
     */
    public void showApplyMemberDialog(final int position, FindGroupMenberModle.DataBean dataBean) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_ok);
        TextView msgTitleTv = (TextView) customerView.findViewById(R.id.tv_msg_title);
        msgTitleTv.setText("确认是否加入小组？");
        //拒绝
        Button cancelBtn = (Button) customerView.findViewById(R.id.btn_cancel);
        cancelBtn.setText("拒绝");
        final String teamId =  dataBean.getTeamId();
        final String memberId =  dataBean.getId();
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGroupApplyMemberResult(teamId,memberId,"0",position);
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/11/25 投稿
                getGroupApplyMemberResult(teamId,memberId,"1",position);
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
     * 删除成员对话框
     * @param position
     */
    public void showDeleteMemberDialog(final int position, final FindGroupMenberModle.DataBean dataBean) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_ok);
        TextView msgTitleTv = (TextView) customerView.findViewById(R.id.tv_msg_title);
        msgTitleTv.setText("确认是否删除成员？");
        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/11/25 投稿
                String memberId =  dataBean.getId();
                deleteGroupMember(memberId,position);
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
     * 处理申请成员 同意or拒绝
     */
    public void getGroupApplyMemberResult(String teamId, String memberId, final String result, final int position) {
        mvpView.hideProgress();
        GroupApplyMemberResult_Paramet paramet = new GroupApplyMemberResult_Paramet(teamId,userId,memberId,result);
        addSubscription(apiStores.getGroupApplyMemberResult(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.hideProgress();
                mvpView.getApplyMemberSuccess(model,position,result);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.hideProgress();
                mvpView.getApplyMemberFail(code+"|"+msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideProgress();
            }
        });
    }

    /**
     * 删除成员 or 退出小组
     * @param memberId
     * @param position
     */
    public void deleteGroupMember(String memberId, final int position) {
        mvpView.showProgress();
        DeleteGroupMember_Paramet paramet = new DeleteGroupMember_Paramet(memberId);
        addSubscription(apiStores.deleteGroupMember(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.hideProgress();
                mvpView.deleteGroupMemberSuccess(model,position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.deleteGroupMemberFail(code+"|"+msg);
            mvpView.hideProgress();
        }

            @Override
            public void onFinish() {
                mvpView.hideProgress();
            }
        });
    }

    /**
     * 添加关注
     * @param tagId
     * @param isFocus
     */
    public void loadAddFocus(String tagId,final boolean isFocus ,final int position){

        ChangeFocusState_Paramet paramet = new ChangeFocusState_Paramet(userId,tagId);
        addSubscription(apiStores.getAddFocus(paramet), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getAddFollowDataSuccess(model,isFocus, position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getAddFollowDataFail("code+" + code + "/msg:" + msg,isFocus);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 取消关注
     * @param tagId
     * @param isFocus
     */

    public void loadDelFocus(String tagId,final boolean isFocus ,final int position){
        ChangeFocusState_Paramet paramet = new ChangeFocusState_Paramet(userId,tagId);
        addSubscription(apiStores.getDelFocus(paramet), new ApiCallback<HomeFocusResult>() {
            @Override
            public void onSuccess(HomeFocusResult model) {
                mvpView.getAddFollowDataSuccess(model,isFocus,position);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getAddFollowDataFail("code+" + code + "/msg:" + msg,isFocus);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}