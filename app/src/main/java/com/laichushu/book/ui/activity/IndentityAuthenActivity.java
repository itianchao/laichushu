package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.UploadIdcardInfor_Parmet;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;


public class IndentityAuthenActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack, ivFront, ivOpposite;
    private TextView tvTitle;
    private Button btnAuditing;
    private EditText edRealName, edIdCardNum;
    private PersonalCentreResult resultData = new PersonalCentreResult();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_indentity_authen);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        btnAuditing = ((Button) inflate.findViewById(R.id.btn_idCardAuditing));
        ivFront = ((ImageView) inflate.findViewById(R.id.iv_idCardFront));
        ivOpposite = ((ImageView) inflate.findViewById(R.id.iv_idCardOpposite));
        edRealName = ((EditText) inflate.findViewById(R.id.ed_identityNameContent));
        edIdCardNum = ((EditText) inflate.findViewById(R.id.ed_idNumContent));
        return inflate;
    }


    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("身份认证");
        resultData = (PersonalCentreResult) getIntent().getSerializableExtra("idcard");
        ivBack.setOnClickListener(this);
        btnAuditing.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.iv_idCardFront:
                //上传正面照
                break;
            case R.id.iv_idCardOpposite:
                //反面照

                break;
            case R.id.btn_idCardAuditing:
                //提交审核
                UploadIdcardInfor_Parmet paramet = new UploadIdcardInfor_Parmet(SharePrefManager.getUserId(),
                        edIdCardNum.getText().toString(), edRealName.getText().toString());
                addSubscription(apiStores.getUploadInfor(paramet), new ApiCallback() {
                    @Override
                    public void onSuccess(Object model) {

                    }

                    @Override
                    public void onFailure(int code, String msg) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
                break;
        }
    }
}
