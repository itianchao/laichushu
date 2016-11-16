package com.laichushu.book.ui.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.menus.TxtBrightMenu;
import com.laichushu.book.menus.TxtProgressMenu;
import com.laichushu.book.menus.TxtTextMenu;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.R;
import com.laichushu.book.menus.TxtStyleMenu;
import com.laichushu.book.menus.TxtViewMenu;

import hwtxtreader.bean.TxtFile;
import hwtxtreader.bean.TxtLoadListsner;
import hwtxtreader.bean.Txterror;
import hwtxtreader.main.PageSeparateListener;
import hwtxtreader.main.TxtManager;
import hwtxtreader.main.TxtManagerImp;
import hwtxtreader.main.TxtPageChangeListsner;
import hwtxtreader.main.TxtReadView;
import hwtxtreader.main.TxtViewCenterAreaTouchListener;


public class BookPlayActivity extends Activity {

    private TxtReadView txtreadview;//阅读主页
    private TxtManager txtManager;
    private TxtViewMenu mMenu;//popwindow
    private TxtTextMenu mTxtTextMenu;//字体
    private RelativeLayout mTitlebar;//标题
    private TxtProgressMenu mProgressMenu;//进度
    private TxtBrightMenu mBrightMenu;//亮度
    private TxtStyleMenu mStyleMenu;//样式
    private RelativeLayout mLoadingView;
    private RelativeLayout mNodataView;
    private AnimationDrawable mAnimation;
    private TxtReadView mReadView;
    private TextView mTitle, mNodataText, mLoadingMsg;

    private String mBookname = "testbook";
    private int pageindex, pagenums;
    private Handler mHander;
    private Boolean isChangeText = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.txttest_activity_main);
        findview();
        initedata();
    }

    private void initedata() {

        String path;
        Intent intent = getIntent();
        mBookname = intent.getStringExtra("bookname");
        path = intent.getStringExtra("bookpath");

        TxtFile txtFile = new TxtFile();
        txtFile.setBookname(mBookname);
        showLoadingView("加载中...");
        txtManager = new TxtManagerImp(this, txtFile, new TxtLoadListsner() {

            @Override
            public void onLoadSucess() {

                showdataView();
            }

            @Override
            public void onError(Txterror txterror) {

                showNodataViewMsg(txterror.message);
            }

        });

        mMenu = new TxtViewMenu(this);
        mTxtTextMenu = new TxtTextMenu(this, SharePrefManager.getTextSize());
        mTitlebar.setVisibility(View.GONE);
        mStyleMenu = new TxtStyleMenu(this);
        mBrightMenu = new TxtBrightMenu(this);
        mProgressMenu = new TxtProgressMenu(this,mBookname);

        txtFile.setFilepath(path);
        txtreadview.setTxtManager(txtManager);
        registListener();
        startloadbook();
        //设置字体大小
        txtManager.getViewConfig().setTextSize(SharePrefManager.getTextSize());
        //设置样式
        txtManager.getViewConfig().setBackBroundColor(SharePrefManager.getReadState());
        if (SharePrefManager.getReadState() == R.drawable.reading__reading_themes_vine_dark) {
            txtManager.getViewConfig().setTextColor(Color.WHITE);
        } else {
            txtManager.getViewConfig().setTextColor(Color.BLACK);
        }
        //提交设置
        txtManager.CommitSetting();
    }

    private void startloadbook() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                txtManager.LoadFile();
            }
        }).start();
    }

    private void findview() {
        txtreadview = (TxtReadView) findViewById(R.id.txtreadView);
        mTitlebar = (RelativeLayout) findViewById(R.id.textview_titlebar);
        mTitle = (TextView) findViewById(R.id.textview_title_textview);
        mLoadingView = (RelativeLayout) findViewById(R.id.txtview_loadingview);
        mNodataView = (RelativeLayout) findViewById(R.id.txtview_nodataview);
        mNodataText = (TextView) findViewById(R.id.txtview_nodata_text);
        mLoadingMsg = (TextView) findViewById(R.id.txtview_loading_msg);
        mTitle.setText(mBookname);

    }

    private void registListener() {

        txtreadview.setOnTxtPageChangeListener(new TxtPageChangeListsner() {

            @Override
            public void onCurrentPage(int pageindex0, int pagenums0) {

                pageindex = pageindex0;
                pagenums = pagenums0;
                getHander().post(new Runnable() {

                    @Override
                    public void run() {

                        if (mProgressMenu != null) {
                            mProgressMenu.setPageIndex(pageindex, pagenums);
                        }
                    }
                });
            }
        });

        txtreadview.setOnPageSeparateListener(new PageSeparateListener() {

            @Override
            public void onSeparateStart() {
                showProgressmenuLoadingview();
            }

            @Override
            public void onSeparateDone() {
                showProgressmenuDataview();
            }
        });

        txtreadview.setOnTxtViewCenterAreaTouchListener(new TxtViewCenterAreaTouchListener() {

            @Override
            public void onOutSideAreaTouch() {
                hidememu();
                if (isChangeText) {
                    txtManager.separatepage();
                    isChangeText = false;
                }
            }

            @Override
            public void onAreaTouch() {
                showMenu();

            }
        });

        mLoadingView.setOnClickListener(new OnClickListener() {// ��������ڻ�ȡ�����㣬��ֹ�����¼���������
            @Override
            public void onClick(View arg0) {

            }
        });

        mNodataView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                initedata();
            }
        });

        mMenu.setOnDismissListener(new OnDismissListener() {// �ײ��˵���ʧ����

            @Override
            public void onDismiss() {
                getHander().post(new Runnable() {

                    @Override
                    public void run() {
                        mTitlebar.setVisibility(View.GONE);

                    }
                });

            }
        });

        mMenu.setOnTxtMenuClickListener(new TxtViewMenu.TxtMenuClockListener() {// �ײ��˵�ѡ�����

            @Override
            public void onTextMenuClicked() {

                showTextMenu();

            }

            @Override
            public void onStytleMenuClicked() {

                showStyleMune();

            }

            @Override
            public void onProgressMenuClicked() {

                showProgressMune();

            }

            @Override
            public void onLightMenuClicked() {

                showBrightMune();

            }

        });

        mTxtTextMenu.setonTxtTextChangeListener(new TxtTextMenu.onTxtTextChangeListener() {// �������ü���

            @Override
            public void onTextSizeChange(int spTextsize) {
                isChangeText = true;
                txtManager.getViewConfig().setTextSize(spTextsize);
                txtManager.CommitSetting();
                txtManager.jumptopage(1);
            }

            @Override
            public void onTextSortChange(String textsort) {
                isChangeText = true;
                txtManager.getViewConfig().setTextSort(textsort);
                txtManager.CommitSetting();
                txtManager.jumptopage(1);
            }

        });

        mProgressMenu.setonProgressChangeListener(new TxtProgressMenu.onProgressChangeListener() {// ������ת

            @Override
            public void onProgressChange(int inpageindex) {

                txtManager.jumptopage(inpageindex);

            }
        });

        mStyleMenu.setonTxtStyleChangeListener(new TxtStyleMenu.onTxtStyleChangeListener() {

            @Override
            public void onStyleChange(int stylecolor) {
                if (stylecolor == R.drawable.reading__reading_themes_vine_dark) {
                    txtManager.getViewConfig().setTextColor(Color.WHITE);
                } else {
                    txtManager.getViewConfig().setTextColor(Color.BLACK);
                }
                txtManager.CommitSetting();
                txtManager.getViewConfig().setBackBroundColor(stylecolor);
                txtManager.refreshBitmapBackground();
            }
        });
    }

    // ------------------------------------view---------------------------------------------------

    private void showProgressmenuLoadingview() {
        getHander().post(new Runnable() {

            @Override
            public void run() {
                if (mProgressMenu != null) {
                    mProgressMenu.showLoadingview();
                }
            }
        });

    }

    private void showProgressmenuDataview() {
        getHander().post(new Runnable() {

            @Override
            public void run() {

                if (mProgressMenu != null) {
                    mProgressMenu.setPageIndex(pageindex, pagenums);
                    mProgressMenu.showViewLayout();
                }
            }
        });
    }

    private void showMenu() {
        getHander().post(new Runnable() {

            @Override
            public void run() {

                View parent = BookPlayActivity.this.getWindow().getDecorView();
                mMenu.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
                mTitlebar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showTextMenu() {
        getHander().post(new Runnable() {

            @Override
            public void run() {

                mMenu.dismiss();
                View parent = BookPlayActivity.this.getWindow().getDecorView();
                if (!mTxtTextMenu.isShowing())
                    mTxtTextMenu.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    private void showBrightMune() {
        getHander().post(new Runnable() {

            @Override
            public void run() {

                mMenu.dismiss();
                View parent = BookPlayActivity.this.getWindow().getDecorView();
                mBrightMenu.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    private void showProgressMune() {
        getHander().post(new Runnable() {

            @Override
            public void run() {
                mMenu.dismiss();
                View parent = BookPlayActivity.this.getWindow().getDecorView();
                mProgressMenu.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            }
        });

    }

    private void showStyleMune() {
        getHander().post(new Runnable() {

            @Override
            public void run() {
                mMenu.dismiss();
                View parent = BookPlayActivity.this.getWindow().getDecorView();
                mStyleMenu.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            }
        });
    }

    private void showLoadingView(final String msg) {

        getHander().post(new Runnable() {
            @Override
            public void run() {
                mLoadingMsg.setText(msg);
                mTitlebar.setVisibility(View.GONE);
                mNodataView.setVisibility(View.GONE);

                if (mAnimation == null) {
                    ImageView imageView = (ImageView) findViewById(R.id.txtview_loading_img);
                    imageView.setBackgroundResource(R.anim.loadingdialog_animation);
                    mAnimation = (AnimationDrawable) imageView.getBackground();
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            mAnimation.start();
                        }
                    });
                }
                mLoadingView.setVisibility(View.VISIBLE);

            }
        });

    }

    private void showdataView() {
        getHander().post(new Runnable() {

            @Override
            public void run() {

                mTitlebar.setVisibility(View.GONE);
                mLoadingView.setVisibility(View.GONE);
                mNodataView.setVisibility(View.GONE);

            }
        });

    }

    private void showNodataViewMsg(final String msg) {
        getHander().post(new Runnable() {
            @Override
            public void run() {
                mNodataText.setText(msg);
                mTitlebar.setVisibility(View.VISIBLE);
                mNodataView.setVisibility(View.VISIBLE);
                mLoadingView.setVisibility(View.GONE);

            }
        });
    }

    private void hidememu() {
        getHander().post(new Runnable() {
            @Override
            public void run() {
                mMenu.dismiss();

            }
        });
    }

    private Handler getHander() {
        if (mHander == null) {
            mHander = new Handler();
        }
        return mHander;
    }

    public void finishactivity(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (txtManager != null) {
            txtManager.Clear();
        }
    }

    public TxtManager getTxtManager() {
        return txtManager;
    }

    public void setTxtManager(TxtManager txtManager) {
        this.txtManager = txtManager;
    }
}
