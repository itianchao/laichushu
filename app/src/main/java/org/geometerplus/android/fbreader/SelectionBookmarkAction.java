/*
 * Copyright (C) 2007-2015 FBReader.ORG Limited <contact@fbreader.org>
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

import android.content.Intent;

import com.google.gson.Gson;
import com.laichushu.book.R;
import com.laichushu.book.bean.otherbean.BookMarkIdeaJsonBean;
import com.laichushu.book.event.TransmitBookMarkEvent3;
import com.laichushu.book.utils.SharePrefManager;
import com.laichushu.book.utils.StringUtil;

import org.fbreader.util.FBReaderWindowUtil;
import org.geometerplus.android.fbreader.api.FBReaderIntents;
import org.geometerplus.android.fbreader.bookmark.BookmarksEditActivity;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.android.util.OrientationUtil;
import org.geometerplus.fbreader.book.Bookmark;
import org.geometerplus.fbreader.book.BookmarkQuery;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 书签
 */
public class SelectionBookmarkAction extends FBAndroidAction {

    private Bookmark bookmark;
    private FBReader baseApplication;
    private final BookCollectionShadow myCollection = new BookCollectionShadow();

    SelectionBookmarkAction(FBReader baseApplication, FBReaderApp fbreader) {
        super(baseApplication, fbreader);
        EventBus.getDefault().register(this);
        this.baseApplication = baseApplication;
    }

    @Override
    protected void run(Object... params) {
        /**
         * 传入动作数组判断
         */
        if (params.length == 1) {//选择
            bookmark = (Bookmark) params[0];
            baseApplication.showSelectionPanel();
        } else if (params.length == 2) {//颜色
            //改变样式
            Bookmark bookmark = Reader.addSelectionBookmark();
            if (bookmark != null) {
                this.bookmark = bookmark;
            }
            int mSelectColor = (int) params[0];
            int styleId = (int) params[1];
            if (mSelectColor != 0 && styleId != 0) {
                changeColor(mSelectColor, styleId);
            }
            if (bookmark == null) {
                return;
            }

//		final SuperActivityToast toast =
//			new SuperActivityToast(BaseActivity, SuperToast.Type.BUTTON);
//		toast.setText(bookmark.getText());
//		toast.setDuration(SuperToast.Duration.EXTRA_LONG);
//		toast.setButtonIcon(
//			android.R.drawable.ic_menu_edit,
//			ZLResource.resource("dialog").getResource("button").getResource("edit").getValue()
//		);
//		toast.setOnClickWrapper(new OnClickWrapper("bkmk", new SuperToast.OnClickListener() {
//			@Override
//			public void onClick(View view, Parcelable token) {
//				final Intent intent =
//					new Intent(BaseActivity.getApplicationContext(), EditBookmarkActivity.class);
//				FBReaderIntents.putBookmarkExtra(intent, bookmark);
//				OrientationUtil.startActivity(BaseActivity, intent);
//			}
//		}));
//		BaseActivity.showToast(toast);
        } else if (params.length > 5) {//删除
            if (bookmark == null) {
                Reader.getTextView().clearSelection();
            } else {
                myCollection.bindToService(baseApplication, new Runnable() {
                    public void run() {
                        myCollection.deleteBookmark(bookmark);
                        bookmark = null;
                    }
                });
                FBReaderWindowUtil.deleteBookmarkForIdea_Table(bookmark);
            }
        } else {//想法
            if (bookmark == null) {
                bookmark = (Bookmark) params[0];
                if (bookmark == null) {
                    Bookmark bookmark = Reader.addSelectionBookmark();
                    this.bookmark = bookmark;
                    int mSelectColor = (int) params[1];
                    int styleId = (int) params[2];
                    if (bookmark != null) {
//                        bookmark.setText("");
                        changeColor(mSelectColor, styleId);
//                        startActivity();
                    }
                }
            } else {
                startActivity();//打开pop
            }
//            TransmitBookMarkEvent3 event = new TransmitBookMarkEvent3();
//            event.setMyBookmark(bookmark);
//            EventBus.getDefault().postSticky(event);
        }
        myCollection.unbind();
        EventBus.getDefault().unregister(this);
    }

    private void changeColor(int mSelectColor, final int styleId) {
        Intent data = new Intent();
        data.putExtra("selectColor", mSelectColor);
        baseApplication.setResult(7, data);
        myCollection.bindToService(baseApplication, new Runnable() {
            public void run() {
                bookmark.setStyleId(styleId);
                myCollection.setDefaultHighlightingStyleId(styleId);
                myCollection.saveBookmark(bookmark);
            }
        });
    }

    public void startActivity() {
        final Intent intent =
                new Intent(BaseActivity.getApplicationContext(), BookmarksEditActivity.class);
        FBReaderIntents.putBookmarkExtra(intent, bookmark);
        intent.putExtra("type", false);
        intent.putExtra("content", bookmark.getText());
        OrientationUtil.startActivity(BaseActivity, intent);
        bookmark = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TransmitBookMarkEvent3 event) {
        EventBus.getDefault().removeStickyEvent(event);
        bookmark = event.getMyBookmark();
    }

    /**
     * 查询合并想法
     *
     * @param bookmark
     * @return
     */
    public Bookmark findBookMarkByDB(final Bookmark bookmark) {
        myCollection.bindToService(baseApplication, new Runnable() {
            public void run() {
                FBReaderApp fbReaderApp = (FBReaderApp) FBReaderApp.Instance();
                BookmarkQuery query = new BookmarkQuery(fbReaderApp.getCurrentBook(), 10000);
                List<Bookmark> bookmarks = myCollection.bookmarks(query);
                boolean b = true;
                for (Bookmark bm : bookmarks) {
                    b = false;
                    int Index = bm.getEnd().getParagraphIndex();
                    int endhIndex = bookmark.getEnd().getParagraphIndex();
                    if ((Index == endhIndex)) {
                        b = false;
                        //合并想法Json
                        String json = bm.getText();
                        BookMarkIdeaJsonBean bean = new Gson().fromJson(json, BookMarkIdeaJsonBean.class);
                        BookMarkIdeaJsonBean.DataBean databean = new BookMarkIdeaJsonBean.DataBean();
                        databean.setContent(bookmark.getText());
                        databean.setHear("");
                        databean.setName(SharePrefManager.getNickName());
                        databean.setTime(StringUtil.formatSystemTime());
                        bean.getData().add(databean);
                        json = new Gson().toJson(bean);
                        bookmark.setText(json);
                        break;
                    }
                }
                if (b){//添加想法
                    BookMarkIdeaJsonBean bean = new BookMarkIdeaJsonBean();
                    ArrayList<BookMarkIdeaJsonBean.DataBean> datas = new ArrayList<>();
                    BookMarkIdeaJsonBean.DataBean databean = new BookMarkIdeaJsonBean.DataBean();
                    databean.setContent(bookmark.getText());
                    databean.setHear("");
                    databean.setName(SharePrefManager.getNickName());
                    databean.setTime(StringUtil.formatSystemTime());
                    datas.add(databean);
                    bean.setData(datas);
                    String json = new Gson().toJson(bean);
                    bookmark.setText(json);
                }
            }
        });
        return bookmark;
    }
}
