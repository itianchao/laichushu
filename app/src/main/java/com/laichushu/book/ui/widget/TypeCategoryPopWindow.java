package com.laichushu.book.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.mvp.homecategory.CategoryModle;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;
import java.util.List;

public class TypeCategoryPopWindow extends PopupWindow implements OnItemClickListener {
    private View mConentView;                // PopupWindow视图
    private Activity mActivity;
    private Context mContext;
    private SpinerListAdapter mAdapter;
    private IListItemClickListener mListItemClickListener;
    private ArrayList<CategoryModle.DataBean> mDatas;
    private SpinerListAdapter2 mAdapter2;
    private ArrayList<CategoryModle.DataBean.ChildBean> mChildData = new ArrayList<>();
    private ListView mListView;

    public TypeCategoryPopWindow(Context context, ArrayList<CategoryModle.DataBean> datas) {
        super(context);
        this.mActivity = (Activity) context;
        this.mContext = context;
        this.mDatas = datas;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mConentView = inflater.inflate(R.layout.pop_window_category_list_layout, null);
        this.setContentView(mConentView);
        initPopWindow();
        initView();
        checkItem(0);
        onItemClick(mListView,mListView,0,0);
    }

    @SuppressWarnings("deprecation")
    private void initPopWindow() {
        // 设置SelectPicPopupWindow弹出窗体的宽
        // int width =
        // mActivity.getWindowManager().getDefaultDisplay().getWidth();
        // this.setWidth(width / 2 + 50);
        // this.setWidth(width - 100);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        // 刷新状态
        this.update();
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(new BitmapDrawable());
        // 设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(android.R.style.Animation_Dialog);
        this.setAnimationStyle(R.style.add_popwin_anim_style);// 自定义动画
        // 幕背景变暗
        // setBackgroundAlpha(0.5f);
    }
    private final int[] count = {0};

    public int[] getCount() {
        return count;
    }

    private void initView() {
        mListView = (ListView) mConentView.findViewById(R.id.lv_popwin);
        ListView mChildView = (ListView) mConentView.findViewById(R.id.lv_popwin2);
        mListView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mChildView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAdapter = new SpinerListAdapter();
        mListView.setAdapter(mAdapter);
        mAdapter2 = new SpinerListAdapter2();
        mChildView.setAdapter(mAdapter2);
        mListView.setOnItemClickListener(this);
        mChildView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListItemClickListener.clickItem(position);
                TypeCategoryPopWindow.this.dismiss();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        mActivity.getWindow().setAttributes(lp);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.lv_popwin:
                mChildData.clear();
                if(null!=mDatas.get(position).getChild()){
                    mChildData.addAll(mDatas.get(position).getChild());
                    mAdapter.notifyDataSetChanged();
                    mAdapter2.notifyDataSetChanged();
                    count[0] = position;
                    checkItem(position);
                }
                break;
        }
    }

    class SpinerListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (mDatas != null) {
                return mDatas.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mDatas != null) {
                return mDatas.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_poplist, null);
                holder = new ViewHolder();
                holder.tv = (TextView) convertView.findViewById(R.id.tv_type);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (mDatas.get(position).isPressd()){
                holder.tv.setBackgroundColor(UIUtil.getColor(R.color.frenchGrey));
                holder.tv.setTextColor(UIUtil.getColor(R.color.black));
            }else {
                holder.tv.setBackgroundColor(UIUtil.getColor(R.color.white));
                holder.tv.setTextColor(UIUtil.getColor(R.color.hint));
            }
            holder.tv.setText(mDatas.get(position).getName());
            return convertView;
        }
    }

    class SpinerListAdapter2 extends BaseAdapter {
        @Override
        public int getCount() {
            if (mChildData != null) {
                return mChildData.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mChildData != null) {
                return mChildData.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_poplist, null);
                holder = new ViewHolder();
                holder.tv = (TextView) convertView.findViewById(R.id.tv_type);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setBackgroundColor(UIUtil.getColor(R.color.frenchGrey));
            holder.tv.setTextColor(UIUtil.getColor(R.color.black));
            holder.tv.setText(mChildData.get(position).getName());
            return convertView;
        }
    }

    static class ViewHolder {
        TextView tv;
    }

    public interface IListItemClickListener {
        void clickItem(int position);
    }

    public void setListItemClickListener(IListItemClickListener listener) {
        this.mListItemClickListener = listener;
    }
    public void checkItem(int position) {
        for (int i = 0; i < mDatas.size(); i++) {
            CategoryModle.DataBean dataBean = mDatas.get(i);
            if (i == position) {
                dataBean.setPressd(true);
            } else {
                dataBean.setPressd(false);
            }
        }
    }
}
