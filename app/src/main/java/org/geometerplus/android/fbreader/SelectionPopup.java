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

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laichushu.book.R;

import org.geometerplus.android.fbreader.api.FBReaderIntents;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.fbreader.book.Bookmark;
import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
class SelectionPopup extends PopupPanel implements View.OnClickListener {
	final static String ID = "SelectionPopup";
	private Bookmark myBookmark;
	private FBReader activity;
	private final BookCollectionShadow myCollection = new BookCollectionShadow();
	SelectionPopup(FBReaderApp fbReader) {
		super(fbReader);
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void createControlPanel(FBReader activity, RelativeLayout root) {
		this.activity = activity;
		if (myWindow != null && activity == myWindow.getContext()) {
			return;
		}

		activity.getLayoutInflater().inflate(R.layout.selection_panel, root);
		myWindow = (SimplePopupWindow)root.findViewById(R.id.selection_panel);
		myBookmark = FBReaderIntents.getBookmarkExtra(activity.getIntent());
		TextView copyTv = (TextView)myWindow.findViewById(R.id.selection_panel_copy);
		TextView shareTv = (TextView)myWindow.findViewById(R.id.selection_panel_share);
		TextView bookmarkTv = (TextView)myWindow.findViewById(R.id.selection_panel_bookmark);
		TextView closeTv = (TextView)myWindow.findViewById(R.id.selection_panel_close);
		ImageView iv_5BA8F6 = (ImageView)myWindow.findViewById(R.id.iv_5BA8F6);
		ImageView iv_61BC16 = (ImageView)myWindow.findViewById(R.id.iv_61BC16);
		ImageView iv_F57200 = (ImageView)myWindow.findViewById(R.id.iv_F57200);
		ImageView iv_A35DE4 = (ImageView)myWindow.findViewById(R.id.iv_A35DE4);
		copyTv.setOnClickListener(this);
		shareTv.setOnClickListener(this);
		bookmarkTv.setOnClickListener(this);
		closeTv.setOnClickListener(this);
		iv_5BA8F6.setOnClickListener(this);
		iv_61BC16.setOnClickListener(this);
		iv_F57200.setOnClickListener(this);
		iv_A35DE4.setOnClickListener(this);
//		final ZLResource resource = ZLResource.resource("selectionPopup");
//		setupButton(R.id.selection_panel_copy, resource.getResource("copyToClipboard").getValue());
//		setupButton(R.id.selection_panel_share, resource.getResource("share").getValue());
//		setupButton(R.id.selection_panel_translate, resource.getResource("translate").getValue());
//		setupButton(R.id.selection_panel_bookmark, resource.getResource("bookmark").getValue());
//		setupButton(R.id.selection_panel_close, resource.getResource("close").getValue());
	}

	private void setupButton(int buttonId, String description) {
		final View button = myWindow.findViewById(buttonId);
		button.setOnClickListener(this);
		button.setContentDescription(description);
	}

	public void move(int selectionStartY, int selectionEndY) {
		if (myWindow == null) {
			return;
		}

		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.WRAP_CONTENT,
			RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

		final int verticalPosition;
		final int screenHeight = ((View)myWindow.getParent()).getHeight();
		final int diffTop = screenHeight - selectionEndY;
		final int diffBottom = selectionStartY;
		if (diffTop > diffBottom) {
			verticalPosition = diffTop > myWindow.getHeight() + 20
				? RelativeLayout.ALIGN_PARENT_BOTTOM : RelativeLayout.CENTER_VERTICAL;
		} else {
			verticalPosition = diffBottom > myWindow.getHeight() + 20
				? RelativeLayout.ALIGN_PARENT_TOP : RelativeLayout.CENTER_VERTICAL;
		}

		layoutParams.addRule(verticalPosition);
		myWindow.setLayoutParams(layoutParams);
	}

	@Override
	protected void update() {
	}

	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.selection_panel_copy:
				Application.runAction(ActionCode.SELECTION_COPY_TO_CLIPBOARD);
				break;
			case R.id.selection_panel_share:
				Application.runAction(ActionCode.SELECTION_SHARE);
				break;
			case R.id.selection_panel_close:
				Application.runAction(ActionCode.SELECTION_CLEAR);
				break;
			case R.id.selection_panel_bookmark:
				Application.runAction(ActionCode.SELECTION_BOOKMARK,Color.parseColor("#5BA8F6"),1);
				break;
			case R.id.iv_5BA8F6:
				Application.runAction(ActionCode.SELECTION_BOOKMARK,Color.parseColor("#5BA8F6"),1);
				break;
			case R.id.iv_61BC16:
				Application.runAction(ActionCode.SELECTION_BOOKMARK,Color.parseColor("#61BC16"),2);
				break;
			case R.id.iv_F57200:
				Application.runAction(ActionCode.SELECTION_BOOKMARK,Color.parseColor("#F57200"),3);
				break;
			case R.id.iv_A35DE4:
				Application.runAction(ActionCode.SELECTION_BOOKMARK,Color.parseColor("#A35DE4"),4);
				break;
		}
		Application.hideActivePopup();
	}
}
