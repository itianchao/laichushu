package com.laichushu.book.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.directories.MaterialListModel;
import com.laichushu.book.ui.activity.DirectoriesActivity;
import com.laichushu.book.ui.widget.NoScrollGridView;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 素材目录
 * Created by wangtong on 2016/11/7.
 */
public class DirectoriesAdapter extends BaseAdapter {
    private DirectoriesActivity mActivity;
    private ArrayList<MaterialListModel.DataBean> mData;

    public DirectoriesAdapter(DirectoriesActivity mActivity, ArrayList<MaterialListModel.DataBean> mData) {
        this.mActivity = mActivity;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public MaterialListModel.DataBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoler holer = null;

        if (convertView == null) {
            convertView = UIUtil.inflate(R.layout.item_directories);
            holer = new ViewHoler(convertView);
            convertView.setTag(holer);
        }else {
            holer = (ViewHoler) convertView.getTag();
        }
        MaterialListModel.DataBean bean = getItem(position);
        holer.categoryTv.setText(bean.getName());
        ContentGvAdapter adapter = new ContentGvAdapter(bean.getData());
        holer.contentGv.setAdapter(adapter);
        return convertView;
    }
    class ViewHoler{
        private TextView categoryTv;
        private NoScrollGridView contentGv;

        public ViewHoler(View itemView) {
            categoryTv = (TextView)itemView.findViewById(R.id.tv_category);
            contentGv = (NoScrollGridView)itemView.findViewById(R.id.nsgv_content);
        }
    }
    class ContentGvAdapter extends BaseAdapter{
        ArrayList<MaterialListModel.DataBean.InDataBean> data;
        public ContentGvAdapter(ArrayList<MaterialListModel.DataBean.InDataBean> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data == null?0:data.size();
        }

        @Override
        public MaterialListModel.DataBean.InDataBean getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHoler2 holer = null;
            if (convertView == null) {
                convertView = UIUtil.inflate(R.layout.item_photo);
                holer = new ViewHoler2(convertView);
                convertView.setTag(holer);
            }else {
                holer = (ViewHoler2) convertView.getTag();
            }
            MaterialListModel.DataBean.InDataBean bean = getItem(position);
            holer.nameTv.setText(bean.getName());
            GlideUitl.loadImg(mActivity,bean.getImageUrl(),holer.photoIv);
            return convertView;
        }
    }
    class ViewHoler2{
        private TextView nameTv;
        private ImageView photoIv;

        public ViewHoler2(View itemView) {
            nameTv = (TextView)itemView.findViewById(R.id.tv_name);
            photoIv = (ImageView)itemView.findViewById(R.id.iv_photo);
        }
    }

    public ArrayList<MaterialListModel.DataBean> getmData() {
        return mData;
    }

    public void setmData(ArrayList<MaterialListModel.DataBean> mData) {
        this.mData = mData;
    }
}
