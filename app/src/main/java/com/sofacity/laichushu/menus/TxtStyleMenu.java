package com.sofacity.laichushu.menus;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.utils.SharePrefManager;

public class TxtStyleMenu extends PopupWindow {

	public static final int STYLE_PAPE = R.drawable.reading__reading_themes_vine_pager;
	public static final int STYLE_GREEN2 = R.drawable.reading__reading_themes_vine_green2;
	public static final int STYLE_GREEN = R.drawable.reading__reading_themes_vine_green;
	public static final int STYLE_DEFOULT = R.drawable.reading__reading_themes_vine_white;
	public static final int STYLE_GRAY = R.drawable.reading__reading_themes_vine_gary;

	private Context mContext;
	private int mWindow_With;
	private int mWindow_Heigh;
	private int mSelectedposition;
	private View SelectedTag;
	private onTxtStyleChangeListener mListener;

	public TxtStyleMenu(Context context) {
		this.mContext = context;
		inite();
	}

	public void setonTxtStyleChangeListener(onTxtStyleChangeListener listener) {
		mListener = listener;
	}

	@SuppressLint({ "NewApi", "CutPasteId" })
	private void inite() {
		WindowManager m = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		m.getDefaultDisplay().getMetrics(metrics);

		mWindow_With = metrics.widthPixels;
		mWindow_Heigh = metrics.heightPixels;

		int rootwith = mWindow_With;
		int rootheigh = mWindow_Heigh / 7;

		LinearLayout layout = (LinearLayout) LinearLayout.inflate(mContext, R.layout.txtstylemenu_layout, null);

		this.setWidth(rootwith);
		this.setHeight(rootheigh);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.setContentView(layout);
		ColorDrawable dw = new ColorDrawable(Color.parseColor("#88000000"));

		RelativeLayout s_layout1 = (RelativeLayout) layout.findViewById(R.id.txtstyle1_layout);
		RelativeLayout s_layout2 = (RelativeLayout) layout.findViewById(R.id.txtstyle2_layout);
		RelativeLayout s_layout3 = (RelativeLayout) layout.findViewById(R.id.txtstyle3_layout);
		RelativeLayout s_layout4 = (RelativeLayout) layout.findViewById(R.id.txtstyle4_layout);
		RelativeLayout s_layout5 = (RelativeLayout) layout.findViewById(R.id.txtstyle5_layout);

		ImageView view1 = (ImageView) layout.findViewById(R.id.txtstyle1);
		ImageView view2 = (ImageView) layout.findViewById(R.id.txtstyle2);
		ImageView view3 = (ImageView) layout.findViewById(R.id.txtstyle3);
		ImageView view4 = (ImageView) layout.findViewById(R.id.txtstyle4);
		ImageView view5 = (ImageView) layout.findViewById(R.id.txtstyle5);

		final View slid1 = layout.findViewById(R.id.txtstyle1_tag);
		final View slid2 = layout.findViewById(R.id.txtstyle2_tag);
		final View slid3 = layout.findViewById(R.id.txtstyle3_tag);
		final View slid4 = layout.findViewById(R.id.txtstyle4_tag);
		final View slid5 = layout.findViewById(R.id.txtstyle5_tag);

		view1.setBackgroundResource(STYLE_DEFOULT);
		view2.setBackgroundResource(STYLE_GREEN);
		view3.setBackgroundResource(STYLE_PAPE);
		view4.setBackgroundResource(STYLE_GREEN);
		view5.setBackgroundResource(STYLE_GRAY);

		mSelectedposition = 1;
		SelectedTag = slid1;

		s_layout1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mSelectedposition!=1){
					mListener.onStyleChange(STYLE_DEFOULT);
					hideSlidtag(SelectedTag);
					SelectedTag = slid1;
					mSelectedposition = 1;
					showSlidTag(SelectedTag);
					SharePrefManager.setReadMoudle(STYLE_DEFOULT);
				}
			}
		});

		s_layout2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mSelectedposition !=2) {
					mListener.onStyleChange(STYLE_GREEN2);
					hideSlidtag(SelectedTag);
					SelectedTag = slid2;
					mSelectedposition = 2;
					showSlidTag(SelectedTag);
					SharePrefManager.setReadMoudle(STYLE_GREEN2);
				}
			}
		});

		s_layout3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mSelectedposition!=3){
					mListener.onStyleChange(STYLE_PAPE);
					hideSlidtag(SelectedTag);
					SelectedTag = slid3;
					mSelectedposition = 3;
					showSlidTag(SelectedTag);
					SharePrefManager.setReadMoudle(STYLE_PAPE);
				}
			}
		});

		s_layout4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mSelectedposition!=4){
					mListener.onStyleChange(STYLE_GREEN2);
					hideSlidtag(SelectedTag);
					SelectedTag = slid4;
					mSelectedposition = 4;
					showSlidTag(SelectedTag);
					SharePrefManager.setReadMoudle(STYLE_GREEN2);
				}
			}
		});

		s_layout5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mSelectedposition!=5) {
					mListener.onStyleChange(STYLE_GRAY);
					hideSlidtag(SelectedTag);
					SelectedTag = slid5;
					mSelectedposition = 5;
					showSlidTag(SelectedTag);
					SharePrefManager.setReadMoudle(STYLE_GRAY);
				}
			}
		});

		this.setBackgroundDrawable(dw);
	}

	private void hideSlidtag(View mSelectedTag) {
		mSelectedTag.setVisibility(View.INVISIBLE);
	}

	private void showSlidTag(View mSelectedTag) {
		mSelectedTag.setVisibility(View.VISIBLE);

	}

	public interface onTxtStyleChangeListener {
		public void onStyleChange(int stylecolor);
	}
}
