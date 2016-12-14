package com.laichushu.book.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.laichushu.book.mvp.homecategory.CategoryModle;
import com.laichushu.book.R;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 分类 child Adapter
 * Created by wangtong on 2016/11/10.
 */

public class CategoryChildAdapter extends BaseAdapter{
    private ArrayList<CategoryModle.DataBean.ChildBean> mParentData;
    public CategoryChildAdapter(ArrayList<CategoryModle.DataBean.ChildBean> mParentData) {
        this.mParentData = mParentData;
    }

    @Override
    public int getCount() {
        return mParentData == null?0:mParentData.size();
    }

    @Override
    public CategoryModle.DataBean.ChildBean getItem(int position) {
        return mParentData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        }else {
            convertView = UIUtil.inflate(R.layout.item_category_child);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        String content = mParentData.get(position).getName()+"("+mParentData.get(position).getArticleNum()+")";
        holder.parentTv.setText(content);
        if ((position%2 == 0)) {
            holder.line.setVisibility(View.VISIBLE);
        }else {
            holder.line.setVisibility(View.GONE);
        }
        return convertView;
    }
    class ViewHolder{

        private TextView parentTv;
        private View line;

        public ViewHolder(View itemView) {
            parentTv = (TextView)itemView.findViewById(R.id.tv_parent);
            line = itemView.findViewById(R.id.view_line);
        }
    }

    public ArrayList<CategoryModle.DataBean.ChildBean> getmParentData() {
        return mParentData;
    }

    public void setmParentData(ArrayList<CategoryModle.DataBean.ChildBean> mParentData) {
        this.mParentData = mParentData;
    }
}
