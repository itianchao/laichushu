/*
 * Copyright (C) 2007-2015 FBReader.ORG Limited <contact@org.geometerplus.android.fbreader.org>
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

package com.laichushu.book.org.geometerplus.android.fbreader;

import android.content.Intent;

import com.laichushu.book.org.geometerplus.android.util.OrientationUtil;
import com.laichushu.book.org.geometerplus.fbreader.bookmodel.BookModel;
import com.laichushu.book.org.geometerplus.fbreader.fbreader.FBReaderApp;

class ShowTOCAction extends FBAndroidAction {
	ShowTOCAction(FBReader baseActivity, FBReaderApp fbreader) {
		super(baseActivity, fbreader);
	}

	static boolean isTOCAvailable(FBReaderApp reader) {
		if (reader == null) {
			return false;
		}
		final BookModel model = reader.Model;
		return model != null && model.TOCTree.hasChildren();
	}

	@Override
	public boolean isVisible() {
		return isTOCAvailable(Reader);
	}

	@Override
	protected void run(Object ... params) {
		OrientationUtil.startActivity(
			BaseActivity, new Intent(BaseActivity.getApplicationContext(), TOCActivity.class)
		);
	}
}
