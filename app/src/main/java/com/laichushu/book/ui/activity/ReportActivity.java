package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.Complaint_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

/**
 * 举报页面
 * Created by wangtong on 2016/12/14.
 */
public class ReportActivity extends MvpActivity2 implements View.OnClickListener {

    private EditText instructionEt;
    private String userId = ConstantValue.USERID;

    /**
     * @return 控制器
     */
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * @return 返回成功页面
     */
    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_report);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        TextView publishTv = (TextView) mSuccessView.findViewById(R.id.tv_title_right);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        RadioButton oneRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_1);
        RadioButton twoRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_2);
        RadioButton threeRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_3);
        RadioButton fourRbn = (RadioButton) mSuccessView.findViewById(R.id.rbn_4);
        instructionEt = (EditText) mSuccessView.findViewById(R.id.et_instruction);//说明
        titleTv.setText("举报");
        publishTv.setText("提交");
        publishTv.setVisibility(View.VISIBLE);
        publishTv.setOnClickListener(this);
        finishIv.setOnClickListener(this);
        oneRbn.setOnClickListener(this);
        twoRbn.setOnClickListener(this);
        threeRbn.setOnClickListener(this);
        fourRbn.setOnClickListener(this);
        return mSuccessView;
    }

    /**
     * 数据初始化
     */
    @Override
    protected void initData() {
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);//每次进入都是成功的
    }

    /**
     * 按钮点击事件
     *
     * @param v
     */
    int position = 1;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish://返回
                finish();
                break;
            case R.id.tv_title_right://提交
                String remarks = instructionEt.getText().toString();
                String articleId = getIntent().getStringExtra("articleId");
                publishReport(articleId, position + "", "无", "无", remarks);
                break;
            case R.id.rbn_1://政治原因
                position = 1;
                break;
            case R.id.rbn_2://色情低俗
                position = 2;
                break;
            case R.id.rbn_3://涉嫌抄袭
                position = 3;
                break;
            case R.id.rbn_4://其他
                position = 4;
                break;
        }
    }

    /**
     * 举报
     *
     * @param articleId 作者id
     * @param reason    原因id
     * @param index     位置
     * @param url       连接
     * @param remarks   备注
     */
    private void publishReport(String articleId, String reason, String index, String url, String remarks) {
        showProgressDialog();
        Complaint_Paramet paramet = new Complaint_Paramet(articleId, userId, reason, index, url, remarks);
        addSubscription(apiStores.complaint(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                dismissProgressDialog();
                if (model.isSuccess()) {
                    ToastUtil.showToast("举报成功");
                    UIUtil.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },1710);
                } else {
                    ToastUtil.showToast("举报失败");
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                ToastUtil.showToast("举报失败");
                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }
}
