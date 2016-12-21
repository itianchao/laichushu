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
 * 修改机构资料
 */
public class ModifyMechanismInfoActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView finishIv;
    private TextView titleTv, tvRight;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_modify_mechanism_info);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        tvRight = ((TextView) mSuccessView.findViewById(R.id.tv_title_right));
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);

        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        titleTv.setText("修改机构资料");
        tvRight.setText("保存");
        tvRight.setVisibility(View.VISIBLE);

        tvRight.setOnClickListener(this);
        finishIv.setOnClickListener(this);

        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.tv_title_right:
                //保存

                break;
        }
    }
    //保存机构资料---修改不传id

//    partyDto.setId(partyView.getId());
//    partyDto.setName(partyView.getName());
//    partyDto.setAddress(partyView.getAddress());
//    partyDto.setLogoUrl(partyView.getLogoUrl());
//    partyDto.setRemarks(partyView.getRemarks());

    public void saveModifyMechanisDate(){

    }
}
