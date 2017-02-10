package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.find.FindPresenter;
import com.laichushu.book.mvp.find.coursera.video.CourseraModle;
import com.laichushu.book.ui.activity.FindCourseDocDetailActivity;
import com.laichushu.book.ui.activity.FindCourseVideoDetailActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 精品课
 * Created by PCPC on 2016/12/19.
 */

public class ClassRecycleAdapter extends RecyclerView.Adapter<ClassRecycleAdapter.ViewHolder> {
    private Activity context;
    private List<CourseraModle.DataBean.LessonListBean> dataBeen;
    private FindPresenter bookcastPresener;
    private final int HEAD_VIEW = 1;
    private final int FOOTER_VIEW = 2;

    public ClassRecycleAdapter(Activity context, List<CourseraModle.DataBean.LessonListBean> dataBean, FindPresenter bookcastPresener) {
        this.context = context;
        this.dataBeen = dataBean;
        this.bookcastPresener = bookcastPresener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_class_find);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if (position <= dataBeen.size()) {
            return HEAD_VIEW;
        } else {
            return FOOTER_VIEW;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int width = (UIUtil.getScreenWidth() / 2) - 24;
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                width, width / 5 * 2
        );
        holder.ivImg.setLayoutParams(linearParams);
        final CourseraModle.DataBean.LessonListBean bean = dataBeen.get(position);
        GlideUitl.loadImg(context, bean.getThumbUrl(), width, width / 5 * 2, holder.ivImg);
        holder.tvItem.setText(bean.getName());
        holder.tvPlayNum.setText("播放量" + bean.getClickNum() + "");
        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getFileType().equals("1")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("lessonId", bean.getId());
                    UIUtil.openActivity(context, FindCourseVideoDetailActivity.class, bundle);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("lessonId", bean.getId());
                    UIUtil.openActivity(context, FindCourseDocDetailActivity.class, bundle);
                }

            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataBeen == null ? 0 : dataBeen.size();
    }

    public void refreshAdapter(ArrayList<CourseraModle.DataBean.LessonListBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout llItem;
        public final TextView tvItem;
        public final TextView tvPlayNum;
        public final ImageView ivImg;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            llItem = (LinearLayout) root.findViewById(R.id.ll_class_item);
            tvItem = (TextView) root.findViewById(R.id.tv_class_name);
            tvPlayNum = (TextView) root.findViewById(R.id.tv_class_playNum);
            ivImg = (ImageView) root.findViewById(R.id.iv_class_img);
            this.root = root;
        }
    }
}
