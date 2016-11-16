package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.event.RefurshCommentListEvent;
import com.laichushu.book.mvp.bookdetail.ArticleCommentModle;
import com.laichushu.book.mvp.commentdetail.CommentDetailModle;
import com.laichushu.book.mvp.commentdetail.CommentDetailView;
import com.laichushu.book.ui.adapter.CommentDetaileAdapter;
import com.laichushu.book.ui.base.MvpActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.orhanobut.logger.Logger;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.mvp.commentdetail.CommentDetailPersenter;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private ArticleCommentModle.DataBean dataBean;
    private String type;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_commentdetail);
        EventBus.getDefault().register(this);
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
        dataBean = getIntent().getParcelableExtra("bean");
        type = getIntent().getStringExtra("type");
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
        likeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.isIsLike()) {
                    mvpPresenter.saveScoreLikeData(dataBean.getScoreId(),"1");
                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_normal, likeIv);
                    dataBean.setIsLike(false);
                    dataBean.setLikeNum(dataBean.getLikeNum()-1);
                    likeTv.setText(dataBean.getLikeNum() + "");
                } else {
                    mvpPresenter.saveScoreLikeData(dataBean.getScoreId(),"0");
                    GlideUitl.loadImg(mActivity, R.drawable.icon_like_red, likeIv);
                    dataBean.setIsLike(true);
                    dataBean.setLikeNum(dataBean.getLikeNum()+1);
                    likeTv.setText(dataBean.getLikeNum() + "");
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
    public void SaveScoreLikeData(RewardResult model, String type) {
        if (model.isSuccess()) {
            if (type.equals("0")){//点赞
                Logger.e("点赞");
            }else {//取消赞
                Logger.e("取消赞");
            }
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
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
                RefurshCommentListEvent event = new RefurshCommentListEvent(true);
                EventBus.getDefault().postSticky(event);
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefurshCommentListEvent event){
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            onRefresh();
            dataBean.setReplyNum(dataBean.getReplyNum()+1);
        }
    }
}
