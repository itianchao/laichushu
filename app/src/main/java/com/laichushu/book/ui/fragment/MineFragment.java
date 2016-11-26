package com.laichushu.book.ui.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.netbean.FeedBack_parmet;
import com.laichushu.book.bean.netbean.PersonalCentreResult;
import com.laichushu.book.bean.netbean.PersonalCentre_Parmet;
import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.ui.activity.EditMyselfeInforActivity;
import com.laichushu.book.ui.activity.FeedbackActivity;
import com.laichushu.book.ui.activity.GeneralSettingActivity;
import com.laichushu.book.ui.activity.ManageWorksActivity;
import com.laichushu.book.ui.activity.MyBookCastActivity;
import com.laichushu.book.ui.activity.MyWalletDetailsActivity;
import com.laichushu.book.ui.activity.PersonalHomePageActivity;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpFragment2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * 我的
 * Created by wangtong on 2016/10/17.
 */
public class MineFragment extends MvpFragment2 implements View.OnClickListener {
    private TextView tvTitle, tvMineName, tvMinebookNum;
    private ImageView ivMineHead, ivMineHeadInto;
    private RelativeLayout rlHead, rlManage, rlBookCast, rlWallet, rlService, rlGeneralSetting, rlAdvice;
    private PersonalCentreResult res = new PersonalCentreResult();
    private UpdateReceiver mUpdateReceiver;
    private Cache_JsonDao cache_jsonDao;

    @Override
    protected BasePresenter createPresenter() {

        return null;
    }

    @Override
    public View createSuccessView() {
        View mRootView = UIUtil.inflate(R.layout.fragment_mine);
        mRootView.findViewById(R.id.iv_title_finish).setVisibility(View.GONE);
        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title);
        ivMineHead = (ImageView) mRootView.findViewById(R.id.iv_minaHead);
        ivMineHeadInto = (ImageView) mRootView.findViewById(R.id.iv_mineHeadInto);
        tvMineName = (TextView) mRootView.findViewById(R.id.tv_mineNickName);
        tvMinebookNum = (TextView) mRootView.findViewById(R.id.tv_mineBookNum);
        rlHead = ((RelativeLayout) mRootView.findViewById(R.id.rl_mainHead));
        rlManage = (RelativeLayout) mRootView.findViewById(R.id.rl_manage);
        rlBookCast = (RelativeLayout) mRootView.findViewById(R.id.rl_bookCast);
        rlWallet = (RelativeLayout) mRootView.findViewById(R.id.rl_Wallet);
        rlService = (RelativeLayout) mRootView.findViewById(R.id.rl_service);
        rlGeneralSetting = (RelativeLayout) mRootView.findViewById(R.id.rl_GeneralSetting);
        rlAdvice = (RelativeLayout) mRootView.findViewById(R.id.rl_Advice);
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

    public void getData() {
        registerPlayerReceiver();
        QueryBuilder<Cache_Json> userQueryBuilder = cache_jsonDao.queryBuilder();
        QueryBuilder<Cache_Json> builder = userQueryBuilder.where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails"));
        Query<Cache_Json> build = builder.build();
        List<Cache_Json> cache_jsons = build.list();
        PersonalCentreResult result = new Gson().fromJson(cache_jsons.get(0).getJson(), PersonalCentreResult.class);
        GlideUitl.loadRandImg(mActivity, result.getPhoto(), ivMineHead);
        tvMineName.setText("  " + result.getNickName());
        tvMinebookNum.setText(result.getArticleCount() + "部  ");
        refreshPage(LoadingPager.PageState.STATE_SUCCESS);
        res = result;
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

    public class UpdateReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ConstantValue.ACTION_UPDATE_DATA.equals(action)) {
                initData();
                ToastUtil.showToast("update date!");
            }
        }
    }

    private void registerPlayerReceiver() {
        if (mUpdateReceiver == null) {
            mUpdateReceiver = new UpdateReceiver();

            IntentFilter filter = new IntentFilter();
            filter.addCategory(getActivity().getPackageName());

            filter.addAction(ConstantValue.ACTION_UPDATE_DATA);
            mActivity.registerReceiver(mUpdateReceiver, filter);
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

}
