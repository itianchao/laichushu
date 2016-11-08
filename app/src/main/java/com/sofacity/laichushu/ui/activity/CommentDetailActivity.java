package com.sofacity.laichushu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.mvp.bookdetail.ArticleCommentModle;
import com.sofacity.laichushu.mvp.commentdetail.CommentDetailModle;
import com.sofacity.laichushu.mvp.commentdetail.CommentDetailPersenter;
import com.sofacity.laichushu.mvp.commentdetail.CommentDetailView;
import com.sofacity.laichushu.ui.adapter.CommentDetaileAdapter;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.GlideUitl;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 评论详情
 * Created by wangtong on 2016/11/4.
 */
public class CommentDetailActivity extends MvpActivity<CommentDetailPersenter> implements CommentDetailView, PullLoadMoreRecyclerView.PullLoadMoreListener, View.OnClickListener {
    private String pageNo = "1";
    private ImageView headIv;
    private TextView nameTv;
    private TextView contentTv;
    private TextView timeTv;
    private ImageView likeIv;
    private TextView likeTv;
    private TextView numberTv;
    private ImageView inIv;
    private ArrayList<CommentDetailModle.DataBean> mData = new ArrayList<>();
    private CommentDetaileAdapter mAdapter;
    private PullLoadMoreRecyclerView commentRyv;
    private String commentId;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_commentdetail);
        initTitleBar("详情");
        commentRyv = (PullLoadMoreRecyclerView) findViewById(R.id.ryv_comment);
        commentRyv.setLinearLayout();
        commentRyv.setOnPullLoadMoreListener(this);
        headIv = (ImageView) findViewById(R.id.iv_comment_head);
        nameTv = (TextView) findViewById(R.id.tv_comment_name);
        contentTv = (TextView) findViewById(R.id.tv_comment_content);
        timeTv = (TextView) findViewById(R.id.tv_comment_time);
        likeIv = (ImageView) findViewById(R.id.iv_comment_like);
        likeTv = (TextView) findViewById(R.id.tv_comment_like);
        numberTv = (TextView) findViewById(R.id.tv_comment_number);
        inIv = (ImageView) findViewById(R.id.iv_comment_in);
        mAdapter = new CommentDetaileAdapter(this, mData);
        commentRyv.setAdapter(mAdapter);
    }

    /**
     * 初始化标题
     *
     * @param title 标题名称
     */
    private void initTitleBar(String title) {
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) findViewById(R.id.iv_title_finish);
        titleTv.setText(title);
        finishIv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        final ArticleCommentModle.DataBean dataBean = getIntent().getParcelableExtra("bean");
        commentId = dataBean.getScoreId();
        onRefresh();
        GlideUitl.loadRandImg(this, dataBean.getPhoto(), headIv);//头像
        nameTv.setText(dataBean.getNickName());//用户名
        contentTv.setText(dataBean.getContent());//评论内容
        timeTv.setText(dataBean.getCreateDate());//创建时间
        likeTv.setText(dataBean.getLikeNum() + "");//喜欢人数
        numberTv.setText(dataBean.getReplyNum() + "");//回复人数
        numberTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("commentId", commentId);
                UIUtil.openActivity(CommentDetailActivity.this,CommentSendActivity.class,bundle);
            }
        });
        inIv.setVisibility(View.INVISIBLE);
        if (dataBean.isIsLike()) {
            GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
        } else {
            GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
        }
        likeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.isIsLike()) {
                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
                    dataBean.setIsLike(false);
                } else {
                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
                    dataBean.setIsLike(true);
                }
            }
        });
    }

    @Override
    public void getDataSuccess(CommentDetailModle model) {
        hideLoading();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                commentRyv.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (model.getData().size() != 0) {
                pageNo = Integer.parseInt(pageNo) + 1 + "";
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
            }
        } else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtil.showToast("请检查网络");
        commentRyv.setPullLoadMoreCompleted();
        Logger.e(msg);
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
    protected CommentDetailPersenter createPresenter() {
        return new CommentDetailPersenter(this);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = "1";
        mData.clear();
        mvpPresenter.getParamet().setPageNo(pageNo);
        mvpPresenter.loadCommentData(commentId);
    }

    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(pageNo);
        mvpPresenter.loadCommentData(commentId);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        onRefresh();
    }
}
