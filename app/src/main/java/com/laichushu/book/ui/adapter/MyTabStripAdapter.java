package com.laichushu.book.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.MyTabStrip;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.GlideUitl;

import java.util.List;

/**
 * Created by PCPC on 2016/12/10.
 */

public class MyTabStripAdapter extends BaseAdapter{
    private Context context;
    private List<MyTabStrip> list;

    public MyTabStripAdapter(Context context,List<MyTabStrip> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_tabstrip, null);
            holder = new ViewHolder();
            holder.tvContent = (TextView) view
                    .findViewById(R.id.tv_stripContent);
            holder.ivIcon = (ImageView) view
                    .findViewById(R.id.iv_stripIcon);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvContent.setText(list.get(position).getTitle());
        GlideUitl.loadImg(context,list.get(position).getDrawble(),holder.ivIcon);
        return view;
    }

    static class ViewHolder {
        TextView tvContent;
        ImageView ivIcon;
    }
}
