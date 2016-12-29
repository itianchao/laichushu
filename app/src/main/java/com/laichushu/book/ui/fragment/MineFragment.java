package com.laichushu.book.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.event.RefrushMineEvent;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.mvp.mine.MinePresenter;
import com.laichushu.book.mvp.mine.MineView;
import com.laichushu.book.ui.activity.EditMyselfeInforActivity;
import com.laichushu.book.ui.activity.FeedbackActivity;
import com.laichushu.book.ui.activity.FindServicePageActivity;
import com.laichushu.book.ui.activity.GeneralSettingActivity;
import com.laichushu.book.ui.activity.ManageWorksActivity;
import com.laichushu.book.ui.activity.MineServicePageActivity;
import com.laichushu.book.ui.activity.MyBookCastActivity;
import com.laichushu.book.ui.activity.MyWalletDetailsActivity;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 我的
 * Created by wangtong on 2016/10/17.
 */
public class MineFragment extends MvpFragment2<MinePresenter> implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, MineView {
    private TextView tvTitle, tvMineName, tvMinebookNum;
    private ImageView ivMineHead, ivMineHeadInto;
    private RelativeLayout rlHead, rlManage, rlBookCast, rlWallet, rlService, rlGeneralSetting, rlAdvice, rlBody;
    private PersonalCentreResult res = new PersonalCentreResult();
    private Cache_JsonDao cache_jsonDao;
    private SwipeRefreshLayout dataSwipe;

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    public View createSuccessView() {
        View mRootView = UIUtil.inflate(R.layout.fragment_mine);
        EventBus.getDefault().register(this);
        mRootView.findViewById(R.id.iv_title_finish).setVisibility(View.GONE);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        ivMineHead = (ImageView) mRootView.findViewById(R.id.iv_minaHead);
        ivMineHeadInto = (ImageView) mRootView.findViewById(R.id.iv_mineHeadInto);
        tvMineName = (TextView) mRootView.findViewById(R.id.tv_mineNickName);
        tvMinebookNum = (TextView) mRootView.findViewById(R.id.tv_mineBookNum);
        rlHead = ((RelativeLayout) mRootView.findViewById(R.id.rl_mainHead));
        rlBody = ((RelativeLayout) mRootView.findViewById(R.id.rl_headBody));
        rlManage = (RelativeLayout) mRootView.findViewById(R.id.rl_manage);
        rlBookCast = (RelativeLayout) mRootView.findViewById(R.id.rl_bookCast);
        rlWallet = (RelativeLayout) mRootView.findViewById(R.id.rl_Wallet);
        rlService = (RelativeLayout) mRootView.findViewById(R.id.rl_service);
        rlGeneralSetting = (RelativeLayout) mRootView.findViewById(R.id.rl_GeneralSetting);
        rlAdvice = (RelativeLayout) mRootView.findViewById(R.id.rl_Advice);
        dataSwipe = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_data);
        dataSwipe.setOnRefreshListener(this);
        dataSwipe.setColorSchemeColors(UIUtil.getColor(R.color.global));
        //initListener
        rlHead.setOnClickListener(this);
        rlManage.setOnClickListener(this);
        rlBookCast.setOnClickListener(this);
        rlWallet.setOnClickListener(this);
        rlService.setOnClickListener(this);
        rlGeneralSetting.setOnClickListener(this);
        rlAdvice.setOnClickListener(this);
        tvMineName.setOnClickListener(this);
        ivMineHead.setOnClickListener(this);
        ivMineHeadInto.setOnClickListener(this);
        rlBody.setOnClickListener(this);

        tvTitle.setText("个人中心");
        return mRootView;
    }

    @Override
    protected void initData() {
        super.initData();
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        cache_jsonDao = daoSession.getCache_JsonDao();
        getData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefrushMineEvent event) {
        EventBus.getDefault().removeStickyEvent(event);
        if (event.isRefursh) {
            initData();
        }
    }

    public void getData() {
        List<Cache_Json> cache_jsons = cache_jsonDao.queryBuilder().
                where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails")).build().list();
        PersonalCentreResult result = new Gson().fromJson(cache_jsons.get(0).getJson(), PersonalCentreResult.class);
        GlideUitl.loadRandImg(mActivity, result.getPhoto(), ivMineHead);
        tvMineName.setText("  " + result.getNickName());
        if (TextUtils.isEmpty(result.getArticleCount())) {
            tvMinebookNum.setText(0 + "部  ");
        } else {
            tvMinebookNum.setText(result.getArticleCount() + "部  ");
        }
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        res = result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_minaHead:
                //作品管理
                Bundle homePage = new Bundle();
                UIUtil.openActivity(mActivity, PersonalHomePageActivity.class, homePage);
                break;
            case R.id.tv_mineNickName:
            case R.id.iv_mineHeadInto:
            case R.id.rl_headBody:
                Intent editAct = new Intent(mActivity, EditMyselfeInforActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("result", res);
                editAct.putExtras(bundle);
                startActivity(editAct);
                break;
            case R.id.rl_manage:
                //作品管理
                Bundle manageBundle = new Bundle();
                UIUtil.openActivity(mActivity, ManageWorksActivity.class, manageBundle);
                break;
            case R.id.rl_bookCast:
                //我的书架
                Bundle bookCastBundle = new Bundle();
                UIUtil.openActivity(mActivity, MyBookCastActivity.class, bookCastBundle);
                break;
            case R.id.rl_Wallet:
                //我的钱包
                UIUtil.openActivity(mActivity, MyWalletDetailsActivity.class);
                break;
            case R.id.rl_service:
                //我的服务
                UIUtil.openActivity(mActivity, MineServicePageActivity.class);
                break;
            case R.id.rl_GeneralSetting:
                //通用设置
                UIUtil.openActivity(mActivity, GeneralSettingActivity.class);
                break;
            case R.id.rl_Advice:
                //意见反馈
                UIUtil.openActivity(mActivity, FeedbackActivity.class);
                break;
        }
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        mvpPresenter.loadData();
    }

    public class UpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ConstantValue.ACTION_UPDATE_DATA.equals(action)) {
                initData();
            }
        }
    }

    public void reLoadData() {
        mPage.setmListener(new LoadingPager.ReLoadDataListenListener() {
            @Override
            public void reLoadData() {
                refreshPage(LoadingPager.PageState.STATE_LOADING);
            }
        });
    }

    @Override
    public void getDataSuccess(PersonalCentreResult result) {
        dataSwipe.setRefreshing(false);
        if (result.getSuccess()) {
            //更新数据库
            List<Cache_Json> list = cache_jsonDao.queryBuilder().
                    where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails")).build().list();
            Cache_Json cache_json = list.get(0);
            cache_json.setJson(new Gson().toJson(result));
            cache_jsonDao.update(cache_json);
            //存储昵称
            SharePrefManager.setNickName(result.getNickName());
            getData();
        } else {
            ToastUtil.showToast(result.getErrMsg());
        }
    }

    @Override
    public void getDataFail(String msg) {
        ToastUtil.showToast("加载失败");
        dataSwipe.setRefreshing(false);
    }
}
