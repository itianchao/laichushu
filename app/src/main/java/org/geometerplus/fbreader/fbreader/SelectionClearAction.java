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

package org.geometerplus.fbreader.fbreader;

import android.content.Intent;

import org.geometerplus.android.fbreader.FBReader;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.fbreader.book.Bookmark;

public class SelectionClearAction extends FBAction {
    private FBReader baseApplication;
    private Bookmark bookmark;
    private final BookCollectionShadow myCollection = new BookCollectionShadow();

    public SelectionClearAction(FBReader baseApplication, FBReaderApp fbreader) {
        super(fbreader);
        this.baseApplication = baseApplication;
    }

    @Override
    protected void run(Object... params) {
        Reader.getTextView().clearSelection();
//        bookmark = Reader.addSelectionBookmark();
//        Intent data = new Intent();
//        baseApplication.setResult(7, data);
//        myCollection.bindToService(baseApplication, new Runnable() {
//            public void run() {
//                myCollection.saveBookmark(bookmark);
//                myCollection.deleteBookmark(bookmark);
//            }
//        });
    }
}
