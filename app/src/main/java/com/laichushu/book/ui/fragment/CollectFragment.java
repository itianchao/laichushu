package com.laichushu.book.ui.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.event.CollectEvent;
import com.laichushu.book.mvp.find.collect.CollectPresenter;
import com.laichushu.book.mvp.find.collect.CollectView;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 收藏
 * Created by wangtong on 2017/2/10.
 */

public class CollectFragment extends MvpFragment2<CollectPresenter> implements View.OnClickListener, CollectView {

    private String isCollect;
    private ImageView collectIv;
    private String sourceId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isCollect = getArguments().getString("isCollect", "2");
        sourceId = getArguments().getString("sourceId", "0");
    }

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mSuccessView = UIUtil.inflate(R.layout.fragment_collect);
        collectIv = (ImageView) mSuccessView.findViewById(R.id.iv_collect);
        return mSuccessView;
    }

    @Override
    public void initData() {
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 20);
        if (isCollect.equals("2")) {
            GlideUitl.loadImg(getActivity(), R.drawable.icon_uncollect, collectIv);
        } else {
            GlideUitl.loadImg(getActivity(), R.drawable.icon_praise_yes2x, collectIv);
        }
        collectIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_collect:
                String type;
                if (isCollect.equals("2")) {//未收藏
                    type = "0";
                } else {
                    type = "1";
                }
                mvpPresenter.collectSave(sourceId, type);
                break;
        }
    }

    /**
     * 收藏 or 取消收藏 成功
     *
     * @param model
     * @param type  0 收藏 1 取消
     */
    @Override
    public void loadCollectDataSuccess(RewardResult model, String type) {
        if (model.isSuccess()) {
            if (type.equals("0")) {//收藏
                ToastUtil.showToast("收藏成功");
                isCollect = "1";
                GlideUitl.loadImg(mActivity, R.drawable.icon_praise_yes2x, collectIv);
            } else {//取消
                ToastUtil.showToast("取消收藏成功");
                GlideUitl.loadImg(mActivity, R.drawable.icon_uncollect, collectIv);
                isCollect = "2";
            }
            EventBus.getDefault().postSticky(new CollectEvent(isCollect));
        } else {
            if (type.equals("0")) {//收藏
                ToastUtil.showToast("收藏失败");
            } else {//取消
                ToastUtil.showToast("取消收藏失败");
            }
        }
    }

    /**
     * 收藏 or 取消收藏 失败
     *
     * @param msg
     * @param type 0 收藏 1 取消
     */
    @Override
    public void loadCollectDataFail(String msg, String type) {
        if (type.equals("0")) {//收藏
            ToastUtil.showToast("收藏失败");
        } else {//取消
            ToastUtil.showToast("取消收藏失败");
        }
    }

}
