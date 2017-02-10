package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.effects.NewsPaper;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.UploadIdcardInfor_Parmet;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.IDCardValidate;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * 修改信息界面---身份验证
 */
public class IndentityAuthenActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack, ivFront, ivOpposite;
    private TextView tvTitle;
    private Button btnAuditing;
    private EditText edRealName, edIdCardNum;
    private PersonalCentreResult resultData = new PersonalCentreResult();
    private int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private File frontFile, oppsiteFile;
    /**
     * type 1;front ; 2: oppsite
     */
    private int type;

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
        ivFront.setOnClickListener(this);
        ivOpposite.setOnClickListener(this);
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
            case R.id.iv_idCardFront:
                //上传正面照
                type = 1;
                openAlertDialog();
                break;
            case R.id.iv_idCardOpposite:
                //反面照
                type = 2;
                openAlertDialog();
                break;
            case R.id.btn_idCardAuditing:
                //判断空
                if (judgeDate()) {
                    //提交审核
                    ArrayMap<String, RequestBody> params = new ArrayMap<>();
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), SharePrefManager.getUserId().toString());
                    RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), edRealName.getText().toString());
                    RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), edIdCardNum.getText().toString());
                    RequestBody requestBody4 = RequestBody.create(MediaType.parse("multipart/form-data"), Compressor.getDefault(mActivity).compressToFile(frontFile));
                    RequestBody requestBody5 = RequestBody.create(MediaType.parse("multipart/form-data"), Compressor.getDefault(mActivity).compressToFile(oppsiteFile));
                    params.put("userId", requestBody1);
                    params.put("name", requestBody2);
                    params.put("idCard", requestBody3);
                    addSubscription(apiStores.getUploadInfor(params, requestBody4, requestBody5), new ApiCallback<RewardResult>() {
                        @Override
                        public void onSuccess(RewardResult model) {
                            if (model.isSuccess()) {
                                mActivity.finish();
                                ToastUtil.showToast("提交信息成功！");
                            } else {
                                ToastUtil.showToast("提交信息失败！");
                                LoggerUtil.e(model.getErrMsg().toString());
                            }
                        }

                        @Override
                        public void onFailure(int code, String msg) {

                        }

                        @Override
                        public void onFinish() {

                        }
                    });
                }

                break;
        }
    }

    private boolean judgeDate() {
        if (TextUtils.isEmpty(edRealName.getText())) {
            ToastUtil.showToast("请输入姓名!");
            return false;
        }
        //验证身份证
        try {
            String idCardValidate = IDCardValidate.IDCardValidate(edIdCardNum.getText().toString().trim());
            if(!TextUtils.isEmpty(idCardValidate))
            {
                ToastUtil.showToast(idCardValidate);
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (frontFile==null) {
            ToastUtil.showToast("请上传身份证正面照!");
            return false;
        }
        if (oppsiteFile==null) {
            ToastUtil.showToast("请上传身份证反面照!");
            return false;
        }
        return true;
    }

    /**
     * 打开相册
     */
    public void openAlertDialog() {

        Album.startAlbum(mActivity, ACTIVITY_REQUEST_SELECT_PHOTO
                , 1                                                         // 指定选择数量。
                , ContextCompat.getColor(mActivity, R.color.global)        // 指定Toolbar的颜色。
                , ContextCompat.getColor(mActivity, R.color.global));  // 指定状态栏的颜色。
    }

    /**
     * 得到选择的图片路径集合
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            List<String> imagesPath = null;
            if (imagesPath!=null){
                imagesPath.clear();
            }
            imagesPath =  Album.parseResult(data);
            if (imagesPath != null && imagesPath.size() > 0) {
                String path = imagesPath.get(0);
                ToastUtil.showToast(path);
                //压缩图片
                switch (type) {
                    case 1:
                        GlideUitl.loadImg(mActivity, path,240,100, ivFront);
                        frontFile = new File(path);
                        break;
                    case 2:
                        GlideUitl.loadImg(mActivity, path,240,100, ivOpposite);
                        oppsiteFile = new File(path);
                        break;
                }

            }
        }
    }
}
