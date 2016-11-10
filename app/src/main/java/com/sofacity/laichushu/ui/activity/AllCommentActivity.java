package com.sofacity.laichushu.ui.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sofacity.laichushu.R;
import com.sofacity.laichushu.bean.JsonBean.RewardResult;
import com.sofacity.laichushu.event.RefurshCommentListEvent;
import com.sofacity.laichushu.mvp.allcomment.AllCommentPresenter;
import com.sofacity.laichushu.mvp.allcomment.AllCommentView;
import com.sofacity.laichushu.mvp.allcomment.SendCommentMoudle;
import com.sofacity.laichushu.mvp.bookdetail.ArticleCommentModle;
import com.sofacity.laichushu.ui.adapter.CommentAllAdapter;
import com.sofacity.laichushu.ui.base.MvpActivity;
import com.sofacity.laichushu.utils.ToastUtil;
import com.sofacity.laichushu.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * 全部评论
 * Created by wangtong on 2016/10/27.
 */
public class AllCommentActivity extends MvpActivity<AllCommentPresenter> implements View.OnClickListener, AllCommentView, PullLoadMoreRecyclerView.PullLoadMoreListener, TextView.OnEditorActionListener {
    private String pageNo = "1";
    private PullLoadMoreRecyclerView commentRyv;
    private ArrayList<ArticleCommentModle.DataBean> mData = new ArrayList<>();
    private String articleId;
    private CommentAllAdapter mAdapter;
    private EditText commentEt;
    private RatingBar numRb;

    @Override
    protected AllCommentPresenter createPresenter() {
        return new AllCommentPresenter(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_allcomment);
        initTitleBar("全部评论");
        commentRyv = (PullLoadMoreRecyclerView)findViewById(R.id.ryv_comment);
        commentEt = (EditText)findViewById(R.id.et_comment);
        numRb = (RatingBar)findViewById(R.id.ratbar_num);
        commentRyv.setLinearLayout();
        mAdapter = new CommentAllAdapter(this, mData,mvpPresenter);
        commentRyv.setAdapter(mAdapter);
        commentRyv.setOnPullLoadMoreListener(this);
        articleId = getIntent().getStringExtra("articleId");
        commentEt.setOnEditorActionListener(this);
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
    /**
     * 点击事件
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                EventBus.getDefault().postSticky(new RefurshCommentListEvent(true));
                finish();
                break;
        }
    }

    @Override
    public void getDataSuccess(ArticleCommentModle model) {
        hideLoading();
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                commentRyv.setPullLoadMoreCompleted();
            }
        }, 300);
        if (model.isSuccess()) {
            if (model.getData().size()!=0){
                pageNo = Integer.parseInt(pageNo) + 1 + "";
                mData.addAll(model.getData());
                mAdapter.setmData(mData);
                mAdapter.notifyDataSetChanged();
            }
        }else {
            ToastUtil.showToast(model.getErrorMsg());
        }
    }

    @Override
    public void getSendDataSuccess(SendCommentMoudle model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("发送成功");
            onRefresh();
        }else {
            String errorMsg = model.getErrMsg();
            if (errorMsg.contains("该用户已经评分了")){
                ToastUtil.showToast(errorMsg);
            }else {
                ToastUtil.showToast("发送失败");
            }
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

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageNo = "1";
        mData.clear();
        mvpPresenter.getParamet().setPageNo(pageNo);
        mvpPresenter.loadAllCommentData(articleId);
    }
    /**
     * 上拉刷新
     */
    @Override
    public void onLoadMore() {
        mvpPresenter.getParamet().setPageNo(pageNo);
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
}
