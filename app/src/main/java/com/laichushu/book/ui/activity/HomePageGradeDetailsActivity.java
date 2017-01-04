package com.laichushu.book.ui.activity;

import android.graphics.drawable.Drawable;
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
    private TextView tvTitle, tvRemarks, total, flg;
    private String userID;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_home_page_grade_details);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRemarks = ((TextView) inflate.findViewById(R.id.tv_remarks));
        total = ((TextView) inflate.findViewById(R.id.tv_gradeTotal));
        flg = ((TextView) inflate.findViewById(R.id.tv_gradeFlg));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        userID=getIntent().getStringExtra("userID");
        tvTitle.setText("等级分类");
        tvTitle.setVisibility(View.VISIBLE);
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
        GradeDetails_Paramet paramet = new GradeDetails_Paramet(userID);
        addSubscription(apiStores.gradeDetails(paramet), new ApiCallback<GradeRemarksResult>() {
            @Override
            public void onSuccess(GradeRemarksResult result) {
                if (result.isSuccess()) {
                    refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                    total.setText(result.getGrade() + "分，为");
                    tvRemarks.setText(result.getRemarks());
                    Drawable drawable = null;
                    switch (result.getType()) {
                        case 1:
                            //"金牌";
                            drawable = mActivity.getResources().getDrawable(R.drawable.icon_gold_medal2x);
                            break;
                        case 2:
                            // "银牌";
                            drawable = mActivity.getResources().getDrawable(R.drawable.icon_silver_medal2x);
                            break;
                        case 3:
                            //"铜牌";
                            drawable = mActivity.getResources().getDrawable(R.drawable.icon_copper_medal2x);
                            break;
                    }
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    flg.setCompoundDrawables(drawable, null, null, null);
                    flg.setText(result.getTypeName());
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
