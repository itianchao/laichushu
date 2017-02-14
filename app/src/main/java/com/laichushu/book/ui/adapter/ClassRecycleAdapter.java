package com.laichushu.book.ui.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.laichushu.book.mvp.find.group.GroupListModle;
import com.laichushu.book.ui.activity.FindCourseDocDetailActivity;
import com.laichushu.book.ui.activity.FindCourseVideoDetailActivity;
import com.laichushu.book.ui.activity.FindGroupDetailActivity;
import com.laichushu.book.ui.fragment.FragmentFactory;
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
    private List<CourseraModle.DataBean.LessonListBean> dataBeen;//课程
    private ArrayList<GroupListModle.DataBean> courseDate;//推荐小组
    private RecyclerView recyclerView;
    private FindPresenter bookcastPresener;
    private final int VIEW_HEADTITLE = 0;
    private final int VIEW_HEAD = 1;
    private final int VIEW_TITLE = 2;
    private final int VIEW_BODY = 3;
    private View itemView;
    private int lessonSize;//课程item个数
    private int courseSize;//推荐小组item个数
    private int courseFrequency;

    public ClassRecycleAdapter(Activity context, List<CourseraModle.DataBean.LessonListBean> dataBean, ArrayList<GroupListModle.DataBean> mCourseDate, FindPresenter bookcastPresener) {
        this.context = context;
        this.dataBeen = dataBean;
        this.courseDate = mCourseDate;
        this.bookcastPresener = bookcastPresener;
        initData();
    }

    public void initData() {
        lessonSize = dataBeen.size() / 2 + dataBeen.size() % 2;
        courseSize = lessonSize + 2;
        courseFrequency = courseDate.size() % 4 > 0 ? (courseDate.size() / 4) + 1 : courseDate.size() / 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADTITLE;
        } else if (position <=lessonSize) {
            return VIEW_HEAD;
        } else if (position == lessonSize+1) {
            return VIEW_TITLE;
        } else {
            return VIEW_BODY;
        }
    }

    @Override
    public int getItemCount() {
        return lessonSize + courseFrequency + 2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        switch (viewType) {
            case VIEW_HEADTITLE:
                itemView = UIUtil.inflate(R.layout.item_find_course_title);
                holder = new ViewHolder0(itemView);
                break;
            case VIEW_HEAD:
                itemView = UIUtil.inflate(R.layout.item_class_find_new);
                holder = new ViewHolder1(itemView);
                break;
            case VIEW_TITLE:
                itemView = UIUtil.inflate(R.layout.item_find_title);
                holder = new ViewHolder3(itemView);
                break;
            case VIEW_BODY:
                itemView = UIUtil.inflate(R.layout.item_find_course_child_new);
                holder = new ViewHolder2(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case VIEW_HEAD:
                            return dataBeen.size();
                        case VIEW_TITLE:
                            return 1;
                        case VIEW_BODY:
                            return courseDate.size();
                        default:
                            return dataBeen.size();
                    }
                }
            });
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (getItemViewType(position) == VIEW_HEADTITLE) {
            //

        } else if (getItemViewType(position) == VIEW_HEAD) {
            int width = (UIUtil.getScreenWidth() / 2) - 24;
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                    width, width / 5 * 2
            );
            if (dataBeen.size() > 0 && null != dataBeen.get(2 * position-2)) {
                ((ViewHolder1) holder).ivImg.setLayoutParams(linearParams);
                final CourseraModle.DataBean.LessonListBean bean = dataBeen.get(2 * position-2);
                GlideUitl.loadImg(context, bean.getThumbUrl(), width, width / 5 * 2, ((ViewHolder1) holder).ivImg);
                ((ViewHolder1) holder).tvItem.setText(bean.getName());
                ((ViewHolder1) holder).tvPlayNum.setText("播放量" + bean.getClickNum() + "");
                ((ViewHolder1) holder).llItem.setOnClickListener(new View.OnClickListener() {
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
                if (null != dataBeen.get(2 * position -1)) {
                    ((ViewHolder1) holder).ivImg2.setLayoutParams(linearParams);
                    final CourseraModle.DataBean.LessonListBean bean2 = dataBeen.get(2 * position - 1);
                    GlideUitl.loadImg(context, bean2.getThumbUrl(), width, width / 5 * 2, ((ViewHolder1) holder).ivImg2);
                    ((ViewHolder1) holder).tvItem.setText(bean2.getName());
                    ((ViewHolder1) holder).tvPlayNum2.setText("播放量" + bean2.getClickNum() + "");
                    ((ViewHolder1) holder).llItem2.setOnClickListener(new View.OnClickListener() {
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
            }

        } else if (getItemViewType(position) == VIEW_TITLE) {
            //更多点击事件
            if (courseDate.size() > 0) {

            }
        } else {
            int width = 0, width2 = UIUtil.getScreenWidth() / 4;
            if (UIUtil.getScreenWidth() < 1000) {
                width = (UIUtil.getScreenWidth() / 4) - (12 * 6);
            } else {
                width = (UIUtil.getScreenWidth() / 4) - (12 * 12);
            }

            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                    width2, width2
            );
            LinearLayout.LayoutParams imgParams = new LinearLayout.LayoutParams(
                    width, width
            );
            if (courseDate.size() > 0 && null != courseDate.get(position - courseSize)) {
                //1
                if (null != courseDate.get(position - courseSize)) {
                    ((ViewHolder2) holder).llItem.setLayoutParams(linearParams);
                    ((ViewHolder2) holder).hot1Iv.setLayoutParams(imgParams);
                    final GroupListModle.DataBean dataBean = courseDate.get(position - courseSize);
                    GlideUitl.loadCornersImg(context, 80, dataBean.getPhoto(), width, width, ((ViewHolder2) holder).hot1Iv);
                    ((ViewHolder2) holder).tvNAme.setText(dataBean.getName());
                    ((ViewHolder2) holder).hot1Iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("bean", dataBean);
                            bundle.putInt("position", position);
                            UIUtil.openActivity(context, FindGroupDetailActivity.class, bundle);
                        }
                    });
                }
                //2
                if (null != courseDate.get(position - courseSize + 1)) {
                    ((ViewHolder2) holder).llItem2.setLayoutParams(linearParams);
                    ((ViewHolder2) holder).hot1Iv2.setLayoutParams(imgParams);
                    final GroupListModle.DataBean dataBean = courseDate.get(position - courseSize + 1);
                    GlideUitl.loadCornersImg(context, 80, dataBean.getPhoto(), width, width, ((ViewHolder2) holder).hot1Iv2);
                    ((ViewHolder2) holder).tvNAme2.setText(dataBean.getName());
                    ((ViewHolder2) holder).hot1Iv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("bean", dataBean);
                            bundle.putInt("position", position);
                            UIUtil.openActivity(context, FindGroupDetailActivity.class, bundle);
                        }
                    });
                }
                //3
                if (null != courseDate.get(position - courseSize + 2)) {
                    ((ViewHolder2) holder).llItem3.setLayoutParams(linearParams);
                    ((ViewHolder2) holder).hot1Iv3.setLayoutParams(imgParams);
                    final GroupListModle.DataBean dataBean = courseDate.get(position - courseSize + 2);
                    GlideUitl.loadCornersImg(context, 80, dataBean.getPhoto(), width, width, ((ViewHolder2) holder).hot1Iv3);
                    ((ViewHolder2) holder).tvNAme3.setText(dataBean.getName());
                    ((ViewHolder2) holder).hot1Iv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("bean", dataBean);
                            bundle.putInt("position", position);
                            UIUtil.openActivity(context, FindGroupDetailActivity.class, bundle);
                        }
                    });
                }
                //4
                if (null != courseDate.get(position - courseSize + 3)) {
                    ((ViewHolder2) holder).llItem4.setLayoutParams(linearParams);
                    ((ViewHolder2) holder).hot1Iv4.setLayoutParams(imgParams);
                    final GroupListModle.DataBean dataBean = courseDate.get(position - courseSize + 3);
                    GlideUitl.loadCornersImg(context, 80, dataBean.getPhoto(), width, width, ((ViewHolder2) holder).hot1Iv4);
                    ((ViewHolder2) holder).tvNAme4.setText(dataBean.getName());
                    ((ViewHolder2) holder).hot1Iv4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("bean", dataBean);
                            bundle.putInt("position", position);
                            UIUtil.openActivity(context, FindGroupDetailActivity.class, bundle);
                        }
                    });
                }
            }
        }


    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void refreshAdapter(ArrayList<CourseraModle.DataBean.LessonListBean> listData, ArrayList<GroupListModle.DataBean> mCourseDate) {
        if (listData.size() > 0) {
            dataBeen.clear();
            dataBeen.addAll(listData);
        }
        if (mCourseDate.size() > 0) {
            this.courseDate.clear();
            this.courseDate.addAll(mCourseDate);
        }
        initData();
        this.notifyDataSetChanged();
    }

    /**
     * base
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder0 extends ViewHolder {
        public ViewHolder0(View root) {
            super(root);
        }
    }

    public class ViewHolder1 extends ViewHolder {
        public final LinearLayout llItem, llItem2;
        public final TextView tvItem, tvItem2;
        public final TextView tvPlayNum, tvPlayNum2;
        public final ImageView ivImg, ivImg2;

        public ViewHolder1(View root) {
            super(root);
            llItem = (LinearLayout) root.findViewById(R.id.ll_class_item);
            tvItem = (TextView) root.findViewById(R.id.tv_class_name);
            tvPlayNum = (TextView) root.findViewById(R.id.tv_class_playNum);
            ivImg = (ImageView) root.findViewById(R.id.iv_class_img);
            llItem2 = (LinearLayout) root.findViewById(R.id.ll_class_item2);
            tvItem2 = (TextView) root.findViewById(R.id.tv_class_name2);
            tvPlayNum2 = (TextView) root.findViewById(R.id.tv_class_playNum2);
            ivImg2 = (ImageView) root.findViewById(R.id.iv_class_img2);
        }
    }

    class ViewHolder2 extends ViewHolder {
        private LinearLayout llItem;
        private ImageView hot1Iv;
        private TextView tvNAme;

        private LinearLayout llItem2;
        private ImageView hot1Iv2;
        private TextView tvNAme2;

        private LinearLayout llItem3;
        private ImageView hot1Iv3;
        private TextView tvNAme3;

        private LinearLayout llItem4;
        private ImageView hot1Iv4;
        private TextView tvNAme4;

        public ViewHolder2(View root) {
            super(root);
            llItem = (LinearLayout) root.findViewById(R.id.ll_item);
            hot1Iv = (ImageView) root.findViewById(R.id.iv_hot1);
            tvNAme = (TextView) root.findViewById(R.id.tv_name1);

            llItem2 = (LinearLayout) root.findViewById(R.id.ll_item2);
            hot1Iv2 = (ImageView) root.findViewById(R.id.iv_hot12);
            tvNAme2 = (TextView) root.findViewById(R.id.tv_name12);

            llItem3 = (LinearLayout) root.findViewById(R.id.ll_item3);
            hot1Iv3 = (ImageView) root.findViewById(R.id.iv_hot13);
            tvNAme3 = (TextView) root.findViewById(R.id.tv_name13);

            llItem4 = (LinearLayout) root.findViewById(R.id.ll_item4);
            hot1Iv4 = (ImageView) root.findViewById(R.id.iv_hot14);
            tvNAme4 = (TextView) root.findViewById(R.id.tv_name14);
        }
    }

    class ViewHolder3 extends ViewHolder {
        public ViewHolder3(View root) {
            super(root);
        }
    }
}
