package com.laichushu.book.mvp.findeditmypage;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.AddPerMsgInfo_Paramet;
import com.laichushu.book.bean.netbean.FindEditorCommentList_Paramet;
import com.laichushu.book.bean.netbean.FindEditorInfoModel;
import com.laichushu.book.bean.netbean.FindEditorInfo_Paramet;
import com.laichushu.book.bean.netbean.SaveComment_Paramet;
import com.laichushu.book.bean.netbean.TopicDyLike_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.allcomment.SendCommentMoudle;
import com.laichushu.book.mvp.topicdetail.TopicdetailModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindEditMyPageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;


/**
 * Created by PCPC on 2016/12/24.
 */

public class FindEditMyPagePresenter extends BasePresenter<FindEditMyPageView> {
    private FindEditMyPageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public FindEditMyPagePresenter(FindEditMyPageView view) {
        attachView(view);
        mActivity = (FindEditMyPageActivity) view;
    }
    //显示私信

    /**
     * 发送私信对话框
     */
    public void openSendPerMsgDialog(final String userID) {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_send_per_msg);
        final EditText edMsg = (EditText) customerView.findViewById(R.id.et_dialogMsg);

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
                // TODO: 请求私信接口
                sendMsgToParty(userID, edMsg.getText().toString().trim());
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle("发送私信")                                  // 为null时不显示title
                .withDialogColor("#94C3B7")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mActivity)                // 添加自定义View
                .show();

    }

    public void openTeamworkDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity, R.style.DialogStyle);
        final AlertDialog alertDialog = dialogBuilder.create();
        View customerView = UIUtil.inflate(R.layout.item_pop_teamwork);
        Spinner spBook = (Spinner) customerView.findViewById(R.id.sp_bookList);
        EditText edContent = (EditText) customerView.findViewById(R.id.ed_remarksContent);
        Button btnContract = (Button) customerView.findViewById(R.id.bt_sendContract);


        alertDialog.setView(customerView);
        alertDialog.show();
        WindowManager m = mActivity.getWindowManager();
        Display display = m.getDefaultDisplay();  //为获取屏幕宽、高
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        alertDialog.getWindow().setLayout(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
        alertDialog.getWindow().setWindowAnimations(R.style.periodpopwindow_anim_style);
        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }
    }

    /**
     * 发消息
     *
     * @param content
     */
    public void sendMsgToParty(String id, String content) {
        mvpView.showDialog();
        Logger.e("发消息");
        AddPerMsgInfo_Paramet paramet = new AddPerMsgInfo_Paramet(content, id, userId);
        addSubscription(apiStores.getAddPerMsgInfDetails(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getSendMsgToPartyDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail5("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }


    //获取编辑主页用户头像信息
    public void loadEditorInfoData(String userID) {
        FindEditorInfo_Paramet infoParamet = new FindEditorInfo_Paramet(userID);
        mvpView.showDialog();
        Logger.e("用户信息");
        Logger.json(new Gson().toJson(infoParamet));
        addSubscription(apiStores.getEditorInfoDatails(infoParamet), new ApiCallback<FindEditorInfoModel>() {
            @Override
            public void onSuccess(FindEditorInfoModel model) {
                mvpView.getEditorInfoDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }

    public FindEditorCommentList_Paramet getCommentList_paramet() {
        return commentList_paramet;
    }

    public void setCommentList_paramet(FindEditorCommentList_Paramet commentList_paramet) {
        this.commentList_paramet = commentList_paramet;
    }

    //编辑-查询所有评论
    FindEditorCommentList_Paramet commentList_paramet = new FindEditorCommentList_Paramet(userId,"",pageSize,pageNo);
    public void loadEditorCommentListData(String editorId) {
       getCommentList_paramet().setEditorId(editorId);
        mvpView.showDialog();
        Logger.e("所有评论");
        Logger.json(new Gson().toJson(commentList_paramet));
        addSubscription(apiStores.getEditorCommentList(commentList_paramet), new ApiCallback<TopicdetailModel>() {
            @Override
            public void onSuccess(TopicdetailModel model) {
                mvpView.getEditorCommentListDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
//发表评论
    public void loadSendCommentData(String sourceId,String content,String starLevel){
        mvpView.showDialog();
        SaveComment_Paramet paramet = new SaveComment_Paramet(sourceId,userId,content,starLevel);
        Logger.e("发送评论");
        Logger.json(new Gson().toJson(paramet));

        addSubscription(apiStores.saveComment(paramet), new ApiCallback<SendCommentMoudle>() {
            @Override
            public void onSuccess(SendCommentMoudle model) {
                mvpView.getSendDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
    /**
     * 点赞 取消赞
     * @param sourceId
     * @param type
     */
    public void saveScoreLikeData(String sourceId, final String type){
        mvpView.showDialog();
        String sourceType = "1";
        TopicDyLike_Paramet paramet = new TopicDyLike_Paramet(userId,sourceId,sourceType,type);
        Logger.e("点赞");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.saveScoreLike(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.SaveScoreLikeData(model,type);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }
}
