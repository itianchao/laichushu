package com.laichushu.book.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.laichushu.book.event.RefurshBookCommentListEvent;
import com.laichushu.book.mvp.home.allcomment.AllCommentPresenter;
import com.laichushu.book.mvp.home.allcomment.AllCommentView;
import com.laichushu.book.mvp.home.allcomment.SendCommentMoudle;
import com.laichushu.book.mvp.home.bookdetail.ArticleCommentModle;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.ui.adapter.CommentAllAdapter;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * 全部评论
 * Created by wangtong on 2016/10/27.
 */
public class AllCommentActivity extends MvpActivity2<AllCommentPresenter> implements View.OnClickListener, AllCommentView, PullLoadMoreRecyclerView.PullLoadMoreListener, TextView.OnEditorActionListener {
    private int pageNo = 1;
    private PullLoadMoreRecyclerView commentRyv;
    private ArrayList<ArticleCommentModle.DataBean> mData = new ArrayList<>();
    private String articleId;
    private CommentAllAdapter mAdapter;
    private EditText commentEt;
    private RatingBar numRb;
    private boolean isScore;
    private LinearLayout contentLay;

    @Override
    protected AllCommentPresenter createPresenter() {
        return new AllCommentPresenter(this);
    }

    @Override
    protected View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.activity_allcomment);
        contentLay = (LinearLayout) mSuccessView.findViewById(R.id.lay_content);
        TextView titleTv = (TextView) mSuccessView.findViewById(R.id.tv_title);
        ImageView finishIv = (ImageView) mSuccessView.findViewById(R.id.iv_title_finish);
        commentRyv = (PullLoadMoreRecyclerView)mSuccessView.findViewById(R.id.ryv_comment);
        commentEt = (EditText)mSuccessView.findViewById(R.id.et_comment);
        numRb = (RatingBar)mSuccessView.findViewById(R.id.ratbar_num);

        commentRyv.setLinearLayout();
        mAdapter = new CommentAllAdapter(this, mData,mvpPresenter);
        commentRyv.setAdapter(mAdapter);
        titleTv.setText("全部评论");
        commentRyv.setOnPullLoadMoreListener(this);
        commentEt.setOnEditorActionListener(this);
        finishIv.setOnClickListener(this);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        articleId = getIntent().getStringExtra("articleId");
        isScore = getIntent().getBooleanExtra("isScore",false);
        if (isScore){
            contentLay.setVisibility(View.GONE);
        }else {
            contentLay.setVisibility(View.VISIBLE);
        }
    }
    @Override
    protected void initView() {
        super.initView();
        mPage.tvTitle.setText("全部评论");
    }
    /**
     * 点击事件
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                finish();
                break;
        }
    }

    /**
     * 获取评论列表 成功
     * @param model
     */
    @Override
    public void getDataSuccess(ArticleCommentModle model) {
        hideLoading();
        UIUtil.postPullLoadMoreCompleted(commentRyv);
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            if (model.getData().size()!=0){
                pageNo++;
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
            }
        }else {
            reloadErrorBtn(model.getErrorMsg());
        }
    }

    /**
     * 发送评论
     * @param model
     */
    @Override
    public void getSendDataSuccess(SendCommentMoudle model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
            isScore = true;
            contentLay.setVisibility(View.GONE);
            onRefresh();
            Intent data = new Intent();
            data.putExtra("isScore",isScore);
            setResult(5,data);
        }else {
            reloadErrorBtn(model.getErrMsg());
        }
    }

    /**
     * 点赞
     * @param model
     * @param type
     * @param position
     */
    @Override
    public void SaveScoreLikeData(RewardResult model, String type, int position) {
        if (model.isSuccess()) {
            if (type.equals("0")) {//点赞
                Logger.e("点赞");
                mData.get(position).setIsLike(true);
                mData.get(position).setLikeNum(mData.get(position).getLikeNum()+1);
            } else {//取消赞
                Logger.e("取消赞");
                mData.get(position).setIsLike(false);
                mData.get(position).setLikeNum(mData.get(position).getLikeNum()-1);
            }
            mAdapter.setmData(mData);
        }else {
            reloadErrorBtn(model.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        reloadErrorBtn(msg);
    }

    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void hideLoading() {
        dismissProgressDialog();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = 1;
        mData.clear();
        mvpPresenter.getParamet().setPageNo(pageNo+"");
        mvpPresenter.loadAllCommentData(articleId);
    }
    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(pageNo+"");
        mvpPresenter.loadAllCommentData(articleId);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            String conent = commentEt.getText().toString();
            if (!TextUtils.isEmpty(conent)){
                mvpPresenter.loadSendCommentData(articleId,conent,(int)numRb.getRating()+"");
                commentEt.setText("");
                UIUtil.hideKeyboard(commentEt);
            }else {
                ToastUtil.showToast("请输入评论内容");
            }
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        onRefresh();
    }

    @Override
    public void finish() {
        super.finish();
        int commentNum = getIntent().getIntExtra("commentNum",0);
        EventBus.getDefault().postSticky(new RefurshBookCommentListEvent(true,commentNum));
    }

    /**
     * 重新加载按钮
     */
    public void reloadErrorBtn(String msg) {
        LoggerUtil.e(msg);
        UIUtil.postPullLoadMoreCompleted(commentRyv);
        if (pageNo == 1 && msg.contains("全部评论")) {
            refreshPage(LoadingPager.PageState.STATE_ERROR);
            mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
                @Override
                public void reLoadData() {
                    refreshPage(LoadingPager.PageState.STATE_LOADING);
                    mvpPresenter.loadAllCommentData(articleId);
                }
            });
        } else {
            if (msg.contains("全部评论")) {
                ToastUtil.showToast("加载失败");
            }else if(msg.contains("点赞")){
                ToastUtil.showToast("点赞失败");
            }else if (msg.contains("发送")){
                ToastUtil.showToast("发送失败");
            }else {
                ToastUtil.showToast("请检查网络");
            }
        }
    }
}
