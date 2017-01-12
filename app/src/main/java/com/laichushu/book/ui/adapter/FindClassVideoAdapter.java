package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.ui.activity.FindClassDocDetailActivity;
import com.laichushu.book.ui.activity.FindClassVideoDetailActivity;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 发现 - 课程 - 视频文档 适配器
 * Created by wangtong on 2017/1/6.
 */

public class FindClassVideoAdapter extends RecyclerView.Adapter<FindClassVideoAdapter.ViewHolder> {
    private MvpActivity2 mActivity;
    private ArrayList<CourseraModle.DataBean.LessonListBean> mData = new ArrayList<>();

    public FindClassVideoAdapter(MvpActivity2 mActivity, ArrayList mData) {
        this.mActivity = mActivity;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_find_class_video);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        holder.itemView.setLayoutParams(params);
        final CourseraModle.DataBean.LessonListBean bean = mData.get(position);
        holder.photoIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getFileType().equals("1")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("lessonId",bean.getId());
                    UIUtil.openActivity(mActivity, FindClassVideoDetailActivity.class,bundle);
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString("lessonId",bean.getId());
                    UIUtil.openActivity(mActivity, FindClassDocDetailActivity.class,bundle);
                }

            }
        });
        holder.nameTv.setText(bean.getName());
        holder.briefTv.setText(bean.getRemarks());
        holder.lookTv.setText(bean.getClickNum() + "");
        holder.downloadTv.setText(bean.getDownloadNum() + "");
        holder.likeTv.setText(bean.getCollectionNum() + "");
        GlideUitl.loadImg(mActivity, bean.getThumbUrl(), holder.photoIv);
        if (bean.getFileType().equals("1")) {
            GlideUitl.loadImg(mActivity, R.drawable.icon_video, holder.typeIv);
        } else {
            GlideUitl.loadImg(mActivity, R.drawable.icon_doc, holder.typeIv);
        }
        holder.markTv.setText(bean.getAverageStar()+"分");
        holder.mRatingBar.setRating((float) bean.getAverageStar());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoIv;//图片
        private ImageView typeIv;//类型
        private TextView nameTv;//标题
        private TextView briefTv;//简介
        private TextView lookTv;//浏览数
        private TextView downloadTv;//下载数
        private TextView likeTv;//收藏
        private RatingBar mRatingBar;//星
        private TextView markTv;//评分

        public ViewHolder(View itemView) {
            super(itemView);
            photoIv = (ImageView) itemView.findViewById(R.id.iv_photo);
            typeIv = (ImageView) itemView.findViewById(R.id.iv_type);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            briefTv = (TextView) itemView.findViewById(R.id.tv_brief);
            lookTv = (TextView) itemView.findViewById(R.id.tv_look);
            downloadTv = (TextView) itemView.findViewById(R.id.tv_download);
            likeTv = (TextView) itemView.findViewById(R.id.tv_like);
            markTv = (TextView) itemView.findViewById(R.id.tv_mark);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.ratbar_num);
        }
    }

    public void setmData(ArrayList<CourseraModle.DataBean.LessonListBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }
}
