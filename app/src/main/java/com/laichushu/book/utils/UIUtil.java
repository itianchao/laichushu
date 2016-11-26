package com.laichushu.book.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.laichushu.book.global.BaseApplication;
import com.laichushu.book.R;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 和UI操作相关的类
 */
public class UIUtil
{
	/**
	 * 上下文的获取
	 * @return
	 */
	public static Context getContext()
	{
		return BaseApplication.getContext();
	}

	/**
	 * 获取资源
	 * @return
	 */
	public static Resources getResources()
	{
		return getContext().getResources();
	}

	public static long getMainThreadId()
	{
		return BaseApplication.getMainThreadId();
	}

	public static Handler getMainThreadHandler()
	{
		return BaseApplication.getMainThreadHandler();
	}

	/**
	 * 主线程中执行 任务
	 * @param task
	 */
	public static void runOnUiThread(Runnable task)
	{
		long currentThreadId = android.os.Process.myTid();
		long mainThreadId = getMainThreadId();

		if (currentThreadId == mainThreadId)
		{
			// 如果在主线程中执行
			task.run();
		}
		else
		{
			// 需要转的主线程执行
			getMainThreadHandler().post(task);
		}
	}

	/**
	 * @param dip
	 * @return
	 */
	public static int dip2px(int dip)
	{
		// 公式 1: px = dp * (dpi / 160)
		// 公式 2: dp = px / denistity;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		// metrics.densityDpi
		return (int) (dip * density + 0.5f);
	}

	public static int px2dip(int px)
	{
		// 公式 1: px = dp * (dpi / 160)
		// 公式 2: dp = px / denistity;
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		// metrics.densityDpi
		return (int) (px / density + 0.5f);
	}

	public static String getString(int resId)
	{
		return getResources().getString(resId);
	}

	public static String getPackageName()
	{
		return getContext().getPackageName();
	}

	public static String[] getStringArray(int resId)
	{
		return getResources().getStringArray(resId);
	}

	public static int getColor(int resId)
	{
		return getResources().getColor(resId);
	}

	/**
	 * 执行延时操作
	 * @param task
	 * @param delay
	 */
	public static void postDelayed(Runnable task, long delay)
	{
		getMainThreadHandler().postDelayed(task, delay);
	}

	/**
	 * 移除任务
	 * @param task
	 */
	public static void removeCallbacks(Runnable task)
	{
		getMainThreadHandler().removeCallbacks(task);
	}

	/**
	 * 开启activity
	 * @param intent
	 */
	public static void startActivity(Intent intent)
	{
		getContext().startActivity(intent);
	}

	public static void displayToast(String str)
	{
		Toast.makeText(UIUtil.getContext(), str, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 通过类名启动Activity
	 * @param pClass
	 */
	public static void openActivity(Activity activity, Class<?> pClass)
	{
		openActivity(activity, pClass, null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 * @param pClass
	 * @param pBundle
	 */
	public static void openActivity(Activity activity, Class<?> pClass, Bundle pBundle)
	{
		Intent intent = new Intent(getContext(), pClass);
		if (pBundle != null)
		{
			intent.putExtras(pBundle);
		}
		activity.startActivityForResult(intent,0);
		activity.overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
	}

	/**
	 * 通过Action启动Activity
	 * 
	 * @param pAction
	 */
	public static void openActivity(Activity activity, String pAction)
	{
		openActivity(activity, pAction, null);
	}

	/**
	 * 通过Action启动Activity，并且含有Bundle数据
	 * 
	 * @param pAction
	 * @param pBundle
	 */
	public static void openActivity(Activity activity, String pAction, Bundle pBundle)
	{
		Intent intent = new Intent(pAction);
		if (pBundle != null)
		{
			intent.putExtras(pBundle);
		}
		activity.startActivity(intent);
	}

	/**
	 * 设置通知栏颜色
	 */
	public static void setNotifyBgColor(Activity activity, int colorId)
	{
		// 自定义通知栏颜色
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			setTranslucentStatus(activity, true);
			SystemBarTintManager tintManager = new SystemBarTintManager(activity);
			tintManager.setStatusBarTintEnabled(true);
			tintManager.setStatusBarTintResource(colorId);// 通知栏所需颜色
		}
	}

	@TargetApi(19)
	public static void setTranslucentStatus(Activity activity, boolean on)
	{
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on)
		{
			winParams.flags |= bits;
		}
		else
		{
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	/**
	 * 动态获取ListView的高度 listview的item的根布局一定要是LinearLayout 调用该方法需要在适配器数据加载更新之后
	 * @param listView
	 */
	public static int getTotalHeightofListView(ListView listView)
	{
		ListAdapter mAdapter = listView.getAdapter();
		if (mAdapter == null) { return 0; }
		int totalHeight = 0;
		for (int i = 0; i < mAdapter.getCount(); i++)
		{
			View mView = mAdapter.getView(i, null, listView);
			mView.measure(
							MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
							MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			// mView.measure(0, 0);
			totalHeight += mView.getMeasuredHeight();
			// Log.w("HEIGHT" + i, String.valueOf(totalHeight));
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
		return params.height;
	}

	/**
	 * 通过Fragment名寻找Fragment
	 * @param activity
	 * @param fragmentName
	 * @return
	 */
	public static Fragment getFragment(FragmentActivity activity, String fragmentName)
	{
		FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
		List<Fragment> fragments = supportFragmentManager.getFragments();
		if(fragments == null || fragments.size()==0) return null;
		for (Fragment f : fragments)
		{
			String name = f.getClass().getSimpleName();
			if (fragmentName.equals(name)) { return f; }
		}
		return null;
	}

	/**
	 * 获取屏幕高
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenHeight()
	{
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		int height = wm.getDefaultDisplay().getHeight();
		return height;
	}

	/**
	 * 获取View高度
	 * @param view
	 * @return
	 */
	public static int getViewMeasureHeight(View view){
		int w = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		int h = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int height = view.getMeasuredHeight();
		//int width = mTitleContainer.getMeasuredWidth();
		//UIUtil.displayToast("height = " + UIUtil.px2dip(height));
		return height;
	}

	/**
	 * 获取通知栏高度
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context){
		Class c = null;
		Object obj = null;
		Field field = null;
		int x = 0, statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return statusBarHeight;
	}

	/**
	 * 获取屏幕宽
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getScreenWidth()
	{
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}
	
	/**
	 * 获得当前程序版本信息
	 * @return
	 * @throws Exception
	 */
	public static String getVersionName() throws Exception {
		// 获取packagemanager的实例
		PackageManager packageManager = UIUtil.getContext().getPackageManager();
		// getPackageName()是当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
		return packInfo.versionName;
	}
	
	/**
	 * 设置过时价格
	 * @param tv
	 */
	public static void setOutDateStyle(TextView tv)
	{
		// 过时价格
		tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
		tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
		tv.getPaint().setAntiAlias(true);// 抗锯齿
	}
	
	/**
	 * 隐藏键盘
	 * @param edit
	 */
	public static void hideKeyboard(EditText edit){
         InputMethodManager imm = (InputMethodManager) UIUtil.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
         imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
	}
	
	/**
	 * 获取安装包信息
	 */
	public static Drawable getAppIconDrawable(){
		Drawable drawable = null;
		List<PackageInfo> list = UIUtil.getContext().getPackageManager().getInstalledPackages(0);
		for (int i = 0; i < list.size(); i++) {  
		    PackageInfo packageInfo = list.get(i);  
//		    tmpInfo.appName = packageInfo.applicationInfo.loadLabel(UIUtil.getContext().getPackageManager()).toString();
//		    tmpInfo.packageName = packageInfo.packageName;  
//		    tmpInfo.versionName = packageInfo.versionName;  
//		    tmpInfo.versionCode = packageInfo.versionCode;  
//		    tmpInfo.size = Integer.valueOf((int) new File(packageInfo.applicationInfo.publicSourceDir).length());  
		    drawable = packageInfo.applicationInfo.loadIcon(UIUtil.getContext().getPackageManager());
//		    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {  
//		            arrayList.add(tmpInfo);   
//		    } 
		}  
		return drawable;
	}

	/**
	 * 添加view
	 * @param resId
	 * @return
	 */
	public static View inflate(int resId){
		return LayoutInflater.from(getContext()).inflate(resId, null);
	}/**
	 * 添加view
	 * @param resId
	 * @param parent
 * @return
	 */
	public static View inflate(int resId, ViewGroup parent){
		return LayoutInflater.from(getContext()).inflate(resId, parent);
	}
}
