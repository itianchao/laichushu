package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.UpdatePersonalInfor_Parmet;
import com.laichushu.book.global.ConstantValue;
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
import com.pickerview.OptionsPopupWindow;
import com.pickerview.TimePopupWindow;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EditMyselfeInforActivity extends MvpActivity2 implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ImageView ivBack, ivHead;
    private TextView tvTitle, tvRight, tvIdCard, tvSex, edBirthday;
    private EditText edNickName, edCity, edSign;
    private PersonalCentreResult resultData = new PersonalCentreResult();
    //    private Pop_Syllabus_Date pop_PlayPartner_Date;
    private RelativeLayout rlIdCard, rlHead;
    private int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private File compressedImageFile;
    //时间选择器
    private TimePopupWindow timePopupWindow;
    private PopupWindow sexPopWindow;
    private Button submit, cancle;
    private RadioGroup rgSex;
    private RadioButton tvMan, tvFeman;
    private String sexMsg;

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
        edBirthday = ((TextView) inflate.findViewById(R.id.ed_birthdayContent));
        rlIdCard = ((RelativeLayout) inflate.findViewById(R.id.rl_idCard));
        rlHead = ((RelativeLayout) inflate.findViewById(R.id.rl_editHeadImg));

        //initListener;
        ivBack.setOnClickListener(this);
        rlHead.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvSex.setOnClickListener(this);
        edBirthday.setOnClickListener(this);
        rlIdCard.setOnClickListener(this);
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        initPersonInfor();
        tvTitle.setText("编辑个人信息");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        edBirthday.setInputType(InputType.TYPE_NULL);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
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
                        edNickName.getText().toString(), tvSex.getText().toString(), edCity.getText().toString(), edSign.getText().toString(), edBirthday.getText().toString());
                Logger.json(new Gson().toJson(paramet));
                addSubscription(apiStores.getUpdateDetails(paramet), new ApiCallback<RewardResult>() {
                    @Override
                    public void onSuccess(RewardResult result) {
                        if (result.isSuccess()) {
                            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                            ToastUtil.showToast("success");
                            mActivity.finish();
                            updateDate();
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
            case R.id.ed_birthdayContent:
//                showBirthdayPop();
                setTimePopupwindow();
                break;
            case R.id.rl_idCard:
                Bundle bundle = new Bundle();
                bundle.putSerializable("idcard", resultData);
                UIUtil.openActivity(this, IndentityAuthenActivity.class, bundle);
                break;
            case R.id.rl_editHeadImg:
                openAlertDialog();
                break;

            case R.id.sex_btnSubmit:
                sexPopWindow.dismiss();
                if (!TextUtils.isEmpty(sexMsg)) {
                    tvSex.setText(sexMsg);
                }

                break;
            case R.id.sex_btnCancel:
                sexPopWindow.dismiss();
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
            edNickName.setText(resultData.getNickName());
            if (TextUtils.isEmpty(resultData.getSex())) {
                tvSex.setText("男");
            } else {
                tvSex.setText(resultData.getSex());
            }
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
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }

    }

    /**
     * 判断是否录入为空
     */
    private boolean judgeAttrbute() {

        if (TextUtils.isEmpty(edNickName.getText())) {
            ToastUtil.showToast("请输入昵称!");
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

    /**
     * 设置性别
     */
    public void setPopWindow() {
        View sexView = UIUtil.inflate(R.layout.pop_sex);
        submit = (Button) sexView.findViewById(R.id.sex_btnSubmit);
        cancle = (Button) sexView.findViewById(R.id.sex_btnCancel);
        rgSex = (RadioGroup) sexView.findViewById(R.id.rg_sex);
        tvMan = (RadioButton) sexView.findViewById(R.id.sex_man);
        tvFeman = (RadioButton) sexView.findViewById(R.id.sex_femal);
        rgSex.setOnCheckedChangeListener(this);
        submit.setOnClickListener(this);
        cancle.setOnClickListener(this);
        sexPopWindow = new PopupWindow(sexView, WindowManager.LayoutParams.FILL_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        sexPopWindow.setFocusable(true);
        sexPopWindow.setOutsideTouchable(true);
        sexPopWindow.showAtLocation(tvSex, Gravity.BOTTOM, 0, 0);
        sexPopWindow.setAnimationStyle(R.style.timepopwindow_anim_style);
    }


    //选择日期
    private void setTimePopupwindow() {
        timePopupWindow = new TimePopupWindow(this, TimePopupWindow.Type.YEAR_MONTH_DAY);
        timePopupWindow.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                edBirthday.setText(DateUtil.getFormatDateTime(date));
            }
        });
        timePopupWindow.showAtLocation(edBirthday, Gravity.BOTTOM, 0, 0, null);
    }

    /**
     * 通知fragment更新数据
     */
    private void updateDate() {
        Intent intent = new Intent(ConstantValue.ACTION_UPDATE_DATA);
        intent.addCategory(mActivity.getPackageName());
        mActivity.sendBroadcast(intent);
    }

    private void showBirthdayPop() {
        // 选择日期
//        pop_PlayPartner_Date = new Pop_Syllabus_Date(
//                mActivity, findViewById(R.id.rl_head),
//                findViewById(R.id.rl_Birthday), "年--月--日", edBirthday);
    }

    /**
     * 选择模版 对话框
     */
    public void openAlertDialog() {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(mActivity);
        final View customerView = UIUtil.inflate(R.layout.dialog_photo);

        //从模版中选择
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //从相册中选择
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album.startAlbum(mActivity, ACTIVITY_REQUEST_SELECT_PHOTO
                        , 1                                                         // 指定选择数量。
                        , ContextCompat.getColor(mActivity, R.color.global)        // 指定Toolbar的颜色。
                        , ContextCompat.getColor(mActivity, R.color.global));  // 指定状态栏的颜色。
                dialogBuilder.dismiss();
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, mActivity)                // 添加自定义View
                .show();
    }

    /**
     * 得到选择的图片路径集合
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            List<String> imagesPath = Album.parseResult(data);
            if (imagesPath != null && imagesPath.size() > 0) {
                String path = imagesPath.get(0);
                GlideUitl.loadImg(mActivity, path, ivHead);
                //压缩图片
                compressedImageFile = new File(path);
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.sex_man:
                sexMsg = "男";
                break;
            case R.id.sex_femal:
                sexMsg = "女";
                break;
        }

    }
}
