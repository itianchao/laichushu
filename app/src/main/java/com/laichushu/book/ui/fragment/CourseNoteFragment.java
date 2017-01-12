package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.otherbean.NoteListModle;
import com.laichushu.book.mvp.find.coursera.note.NotePresenter;
import com.laichushu.book.mvp.find.coursera.note.NoteView;
import com.laichushu.book.ui.adapter.NoteAdapter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;


/**
 * 发现 - 课程- 笔记
 * Created by wangtong on 2017/1/9.
 */

public class CourseNoteFragment extends MvpFragment2<NotePresenter> implements NoteView, View.OnClickListener {

    public TextView createNoteTv;
    public PullLoadMoreRecyclerView mRecyclerView;
    public NoteAdapter mAdapter;
    public ArrayList<NoteListModle.DataBean.LessonNoteListBean> mData;
    public RelativeLayout noteRay;
    public Button okBtn;
    public Button cancelBtn;
    public EditText titleEt;
    public EditText contentEt;
    public String lessonId;
    private String noteId;

    @Override
    protected NotePresenter createPresenter() {
        return new NotePresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_coursenote);
        createNoteTv = (TextView) mSuccessView.findViewById(R.id.tv_create_note);//创建笔记
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_note);
        noteRay = (RelativeLayout) mSuccessView.findViewById(R.id.ray_note);//创建笔记容器
        okBtn = (Button) mSuccessView.findViewById(R.id.btn_ok);
        cancelBtn = (Button) mSuccessView.findViewById(R.id.btn_cancel);
        titleEt = (EditText) mSuccessView.findViewById(R.id.et_title);
        contentEt = (EditText) mSuccessView.findViewById(R.id.et_content);
        mRecyclerView.setLinearLayout();
        mAdapter = new NoteAdapter(this,mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setPullRefreshEnable(false);
        mRecyclerView.setPushRefreshEnable(false);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        refreshPage(LoadingPager.PageState.STATE_LOADING);
        lessonId = getArguments().getString("lessonId");
        mvpPresenter.loadNoteData(lessonId);
        createNoteTv.setOnClickListener(this);
        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    /**
     * 获取笔记列表 成功
     * @param model
     */
    @Override
    public void loadNoteListDataSuccess(NoteListModle model) {
        if (model.isSuccess()) {
            refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            mData = model.getData().getLessonNoteList();
            mAdapter.setmData(mData);
        }else {
            reloadErrorBtn();
        }
    }

    /**
     * 获取笔记列表 失败
     * @param msg
     */
    @Override
    public void loadNoteListDataFail(String msg) {
        reloadErrorBtn();
    }

    /**
     * 获取保存or修改笔记成功
     * @param model
     * @param finalType 0创建 1修改
     */
    @Override
    public void loadSaveNoteSuccess(RewardResult model, int finalType) {
        if (model.isSuccess()) {
            if (finalType ==0){
                ToastUtil.showToast("笔记创建成功");
            }else {
                ToastUtil.showToast("笔记修改成功");
            }
            noteRay.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            createNoteTv.setVisibility(View.VISIBLE);
            initData();
        }else {
            if (finalType ==0){
                ToastUtil.showToast("笔记创建失败");
            }else {
                ToastUtil.showToast("笔记修改失败");
            }
        }
    }

    /**
     * 获取保存or修改笔记失败
     * @param msg
     * @param finalType 0创建 1修改
     */
    @Override
    public void loadSaveNoteDataFail(String msg, int finalType) {
        if (finalType ==0){
            ToastUtil.showToast("笔记创建失败");
        }else {
            ToastUtil.showToast("笔记修改失败");
        }
    }

    /**
     * 删除笔记成功
     * @param model
     */
    @Override
    public void loadDeleteNoteDataSuccess(RewardResult model) {
        if (model.isSuccess()) {
            ToastUtil.showToast("笔记删除成功");
        }else {
            ToastUtil.showToast("笔记删除失败");
        }
    }

    /**
     * 删除笔失败
     * @param msg
     */
    @Override
    public void loadDeleteNoteDataFail(String msg) {
        ToastUtil.showToast("笔记删除失败");
    }

    /**
     * 重新加载按钮
     */
    public void reloadErrorBtn(){
        refreshPage(LoadingPager.PageState.STATE_ERROR);
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
                initData();
            }
        });
    }

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_create_note:
                noteRay.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                createNoteTv.setVisibility(View.GONE);
                noteId = "";
                titleEt.setText("");
                contentEt.setText("");
                break;
            case R.id.btn_ok:
                mvpPresenter.preEdit(lessonId, noteId);//空串创建 非空修改
                break;
            case R.id.btn_cancel:
                noteRay.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                createNoteTv.setVisibility(View.VISIBLE);
                break;
        }
    }

    public NotePresenter getPersenter() {
        return mvpPresenter;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
