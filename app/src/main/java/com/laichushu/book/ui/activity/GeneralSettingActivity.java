package com.laichushu.book.ui.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;
import com.laichushu.book.db.Cache_Json;
import com.laichushu.book.db.Cache_JsonDao;
import com.laichushu.book.db.DaoSession;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.ui.base.BasePresenter;
import com.laichushu.book.ui.base.MvpActivity2;
import com.laichushu.book.ui.widget.LoadingPager;
import com.laichushu.book.utils.AppManager;
import com.laichushu.book.utils.GlideCacheUtil;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.io.File;

import okhttp3.Cache;

/**
 * 通用设置
 */
public class GeneralSettingActivity extends MvpActivity2 implements View.OnClickListener {
    private ImageView ivBack;
    private TextView tvTitle, tvAnother, tvCacheSize;
    private RelativeLayout rlAbout, rlSignOut, rlCleanCache;
    private String cacheSize =null;
    private Cache_JsonDao cache_jsonDao;
    private   Cache_Json personalDetails;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ToastUtil.showToast("清理缓存成功！");
                    tvCacheSize.setText( GlideCacheUtil.getInstance().getCacheSize(mActivity));
                    dismissProgressDialog();
                    break;
                case 2:
                    ToastUtil.showToast("清理缓存失败！");
                    dismissProgressDialog();
                    break;

            }
        }
    };

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected View createSuccessView() {
        View inflate = UIUtil.inflate(R.layout.activity_general_setting);
        ivBack = ((ImageView) inflate.findViewById(R.id.iv_title_finish));
        tvTitle = ((TextView) inflate.findViewById(R.id.tv_title));
        tvCacheSize = ((TextView) inflate.findViewById(R.id.tv_cacheSize));
        rlAbout = ((RelativeLayout) inflate.findViewById(R.id.rl_aboutApp));
        rlSignOut = ((RelativeLayout) inflate.findViewById(R.id.rl_signOut));
        rlCleanCache = ((RelativeLayout) inflate.findViewById(R.id.rl_cleanCache));
        tvAnother = ((TextView) inflate.findViewById(R.id.tv_title_right));
        return inflate;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("通用设置");
        tvAnother.setText("保存");
        DaoSession daoSession = BaseApplication.getDaoSession(mActivity);
        cache_jsonDao = daoSession.getCache_JsonDao();
        personalDetails= cache_jsonDao.queryBuilder()
                .where(Cache_JsonDao.Properties.Inter.eq("PersonalDetails")).list().get(0);
        tvAnother.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        try {
            cacheSize = GlideCacheUtil.getInstance().getCacheSize(mActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvCacheSize.setText(cacheSize);

        ivBack.setOnClickListener(this);
        rlAbout.setOnClickListener(this);
        rlSignOut.setOnClickListener(this);
        rlCleanCache.setOnClickListener(this);
        tvAnother.setOnClickListener(this);
        UIUtil.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshPage(LoadingPager.PageState.STATE_SUCCESS);
            }
        }, 30);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_finish:
                this.finish();
                break;
            case R.id.rl_cleanCache:
                if(cacheSize.equals("0.0Byte")){
                    ToastUtil.showToast("无需清理缓存！");
                }else{
                    cleanClideChcheMe(mActivity);
                    showProgressDialog();
                }

                break;
            case R.id.rl_aboutApp:
                //关于来出书
                UIUtil.openActivity(mActivity, AboutAppDetails.class);
                break;
            case R.id.rl_signOut:
                //退出
                SharePrefManager.setLoginInfo("");
                SharePrefManager.setUserId(null);
                cache_jsonDao.delete(personalDetails);
                AppManager.getInstance().killAllActivity();
                UIUtil.openActivity(mActivity, LoginActivity.class);
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
            case R.id.tv_title_right:
                //保存
                SharePrefManager.setCharacterNum("");
                SharePrefManager.setMsgState("");
                ToastUtil.showToast("保存成功！");
                this.finish();
                break;
        }
    }

    /**
     * 清除磁盘+内存缓存并获取内存大小
     *
     * @param context
     * @return
     */
    public void cleanClideChcheMe(final Context context) {
        new Thread() {
            @Override
            public void run() {

                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //清楚磁盘+内存缓存
                        Message message = new Message();
                        try {
                            GlideCacheUtil.getInstance().clearImageAllCache(context);
                            GlideCacheUtil.getInstance().deleteFolderFile((context.getCacheDir()).getPath(),true);
                            GlideCacheUtil.getInstance().deleteFolderFile((context.getDir(ConstantValue.LOCAL_PATH.FileCacheCompress, Context.MODE_PRIVATE)).getAbsolutePath(),true);
                                    message.what = 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                            message.what = 2;
                        }
                        handler.sendMessageDelayed(message, 2000);
                    }
                });
            }
        }.start();
    }
}
