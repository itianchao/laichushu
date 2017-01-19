package com.laichushu.book.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FeedBack_parmet;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

public class FeedbackActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle;
    private EditText edContent, edAccount;
    private Button btnSubmit;
private String conHint,accHint;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_feedback);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        edContent = ((EditText) inflate.findViewById(R.id.ed_adviceContent));
        edAccount = ((EditText) inflate.findViewById(R.id.ed_adviceAccount));
        btnSubmit = ((Button) inflate.findViewById(R.id.btn_adviceSubmit));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("意见反馈");
        tvTitle.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        edContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    conHint=edContent.getHint().toString();
                    edContent.setHint(null);
                }else{
                    if(TextUtils.isEmpty(edContent.getText().toString()))
                    edContent.setHint(conHint);
                }
            }
        }); edAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    accHint=edAccount.getHint().toString();
                    edAccount.setHint(null);
                }else{
                    if(TextUtils.isEmpty(edContent.getText()))
                        if(TextUtils.isEmpty(edAccount.getText().toString()))
                            edAccount.setHint(accHint);
                }
            }
        });

        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 30);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.btn_adviceSubmit:
                if (judgeDate()) {
                    submitDate();
                }
                break;
        }
    }

    private void submitDate() {
        FeedBack_parmet paramet = new FeedBack_parmet(SharePrefManager.getUserId(), edContent.getText().toString(), "1", edAccount.getText().toString());
        addSubscription(apiStores.feedBackDetails(paramet), new ApiCallback<RewardResult>() {

            @Override
            public void onSuccess(RewardResult result) {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                if (result.isSuccess()) {
                    ToastUtil.showToast("意见反馈成功！");
                    mActivity.finish();
                } else {
                    ToastUtil.showToast("意见反馈失败！");
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

    private boolean judgeDate() {
        if (TextUtils.isEmpty(edContent.getText())) {
            ToastUtil.showToast("请输入您的意见或者建议!");
            return false;
        }
        if (TextUtils.isEmpty(edAccount.getText())) {
            ToastUtil.showToast("请输入邮箱/微信!");
            return false;
        }
        return true;
    }
}
