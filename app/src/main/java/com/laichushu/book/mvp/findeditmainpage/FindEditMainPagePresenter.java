package com.laichushu.book.mvp.findeditmainpage;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.AddPerMsgInfo_Paramet;
import com.laichushu.book.bean.netbean.AuthorWorks_Paramet;
import com.laichushu.book.bean.netbean.CollectSaveDate_Paramet;
import com.laichushu.book.bean.netbean.EditorSaveComment_Paramet;
import com.laichushu.book.bean.netbean.FindArticleByCaseId_Paramet;
import com.laichushu.book.bean.netbean.FindArticleVote_Paramet;
import com.laichushu.book.bean.netbean.FindEditorCommentList_Paramet;
import com.laichushu.book.bean.netbean.FindEditorInfoModel;
import com.laichushu.book.bean.netbean.FindEditorInfo_Paramet;
import com.laichushu.book.bean.netbean.TopicDyLike_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.mvp.topicdetail.TopicdetailModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.FindEditMainPageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by PCPC on 2016/12/24.
 */

public class FindEditMainPagePresenter extends BasePresenter<FindEditMainPageView> {
    private FindEditMainPageActivity mActivity;
    private String pageSize = ConstantValue.PAGESIZE1;
    private String pageNo = "1";
    private String userId = ConstantValue.USERID;

    //初始化构造
    public FindEditMainPagePresenter(FindEditMainPageView view) {
        attachView(view);
        mActivity = (FindEditMainPageActivity) view;
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

    /**
     * 获取作品列表
     */
    public void loadAuthorWorksData() {
        mvpView.showDialog();
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
                mvpView.getDataFail("code+" + code + "/msg:" + msg);
            }

            @Override
            public void onFinish() {
                mvpView.dismissDialog();
            }
        });
    }

    /**  //合作
     * @param id
     * @param cooperateId
     * @param remarks
     */

    public void loadArticleVoteData(String id, String cooperateId, String remarks) {
        mvpView.showDialog();
        FindArticleVote_Paramet vote_paramet = new FindArticleVote_Paramet(id, userId, cooperateId, ConstantValue.FIND_EDITORTYPE, remarks);
        Logger.e("合作");
        Logger.json(new Gson().toJson(vote_paramet));
        addSubscription(apiStores.getArticleVoteDetails(vote_paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getArticleVoteDataSuccess(model);
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
     * 打开合作对话框
     */
    public void openTeamworkDialog(final List<AuthorWorksModle.DataBean> workDate, final String cooperateId) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mActivity, R.style.DialogStyle);
        final AlertDialog alertDialog = dialogBuilder.create();
        View customerView = UIUtil.inflate(R.layout.item_pop_teamwork);
        final List<String> articleName = new ArrayList<>();
        articleName.clear();
        final String[] currentArticleId = {null};
        final TextView tvSelect = (TextView) customerView.findViewById(R.id.tv_bookList);
        final ListView lvItem = (ListView) customerView.findViewById(R.id.lv_item);
        final EditText edContent = (EditText) customerView.findViewById(R.id.ed_remarksContent);
        Button btnContract = (Button) customerView.findViewById(R.id.bt_sendContract);
        for (int i = 0; i < workDate.size(); i++) {
            articleName.add(workDate.get(i).getArticleName());
        }
        tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lvItem.getVisibility() == View.GONE) {
                    lvItem.setVisibility(View.VISIBLE);
                } else {
                    lvItem.setVisibility(View.GONE);
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, R.layout.spiner_item_layout, articleName);
        lvItem.setAdapter(adapter);
        customerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (lvItem.getVisibility() == View.VISIBLE) {
                    lvItem.setVisibility(View.GONE);
                }
                return false;
            }
        });
        lvItem.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO 获取当前选中的图书id
                currentArticleId[0] = workDate.get(position).getArticleId();
                tvSelect.setText(articleName.get(position));
                lvItem.setVisibility(View.GONE);
            }
        }));
        btnContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 请求合作   判断参数
                if (TextUtils.isEmpty(currentArticleId[0])) {
                    ToastUtil.showToast("请选择图书！");
                    return;
                }
                if (TextUtils.isEmpty(edContent.getText().toString().trim())) {
                    ToastUtil.showToast("请输入备注信息！");
                    return;
                }
                loadArticleVoteData(currentArticleId[0], cooperateId, edContent.getText().toString().trim());
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(customerView);
        alertDialog.show();
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
        FindEditorInfo_Paramet infoParamet = new FindEditorInfo_Paramet(userID,"");
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
    FindEditorCommentList_Paramet commentList_paramet = new FindEditorCommentList_Paramet(userId, "", pageSize, pageNo);

    public void loadEditorCommentListData(String editorId) {
        getCommentList_paramet().setEditorId(editorId);
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
    public void loadSendCommentData(String sourceId, String content, String starLevel) {
        mvpView.showDialog();
        EditorSaveComment_Paramet paramet = new EditorSaveComment_Paramet(userId,sourceId,"", content, starLevel);
        Logger.e("发送评论");
        Logger.json(new Gson().toJson(paramet));

        addSubscription(apiStores.saveEditorComment(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
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
     *
     * @param sourceId
     * @param type
     */
    public void saveScoreLikeData(String sourceId, final String type) {
        mvpView.showDialog();
        String sourceType = "1";
        TopicDyLike_Paramet paramet = new TopicDyLike_Paramet(userId, sourceId, sourceType, type);
        Logger.e("点赞");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.saveScoreLike(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.SaveScoreLikeData(model, type);
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

    public FindArticleByCaseId_Paramet getCaseIdParamet() {
        return caseIdParamet;
    }

    public void setCaseIdParamet(FindArticleByCaseId_Paramet caseIdParamet) {
        this.caseIdParamet = caseIdParamet;
    }

    //查询案列图书
    FindArticleByCaseId_Paramet caseIdParamet = new FindArticleByCaseId_Paramet(userId, "", ConstantValue.FIND_EDITORTYPE, pageNo, pageSize);

    public void loadFindArticleByCaseIdData(String sourceId) {
        getCaseIdParamet().setSourceId(sourceId);
        Logger.e("查询案列图书");
        Logger.json(new Gson().toJson(caseIdParamet));
        addSubscription(apiStores.getEditorFindArticleByCaseId(caseIdParamet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                mvpView.getEditorFindArticleDataSuccess(model);
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
    //收藏编辑
    public void loadCollectSaveDate(String sourceId, String sourceType, final String type) {
        CollectSaveDate_Paramet collectSave = new CollectSaveDate_Paramet(userId, sourceId, sourceType, type);
        mvpView.showDialog();
        LoggerUtil.toJson(collectSave);
        addSubscription(apiStores.collectSaveData(collectSave), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                mvpView.getSaveCollectSuccess(model, type);
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
