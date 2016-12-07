package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.UIUtil;

public class AgreementDetailsActivity extends MvpActivity2 implements View.OnClickListener {

    private TextView titleTv;
    private ImageView finishIv;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_agreement_details);
        titleTv = (TextView) inflate.findViewById(R.id.tv_title);
        finishIv = (ImageView) inflate.findViewById(R.id.iv_title_finish);
        titleTv.setText("注册协议");
        return inflate;
    }

    @Override
    protected void initData() {
        finishIv.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
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
