package com.laichushu.book.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.laichushu.book.R;
import com.pickerview.lib.Province;

import java.util.List;

/**
 * Created by PCPC on 2016/12/1.
 */

public class ProvinceAdapter extends BaseAdapter {
    private Context context;
    private List<Province> list;

    public ProvinceAdapter(Context context, List<Province> list) {
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
            view = View.inflate(context, R.layout.item_pop_myview, null);
            holder = new ViewHolder();
            holder.music_name = (TextView) view
                    .findViewById(R.id.tv_province);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.music_name.setText(list.get(position).getProName());
        return view;
    }

    static class ViewHolder {
        TextView music_name;

    }
}
