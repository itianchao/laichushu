/*
 * Copyright (C) 2009-2015 FBReader.ORG Limited <contact@fbreader.org>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.android.fbreader;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.gitonway.lee.niftymodaldialogeffects.lib.effects.NewsPaper;
import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.JsonBean.BalanceBean;
import com.laichushu.book.bean.JsonBean.RewardResult;
import com.laichushu.book.bean.netbean.Balance_Paramet;
import com.laichushu.book.bean.netbean.RewardMoney_Paramet;
import com.laichushu.book.bean.otherbean.BaseBookEntity;
import com.laichushu.book.bean.otherbean.BookSelectOptionBean;
import com.laichushu.book.db.Idea_Table;
import com.laichushu.book.db.Idea_TableDao;
import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiCallback;
import com.laichushu.book.retrofit.ApiStores;
import com.laichushu.book.retrofit.AppClient;
import com.laichushu.book.ui.activity.ReportActivity;
import com.laichushu.book.ui.widget.LoadDialog;
import com.laichushu.book.ui.widget.TypeBookSelectWindow;
import com.laichushu.book.utils.Base64Utils;
import com.laichushu.book.utils.GlideUitl;
import com.laichushu.book.utils.LoggerUtil;
import com.laichushu.book.utils.ShareUtil;
import com.laichushu.book.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import org.geometerplus.android.fbreader.api.ApiListener;
import org.geometerplus.android.fbreader.api.ApiServerImplementation;
import org.geometerplus.android.fbreader.api.FBReaderIntents;
import org.geometerplus.android.fbreader.api.PluginApi;
import org.geometerplus.android.fbreader.dict.DictionaryUtil;
import org.geometerplus.android.fbreader.httpd.DataService;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.android.fbreader.sync.SyncOperations;
import org.geometerplus.android.fbreader.tips.TipsActivity;
import org.geometerplus.android.util.DeviceType;
import org.geometerplus.android.util.SearchDialogUtil;
import org.geometerplus.android.util.UIMessageUtil;
import org.geometerplus.android.util.UIUtil;
import org.geometerplus.fbreader.Paths;
import org.geometerplus.fbreader.book.Book;
import org.geometerplus.fbreader.book.BookUtil;
import org.geometerplus.fbreader.book.Bookmark;
import org.geometerplus.fbreader.book.BookmarkQuery;
import org.geometerplus.fbreader.bookmodel.BookModel;
import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.fbreader.fbreader.DictionaryHighlighting;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.fbreader.fbreader.SelectionClearAction;
import org.geometerplus.fbreader.fbreader.options.CancelMenuHelper;
import org.geometerplus.fbreader.fbreader.options.ColorProfile;
import org.geometerplus.fbreader.tips.TipsManager;
import org.geometerplus.zlibrary.core.application.ZLApplicationWindow;
import org.geometerplus.zlibrary.core.filesystem.ZLFile;
import org.geometerplus.zlibrary.core.library.ZLibrary;
import org.geometerplus.zlibrary.core.options.Config;
import org.geometerplus.zlibrary.core.util.ZLColor;
import org.geometerplus.zlibrary.core.view.ZLView;
import org.geometerplus.zlibrary.core.view.ZLViewWidget;
import org.geometerplus.zlibrary.text.view.ZLTextRegion;
import org.geometerplus.zlibrary.text.view.ZLTextView;
import org.geometerplus.zlibrary.ui.android.error.ErrorKeys;
import org.geometerplus.zlibrary.ui.android.library.ZLAndroidApplication;
import org.geometerplus.zlibrary.ui.android.library.ZLAndroidLibrary;
import org.geometerplus.zlibrary.ui.android.view.AndroidFontUtil;
import org.geometerplus.zlibrary.ui.android.view.ZLAndroidWidget;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public final class FBReader extends FBReaderMainActivity implements ZLApplicationWindow {
    public static final int RESULT_DO_NOTHING = RESULT_FIRST_USER;
    public static final int RESULT_REPAINT = RESULT_FIRST_USER + 1;
    public static final int REQUEST_EDITBOOKMARK = 6;
    public static final int RESULT_SELECTCOLOR = 7;
    private static TextView titleTv;
    private ImageView finishIv;
    private ImageView selectIv;
    ArrayList<BookSelectOptionBean> list = new ArrayList();
    private Timer timer;
    private ImageView rewardMoneyIv;

    public static Intent defaultIntent(Context context) {
        return new Intent(context, FBReader.class)
                .setAction(FBReaderIntents.Action.VIEW)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    public static void openBookActivity(Context context, Book book, Bookmark bookmark) {
        final Intent intent = defaultIntent(context);
        FBReaderIntents.putBookExtra(intent, book);
        FBReaderIntents.putBookmarkExtra(intent, bookmark);
        context.startActivity(intent);
    }

    private FBReaderApp myFBReaderApp;
    private volatile Book myBook;

    private RelativeLayout myRootView;
    public static ZLAndroidWidget myMainView;

    private volatile boolean myShowStatusBarFlag;
    private String myMenuLanguage;

    final DataService.Connection DataConnection = new DataService.Connection();

    volatile boolean IsPaused = false;
    private volatile long myResumeTimestamp;
    volatile Runnable OnResumeAction = null;

    private Intent myCancelIntent = null;
    private Intent myOpenBookIntent = null;

    private final FBReaderApp.Notifier myNotifier = new AppNotifier(this);

    private static final String PLUGIN_ACTION_PREFIX = "___";
    private final List<PluginApi.ActionInfo> myPluginActions =
            new LinkedList<PluginApi.ActionInfo>();
    private final BroadcastReceiver myPluginInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final ArrayList<PluginApi.ActionInfo> actions = getResultExtras(true).<PluginApi.ActionInfo>getParcelableArrayList(PluginApi.PluginInfo.KEY);
            if (actions != null) {
                synchronized (myPluginActions) {
                    int index = 0;
                    while (index < myPluginActions.size()) {
                        myFBReaderApp.removeAction(PLUGIN_ACTION_PREFIX + index++);
                    }
                    myPluginActions.addAll(actions);
                    index = 0;
                    for (PluginApi.ActionInfo info : myPluginActions) {
                        myFBReaderApp.addAction(
                                PLUGIN_ACTION_PREFIX + index++,
                                new RunPluginAction(FBReader.this, myFBReaderApp, info.getId())
                        );
                    }
                }
            }
        }
    };

    private synchronized void openBook(Intent intent, final Runnable action, boolean force) {
        if (!force && myBook != null) {
            return;
        }

        myBook = FBReaderIntents.getBookExtra(intent, myFBReaderApp.Collection);
        final Bookmark bookmark = FBReaderIntents.getBookmarkExtra(intent);
        if (myBook == null) {
            BaseBookEntity baseBookEntity = (BaseBookEntity) intent.getSerializableExtra(ConstantValue.BASEBOOK);
            Uri data = null;
            if (baseBookEntity!=null){
                data = Uri.parse(baseBookEntity.getBook_path());
            }else {
                data = intent.getData();
            }
            if (data != null) {
                myBook = createBookForFile(ZLFile.createFileByPath(data.getPath()));
            }
        }
        if (myBook != null) {
            ZLFile file = BookUtil.fileByBook(myBook);
            if (!file.exists()) {
                if (file.getPhysicalFile() != null) {
                    file = file.getPhysicalFile();
                }
                UIMessageUtil.showErrorMessage(this, "fileNotFound", file.getPath());
                myBook = null;
            } else {
                NotificationUtil.drop(this, myBook);
            }
        }
        Config.Instance().runOnConnect(new Runnable() {
            public void run() {
                myFBReaderApp.openBook(myBook, bookmark, action, myNotifier);
                AndroidFontUtil.clearFontCache();
            }
        });
    }

    private Book createBookForFile(ZLFile file) {
        if (file == null) {
            return null;
        }
        Book book = myFBReaderApp.Collection.getBookByFile(file.getPath());
        if (book != null) {
            return book;
        }
        if (file.isArchive()) {
            for (ZLFile child : file.children()) {
                book = myFBReaderApp.Collection.getBookByFile(child.getPath());
                if (book != null) {
                    return book;
                }
            }
        }
        return null;
    }

    private Runnable getPostponedInitAction() {
        return new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        new TipRunner().start();
                        DictionaryUtil.init(FBReader.this, null);
                        final Intent intent = getIntent();
                        if (intent != null && FBReaderIntents.Action.PLUGIN.equals(intent.getAction())) {
                            new RunPluginAction(FBReader.this, myFBReaderApp, intent.getData()).run();
                        }
                    }
                });
            }
        };
    }

    public ZLAndroidWidget getMyMainView() {
        return myMainView;
    }

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        bindService(
                new Intent(this, DataService.class),
                DataConnection,
                DataService.BIND_AUTO_CREATE
        );

        final Config config = Config.Instance();
        config.runOnConnect(new Runnable() {
            public void run() {
                config.requestAllValuesForGroup("Options");
                config.requestAllValuesForGroup("Style");
                config.requestAllValuesForGroup("LookNFeel");
                config.requestAllValuesForGroup("Fonts");
                config.requestAllValuesForGroup("Colors");
                config.requestAllValuesForGroup("Files");
            }
        });

        final ZLAndroidLibrary zlibrary = getZLibrary();
        myShowStatusBarFlag = zlibrary.ShowStatusBarOption.getValue();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        myRootView = (RelativeLayout) findViewById(R.id.root_view);
        myMainView = (ZLAndroidWidget) findViewById(R.id.main_view);

        titleTv = (TextView) findViewById(R.id.tv_title);
        finishIv = (ImageView) findViewById(R.id.iv_title_finish);
        selectIv = (ImageView) findViewById(R.id.iv_title_other);//选择弹窗 举报or书签
        rewardMoneyIv = (ImageView) findViewById(R.id.iv_title_another);//打赏
        selectIv.setScaleType(ImageView.ScaleType.CENTER);
        rewardMoneyIv.setScaleType(ImageView.ScaleType.CENTER);
        GlideUitl.loadImg2(this, R.drawable.icon_more2x, selectIv);
        GlideUitl.loadImg2(this, R.drawable.reward, rewardMoneyIv);

        //弹窗
        list.clear();
        list.add(new BookSelectOptionBean("举报", R.drawable.icon_report));
        list.add(new BookSelectOptionBean("书签", R.drawable.icon_bookmark));
        list.add(new BookSelectOptionBean("分享", R.drawable.icon_share));
        selectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopwindow(v, list);
            }
        });
        //打赏
        rewardMoneyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBalace();
            }
        });
        //关闭
        finishIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);

        if (myFBReaderApp == null) {
            myFBReaderApp = new FBReaderApp(Paths.systemInfo(this), new BookCollectionShadow());
        }
        getCollection().bindToService(this, null);
        myBook = null;

        myFBReaderApp.setWindow(this);
        myFBReaderApp.initWindow();
        //仿真翻页
        myFBReaderApp.PageTurningOptions.Animation.setValue(ZLView.Animation.curl);
        myFBReaderApp.setExternalFileOpener(new ExternalFileOpener(this));
        myFBReaderApp = (FBReaderApp) FBReaderApp.Instance();

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                myShowStatusBarFlag ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        if (myFBReaderApp.getPopupById(TextSearchPopup.ID) == null) {
            new TextSearchPopup(myFBReaderApp);
        }
        if (myFBReaderApp.getPopupById(NavigationPopup.ID) == null) {
            new NavigationPopup(myFBReaderApp);
        }
        if (myFBReaderApp.getPopupById(SettingPopup.ID) == null) {
            new SettingPopup(myFBReaderApp);
        }
        if (myFBReaderApp.getPopupById(SelectionPopup.ID) == null) {
            new SelectionPopup(myFBReaderApp);
        }
        if (myFBReaderApp.getPopupById(SettingFontPopup.ID) == null) {
            new SettingFontPopup(myFBReaderApp);
        }
        if (myFBReaderApp.getPopupById(SettingProgressPopup.ID) == null) {
            new SettingProgressPopup(myFBReaderApp);
        }
        if (myFBReaderApp.getPopupById(SettingModlePopup.ID) == null) {
            new SettingModlePopup(myFBReaderApp);
        }
        myFBReaderApp.addAction(ActionCode.SHOW_LIBRARY, new ShowLibraryAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SHOW_PREFERENCES, new ShowPreferencesAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SHOW_BOOK_INFO, new ShowBookInfoAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SHOW_TOC, new ShowTOCAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SHOW_BOOKMARKS, new ShowBookmarksAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SHOW_NETWORK_LIBRARY, new ShowNetworkLibraryAction(this, myFBReaderApp));

        myFBReaderApp.addAction(ActionCode.SHOW_MENU, new ShowMenuAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SHOW_NAVIGATION, new ShowNavigationAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SEARCH, new SearchAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SHARE_BOOK, new ShareBookAction(this, myFBReaderApp));

        myFBReaderApp.addAction(ActionCode.SELECTION_SHOW_PANEL, new SelectionShowPanelAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SELECTION_HIDE_PANEL, new SelectionHidePanelAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SELECTION_COPY_TO_CLIPBOARD, new SelectionCopyAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SELECTION_SHARE, new SelectionShareAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SELECTION_TRANSLATE, new SelectionTranslateAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.SELECTION_BOOKMARK, new SelectionBookmarkAction(this, myFBReaderApp));

        myFBReaderApp.addAction(ActionCode.DISPLAY_BOOK_POPUP, new DisplayBookPopupAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.PROCESS_HYPERLINK, new ProcessHyperlinkAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.OPEN_VIDEO, new OpenVideoAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.HIDE_TOAST, new HideToastAction(this, myFBReaderApp));

//        myFBReaderApp.addAction(ActionCode.SHOW_CANCEL_MENU, new ShowCancelMenuAction(this, myFBReaderApp));//这是exit的menu
        myFBReaderApp.addAction(ActionCode.OPEN_START_SCREEN, new StartScreenAction(this, myFBReaderApp));

        myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_SYSTEM, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_SYSTEM));
        myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_SENSOR, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_SENSOR));
        myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_PORTRAIT, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_PORTRAIT));
        myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_LANDSCAPE, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_LANDSCAPE));
        myFBReaderApp.addAction(ActionCode.SELECTION_CLEAR, new SelectionClearAction(this,myFBReaderApp));
        if (getZLibrary().supportsAllOrientations()) {
            myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_REVERSE_PORTRAIT, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_REVERSE_PORTRAIT));
            myFBReaderApp.addAction(ActionCode.SET_SCREEN_ORIENTATION_REVERSE_LANDSCAPE, new SetScreenOrientationAction(this, myFBReaderApp, ZLibrary.SCREEN_ORIENTATION_REVERSE_LANDSCAPE));
        }
        myFBReaderApp.addAction(ActionCode.OPEN_WEB_HELP, new OpenWebHelpAction(this, myFBReaderApp));
        myFBReaderApp.addAction(ActionCode.INSTALL_PLUGINS, new InstallPluginsAction(this, myFBReaderApp));

        myFBReaderApp.addAction(ActionCode.SWITCH_TO_DAY_PROFILE, new SwitchProfileAction(this, myFBReaderApp, ColorProfile.DAY));
        myFBReaderApp.addAction(ActionCode.SWITCH_TO_NIGHT_PROFILE, new SwitchProfileAction(this, myFBReaderApp, ColorProfile.NIGHT));

        final Intent intent = getIntent();
        final String action = intent.getAction();

        myOpenBookIntent = intent;
        if ((intent.getFlags() & Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) == 0) {
            if (FBReaderIntents.Action.CLOSE.equals(action)) {
                myCancelIntent = intent;
                myOpenBookIntent = null;
            } else if (FBReaderIntents.Action.PLUGIN_CRASH.equals(action)) {
                myFBReaderApp.ExternalBook = null;
                myOpenBookIntent = null;
                getCollection().bindToService(this, new Runnable() {
                    public void run() {
                        myFBReaderApp.openBook(null, null, null, myNotifier);
                    }
                });
            }
        }
    }

    private static Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String title = (String) msg.obj;
            if (title!=null){
                titleTv.setText(title);
            }
            return false;
        }
    });

    public static void setBookTitle(String mBookTitle) {
        if (titleTv!=null&&TextUtils.isEmpty(titleTv.getText())){
            Message msg = new Message();
            msg.obj = mBookTitle;
            mHandler.sendMessage(msg);
        }
    }

    //标题按钮
    private void openPopwindow(View v, List<BookSelectOptionBean> datas) {
        final TypeBookSelectWindow popWindow = new TypeBookSelectWindow(this, datas);
        popWindow.setListItemClickListener(new TypeBookSelectWindow.IListItemClickListener() {
            @Override
            public void clickItem(int position) {
                if ((position == 0)) {// TODO: 2016/12/8  举报
                    Bundle bundle = new Bundle();
                    String articleId = getIntent().getStringExtra("articleId");
                    if (!TextUtils.isEmpty(articleId)){
                        bundle.putString("articleId", articleId);
                        com.laichushu.book.utils.UIUtil.openActivity(FBReader.this, ReportActivity.class,bundle);
                    }

                } else if((position == 1)){//书签
                    BookCollectionShadow myCollection = getCollection();
                    Bookmark bookmark = myFBReaderApp.createBookmark(30, true);
                    bookmark.setStyleId(5);
//                    bookmark.setText("");
                    for (BookmarkQuery query = new BookmarkQuery(myBook, 10000); ; query = query.next()) {
                        final List<Bookmark> thisBookBookmarks = myCollection.bookmarks(query);
                        if (thisBookBookmarks.isEmpty()) {
                            myCollection.saveBookmark(bookmark);
                            ToastUtil.showToast("加入书签");
                            break;
                        } else {
                            for (Bookmark mbook : thisBookBookmarks) {
                                if ((mbook.getStyleId() == 5 && mbook.getParagraphIndex() == (bookmark.getParagraphIndex()))) {
                                    ToastUtil.showToast("书签已存在");
                                    return;
                                }
                            }
                            myCollection.saveBookmark(bookmark);
                            ToastUtil.showToast("加入书签");
                        }
                    }

                }else {
                    String articleId = getIntent().getStringExtra("articleId");
                    String photo = getIntent().getStringExtra("photo");
                    String brife = getIntent().getStringExtra("brife");
                    String bookName = getIntent().getStringExtra("bookName");
                    String shareContent = "#来出书邀请您看好书#一起来看<<"+bookName+">>吧！";
                    String linkUrl= Base64Utils.getStringUrl(articleId,ConstantValue.SHARE_TYPR_BOOK);
                    ShareUtil.showShare(FBReader.this, linkUrl,shareContent,photo,brife,titleTv.getText().toString());
                }
            }
        });
        popWindow.setWidth(com.laichushu.book.utils.UIUtil.dip2px(100));
        popWindow.setHeight(com.laichushu.book.utils.UIUtil.dip2px(135));
        popWindow.showAsDropDown(v, 0, com.laichushu.book.utils.UIUtil.dip2px(10));
    }

//打赏 先查询余额 =========================================================================

    /**
     * 查询余额
     */
    public void getBalace() {
        showProgressDialog("加载中请稍候");
        Balance_Paramet paramet = new Balance_Paramet(ConstantValue.USERID);
        Logger.e("余额参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.getBalance(paramet), new ApiCallback<BalanceBean>() {
            @Override
            public void onSuccess(BalanceBean model) {
                dismissProgressDialog();
                if (model.isSuccess()) {
                    String balance = model.getData().getMoney();
                    double maxLimit = model.getData().getMaxLimit();
                    double minLimit = model.getData().getMinLimit();
                    String accepterId = getIntent().getStringExtra("authorId");
                    String articleId = getIntent().getStringExtra("articleId");
                    if (accepterId!=null&&articleId!=null){
                        openReward(balance + "", accepterId, articleId,maxLimit,minLimit);
                    }
                } else {
                    ToastUtil.showToast(model.getErrMsg());
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                dismissProgressDialog();
                LoggerUtil.e("code+" + code + "/msg:" + msg);
                ToastUtil.showToast("打赏失败");
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }

    /**
     * 打赏对话框
     * @param balance   余额
     * @param accepterId  被打赏者
     * @param articleId  书id
     * @param maxLimit
     * @param minLimit
     */
    private void openReward(String balance, final String accepterId, final String articleId, final double maxLimit, final double minLimit){
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        final View customerView = com.laichushu.book.utils.UIUtil.inflate(R.layout.dialog_reward);
        final EditText payEt = (EditText) customerView.findViewById(R.id.et_pay);
        TextView balanceTv = (TextView) customerView.findViewById(R.id.tv_balance);
        balanceTv.setText("当前余额："+balance);
        payEt.setHint("只能打赏"+minLimit+"-"+maxLimit+"金额");
        //取消
        customerView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });
        //确认
        customerView.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pay = payEt.getText().toString();

                if (TextUtils.isEmpty(pay)) {
                    ToastUtil.showToast("请输入打赏金额");
                } else {
                    try {
                        if (Double.parseDouble(pay) >= minLimit && Double.parseDouble(pay) <= maxLimit) {
                            if (pay.contains(".") && pay.substring(pay.indexOf(".")+1,pay.length()-1).length()>2) {
                                ToastUtil.showToast("不能超过小数点后两位");
                            } else {
                                // TODO: 2016/11/8 请求打赏
                                rewardMoney(ConstantValue.USERID, accepterId, articleId, pay);
                                dialogBuilder.dismiss();
                            }

                        } else {
                            ToastUtil.showToast("只能打赏"+minLimit+"-"+maxLimit+"金额");
                        }
                    } catch (NumberFormatException e) {
                        ToastUtil.showToast("请输入正确的价格");
                    }
                }
            }
        });
        dialogBuilder
                .withTitle(null)                                  // 为null时不显示title
                .withDialogColor("#FFFFFF")                       // 设置对话框背景色                               //def
                .isCancelableOnTouchOutside(true)                 // 点击其他地方或按返回键是否可以关闭对话框
                .withDuration(500)                                // 对话框动画时间
                .withEffect(Effectstype.Slidetop)                 // 动画形式
                .setCustomView(customerView, this)                // 添加自定义View
                .show();
    }

    /**
     * 打赏请求
     */
    /**
     * 打赏请求
     *
     * @param money
     * @param articleId
     * @param accepterId
     * @param awarderId
     */
    public void rewardMoney(String awarderId, String accepterId, String articleId, String money) {
        showProgressDialog("加载中");
        RewardMoney_Paramet paramet = new RewardMoney_Paramet(awarderId, accepterId, articleId, money);
        Logger.e("打赏参数");
        Logger.json(new Gson().toJson(paramet));
        addSubscription(apiStores.rewardMoney(paramet), new ApiCallback<RewardResult>() {
            @Override
            public void onSuccess(RewardResult model) {
                dismissProgressDialog();
                if (model.isSuccess()) {
                    ToastUtil.showToast("打赏成功，感谢支持");
                } else {
                    ToastUtil.showToast(model.getErrMsg());
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                dismissProgressDialog();
                LoggerUtil.e("code+" + code + "/msg:" + msg);
                ToastUtil.showToast("打赏失败");
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }
//==================================================================================================================================================
//	@Override
//	public boolean onPrepareOptionsMenu(Menu menu) {
//		setStatusBarVisibility(true);
//		setupMenu(menu);
//
//		return super.onPrepareOptionsMenu(menu);
//	}
//
//	@Override
//	public void onOptionsMenuClosed(Menu menu) {
//		super.onOptionsMenuClosed(menu);
//		setStatusBarVisibility(false);
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		setStatusBarVisibility(false);
//		return super.onOptionsItemSelected(item);
//	}

    //	@Override
//	protected void onNewIntent(final Intent intent) {
//		final String action = intent.getAction();
//		final Uri data = intent.getData();
//
//		if ((intent.getFlags() & Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY) != 0) {
//			super.onNewIntent(intent);
//		} else if (Intent.ACTION_VIEW.equals(action)
//				&& data != null && "fbreader-action".equals(data.getScheme())) {
//			myFBReaderApp.runAction(data.getEncodedSchemeSpecificPart(), data.getFragment());
//		} else if (Intent.ACTION_VIEW.equals(action) || FBReaderIntents.Action.VIEW.equals(action)) {
//			myOpenBookIntent = intent;
//			if (myFBReaderApp.Model == null && myFBReaderApp.ExternalBook != null) {
//				final BookCollectionShadow collection = getCollection();
//				final Book b = FBReaderIntents.getBookExtra(intent, collection);
//				if (!collection.sameBook(b, myFBReaderApp.ExternalBook)) {
//					try {
//						final ExternalFormatPlugin plugin =
//								(ExternalFormatPlugin)BookUtil.getPlugin(
//										PluginCollection.Instance(Paths.systemInfo(this)),
//										myFBReaderApp.ExternalBook
//								);
//						startActivity(PluginUtil.createIntent(plugin, FBReaderIntents.Action.PLUGIN_KILL));
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		} else if (FBReaderIntents.Action.PLUGIN.equals(action)) {
//			new RunPluginAction(this, myFBReaderApp, data).run();
//		} else if (Intent.ACTION_SEARCH.equals(action)) {
//			final String pattern = intent.getStringExtra(SearchManager.QUERY);
//			final Runnable runnable = new Runnable() {
//				public void run() {
//					final TextSearchPopup popup = (TextSearchPopup)myFBReaderApp.getPopupById(TextSearchPopup.ID);
//					popup.initPosition();
//					myFBReaderApp.MiscOptions.TextSearchPattern.setValue(pattern);
//					if (myFBReaderApp.getTextView().search(pattern, true, false, false, false) != 0) {
//						runOnUiThread(new Runnable() {
//							public void run() {
//								myFBReaderApp.showPopup(popup.getId());
//							}
//						});
//					} else {
//						runOnUiThread(new Runnable() {
//							public void run() {
//								UIMessageUtil.showErrorMessage(FBReader.this, "textNotFound");
//								popup.StartPosition = null;
//							}
//						});
//					}
//				}
//			};
//			UIUtil.wait("search", runnable, this);
//		} else if (FBReaderIntents.Action.CLOSE.equals(intent.getAction())) {
//			myCancelIntent = intent;
//			myOpenBookIntent = null;
//		} else if (FBReaderIntents.Action.PLUGIN_CRASH.equals(intent.getAction())) {
//			final Book book = FBReaderIntents.getBookExtra(intent, myFBReaderApp.Collection);
//			myFBReaderApp.ExternalBook = null;
//			myOpenBookIntent = null;
//			getCollection().bindToService(this, new Runnable() {
//				public void run() {
//					final BookCollectionShadow collection = getCollection();
//					Book b = collection.getRecentBook(0);
//					if (collection.sameBook(b, book)) {
//						b = collection.getRecentBook(1);
//					}
//					myFBReaderApp.openBook(b, null, null, myNotifier);
//				}
//			});
//		} else {
//			super.onNewIntent(intent);
//		}
//	}
    @Override
    protected void onStart() {
        super.onStart();

        getCollection().bindToService(this, new Runnable() {
            public void run() {
                new Thread() {
                    public void run() {
                        getPostponedInitAction().run();
                    }
                }.start();

                myFBReaderApp.getViewWidget().repaint();
            }
        });

        initPluginActions();

        final ZLAndroidLibrary zlibrary = getZLibrary();


        Config.Instance().runOnConnect(new Runnable() {

            public void run() {
                final boolean showStatusBar = zlibrary.ShowStatusBarOption.getValue();
                if (showStatusBar != myShowStatusBarFlag) {
                    finish();
                    startActivity(new Intent(FBReader.this, FBReader.class));
                }
                zlibrary.ShowStatusBarOption.saveSpecialValue();
                myFBReaderApp.ViewOptions.ColorProfileName.saveSpecialValue();
                SetScreenOrientationAction.setOrientation(FBReader.this, zlibrary.getOrientationOption().getValue());
            }
        });

        ((PopupPanel) myFBReaderApp.getPopupById(TextSearchPopup.ID)).setPanelInfo(this, myRootView);
        ((NavigationPopup) myFBReaderApp.getPopupById(NavigationPopup.ID)).setPanelInfo(this, myRootView);
        ((PopupPanel) myFBReaderApp.getPopupById(SelectionPopup.ID)).setPanelInfo(this, myRootView);
        ((SettingPopup) myFBReaderApp.getPopupById(SettingPopup.ID)).setPanelInfo(this, myRootView);
        ((SettingFontPopup) myFBReaderApp.getPopupById(SettingFontPopup.ID)).setPanelInfo(this, myRootView);
        ((SettingProgressPopup) myFBReaderApp.getPopupById(SettingProgressPopup.ID)).setPanelInfo(this, myRootView);
        ((SettingModlePopup) myFBReaderApp.getPopupById(SettingModlePopup.ID)).setPanelInfo(this, myRootView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        switchWakeLock(hasFocus &&
                getZLibrary().BatteryLevelToTurnScreenOffOption.getValue() <
                        myFBReaderApp.getBatteryLevel()
        );
    }

    private void initPluginActions() {
        synchronized (myPluginActions) {
            int index = 0;
            while (index < myPluginActions.size()) {
                myFBReaderApp.removeAction(PLUGIN_ACTION_PREFIX + index++);
            }
            myPluginActions.clear();
        }

        sendOrderedBroadcast(
                new Intent(PluginApi.ACTION_REGISTER),
                null,
                myPluginInfoReceiver,
                null,
                RESULT_OK,
                null,
                null
        );
    }

    private class TipRunner extends Thread {
        TipRunner() {
            setPriority(MIN_PRIORITY);
        }

        public void run() {
            final TipsManager manager = new TipsManager(Paths.systemInfo(FBReader.this));
            switch (manager.requiredAction()) {
                case Initialize:
                    startActivity(new Intent(
                            TipsActivity.INITIALIZE_ACTION, null, FBReader.this, TipsActivity.class
                    ));
                    break;
                case Show:
                    startActivity(new Intent(
                            TipsActivity.SHOW_TIP_ACTION, null, FBReader.this, TipsActivity.class
                    ));
                    break;
                case Download:
                    manager.startDownloading();
                    break;
                case None:
                    break;
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        myStartTimer = true;
        Config.Instance().runOnConnect(new Runnable() {
            public void run() {
                SyncOperations.enableSync(FBReader.this, myFBReaderApp.SyncOptions);

                final int brightnessLevel =
                        getZLibrary().ScreenBrightnessLevelOption.getValue();
                if (brightnessLevel != 0) {
                    getViewWidget().setScreenBrightness(brightnessLevel);
                } else {
                    setScreenBrightnessAuto();
                }
                if (getZLibrary().DisableButtonLightsOption.getValue()) {
                    setButtonLight(false);
                }

                getCollection().bindToService(FBReader.this, new Runnable() {
                    public void run() {
                        final BookModel model = myFBReaderApp.Model;
                        if (model == null || model.Book == null) {
                            return;
                        }
                        onPreferencesUpdate(myFBReaderApp.Collection.getBookById(model.Book.getId()));
                    }
                });
            }
        });

        registerReceiver(myBatteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        IsPaused = false;
        myResumeTimestamp = System.currentTimeMillis();
        if (OnResumeAction != null) {
            final Runnable action = OnResumeAction;
            OnResumeAction = null;
            action.run();
        }

        registerReceiver(mySyncUpdateReceiver, new IntentFilter(FBReaderIntents.Event.SYNC_UPDATED));

        SetScreenOrientationAction.setOrientation(this, getZLibrary().getOrientationOption().getValue());
        if (myCancelIntent != null) {
            final Intent intent = myCancelIntent;
            myCancelIntent = null;
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    runCancelAction(intent);
                }
            });
            return;
        } else if (myOpenBookIntent != null) {
            final Intent intent = myOpenBookIntent;
            myOpenBookIntent = null;
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    openBook(intent, null, true);
                }
            });
        } else if (myFBReaderApp.getCurrentServerBook(null) != null) {
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    myFBReaderApp.useSyncInfo(true, myNotifier);
                }
            });
        } else if (myFBReaderApp.Model == null && myFBReaderApp.ExternalBook != null) {
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    myFBReaderApp.openBook(myFBReaderApp.ExternalBook, null, null, myNotifier);
                }
            });
        } else {
            getCollection().bindToService(this, new Runnable() {
                public void run() {
                    myFBReaderApp.useSyncInfo(true, myNotifier);
                }
            });
        }

        PopupPanel.restoreVisibilities(myFBReaderApp);
        ApiServerImplementation.sendEvent(this, ApiListener.EVENT_READ_MODE_OPENED);
    }

    @Override
    protected void onPause() {
        SyncOperations.quickSync(this, myFBReaderApp.SyncOptions);

        IsPaused = true;
        try {
            unregisterReceiver(mySyncUpdateReceiver);
        } catch (IllegalArgumentException e) {
        }

        try {
            unregisterReceiver(myBatteryInfoReceiver);
        } catch (IllegalArgumentException e) {
            // do nothing, this exception means that myBatteryInfoReceiver was not registered
        }

        myFBReaderApp.stopTimer();
        if (getZLibrary().DisableButtonLightsOption.getValue()) {
            setButtonLight(true);
        }
        myFBReaderApp.onWindowClosing();

        super.onPause();
    }

    @Override
    protected void onStop() {
        ApiServerImplementation.sendEvent(this, ApiListener.EVENT_READ_MODE_CLOSED);
        PopupPanel.removeAllWindows(myFBReaderApp, this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        getCollection().unbind();
        unbindService(DataConnection);
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        myFBReaderApp.onWindowClosing();
        super.onLowMemory();
    }

    @Override
    public boolean onSearchRequested() {
        final FBReaderApp.PopupPanel popup = myFBReaderApp.getActivePopup();
        myFBReaderApp.hideActivePopup();
        if (DeviceType.Instance().hasStandardSearchDialog()) {
            final SearchManager manager = (SearchManager) getSystemService(SEARCH_SERVICE);
            manager.setOnCancelListener(new SearchManager.OnCancelListener() {
                public void onCancel() {
                    if (popup != null) {
                        myFBReaderApp.showPopup(popup.getId());
                    }
                    manager.setOnCancelListener(null);
                }
            });
            startSearch(myFBReaderApp.MiscOptions.TextSearchPattern.getValue(), true, null, false);
        } else {
            SearchDialogUtil.showDialog(
                    this, FBReader.class, myFBReaderApp.MiscOptions.TextSearchPattern.getValue(), new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface di) {
                            if (popup != null) {
                                myFBReaderApp.showPopup(popup.getId());
                            }
                        }
                    }
            );
        }
        return true;
    }

    public void showSelectionPanel() {
        final ZLTextView view = myFBReaderApp.getTextView();
        ((SelectionPopup) myFBReaderApp.getPopupById(SelectionPopup.ID))
                .move(view.getSelectionStartY(), view.getSelectionEndY());
        myFBReaderApp.showPopup(SelectionPopup.ID);
    }

    public void hideSelectionPanel() {
        final FBReaderApp.PopupPanel popup = myFBReaderApp.getActivePopup();
        if (popup != null && popup.getId() == SelectionPopup.ID) {
            myFBReaderApp.hideActivePopup();
        }
    }

    private void onPreferencesUpdate(Book book) {
        AndroidFontUtil.clearFontCache();
        myFBReaderApp.onBookUpdated(book);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
            case REQUEST_PREFERENCES:
                if (resultCode != RESULT_DO_NOTHING && data != null) {
                    final Book book = FBReaderIntents.getBookExtra(data, myFBReaderApp.Collection);
                    if (book != null) {
                        getCollection().bindToService(this, new Runnable() {
                            public void run() {
                                onPreferencesUpdate(book);
                            }
                        });
                    }
                }
                break;
            case REQUEST_CANCEL_MENU:
//                runCancelAction(data);
                break;
            case REQUEST_EDITBOOKMARK:
                if (resultCode == RESULT_SELECTCOLOR) {
                    int selectColor = data.getIntExtra("selectColor", 10706404);
                    myFBReaderApp.ViewOptions.getColorProfile().SelectionBackgroundOption.setValue(new ZLColor(selectColor));
                }
                break;

        }
    }

    private void runCancelAction(Intent intent) {
        final CancelMenuHelper.ActionType type;
        try {
            type = CancelMenuHelper.ActionType.valueOf(
                    intent.getStringExtra(FBReaderIntents.Key.TYPE)
            );
        } catch (Exception e) {
            // invalid (or null) type value
            return;
        }
        Bookmark bookmark = null;
        if (type == CancelMenuHelper.ActionType.returnTo) {
            bookmark = FBReaderIntents.getBookmarkExtra(intent);
            if (bookmark == null) {
                return;
            }
        }
//        myFBReaderApp.runCancelAction(type, bookmark);
    }
//菜单
//	private Menu addSubmenu(Menu menu, String id) {
//		return menu.addSubMenu(ZLResource.resource("menu").getResource(id).getValue());
//	}
//
//	private void addMenuItem(Menu menu, String actionId, Integer iconId, String name) {
//		if (name == null) {
//			name = ZLResource.resource("menu").getResource(actionId).getValue();
//		}
//		final MenuItem menuItem = menu.add(name);
//		if (iconId != null) {
//			menuItem.setIcon(iconId);
//		}
//		menuItem.setOnMenuItemClickListener(myMenuListener);
//		myMenuItemMap.put(menuItem, actionId);
//	}
//
//	private void addMenuItem(Menu menu, String actionId, String name) {
//		addMenuItem(menu, actionId, null, name);
//	}
//
//	private void addMenuItem(Menu menu, String actionId, int iconId) {
//		addMenuItem(menu, actionId, iconId, null);
//	}
//
//	private void addMenuItem(Menu menu, String actionId) {
//		addMenuItem(menu, actionId, null, null);
//	}
//
//	private void fillMenu(Menu menu, List<MenuNode> nodes) {
//		for (MenuNode n : nodes) {
//			if (n instanceof MenuNode.Item) {
//				final Integer iconId = ((MenuNode.Item)n).IconId;
//				if (iconId != null) {
//					addMenuItem(menu, n.Code, iconId);
//				} else {
//					addMenuItem(menu, n.Code);
//				}
//			} else /* if (n instanceof MenuNode.Submenu) */ {
//				final Menu submenu = addSubmenu(menu, n.Code);
//				fillMenu(submenu, ((MenuNode.Submenu)n).Children);
//			}
//		}
//	}
//
//	private void setupMenu(Menu menu) {
//		final String menuLanguage = ZLResource.getLanguageOption().getValue();
//		if (menuLanguage.equals(myMenuLanguage)) {
//			return;
//		}
//		myMenuLanguage = menuLanguage;
//
//		menu.clear();
//		fillMenu(menu, MenuData.topLevelNodes());
//		synchronized (myPluginActions) {
//			int index = 0;
//			for (PluginApi.ActionInfo info : myPluginActions) {
//				if (info instanceof PluginApi.MenuActionInfo) {
//					addMenuItem(
//							menu,
//							PLUGIN_ACTION_PREFIX + index++,
//							((PluginApi.MenuActionInfo)info).MenuItemName
//					);
//				}
//			}
//		}
//
//		refresh();
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		super.onCreateOptionsMenu(menu);
//
//		setupMenu(menu);
//
//		return true;
//	}

    protected void onPluginNotFound(final Book book) {
        final BookCollectionShadow collection = getCollection();
        collection.bindToService(this, new Runnable() {
            public void run() {
                final Book recent = collection.getRecentBook(0);
                if (recent != null && !collection.sameBook(recent, book)) {
                    myFBReaderApp.openBook(recent, null, null, null);
                } else {
                    myFBReaderApp.openHelpBook();
                }
            }
        });
    }

    private void setStatusBarVisibility(boolean visible) {
        final ZLAndroidLibrary zlibrary = getZLibrary();
        if (DeviceType.Instance() != DeviceType.KINDLE_FIRE_1ST_GENERATION && !myShowStatusBarFlag) {
            if (visible) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            } else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            }
        }
    }

    public void navigate() {
        ((NavigationPopup) myFBReaderApp.getPopupById(NavigationPopup.ID)).runNavigation();
    }

    public void setting() {
        ((SettingPopup) myFBReaderApp.getPopupById(SettingPopup.ID)).runNavigation();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            finish();
            return true;
        }
        return (myMainView != null && myMainView.onKeyDown(keyCode, event)) || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return (myMainView != null && myMainView.onKeyUp(keyCode, event)) || super.onKeyUp(keyCode, event);
    }

    private void setButtonLight(boolean enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            setButtonLightInternal(enabled);
        }
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    private void setButtonLightInternal(boolean enabled) {
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.buttonBrightness = enabled ? -1.0f : 0.0f;
        getWindow().setAttributes(attrs);
    }

    private PowerManager.WakeLock myWakeLock;
    private boolean myWakeLockToCreate;
    private boolean myStartTimer;

    public final void createWakeLock() {
        if (myWakeLockToCreate) {
            synchronized (this) {
                if (myWakeLockToCreate) {
                    myWakeLockToCreate = false;
                    myWakeLock =
                            ((PowerManager) getSystemService(POWER_SERVICE))
                                    .newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "FBReader");
                    myWakeLock.acquire();
                }
            }
        }
        if (myStartTimer) {
            myFBReaderApp.startTimer();
            myStartTimer = false;
        }
    }

    private final void switchWakeLock(boolean on) {
        if (on) {
            if (myWakeLock == null) {
                myWakeLockToCreate = true;
            }
        } else {
            if (myWakeLock != null) {
                synchronized (this) {
                    if (myWakeLock != null) {
                        myWakeLock.release();
                        myWakeLock = null;
                    }
                }
            }
        }
    }

    private BroadcastReceiver myBatteryInfoReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            final int level = intent.getIntExtra("level", 100);
            final ZLAndroidApplication application = (ZLAndroidApplication) getApplication();
            setBatteryLevel(level);
            switchWakeLock(
                    hasWindowFocus() &&
                            getZLibrary().BatteryLevelToTurnScreenOffOption.getValue() < level
            );
        }
    };

    private BookCollectionShadow getCollection() {
        return (BookCollectionShadow) myFBReaderApp.Collection;
    }

    // methods from ZLApplicationWindow interface
    @Override
    public void showErrorMessage(String key) {
        UIMessageUtil.showErrorMessage(this, key);
    }

    @Override
    public void showErrorMessage(String key, String parameter) {
        UIMessageUtil.showErrorMessage(this, key, parameter);
    }

    @Override
    public FBReaderApp.SynchronousExecutor createExecutor(String key) {
        return UIUtil.createExecutor(this, key);
    }

    private int myBatteryLevel;

    @Override
    public int getBatteryLevel() {
        return myBatteryLevel;
    }

    private void setBatteryLevel(int percent) {
        myBatteryLevel = percent;
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public ZLViewWidget getViewWidget() {
        return myMainView;
    }

    @Override
    public void hideViewWidget(boolean flag) {

    }


    private final HashMap<MenuItem, String> myMenuItemMap = new HashMap<MenuItem, String>();

//	private final MenuItem.OnMenuItemClickListener myMenuListener =
//			new MenuItem.OnMenuItemClickListener() {
//				public boolean onMenuItemClick(MenuItem item) {
//					myFBReaderApp.runAction(myMenuItemMap.get(item));
//					return true;
//				}
//			};

    @Override
    public void refresh() {
        runOnUiThread(new Runnable() {
            public void run() {
                for (Map.Entry<MenuItem, String> entry : myMenuItemMap.entrySet()) {
                    final String actionId = entry.getValue();
                    final MenuItem menuItem = entry.getKey();
                    menuItem.setVisible(myFBReaderApp.isActionVisible(actionId) && myFBReaderApp.isActionEnabled(actionId));
                    switch (myFBReaderApp.isActionChecked(actionId)) {
                        case TRUE:
                            menuItem.setCheckable(true);
                            menuItem.setChecked(true);
                            break;
                        case FALSE:
                            menuItem.setCheckable(true);
                            menuItem.setChecked(false);
                            break;
                        case UNDEFINED:
                            menuItem.setCheckable(false);
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void processException(Exception exception) {
        exception.printStackTrace();

        final Intent intent = new Intent(
                FBReaderIntents.Action.ERROR,
                new Uri.Builder().scheme(exception.getClass().getSimpleName()).build()
        );
        intent.setPackage(FBReaderIntents.DEFAULT_PACKAGE);
        intent.putExtra(ErrorKeys.MESSAGE, exception.getMessage());
        final StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        intent.putExtra(ErrorKeys.STACKTRACE, stackTrace.toString());
        /*
        if (exception instanceof BookReadingException) {
			final ZLFile file = ((BookReadingException)exception).File;
			if (file != null) {
				intent.putExtra("file", file.getPath());
			}
		}
		*/
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // ignore
            e.printStackTrace();
        }
    }

    @Override
    public void setWindowTitle(final String title) {
        runOnUiThread(new Runnable() {
            public void run() {
                setTitle(title);
            }
        });
    }

    private BroadcastReceiver mySyncUpdateReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            myFBReaderApp.useSyncInfo(myResumeTimestamp + 10 * 1000 > System.currentTimeMillis(), myNotifier);
        }
    };

    public void outlineRegion(ZLTextRegion.Soul soul) {
        myFBReaderApp.getTextView().outlineRegion(soul);
        myFBReaderApp.getViewWidget().repaint();
    }

    public void hideOutline() {
        myFBReaderApp.getTextView().hideOutline();
        myFBReaderApp.getViewWidget().repaint();
    }

    public void hideDictionarySelection() {
        myFBReaderApp.getTextView().hideOutline();
        myFBReaderApp.getTextView().removeHighlightings(DictionaryHighlighting.class);
        myFBReaderApp.getViewWidget().reset();
        myFBReaderApp.getViewWidget().repaint();
    }

    List<Idea_Table> otherBookmarks = new ArrayList<>();

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        if (event.getAction() == MotionEvent.ACTION_DOWN){
//            //获取本章开始段落
//            int paragraphIndex = myFBReaderApp.getCurrentTOCElement().getReference().ParagraphIndex;
//            //拿到当前段落
//
//            Bookmark bookmark = myFBReaderApp.createBookmark(500, true);
//            int duanluokaishi = bookmark.getParagraphIndex();
//            int duanluojieshu = bookmark.getEnd().getParagraphIndex();
//            BookCollectionShadow collection = getCollection();
//            //拿到删除书签
//            collection.deleteBookmark(bookmark);
//            //
//            BookmarkQuery query = new BookmarkQuery(myFBReaderApp.getCurrentBook(), 10000);
//            List<Bookmark> bookmarks = collection.bookmarks(query);
//
//            //拿到当前页的书签
//            Idea_TableDao dao = BaseApplication.getDaoSession(BaseApplication.getContext()).getIdea_TableDao();
//            for (Bookmark b : bookmarks) {
//                //查找xy坐标
//                if (b.getParagraphIndex()>duanluokaishi&&b.getEnd().getParagraphIndex()<duanluojieshu){
//                    Idea_Table c = dao.queryBuilder().where(Idea_TableDao.Properties.BookId.eq(b.getBookId())
//                            , Idea_TableDao.Properties.Content.eq(b.getText())
//                            , Idea_TableDao.Properties.Uid.eq(b.getUid())).build().list().get(0);
//                    otherBookmarks.add(c);
//                }
//            }
//            for (Idea_Table otherBookmark : otherBookmarks) {
//                Integer x = otherBookmark.getX();
//                Integer y = otherBookmark.getY();
//                if ((event.getX() > x - 20 && event.getX() < x + 20) && ((event.getY() > y - 10 && event.getY() < y + 10))){
//                    ToastUtil.showToast("点击了");
//                    return false;
//                }
//            }
//        }
//        //判断坐标
//        myMainView.onTouchEvent(event);
//        return true;
//    }

    //进度条对话框
    private LoadDialog progressDialog;
    public LoadDialog showProgressDialog(CharSequence message) {
        if (progressDialog == null) {
            progressDialog = new LoadDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setTitle(message);
        }
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            com.laichushu.book.utils.UIUtil.getMainThreadHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            }, 1600);
        }
    }
    public ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
    private CompositeSubscription mCompositeSubscription;
    //RXjava
    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
