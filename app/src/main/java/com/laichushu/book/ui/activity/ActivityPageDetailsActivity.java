package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.MessageCommentResult;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

public class ActivityPageDetailsActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle, tvName, tvStartTime, tvEndTime, tvNum, tvActContent;
    private MessageCommentResult.DataBean dataBeen;
    private Button btnGoAct;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_page_details);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvName = ((TextView) inflate.findViewById(R.id.tv_activity_name));
        tvStartTime = ((TextView) inflate.findViewById(R.id.tv_activity_start));
        tvEndTime = ((TextView) inflate.findViewById(R.id.tv_activity_end));
        tvNum = ((TextView) inflate.findViewById(R.id.tv_activity_num));
        tvActContent = ((TextView) inflate.findViewById(R.id.tv_activityContent));
        btnGoAct = ((Button) inflate.findViewById(R.id.btn_goActivity));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        dataBeen = (MessageCommentResult.DataBean) getIntent().getSerializableExtra("activityDetails");

        tvTitle.setText("消息详情");
        tvTitle.setVisibility(View.VISIBLE);

        ivBack.setOnClickListener(this);
        if (dataBeen != null) {
            tvName.setText(dataBeen.getSenderName());
            tvStartTime.setText(dataBeen.getSendTime());
            tvEndTime.setText(dataBeen.getSendTime());
            tvActContent.setText(dataBeen.getContent());
        } else {
            ToastUtil.showToast("error");
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.btn_goActivity:
                //参加活动

                break;
        }
    }

}
