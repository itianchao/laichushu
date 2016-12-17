package com.laichushu.book.mvp.mechanismdetail;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.anim.ShakeAnim;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.AddPerMsgInfo_Paramet;
import com.laichushu.book.bean.netbean.ArticleVote_Paramet;
import com.laichushu.book.bean.netbean.AuthorWorks_Paramet;
import com.laichushu.book.bean.netbean.CollectSave_Paramet;
import com.laichushu.book.bean.netbean.SendMsgToParty_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.campaign.AuthorWorksModle;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.MechanismDetailActivity;
import com.laichushu.book.ui.adapter.JoinActivityAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 节 presenter
 * Created by wangtong on 2016/10/12.
 */
public class MechanismDetailPresenter extends BasePresenter<MechanismDetailView> {
    private MechanismDetailActivity mActivity;
    private String userId = ConstantValue.USERID;

    //初始化构造
    public MechanismDetailPresenter(MechanismDetailView view) {
        attachView(view);
        mActivity = (MechanismDetailActivity) view;
    }

    /**
     * 获取书籍目录
     */
    public void loadAuthorWorksData() {
        mvpView.showLoading();
        AuthorWorks_Paramet paramet = new AuthorWorks_Paramet(userId);
        Logger.e("获取作者作品结果");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getAuthorWorks(paramet), new ApiCallback<AuthorWorksModle>() {
            @Override
            public void onSuccess(AuthorWorksModle model) {
                mvpView.getAuthorWorksDataSuccess(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail2("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 投稿对话框
     *
     * @param articleId
     * @param pressId
     */
    public void openSelectBookDialog(final String articleId, final String pressId) {

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_ok);
        TextView msgTitleTv = (TextView) customerView.findViewById(R.id.tv_msg_title);
        msgTitleTv.setText("是否投稿");
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
//                voteBook(mArticleData.get(joinAdapter.getPosition()).getArticleId(), pressId);
                voteBook(articleId, pressId);
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
     * 投稿
     *
     * @param articleId
     * @param pressId
     */
    public void voteBook(String articleId, String pressId) {
        mvpView.showLoading();
        LoggerUtil.e("投稿");
        ArticleVote_Paramet paramet = new ArticleVote_Paramet(articleId, ConstantValue.USERID, pressId);
        addSubscription(apiStores.articleVote(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.articleVote(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail3("code:" + code + "\nmsg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 收藏
     */
    public void collectSave(String targetId, String type, final String collectType) {
        mvpView.showLoading();
        Logger.e("收藏");
        CollectSave_Paramet paramet = new CollectSave_Paramet(userId, targetId, type, collectType);
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.collectSave(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                boolean collect;
                if (collectType.equals("0")) {
                    collect = true;
                } else {
                    collect = false;
                }
                mvpView.collectSaveData(model, collect);
            }

            @Override
            public void onFailure(int code, String msg) {
                mvpView.getDataFail4("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 发消息
     *
     * @param content
     */
    public void sendMsgToParty(String id, String content) {
        mvpView.showLoading();
        Logger.e("发消息");
        AddPerMsgInfo_Paramet paramet = new AddPerMsgInfo_Paramet(content,id,userId);
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
                mvpView.hideLoading();
            }
        });
    }

    /**
     * 发消息对话框
     *
     * @param id
     */
    public void sendMsgToPartyDialog(final String id) {
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
                // TODO: 2016/11/25 投稿

                sendMsgToParty(userId, edMsg.getText().toString());
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
}
