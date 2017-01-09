package com.laichushu.book.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.ui.adapter.NoteAdapter;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.utils.UIUtil;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 发现 - 课程- 笔记
 * Created by wangtong on 2017/1/9.
 */

public class CourseNoteFragment extends MvpFragment2 {

    private TextView createNote;
    private PullLoadMoreRecyclerView mRecyclerView;
    private NoteAdapter mAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_coursenote);
        createNote = (TextView) mSuccessView.findViewById(R.id.tv_create_note);
        mRecyclerView = (PullLoadMoreRecyclerView) mSuccessView.findViewById(R.id.ryv_note);
        mRecyclerView.setLinearLayout();
        mAdapter = new NoteAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return mSuccessView;
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
