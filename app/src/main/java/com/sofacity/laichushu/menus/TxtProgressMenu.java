package com.sofacity.laichushu.menus;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sofacity.laichushu.R;
import com.sofacity.laichushu.utils.SharePrefManager;
import com.sofacity.laichushu.utils.ToastUtil;

import java.text.DecimalFormat;


public class TxtProgressMenu extends PopupWindow {
	private Context mContext;
	private int mWindow_With;
	private int mWindow_Heigh;
	private int Progress;
	private EditText mEditText;
	private TextView mText;
	private Button mConcern;
	private int pageindex = 1, pagecounts = 1;
	private LinearLayout mLoadinglayout, mViewlayout;
	private onProgressChangeListener mListener;
	private SeekBar mSeekBar;
	private String mBookname;
	private boolean isShowToast;
	public TxtProgressMenu(Context context, String mBookname) {
		this.mContext = context;
		this.mBookname = mBookname;
		inite();
	}

	public void setonProgressChangeListener(onProgressChangeListener listener) {
		mListener = listener;
	}

	@SuppressLint("NewApi")
	private void inite() {
		WindowManager m = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics metrics = new DisplayMetrics();
		m.getDefaultDisplay().getMetrics(metrics);

		mWindow_With = metrics.widthPixels;
		mWindow_Heigh = metrics.heightPixels;

		int rootwith = mWindow_With;
		int rootheigh = mWindow_Heigh / 11;

		RelativeLayout layout = (RelativeLayout) LinearLayout.inflate(mContext, R.layout.txtprogressmenu_layout, null);

		this.setWidth(rootwith);
		this.setHeight(rootheigh);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.setContentView(layout);
		this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		ColorDrawable dw = new ColorDrawable(Color.parseColor("#88000000"));
		this.setBackgroundDrawable(dw);

		mEditText = (EditText) layout.findViewById(R.id.pregress_edittext);
		mConcern = (Button) layout.findViewById(R.id.txtprogress_concern);
		mText = (TextView) layout.findViewById(R.id.txtprogress_text);
		mLoadinglayout = (LinearLayout) layout.findViewById(R.id.txtprogress_layout1);
		mViewlayout = (LinearLayout) layout.findViewById(R.id.txtprogress_layout2);
		mSeekBar = (SeekBar) layout.findViewById(R.id.txtprogress_seekbar);
		ImageView preIv = (ImageView)layout.findViewById(R.id.iv_pre);
		ImageView nextIv = (ImageView)layout.findViewById(R.id.iv_next);
		preIv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (pageindex > 1 && pageindex < pagecounts)
				mSeekBar.setProgress(pageindex--);
			}
		});
		nextIv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (pageindex > 1 && pageindex < pagecounts)
				mSeekBar.setProgress(pageindex++);
			}
		});
		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (mListener != null) {
					mListener.onProgressChange(progress);
					DecimalFormat df = new DecimalFormat("######0.00");
					if (SharePrefManager.getIsShowToast())
					ToastUtil.showToast(mBookname+"\n"+df.format(((double)progress)*100/pagecounts)+"%");
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		mConcern.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (mListener != null) {
					int pageindex = getPageIndex();
					if (pageindex > 0) {
						mListener.onProgressChange(pageindex);
					}
				}
			}

			private int getPageIndex() {
				String pageindexstr = mEditText.getText().toString().trim();
				if (pageindexstr.length() == 0) {
					return 0;
				}
				int pageindex = Integer.valueOf(pageindexstr);
				if (pageindex > 0 && pageindex <= pagecounts) {
					return pageindex;
				}
				return 0;
			}
		});
		showLoadingview();
	}

	public void showLoadingview() {

		mLoadinglayout.setVisibility(View.VISIBLE);
		mViewlayout.setVisibility(View.INVISIBLE);

	}

	public void showViewLayout() {

		mLoadinglayout.setVisibility(View.INVISIBLE);
		mViewlayout.setVisibility(View.VISIBLE);

	}

	public void setPageIndex(int pageindex0, int pagecounts0) {
		this.pagecounts = pagecounts0;
		this.pageindex = pageindex0;
//		mText.post(new Runnable() {
//
//			@Override
//			public void run() {
//				mText.setText(pageindex + "/" + pagecounts);
//			}
//		});
		mSeekBar.post(new Runnable() {

			@Override
			public void run() {
				mSeekBar.setMax(pagecounts);
				mSeekBar.setProgress(pageindex);
			}
		});
	}

	private void ButtoninNegativeState() {
		mConcern.setClickable(false);
		mConcern.setTextColor(Color.parseColor("#c1c1c1"));
	}

	private void ButtoninPositiveState() {
		mConcern.setClickable(true);
		mConcern.setTextColor(Color.parseColor("#00bcd4"));
	}

	public interface onProgressChangeListener {
		public void onProgressChange(int progress);

	}

	public int getPageindex() {
		return pageindex;
	}

	public int getPagecounts() {
		return pagecounts;
	}

	public boolean getIsShowToast() {
		return isShowToast;
	}

	public void setIsShowToast(boolean isShowToast) {
		this.isShowToast = isShowToast;
	}

	@Override
	public void dismiss() {
		super.dismiss();
		SharePrefManager.setIsShowToast(false);
	}
}
