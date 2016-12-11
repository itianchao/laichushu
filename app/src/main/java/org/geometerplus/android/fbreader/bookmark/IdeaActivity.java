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

package org.geometerplus.android.fbreader.bookmark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.laichushu.book.R;
import com.laichushu.book.event.TransmitBookMarkEvent3;

import org.geometerplus.android.fbreader.api.FBReaderIntents;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.fbreader.book.Bookmark;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class IdeaActivity extends Activity {
    private final BookCollectionShadow myCollection = new BookCollectionShadow();
    private Bookmark myBookmark;
    private LinearLayout li;

    private EditText editor;
    private Button saveTextButton;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit_bookmark3);
        EventBus.getDefault().register(this);
        myBookmark = FBReaderIntents.getBookmarkExtra(getIntent());
        if (myBookmark == null) {
            finish();
            return;
        }

        li = (LinearLayout) findViewById(R.id.edit_bookmark_tabhost);

        editor = (EditText) findViewById(R.id.edit_bookmark_text);
        editor.setText(myBookmark.getText());
        final int len = editor.getText().length();
        editor.setSelection(len, len);
        saveTextButton = (Button) findViewById(R.id.edit_bookmark_save_text_button);
        initListener();
    }

    private void initListener() {

        saveTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCollection.bindToService(IdeaActivity.this, new Runnable() {
                    public void run() {
                        myBookmark.setText(editor.getText().toString());
                        myCollection.saveBookmark(myBookmark);
                        finish();
                    }
                });
            }
        });
        editor.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence sequence, int start, int before, int count) {
                final String originalText = myBookmark.getText();
                saveTextButton.setEnabled(!originalText.equals(editor.getText().toString()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void changeColor(int mSelectColor, final int styleId) {
        Intent data = new Intent();
        data.putExtra("selectColor", mSelectColor);
        setResult(7, data);
        myCollection.bindToService(IdeaActivity.this, new Runnable() {
            public void run() {
                myBookmark.setStyleId(styleId);
                myCollection.setDefaultHighlightingStyleId(styleId);
                myCollection.saveBookmark(myBookmark);
            }
        });
    }


    @Override
    protected void onDestroy() {
        myCollection.unbind();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TransmitBookMarkEvent3 event) {
        EventBus.getDefault().removeStickyEvent(event);
        myBookmark = event.getMyBookmark();
        editor.setText(myBookmark.getText());
    }
}
