package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.MechanismListBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.campaign.AuthorWorksModle;
import com.laichushu.book.mvp.mechanismdetail.MechanisDetailModel;
import com.laichushu.book.mvp.mechanismdetail.MechanismDetailPresenter;
import com.laichushu.book.mvp.mechanismdetail.MechanismDetailView;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 机构详情页
 * Created by wangtong on 2016/11/25.
 */

public class MechanismDetailActivity extends MvpActivity2<MechanismDetailPresenter> implements MechanismDetailView, View.OnClickListener {

    private MechanismListBean.DataBean bean;
    private ImageView mechanismIv;
    private ImageView shareIv;
    private ImageView moreIv;
    private ImageView finishIv;
    private TextView titleTv;
    private TextView msgTv;
    private TextView submissionTv;
    private TextView collectionTv;
    private FrameLayout spaceFay;
    private ArrayList<AuthorWorksModle.DataBean> data = new ArrayList<>();
    private TextView mechanismTv;
    private TextView collectionCountTv;

    @Override
    protected MechanismDetailPresenter createPresenter() {
        return new MechanismDetailPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_mechanismdetail);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        moreIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_other);
        shareIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_another);
        mechanismIv = (ImageView) mSuccessView.findViewById(R.id.iv_mechanism);//机构图片

        msgTv = (TextView) mSuccessView.findViewById(R.id.tv_msg);
        submissionTv = (TextView) mSuccessView.findViewById(R.id.tv_submission);
        collectionTv = (TextView) mSuccessView.findViewById(R.id.tv_collection);
        spaceFay = (FrameLayout) mSuccessView.findViewById(R.id.fay_space);
        mechanismTv = (TextView) mSuccessView.findViewById(R.id.tv_mechanism);//机构name
        collectionCountTv = (TextView) mSuccessView.findViewById(R.id.tv_collection_count);//收藏数

        finishIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);
        msgTv.setOnClickListener(this);
        submissionTv.setOnClickListener(this);
        collectionTv.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        bean = getIntent().getParcelableExtra("bean");
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        titleTv.setText("机构详情");//设置标题
        GlideUitl.loadImg(mActivity,R.drawable.mechanism_detail_bg,mechanismIv);//设置机构图片
        mechanismTv.setText(bean.getName());
    }

    @Override
    public void getDataSuccess(MechanisDetailModel model) {

    }

    @Override
    public void getAuthorWorksDataSuccess(AuthorWorksModle model) {
        hideLoading();
        if (model.isSuccess()) {
            data.clear();
            if (model.getData()!=null){
                data = model.getData();
                if (!data.isEmpty()) {
                    mvpPresenter.openSelectBookDialog(data, bean.getId());
                }else {
                    ToastUtil.showToast("您还没有作品");
                }
            }
        }else {
            ToastUtil.showToast("获取作品列表失败");
        }
    }

    @Override
    public void articleVote(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("投稿成功，15日内通知审核结果");
        }else {
            ToastUtil.showToast("投稿失败");
        }
    }

    @Override
    public void getDataFail(String msg) {
        hideLoading();
        LoggerUtil.e(msg);
    }

    @Override
    public void getDataFail2(String msg) {
        hideLoading();
        ToastUtil.showToast("获取作品列表失败");
        LoggerUtil.e(msg);
    }

    @Override
    public void getDataFail3(String msg) {
        hideLoading();
        ToastUtil.showToast("投稿失败");
        LoggerUtil.e(msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_title_other:
                break;
            case R.id.iv_title_another:
                break;
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.tv_msg:

                break;
            case R.id.tv_submission:
                mvpPresenter.loadAuthorWorksData();
                break;
            case R.id.tv_collection:

                break;
        }
    }
}
