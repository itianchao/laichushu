/*
 * Copyright (C) 2009-2015 FBReader.ORG Limited <contact@org.geometerplus.android.fbreader.org>
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

package com.laichushu.book.org.geometerplus.android.fbreader.api;

import android.content.Intent;

import com.laichushu.book.org.geometerplus.fbreader.book.*;

public abstract class FBReaderIntents {
//	public static final String DEFAULT_PACKAGE = "org.geometerplus.zlibrary.ui.android";
	public static final String DEFAULT_PACKAGE = "com.laichushu.book";

	public interface Action {
		String API                              = "android.org.geometerplus.android.fbreader.action.API";
		String API_CALLBACK                     = "android.org.geometerplus.android.fbreader.action.API_CALLBACK";
		String VIEW                             = "android.org.geometerplus.android.fbreader.action.VIEW";
		String CANCEL_MENU                      = "android.org.geometerplus.android.fbreader.action.CANCEL_MENU";
		String CONFIG_SERVICE                   = "android.org.geometerplus.android.fbreader.action.CONFIG_SERVICE";
		String LIBRARY_SERVICE                  = "android.org.geometerplus.android.fbreader.action.LIBRARY_SERVICE";
		String BOOK_INFO                        = "android.org.geometerplus.android.fbreader.action.BOOK_INFO";
		String LIBRARY                          = "android.org.geometerplus.android.fbreader.action.LIBRARY";
		String EXTERNAL_LIBRARY                 = "android.org.geometerplus.android.fbreader.action.EXTERNAL_LIBRARY";
		String BOOKMARKS                        = "android.org.geometerplus.android.fbreader.action.BOOKMARKS";
		String EXTERNAL_BOOKMARKS               = "android.org.geometerplus.android.fbreader.action.EXTERNAL_BOOKMARKS";
		String PREFERENCES                      = "android.org.geometerplus.android.fbreader.action.PREFERENCES";
		String NETWORK_LIBRARY                  = "android.org.geometerplus.android.fbreader.action.NETWORK_LIBRARY";
		String OPEN_NETWORK_CATALOG             = "android.org.geometerplus.android.fbreader.action.OPEN_NETWORK_CATALOG";
		String ERROR                            = "android.org.geometerplus.android.fbreader.action.ERROR";
		String CRASH                            = "android.org.geometerplus.android.fbreader.action.CRASH";
		String PLUGIN                           = "android.org.geometerplus.android.fbreader.action.PLUGIN";
		String CLOSE                            = "android.org.geometerplus.android.fbreader.action.CLOSE";
		String PLUGIN_CRASH                     = "android.org.geometerplus.android.fbreader.action.PLUGIN_CRASH";
		String EDIT_STYLES                      = "android.org.geometerplus.android.fbreader.action.EDIT_STYLES";
		String EDIT_BOOKMARK                    = "android.org.geometerplus.android.fbreader.action.EDIT_BOOKMARK";
		String SWITCH_YOTA_SCREEN               = "android.org.geometerplus.android.fbreader.action.SWITCH_YOTA_SCREEN";

		String SYNC_START                       = "android.org.geometerplus.android.fbreader.action.sync.START";
		String SYNC_STOP                        = "android.org.geometerplus.android.fbreader.action.sync.STOP";
		String SYNC_SYNC                        = "android.org.geometerplus.android.fbreader.action.sync.SYNC";
		String SYNC_QUICK_SYNC                  = "android.org.geometerplus.android.fbreader.action.sync.QUICK_SYNC";

		String PLUGIN_VIEW                      = "android.org.geometerplus.android.fbreader.action.plugin.VIEW";
		String PLUGIN_KILL                      = "android.org.geometerplus.android.fbreader.action.plugin.KILL";
		String PLUGIN_CONNECT_COVER_SERVICE     = "android.org.geometerplus.android.fbreader.action.plugin.CONNECT_COVER_SERVICE";
	}

	public interface Event {
		String CONFIG_OPTION_CHANGE             = "org.geometerplus.android.fbreader.config_service.option_change_event";

		String LIBRARY_BOOK                     = "org.geometerplus.android.fbreader.library_service.book_event";
		String LIBRARY_BUILD                    = "org.geometerplus.android.fbreader.library_service.build_event";
		String LIBRARY_COVER_READY              = "org.geometerplus.android.fbreader.library_service.cover_ready";

		String SYNC_UPDATED                     = "android.org.geometerplus.android.fbreader.event.sync.UPDATED";
	}

	public interface Key {
		String BOOK                             = "org.geometerplus.android.fbreader.book";
		String BOOKMARK                         = "org.geometerplus.android.fbreader.bookmark";
		String PLUGIN                           = "org.geometerplus.android.fbreader.plugin";
		String TYPE                             = "org.geometerplus.android.fbreader.type";
	}

	public static Intent defaultInternalIntent(String action) {
		return internalIntent(action).addCategory(Intent.CATEGORY_DEFAULT);
	}

	public static Intent internalIntent(String action) {
		return new Intent(action).setPackage(DEFAULT_PACKAGE);
	}

	public static void putBookExtra(Intent intent, String key, Book book) {
		intent.putExtra(key, SerializerUtil.serialize(book));
	}

	public static void putBookExtra(Intent intent, Book book) {
		putBookExtra(intent, Key.BOOK, book);
	}

	public static <B extends AbstractBook> B getBookExtra(Intent intent, String key, AbstractSerializer.BookCreator<B> creator) {
		return SerializerUtil.deserializeBook(intent.getStringExtra(key), creator);
	}

	public static <B extends AbstractBook> B getBookExtra(Intent intent, AbstractSerializer.BookCreator<B> creator) {
		return getBookExtra(intent, Key.BOOK, creator);
	}

	public static void putBookmarkExtra(Intent intent, String key, Bookmark bookmark) {
		intent.putExtra(key, SerializerUtil.serialize(bookmark));
	}

	public static void putBookmarkExtra(Intent intent, Bookmark bookmark) {
		putBookmarkExtra(intent, Key.BOOKMARK, bookmark);
	}

	public static Bookmark getBookmarkExtra(Intent intent, String key) {
		return SerializerUtil.deserializeBookmark(intent.getStringExtra(key));
	}

	public static Bookmark getBookmarkExtra(Intent intent) {
		return getBookmarkExtra(intent, Key.BOOKMARK);
	}
}
