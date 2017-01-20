package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.NoticesSave_Paramet;
import com.laichushu.book.bean.netbean.PublishTopic_Paramet;
import com.laichushu.book.bean.netbean.SaveServeItem_paramet;
import com.laichushu.book.event.RefrushAunnManagerEvent;
import com.laichushu.book.event.RefrushHomePageEvent;
import com.laichushu.book.event.RefrushMineBeServiceEvent;
import com.laichushu.book.event.RefrushTopicManageEvent;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

public class HomePublishTopicActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle, tvRight;
    private EditText edAddTitle, edAddContent;
    private String type = null, partyId;

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_home_publisha_topic);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRight = ((TextView) inflate.findViewById(R.id.tv_title_right));
        edAddTitle = ((EditText) inflate.findViewById(R.id.ed_addTitle));
        edAddContent = ((EditText) inflate.findViewById(R.id.ed_addContent));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        type = getIntent().getStringExtra("type");
        partyId = getIntent().getStringExtra("partyId");
        if (null != type) {
            switch (type) {
                case "partyManage":
                    tvTitle.setText("发表新公告");
                    tvRight.setText("发表");
                    break;
                case "topicManage":
                    tvTitle.setText("发表新话题");
                    tvRight.setText("发表");
                    break;
                case "service":
                    tvTitle.setText("添加服务");
                    tvRight.setText("保存");
                    break;
                case "2":
                    tvTitle.setText("发表新话题");
                    tvRight.setText("发表");
                    break;
            }
        } else {
            tvTitle.setText("发表新话题");
            tvRight.setText("发表");
        }
        tvRight.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 30);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.tv_title_right:
                //提交话题
                if (judgeContent()) {
                    tvRight.setClickable(false);
                    if (null != type) {
                        switch (type) {
                            case "partyManage":
                                sendPartyMsg();
                                break;
                            case "topicManage":
                                sendTopicMsg(partyId, "3");
                                break;
                            case "service":
                                addServiceMsg();
                                break;
                            case "2"://小组话题
                                sendTopicMsg(partyId, type);
                                break;
                        }
                    } else {
                        sendTopicMsg("", "");
                    }
                }
                break;
        }
    }

    /**
     * 判断发表字段是否为空
     *
     * @return
     */
    private boolean judgeContent() {
        if (TextUtils.isEmpty(edAddTitle.getText())) {
            ToastUtil.showToast("请添加标题！");
            return false;
        }
        if (TextUtils.isEmpty(edAddContent.getText())) {
            ToastUtil.showToast("请添加话题内容！");
            return false;
        }
        return true;
    }

    @Override
    public void finish() {
        super.finish();
        if (null != type) {

            switch (type) {
                case "partyManage":
                    EventBus.getDefault().postSticky(new RefrushAunnManagerEvent(true));
                    break;
                case "topicManage":
                    EventBus.getDefault().postSticky(new RefrushTopicManageEvent(true));
                    break;
                case "service":
                    EventBus.getDefault().postSticky(new RefrushMineBeServiceEvent(true));
                    break;
            }

        } else {
            EventBus.getDefault().postSticky(new RefrushHomePageEvent(true));
        }

    }

    /**
     * 发送话题
     */
    public void sendTopicMsg(String partyId, final String type) {
        showProgressDialog();
        PublishTopic_Paramet paramet = new PublishTopic_Paramet(SharePrefManager.getUserId(), partyId, type, edAddTitle.getText().toString(), edAddContent.getText().toString());
        addSubscription(apiStores.publishTopic(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult result) {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                if (result.isSuccess()) {
                    ToastUtil.showToast("话题发表成功！");
                    UIUtil.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (type.equals("2")) {
                                Intent intent = new Intent();
                                intent.putExtra("back", "updata");
                                setResult(2, intent);
                            }
                            finish();
                        }
                    }, 1700);
                } else {
                    ToastUtil.showToast("发表话题失败！");
                    LoggerUtil.e(result.getErrMsg());
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                LoggerUtil.e(msg);
                refreshPage(LoadingPager.PageState.STATE_ERROR);
                ToastUtil.showToast(R.string.errMsg_data_exception);
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }

        });
    }

    /**
     * 发布新公告
     */
    public void sendPartyMsg() {
        showProgressDialog();
        NoticesSave_Paramet saveParamet = new NoticesSave_Paramet(SharePrefManager.getUserId(), partyId, edAddTitle.getText().toString(), edAddContent.getText().toString());
        addSubscription(apiStores.getNoticesSaveList(saveParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult result) {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                if (result.isSuccess()) {
                    ToastUtil.showToast("公告发表成功！");
                    UIUtil.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1700);
                } else {
                    ToastUtil.showToast("公告发表失败！");
                    LoggerUtil.e(result.getErrMsg());
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                LoggerUtil.e(msg);
                refreshPage(LoadingPager.PageState.STATE_ERROR);
                ToastUtil.showToast(R.string.errMsg_data_exception);
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }

        });
    }

    //我的服务--添加服务
    public void addServiceMsg() {
        showProgressDialog();
        SaveServeItem_paramet saveServiceParamet = new SaveServeItem_paramet(SharePrefManager.getUserId(), edAddTitle.getText().toString(), edAddContent.getText().toString());
        addSubscription(apiStores.getSaveServeItem(saveServiceParamet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult result) {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                if (result.isSuccess()) {
                    ToastUtil.showToast("保存服务成功！");
                    UIUtil.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1700);
                } else {
                    ToastUtil.showToast("保存服务失败！");
                    LoggerUtil.e(result.getErrMsg());
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                LoggerUtil.e(msg);
                refreshPage(LoadingPager.PageState.STATE_ERROR);
                ToastUtil.showToast(R.string.errMsg_data_exception);
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }

        });
    }
}
