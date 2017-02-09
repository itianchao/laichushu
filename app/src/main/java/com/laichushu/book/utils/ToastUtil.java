package com.laichushu.book.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	public static Toast mToast;

	/**
	 * 立即连续弹吐司
	 * @param msg
	 */
	public static void showToast(final String msg) {
		UIUtil.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mToast == null) {
					mToast = Toast.makeText(UIUtil.getContext(), "", Toast.LENGTH_SHORT);
				}
				mToast.setText(msg);
				mToast.show();
			}
		});
	}

	public static void showToast(final int msg) {
		UIUtil.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mToast == null) {
					mToast = Toast.makeText(UIUtil.getContext(), "", Toast.LENGTH_SHORT);
				}
				mToast.setText(msg);
				mToast.show();
			}
		});
	}
	/**
	 * 立即连续弹吐司
	 * @param msg
	 */
	public static void showToast(final String msg, final Context context) {
		UIUtil.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mToast == null) {
					mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
				}
				mToast.setText(msg);
				mToast.show();
			}
		});
	}
}
