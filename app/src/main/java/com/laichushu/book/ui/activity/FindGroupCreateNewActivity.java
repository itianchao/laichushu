package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.ArticleSave_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.album.Album;

import java.io.File;
import java.util.List;
import java.util.UUID;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

import static com.laichushu.book.global.ConstantValue.ACTIVITY_REQUEST_SELECT_PHOTO;

/**
 * 发现 - 小组主页 - 创建小组
 * Created by wangtong on 2016/12/27.
 */

public class FindGroupCreateNewActivity extends MvpActivity2 implements View.OnClickListener {

    private TextView titleTv;
    private EditText briefEt;
    private EditText nameEt;
    private ImageView headIv;
    private String path;
    private File file;
    private TextView completeTv;
    private String userId = ConstantValue.USERID;
    private String status = ConstantValue.GROUP_START;
    private String code = UUID.randomUUID()+"";

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_findgroupcreatenew);
        ImageView finishIv = (ImageView)mSuccessView.findViewById(R.id.iv_title_finish);
        headIv = (ImageView)mSuccessView.findViewById(R.id.iv_select_group_head);
        titleTv = (TextView)mSuccessView.findViewById(R.id.tv_title);
        completeTv = (TextView)mSuccessView.findViewById(R.id.tv_title_right);
        briefEt = (EditText)mSuccessView.findViewById(R.id.et_brief);
        nameEt = (EditText)mSuccessView.findViewById(R.id.et_groupname);

        completeTv.setVisibility(View.VISIBLE);
        GlideUitl.loadRandImg(this,"", headIv);
        completeTv.setOnClickListener(this);
        finishIv.setOnClickListener(this);
        headIv.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        titleTv.setText("创建小组");
        completeTv.setText("完成");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_select_group_head:
                path = "";
                file = null;
                Album.startAlbum(mActivity, ACTIVITY_REQUEST_SELECT_PHOTO
                        , 1                                                         // 指定选择数量。
                        , ContextCompat.getColor(mActivity, R.color.global)        // 指定Toolbar的颜色。
                        , ContextCompat.getColor(mActivity, R.color.global));  // 指定状态栏的颜色。
                break;
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.tv_title_right:
                String brief = briefEt.getText().toString().trim();
                String name = nameEt.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    ToastUtil.showToast("请添加小组名称");
                    return;
                }
                if (TextUtils.isEmpty(brief)){
                    ToastUtil.showToast("请添加小组简介");
                    return;
                }
                if (file == null){
                    ToastUtil.showToast("请添加小组头像");
                    return;
                }
                createNewGroup(name,brief);
                break;
        }
    }

    /**
     * 创建小组
     * @param name
     * @param brief
     */
    private void createNewGroup(String name, String brief) {
        Logger.e("创建小组");
        showProgressDialog();

        ArrayMap<String, RequestBody> params = new ArrayMap<>();

        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody requestBody3 = RequestBody.create(MediaType.parse("multipart/form-data"), userId);
        RequestBody requestBody4 = RequestBody.create(MediaType.parse("multipart/form-data"), status);
        RequestBody requestBody5 = RequestBody.create(MediaType.parse("multipart/form-data"), code);
        RequestBody requestBody6 = RequestBody.create(MediaType.parse("multipart/form-data"), brief);

        params.put("name", requestBody2);
        params.put("leaderId", requestBody3);
        params.put("status", requestBody4);
        params.put("subCategoryId", requestBody5);
        params.put("remarks", requestBody6);

        Observable<RewardResult> newGroup;
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), Compressor.getDefault(mActivity).compressToFile(file));
        newGroup = apiStores.createNewGroup(requestBody1, params);

        addSubscription(newGroup, new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                dismissProgressDialog();
                if (model.isSuccess()) {
                    ToastUtil.showToast("创建成功");
                    UIUtil.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },1700);
                }else {
                    ToastUtil.showToast("创建失败");
                }

            }

            @Override
            public void onFailure(int code, String msg) {
                dismissProgressDialog();
                ToastUtil.showToast("创建失败");
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
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
                path = imagesPath.get(0);
                GlideUitl.loadImg(mActivity, path, headIv);
                //压缩图片
                file = new File(path);
            }
        }
    }
}
