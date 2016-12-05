package com.laichushu.book.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.PerMsgInfoReward;
import com.laichushu.book.ui.activity.PersonalInfomationPageActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

/**
 * Created by PCPC on 2016/12/2.
 */

public class PersonalInfomationAdapter extends RecyclerView.Adapter<PersonalInfomationAdapter.ViewHolder> {
    private PersonalInfomationPageActivity context;
    private List<PerMsgInfoReward.DataBean> dataBeen;
    //多重行视图
    private final static int TYPE1 = 0;
    private final static int TYPE2 = 1;

    public PersonalInfomationAdapter(PersonalInfomationPageActivity context, List<PerMsgInfoReward.DataBean> dataBean) {
        this.context = context;
        this.dataBeen = dataBean;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataBeen.get((dataBeen.size()-1)-position).getSenderId().equals(SharePrefManager.getUserId())) {
            return TYPE1;
        } else {
            return TYPE2;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public PersonalInfomationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        PersonalInfomationAdapter.ViewHolder holder = null;
        switch (viewType) {
            case TYPE1:
                itemView = UIUtil.inflate(R.layout.item_per_info_details_right);
                holder = new ViewHolder1(itemView);
                break;
            case TYPE2:
                itemView = UIUtil.inflate(R.layout.item_per_info_details_left);
                holder = new ViewHolder2(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(PersonalInfomationAdapter.ViewHolder holder, int position) {
        if (dataBeen.get((dataBeen.size()-1)-position).getSenderId().equals(SharePrefManager.getUserId())) {
            GlideUitl.loadRandImg(context, dataBeen.get((dataBeen.size()-1)-position).getSenderPhoto(), ((ViewHolder1) holder).ivReceive);
            ((ViewHolder1) holder).tvReceive.setText(dataBeen.get((dataBeen.size()-1)-position).getContent());
        } else {
            GlideUitl.loadRandImg(context, dataBeen.get((dataBeen.size()-1)-position).getSenderPhoto(), ((ViewHolder2) holder).ivSend);
            ((ViewHolder2) holder).tvSend.setText(dataBeen.get((dataBeen.size()-1)-position).getContent());
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataBeen == null ? 0 : dataBeen.size();
    }

    public void refreshAdapter(List<PerMsgInfoReward.DataBean> listData) {
        dataBeen.clear();
        if (listData.size() > 0) {
            dataBeen.addAll(listData);
            this.notifyDataSetChanged();
        }
    }

    public class ViewHolder1 extends ViewHolder {
        //left
        public  TextView tvReceive;
        public final ImageView ivReceive;

        public ViewHolder1(View root) {
            super(root);
            tvReceive = (TextView) root.findViewById(R.id.tv_msgReceive);
            ivReceive = (ImageView) root.findViewById(R.id.iv_HeadReceive);
        }
    }

    public class ViewHolder2 extends ViewHolder {

        //right


        public final TextView tvSend;
        public final ImageView ivSend;


        public ViewHolder2(View root) {
            super(root);


            tvSend = (TextView) root.findViewById(R.id.tv_headSend);
            ivSend = (ImageView) root.findViewById(R.id.iv_HeadSend);
        }
    }

}
