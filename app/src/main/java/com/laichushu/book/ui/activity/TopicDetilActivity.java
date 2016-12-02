package com.laichushu.book.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.HomeUseDyrResult;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.mechanismtopiclist.MechanismTopicListModel;
import com.laichushu.book.mvp.topicdetail.TopicDetailPresenter;
import com.laichushu.book.mvp.topicdetail.TopicDetailView;
import com.laichushu.book.mvp.topicdetail.TopicdetailModel;
import com.laichushu.book.ui.adapter.TopicCommentDetaileAdapter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.laichushu.book.utils.Validator;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 话题详情
 * Created by wangtong on 2016/11/26.
 */

public class TopicDetilActivity extends MvpActivity2<TopicDetailPresenter> implements TopicDetailView, View.OnClickListener, PullLoadMoreRecyclerView.PullLoadMoreListener {

    private MechanismTopicListModel.DataBean bean;
    private ImageView topicUserheadIv;
    private TextView topicAuthorTv;
    private TextView topicContentTv;
    private TextView topicTiemTv;
    private TextView topicNameTv;
    private TextView titleTv;
    private ImageView finishIv;
    private PullLoadMoreRecyclerView commentRyv;
    private int pageNo = 1;
    private TopicCommentDetaileAdapter mAdapter;
    private ArrayList<CommentDetailModle.DataBean> mData = new ArrayList<>();
    private String topicId;
    private EditText sendmsgEv;
    private ImageView sendmsgIv;
    private String type;
    private HomeUseDyrResult.DataBean homeBean;

    @Override
    protected TopicDetailPresenter createPresenter() {
        return new TopicDetailPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_topicdetail);
        titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        topicUserheadIv = (ImageView) mSuccessView.findViewById(R.id.iv_topic_userhead);
        topicAuthorTv = (TextView) mSuccessView.findViewById(R.id.tv_topic_author);
        topicContentTv = (TextView) mSuccessView.findViewById(R.id.tv_topic_content);
        topicTiemTv = (TextView) mSuccessView.findViewById(R.id.tv_topic_time);
        topicNameTv = (TextView) mSuccessView.findViewById(R.id.tv_topic_name);
        commentRyv = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_comment);
        sendmsgEv = (EditText) mSuccessView.findViewById(R.id.et_sendmsg);
        sendmsgIv = (ImageView) mSuccessView.findViewById(R.id.iv_sendmsg);
        commentRyv.setLinearLayout();
        commentRyv.setOnPullLoadMoreListener(this);
        commentRyv.setFooterViewText("加载中");
        mAdapter = new TopicCommentDetaileAdapter(this, mData,mvpPresenter);
        commentRyv.setAdapter(mAdapter);
        finishIv.setOnClickListener(this);
        sendmsgIv.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        titleTv.setText("话题详情");
        type = getIntent().getStringExtra("type");
        if (type.equals("homepage")) {
            homeBean = (HomeUseDyrResult.DataBean) getIntent().getSerializableExtra("topBean");
            topicId = homeBean.getId();
        } else {
            bean = getIntent().getParcelableExtra("bean");
            topicId = bean.getId();
        }
        mvpPresenter.loadCommentData(topicId);
    }

    @Override
    public void getDataSuccess(TopicdetailModel model) {

        commentRyv.setPullLoadMoreCompleted();
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (model.getData().size() != 0) {
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
                pageNo++;
                if (type.equals("homepage")) {
                    topicNameTv.setText(homeBean.getTitle());
                    topicAuthorTv.setText(homeBean.getCreatUserName());
                    topicContentTv.setText(homeBean.getContent());
                    topicTiemTv.setText(homeBean.getCreateDate());
                    GlideUitl.loadRandImg(mActivity, homeBean.getCreaterPhoto(), topicUserheadIv);
                    refreshPage(LoadingPager.PageState.STATE_SUCCESS);
                } else {
                    topicNameTv.setText(bean.getTitle());
                    topicAuthorTv.setText(bean.getCreatUserName());
                    topicContentTv.setText(bean.getContent());
                    topicTiemTv.setText(bean.getCreateDate());
                    GlideUitl.loadRandImg(mActivity, bean.getCreaterPhoto(), topicUserheadIv);
                }

            }
        } else {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            reLoadDate();
        }
    }

    /**
     * 点赞
     * @param model
     * @param type
     */
    @Override
    public void SaveScoreLikeData(RewardResult model, String type) {
        if (model.isSuccess()) {
            if (type.equals("0")) {
                ToastUtil.showToast("点赞成功！");
            } else {
                ToastUtil.showToast("取消点赞成功！");
            }

        } else {
            ToastUtil.showToast("操作失败！");
            LoggerUtil.toJson(model);
        }
    }

    @Override
    public void getSendDataSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
            sendmsgEv.setText("");
            onRefresh();
        } else {
            ToastUtil.showToast("发送失败");
        }
    }

    @Override
    public void getDataFail(String msg) {
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        reLoadDate();
    }

    @Override
    public void getDataFail2(String msg) {
        hideLoading();
        ToastUtil.showToast("发送失败");
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
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
            case R.id.iv_sendmsg:
                if (type.equals("homepage")) {
                    mvpPresenter.topicDetailCommentSave(sendmsgEv, homeBean.getId(), ConstantValue.COMMENTTOPIC_TYPE);
                } else {
                    mvpPresenter.topicDetailCommentSave(sendmsgEv, bean.getId(), ConstantValue.COMMENTTOPIC_TYPE);
                }

                break;
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = 1;
        mData.clear();
        mvpPresenter.getParamet().setPageNo(pageNo + "");
        mvpPresenter.loadCommentData(topicId);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(pageNo + "");
        mvpPresenter.loadCommentData(topicId);
    }

    public void reLoadDate() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                mvpPresenter.loadCommentData(topicId);
            }
        });
    }
}
