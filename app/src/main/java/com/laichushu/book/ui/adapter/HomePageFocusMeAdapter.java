package com.laichushu.book.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.HomePersonFocusResult;
import com.laichushu.book.mvp.mine.personpage.HomePagePresener;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.activity.UserHomePageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * 关注我的
 * Created by PCPC on 2016/11/23.
 */

public class HomePageFocusMeAdapter extends RecyclerView.Adapter<HomePageFocusMeAdapter.ViewHolder> {
    private PersonalHomePageActivity context;
    private List<HomePersonFocusResult.DataBean> dataBeen;
    private HomePagePresener homePagePresener;
    private int type;

    public HomePageFocusMeAdapter(PersonalHomePageActivity context, List<HomePersonFocusResult.DataBean> dataBean, HomePagePresener homePagePresener, int type) {
        this.context = context;
        this.dataBeen = dataBean;
        this.homePagePresener = homePagePresener;
        this.type = type;
    }

    @Override
    public HomePageFocusMeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtil.inflate(R.layout.item_homepage_focusme);
        return new HomePageFocusMeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        GlideUitl.loadRandImg(context, dataBeen.get(position).getPhoto(), holder.ivImg);
        holder.tvContent.setText(dataBeen.get(position).getNickName());
        //关注我的
        if (dataBeen.get(position).isStatus()) {
            holder.checkBox.setTextColor(context.getResources().getColor(R.color.characterGray));
            holder.checkBox.setText("取消关注");
        } else {
            holder.checkBox.setText("关注");
            holder.checkBox.setTextColor(context.getResources().getColor(R.color.auditing));
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!dataBeen.get(position).isStatus()) {
//                    holder.checkBox.setText("取消关注");
//                    holder.checkBox.setTextColor(context.getResources().getColor(R.color.characterGray));
//                    holder.checkBox.setTextColor(context.getResources().getColor(R.color.auditing));
                    //添加关注
                    homePagePresener.loadAddFocus(dataBeen.get(position).getSourceUserId(), true, dataBeen.get(position), position, type);
//                    dataBeen.get(position).setStatus(true);
                } else {
//                    holder.checkBox.setText("关注");
//                    holder.checkBox.setTextColor(context.getResources().getColor(R.color.auditing));
                    homePagePresener.loadDelFocus(dataBeen.get(position).getSourceUserId(), false, dataBeen.get(position), position, type);
//                    dataBeen.get(position).setStatus(false);
                }

            }
        });
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转用户主页
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                bundle.putSerializable("bean", dataBeen.get(position));
                if (SharePrefManager.getUserId().equals(dataBeen.get(position).getSourceUserId())) {
                    UIUtil.openActivity(context, PersonalHomePageActivity.class, bundle);
                } else {
                    UIUtil.openActivity(context, UserHomePageActivity.class, bundle);
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

    public void refreshAdapter(List<HomePersonFocusResult.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
        }
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final RelativeLayout rlItem;
        public final TextView tvContent;
        public final ImageView ivImg;
        public final CheckBox checkBox;
        public final View root;

        public ViewHolder(View root) {
            super(root);
            rlItem = (RelativeLayout) root.findViewById(R.id.rl_item);
            tvContent = (TextView) root.findViewById(R.id.tv_dyContent);
            ivImg = (ImageView) root.findViewById(R.id.iv_dyHead);
            checkBox = (CheckBox) root.findViewById(R.id.cb_focus);
            this.root = root;
        }
    }

    public void setDataBeen(List<HomePersonFocusResult.DataBean> dataBeen) {
        this.dataBeen = dataBeen;
        notifyDataSetChanged();
    }
}

