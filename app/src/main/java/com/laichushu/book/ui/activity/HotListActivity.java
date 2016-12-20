package com.laichushu.book.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.HomeHot_Paramet;
import com.laichushu.book.mvp.home.HomeHotModel;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.base.BaseActivity;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.UIUtil;

import java.util.ArrayList;

/**
 * 最热列表
 * Created by wangtong on 2016/10/25.
 */
public class HotListActivity extends BaseActivity {

    private LinearLayout parentLay;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_hotlist);
        EditText searchEt = (EditText)findViewById(R.id.et_search);
        LinearLayout allLay = (LinearLayout)findViewById(R.id.lay_all);
        LinearLayout newsLay = (LinearLayout)findViewById(R.id.lay_news);
        LinearLayout hotLay = (LinearLayout)findViewById(R.id.lay_hot);
        parentLay = (LinearLayout)findViewById(R.id.lay_parent);
    }

    /**
     * item 条目
     */
    private ImageView bookIv;
    private TextView titleTv;
    private TextView typeTv;
    private RatingBar numRb;
    private TextView markTv;
    private TextView commentTv;
    private TextView authorTv;
    private TextView wordTv;
    private TextView moneyTv;
    private TextView rewardTv;
    @Override
    protected void initData() {
        HomeHot_Paramet paramet = new HomeHot_Paramet(SharePrefManager.getUserId());
        showProgressDialog();
        addSubscription(apiStores.homeHotData(paramet), new ApiCallback<HomeHotModel>() {
            @Override
            public void onSuccess(HomeHotModel model) {
                result(model);
            }

            @Override
            public void onFailure(int code, String msg) {
                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }
    public void result(HomeHotModel model){
        if (model.isSuccess()){
            ArrayList<HomeHotModel.DataBean> hotBeanList = model.getData();
            for (int i = 0 ;i<hotBeanList.size();i++){
                View itemView = UIUtil.inflate(R.layout.item_home_book);
                bookIv = (ImageView) itemView.findViewById(R.id.iv_book);
                titleTv = (TextView) itemView.findViewById(R.id.tv_title);
                typeTv = (TextView) itemView.findViewById(R.id.tv_type);
                numRb = (RatingBar) itemView.findViewById(R.id.ratbar_num);
                markTv = (TextView) itemView.findViewById(R.id.tv_mark);
                commentTv = (TextView) itemView.findViewById(R.id.tv_comment);
                authorTv = (TextView) itemView.findViewById(R.id.tv_author);
                wordTv = (TextView) itemView.findViewById(R.id.tv_word);
                moneyTv = (TextView) itemView.findViewById(R.id.tv_money);
                rewardTv = (TextView) itemView.findViewById(R.id.tv_reward);
                //添加数据
                final HomeHotModel.DataBean bean = hotBeanList.get(i);
                GlideUitl.loadImg(this,bean.getCoverUrl(),bookIv);
                titleTv.setText(bean.getArticleName());
                typeTv.setText(bean.getTopCategoryName());
                markTv.setText(bean.getScore()+"分");
                commentTv.setText("("+bean.getCommentNum()+"评论)");
                authorTv.setText(bean.getAuthorName());
                wordTv.setText("约"+bean.getWordNum());
                moneyTv.setText(bean.getAwardMoney()+"元");
                rewardTv.setText("("+bean.getAwardNum()+"人打赏)");
                parentLay.addView(itemView);
                numRb.setRating(bean.getLevel());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bean",bean);
                        bundle.putString("pageMsg", "热门推荐列表");
                        UIUtil.openActivity(mActivity, BookDetailActivity.class,bundle);
                    }
                });
            }
        }else {
            ToastUtil.showToast(model.getErrMsg());
        }
    }
}
