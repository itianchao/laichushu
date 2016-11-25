package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.GradeDetails_Paramet;
import com.laichushu.book.bean.netbean.GradeRemarksResult;
import com.laichushu.book.bean.netbean.HomeUserResult;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;

public class HomePageGradeDetailsActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle, tvRemarks;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_home_page_grade_details);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_middleLeft));
        tvRemarks = ((TextView) inflate.findViewById(R.id.tv_remarks));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("等级分类");
        ivBack.setOnClickListener(this);
        getRemarks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
        }
    }

    public void getRemarks() {
//        GradeDetails_Paramet paramet = new GradeDetails_Paramet(SharePrefManager.getUserId());
        GradeDetails_Paramet paramet = new GradeDetails_Paramet("105");

        addSubscription(apiStores.gradeDetails(paramet), new ApiCallback<GradeRemarksResult>() {
            @Override
            public void onSuccess(GradeRemarksResult result) {
                if (result.isSuccess()) {
                    refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                    tvRemarks.setText(result.getRemarks());
                } else {
                    refreshPage(LoadingPager.PageState.STATE_ERROR);
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                refreshPage(LoadingPager.PageState.STATE_ERROR);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
