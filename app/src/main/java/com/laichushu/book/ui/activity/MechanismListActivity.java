package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.netbean.MechanismList_Paramet;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.UIUtil;

/**
 * 机构列表
 * Created by wt on 2016/11/24.
 */

public class MechanismListActivity extends MvpActivity2 implements View.OnClickListener {
    //1、读者组 2、专业社 3、教育社 4、大众社
    private String type = "2";
    private String pageNo = "1";
    private String pageSize = ConstantValue.PAGESIZE1;

    private MechanismList_Paramet paramet = new MechanismList_Paramet(type, pageSize, pageNo);

    private ApiCallback<MechanismListBean> callback = new ApiCallback<MechanismListBean>() {
        @Override
        public void onSuccess(MechanismListBean model) {
            getDataSuccess(model);
        }


        @Override
        public void onFailure(int code, String msg) {
            getFailure("code" + code + "msg:" + msg);
        }

        @Override
        public void onFinish() {

        }
    };

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_mechanismlist);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        ImageView searchIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        finishIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
        GlideUitl.loadImg(this, R.drawable.search_icon, searchIv);
        titleTv.setText("机构");
        return mSuccessView;
    }

    @Override
    protected void initData() {
        paramet.setType("2");
        loadMechanismListData();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.iv_title_other:
                // TODO: 2016/11/24  搜索
                break;
        }

    }

    /**
     * 请求获取机构列表
     */
    public void loadMechanismListData() {
        LoggerUtil.e("获取机构列表");

        addSubscription(apiStores.getMechanismList(paramet), callback);
    }

    private void getDataSuccess(MechanismListBean model) {
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        }else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
        }
    }

    private void getFailure(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        LoggerUtil.e(msg);
    }
}
