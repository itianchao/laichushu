package com.sofacity.laichushu.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.ui.base.BaseActivity;
import com.sofacity.laichushu.utils.GlideUitl;
import com.sofacity.laichushu.utils.UIUtil;

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
        for (int i = 0 ;i<29;i++){
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
            //添加假数据
            GlideUitl.loadImg(this,"",bookIv);
            titleTv.setText("职场心计");
            typeTv.setText("文学");
            markTv.setText("3.0");
            commentTv.setText("(1010评论)");
            authorTv.setText("金庸");
            wordTv.setText("50万");
            moneyTv.setText("7678元");
            rewardTv.setText("(2029人打赏)");
            parentLay.addView(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIUtil.openActivity(mActivity, BookDetailActivity.class);
                }
            });
        }
    }
}
