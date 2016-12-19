package com.laichushu.book.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.bean.netbean.PublishTopic_Paramet;
import com.laichushu.book.event.RefreshHomePageEvent;
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
        tvTitle.setText("发表话题");
        tvRight.setText("发表");
        tvRight.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
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
                    PublishTopic_Paramet paramet = new PublishTopic_Paramet(SharePrefManager.getUserId(), edAddTitle.getText().toString(), edAddContent.getText().toString());
                    addSubscription(apiStores.publishTopic(paramet), new ApiCallback<RewardResult>() {
                        @Override
                        public void onSuccess(RewardResult result) {
                            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                            if (result.isSuccess()) {
                                ToastUtil.showToast("话题发表成功！");
                                mActivity.finish();
                            } else {
                                ToastUtil.showToast("发表话题失败！");
                                LoggerUtil.e(result.getErrMsg());
                            }
                        }

                        @Override
                        public void onFailure(int code, String msg) {
                            LoggerUtil.e(msg);
                            refreshPage(LoadingPager.PageState.STATE_ERROR);
                        }

                        @Override
                        public void onFinish() {
                        }

                    });
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
        EventBus.getDefault().postSticky(new RefreshHomePageEvent(true));
    }
}
