package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.otherbean.NoteListModle;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.ui.fragment.CourseNoteFragment;
import com.laichushu.book.utils.StringUtil;
import com.laichushu.book.utils.UIUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 发现 - 课程 - 笔记
 * Created by wangtong on 2017/1/9.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private ArrayList<NoteListModle.DataBean.LessonNoteListBean> mData;
    private BaseActivity mActivity;
    private CourseNoteFragment mFragment;

    public NoteAdapter(CourseNoteFragment mFragment, ArrayList<NoteListModle.DataBean.LessonNoteListBean> mData) {
        this.mData = mData;
        this.mFragment = mFragment;
        this.mActivity = (BaseActivity) mFragment.getActivity();
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_class_note);
        return new NoteAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NoteListModle.DataBean.LessonNoteListBean bean = mData.get(position);
        holder.timeTv.setText(StringUtil.formatLongTime(bean.getUpdateDate()));
        holder.titleTv.setText(bean.getName());
        holder.contentTv.setText(bean.getRemarks());
        holder.editIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteId = bean.getNoteId();
                String name = bean.getName();
                String remarks = bean.getRemarks();
                mFragment.getPersenter().preEditNote(noteId,name,remarks);//编辑
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setmData(ArrayList<NoteListModle.DataBean.LessonNoteListBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView timeTv;
        private TextView contentTv;
        private ImageView editIv;
        private TextView titleTv;


        public ViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.tv_title);
            timeTv = (TextView) itemView.findViewById(R.id.tv_time);
            contentTv = (TextView) itemView.findViewById(R.id.tv_content);
            editIv = (ImageView) itemView.findViewById(R.id.iv_edit);
        }
    }
}
