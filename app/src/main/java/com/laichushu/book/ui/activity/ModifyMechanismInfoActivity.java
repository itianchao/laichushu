package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.event.RefrushMechanismListEvent;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.yanzhenjie.album.Album;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 修改机构资料
 */
public class ModifyMechanismInfoActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView finishIv;
    private TextView titleTv, tvRight;
    private RelativeLayout rlUpdate;
    private EditText edMechName, edMechaAddress, edModifyIntro;
    private String logoUrl, name, address, profiles;
    private MechanismListBean.DataBean bean;
    private String partyId;
    private File photoFile;
    private int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                finish();
            }
        }
    };

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_modify_mechanism_info);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        tvRight = ((TextView) mSuccessView.findViewById(R.id.tv_title_right));
        edMechName = ((EditText) mSuccessView.findViewById(R.id.ed_mechanismName));
        edMechaAddress = ((EditText) mSuccessView.findViewById(R.id.ed_mechanismAddress));
        edModifyIntro = ((EditText) mSuccessView.findViewById(R.id.ed_modifyIntroduce));
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        rlUpdate = (RelativeLayout) mSuccessView.findViewById(R.id.rl_updateHeadImg);

        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        titleTv.setText("修改机构资料");
        tvRight.setText("保存");
        tvRight.setVisibility(View.VISIBLE);
        bean = getIntent().getParcelableExtra("bean");
        partyId = getIntent().getStringExtra("partyId");
        initModifyDate();

        tvRight.setOnClickListener(this);
        finishIv.setOnClickListener(this);
        rlUpdate.setOnClickListener(this);
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
    }

    /**
     * 初始化信息
     */
    private void initModifyDate() {
        if (null != bean) {

            if (!TextUtils.isEmpty(bean.getLogoUrl())) {
                logoUrl = bean.getLogoUrl();
            }
            if (!TextUtils.isEmpty(bean.getName())) {
                name = bean.getName();
                edMechName.setText(bean.getName());
            }
            if (!TextUtils.isEmpty(bean.getAddress())) {
                address = bean.getAddress();
                edMechaAddress.setText(bean.getAddress());
            }
            if (!TextUtils.isEmpty(bean.getIntroduce())) {
                profiles = bean.getIntroduce();
                edModifyIntro.setText(bean.getIntroduce());
            }

        } else {
            ToastUtil.showToast("获取数据失败！");
        }
    }

    /**
     * 判断当前提交数据是否为空
     *
     * @return
     */
    public boolean judgeDate() {
        //logo

        if (TextUtils.isEmpty(edMechName.getText().toString())) {
            ToastUtil.showToast("请输入机构名称！");
            return false;
        }
        if (TextUtils.isEmpty(edMechaAddress.getText().toString())) {
            ToastUtil.showToast("请输入机构地址！");
            return false;
        }
        if (TextUtils.isEmpty(edModifyIntro.getText().toString())) {
            ToastUtil.showToast("请输入小组简介！");
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.rl_updateHeadImg:
                openAlertDialog();
                break;
            case R.id.tv_title_right:
                //保存机构资料
                if (judgeDate()) {
                    ArrayMap<String, RequestBody> params = new ArrayMap<>();
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), partyId);
                    RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), edMechName.getText().toString().trim());
                    RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), edMechaAddress.getText().toString().trim());
                    RequestBody requestBody4 = RequestBody.create(MediaType.parse("multipart/form-data"), edModifyIntro.getText().toString().trim());
                    RequestBody requestBody6 = RequestBody.create(MediaType.parse("multipart/form-data"),logoUrl);
                    RequestBody requestBody5 = null;
                    if (photoFile != null) {
                        requestBody5 = RequestBody.create(MediaType.parse("multipart/form-data"), Compressor.getDefault(mActivity).compressToFile(photoFile));
                    }
                    params.put("id", requestBody1);
                    params.put("name", requestBody2);
                    params.put("address", requestBody3);
                    params.put("remarks", requestBody4);
                    params.put("logoUrl", requestBody6);
                    saveModifyMechanisDate(params, requestBody5);
                }
                break;
        }
    }

    /**
     * 保存机构资料---修改不传id
     */
    public void saveModifyMechanisDate(ArrayMap<String, RequestBody> params, RequestBody logoUrl) {
        showProgressDialog();
        addSubscription(apiStores.updateTopicDetails(params, logoUrl), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                if (model.isSuccess()) {
                    ToastUtil.showToast("修改信息成功!");
                    handler.sendEmptyMessageDelayed(1, 1700);
                } else {
                    ToastUtil.showToast("修改信息失败!");
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                LoggerUtil.e(msg);
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
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
            List<String> imagesPath = Album.parseResult(data);
            if (imagesPath != null && imagesPath.size() > 0) {
                String path = imagesPath.get(0);
                //压缩图片
                photoFile = new File(path);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        EventBus.getDefault().postSticky(new RefrushMechanismListEvent(true));
    }
}
