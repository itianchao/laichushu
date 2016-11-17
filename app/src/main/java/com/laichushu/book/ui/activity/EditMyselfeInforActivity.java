package com.laichushu.book.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.UpdatePersonalInfor_Parmet;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.ui.widget.TypePopWindow;
import com.laichushu.book.utils.DateUtil;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;


public class EditMyselfeInforActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack, ivHead;
    private TextView tvTitle, tvRight, tvIdCard, tvSex;
    private EditText edNickName, edCity, edSign, edBirthday;
    private PersonalCentreResult resultData;
    private boolean flg = false;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {

        View inflate = UIUtil.inflate(R.layout.activity_edit_myselfe_infor);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        ivHead = ((ImageView) inflate.findViewById(R.id.iv_editHead));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvRight = ((TextView) inflate.findViewById(R.id.tv_title_right));
        tvIdCard = ((TextView) inflate.findViewById(R.id.tv_idCardContent));
        edNickName = ((EditText) inflate.findViewById(R.id.ed_nickNameContent));
        tvSex = ((TextView) inflate.findViewById(R.id.ed_sexContent));
        edCity = ((EditText) inflate.findViewById(R.id.ed_areaContent));
        edSign = ((EditText) inflate.findViewById(R.id.ed_signatureContent));
        edBirthday = ((EditText) inflate.findViewById(R.id.ed_birthdayContent));
        //initListener;
        ivBack.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvSex.setOnClickListener(this);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        initPersonInfor();
        tvTitle.setText("编辑个人信息");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.tv_title_right:
                if (!judgeAttrbute()) {
                    return;
                }
                UpdatePersonalInfor_Parmet paramet = new UpdatePersonalInfor_Parmet(SharePrefManager.getUserId().toString(),
                        edNickName.getText().toString(), tvSex.getText().toString(), edCity.getText().toString(), edSign.getText().toString(), DateUtil.getDate(edBirthday.getText().toString()));
                Logger.json(new Gson().toJson(paramet));
                addSubscription(apiStores.getUpdateDetails(paramet), new ApiCallback<RewardResult>() {
                    @Override
                    public void onSuccess(RewardResult result) {
                        if (result.isSuccess()) {
                            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                            ToastUtil.showToast("success");
                            mActivity.finish();
                        } else {
                            ToastUtil.showToast(result.getErrMsg());
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

                break;
            case R.id.ed_sexContent:
                setPopWindow();
                break;
        }

    }

    /**
     * 初始化数据
     */
    private void initPersonInfor() {
        resultData = (PersonalCentreResult) getIntent().getSerializableExtra("result");
        if (resultData != null) {
            GlideUitl.loadRandImg(mActivity, resultData.getPhoto(), ivHead);
            edNickName.setText(resultData.getName());
            tvSex.setText(resultData.getSex());
            if (!TextUtils.isEmpty(resultData.getBirthday())) {
                edBirthday.setText(resultData.getBirthday().toString());
            }
            if (!TextUtils.isEmpty(resultData.getCity())) {
                edCity.setText(resultData.getCity());
            }
            if (!TextUtils.isEmpty(resultData.getAtteStatus())) {
                switch (Integer.parseInt(resultData.getAtteStatus())) {
                    case 1:
                        tvIdCard.setText("未认证");
                        break;
                    case 2:
                        tvIdCard.setText("认证中");
                        break;
                    case 3:
                        tvIdCard.setText("认证失败");
                        break;
                    case 4:
                        tvIdCard.setText("认证通过");
                        break;
                }
            }

            edSign.setText(resultData.getSign());
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        } else {
            ToastUtil.showToast("获取信息失败！");
        }

    }

    /**
     * 判断是否录入为空
     */
    private boolean judgeAttrbute() {

        if (TextUtils.isEmpty(edNickName.getText())) {
            ToastUtil.showToast("请输入昵称!");
            ;
            return false;
        }
        if (TextUtils.isEmpty(tvSex.getText())) {
            ToastUtil.showToast("强选择输入性别!");
            return false;
        }
        if (TextUtils.isEmpty(edCity.getText())) {
            ToastUtil.showToast("请输入您所在城市!");
            return false;
        }
        if (TextUtils.isEmpty(edBirthday.getText())) {
            ToastUtil.showToast("请输入出生日期!");
            return false;
        }
        if (TextUtils.isEmpty(edNickName.getText())) {
            ToastUtil.showToast("请输入个性签名!");
            return false;
        }
        return true;
    }

    public void setPopWindow() {
        List rankingList = new ArrayList();
        rankingList.clear();
        rankingList.add("男");
        rankingList.add("女");
        TypePopWindow popWindow = new TypePopWindow(mActivity, rankingList);
        popWindow.setListItemClickListener(new TypePopWindow.IListItemClickListener() {
            @Override
            public void clickItem(int position) {

            }
        });
        popWindow.setWidth(tvSex.getWidth());
        popWindow.setHeight((tvSex.getHeight()) * 2);
        popWindow.showAsDropDown(tvSex);
    }
}
