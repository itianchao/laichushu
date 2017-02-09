package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

/**
 * 协议界面
 */
public class AgreementDetailsActivity extends MvpActivity2 implements View.OnClickListener {

    private TextView titleTv;
    private ImageView finishIv;
    private TextView agreementContentTv;
    private TextView agreementTitleTv;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_agreement_details);
        titleTv = (TextView) inflate.findViewById(R.id.tv_title);
        agreementContentTv = (TextView) inflate.findViewById(R.id.tv_agreement_content);
        agreementTitleTv = (TextView) inflate.findViewById(R.id.tv_agreement_title);
        finishIv = (ImageView) inflate.findViewById(R.id.iv_title_finish);
        titleTv.setText("注册协议");
        return inflate;
    }

    @Override
    protected void initData() {
        String type = getIntent().getStringExtra("type");
        if (type.equals("1")){
            agreementContentTv.setText(UIUtil.getString(R.string.agreementcontent2));
            agreementTitleTv.setText("著作权保护声明");
            titleTv.setText("著作权保护声明");
        }
        finishIv.setOnClickListener(this);
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
        }
    }
}
