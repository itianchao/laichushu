package com.laichushu.book.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.laichushu.book.R;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/11/18.
 */

public class MyBookCastAdapter extends BaseAdapter {
    private Context context;
    private List<HomeHotModel.DataBean> dataBeen;

    public MyBookCastAdapter(Context context, List<HomeHotModel.DataBean> dataBean) {
        this.context = context;
        this.dataBeen = dataBean;
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshAdapter(List<HomeHotModel.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_bookcast, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        GlideUitl.loadImg(context, dataBeen.get(position).getCoverUrl(), vh.ivImg);
        vh.tvItem.setText(dataBeen.get(position).getArticleName());
        return convertView;
    }

    public class ViewHolder {
        public final LinearLayout llItem;
        public final TextView tvItem;
        public final ImageView ivImg;
        public final View root;

        public ViewHolder(View root) {
            llItem = (LinearLayout) root.findViewById(R.id.ll_item);
            tvItem = (TextView) root.findViewById(R.id.tv_item);
            ivImg = (ImageView) root.findViewById(R.id.iv_img);
            this.root = root;
        }
    }
}
