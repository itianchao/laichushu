package com.pickerview;

import java.util.ArrayList;

import com.pickerview.lib.ScreenInfo;
import com.pickerview.lib.WheelOptions;
import com.pickerview.lib.WheelView;
import com.pickerview.lib.WheelView.OnItemChangedListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

public class PeriodPopupWindow extends PopupWindow implements OnClickListener, OnItemChangedListener{

	private View rootView; // 总的布局
	WheelOptions wheelOptions;
	private WheelView wheel;
	private View btnSubmit, btnCancel;
	private TextView tv_start_time, tv_end_time;
	private OnOptionsSelectListener optionsSelectListener;
	private OnOptionsChangedListener onOptionsChangedListener;
	private static final String TAG_SUBMIT = "submit";
	private static final String TAG_CANCEL = "cancel";

	public PeriodPopupWindow(Context context) {
		super(context);
		this.setWidth(LayoutParams.FILL_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setBackgroundDrawable(new BitmapDrawable());// 这样设置才能点击屏幕外dismiss窗口
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.setAnimationStyle(R.style.periodpopwindow_anim_style);

		LayoutInflater mLayoutInflater = LayoutInflater.from(context);
		rootView = mLayoutInflater.inflate(R.layout.pw_period, null);
		// -----确定和取消按钮
		btnSubmit = rootView.findViewById(R.id.btnSubmit);
		btnSubmit.setTag(TAG_SUBMIT);
		btnCancel = rootView.findViewById(R.id.btnCancel);
		btnCancel.setTag(TAG_CANCEL);
		btnSubmit.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		//-----开始日期和结束日期
		tv_start_time = (TextView) rootView.findViewById(R.id.tv_start_time);
		tv_end_time = (TextView) rootView.findViewById(R.id.tv_end_time);
		
		wheel = (WheelView) rootView.findViewById(R.id.options1);
		wheel.setOnItemChangedListener(new OnItemChangedListener() {
			
			@Override
			public void onItemChanged(int newValue) {
				onOptionsChangedListener.onOptionsChanged(newValue);
				
			}
		});
		// ----转轮
		final View optionspicker = rootView.findViewById(R.id.optionspicker);
		ScreenInfo screenInfo = new ScreenInfo((Activity) context);
		wheelOptions = new WheelOptions(optionspicker);

		wheelOptions.screenheight = screenInfo.getHeight();

		setContentView(rootView);
	}

	public void setPicker(ArrayList<String> optionsItems) {
		wheelOptions.setPicker(optionsItems, null, null, false);
	}

	public void setPicker(ArrayList<String> options1Items,
			ArrayList<ArrayList<String>> options2Items, boolean linkage) {
		wheelOptions.setPicker(options1Items, options2Items, null, linkage);
	}

	public void setPicker(ArrayList<String> options1Items,
			ArrayList<ArrayList<String>> options2Items,
			ArrayList<ArrayList<ArrayList<String>>> options3Items,
			boolean linkage) {
		wheelOptions.setPicker(options1Items, options2Items, options3Items,
				linkage);
	}
	/**
	 * 设置开始时间
	 * @param startTIme
	 */
	public void setStartTime(String startTIme){
		this.tv_start_time.setText(startTIme);
	}
	public void setEndTime(String endTime){
		this.tv_end_time.setText(endTime);
	}
	/**
	 * 设置选中的item位置
	 * @param option1
	 */
	public void setSelectOptions(int option1){
		wheelOptions.setCurrentItems(option1, 0, 0);
	}
	/**
	 * 设置选中的item位置
	 * @param option1
	 * @param option2
	 */
	public void setSelectOptions(int option1, int option2){
		wheelOptions.setCurrentItems(option1, option2, 0);
	}
	/**
	 * 设置选中的item位置
	 * @param option1
	 * @param option2
	 * @param option3
	 */
	public void setSelectOptions(int option1, int option2, int option3){
		wheelOptions.setCurrentItems(option1, option2, option3);
	}
	/**
	 * 设置选项的单位
	 * @param label1
	 * @param label2
	 */
	public void setLabels(String label1){
		wheelOptions.setLabels(label1, null, null);
	}
	/**
	 * 设置选项的单位
	 * @param label1
	 * @param label2
	 */
	public void setLabels(String label1,String label2){
		wheelOptions.setLabels(label1, label2, null);
	}
	/**
	 * 设置选项的单位
	 * @param label1
	 * @param label2
	 * @param label3
	 */
	public void setLabels(String label1,String label2,String label3){
		wheelOptions.setLabels(label1, label2, label3);
	}
	/**
	 * 设置是否循环滚动
	 * @param cyclic
	 */
	public void setCyclic(boolean cyclic){
		wheelOptions.setCyclic(cyclic);
	}

	@Override
	public void onClick(View v) 
	{
		String tag=(String) v.getTag();
		if(tag.equals(TAG_CANCEL))
		{
			dismiss();
			return;
		}
		else
		{
			if(optionsSelectListener!=null)
			{
				int[] optionsCurrentItems=wheelOptions.getCurrentItems();
				optionsSelectListener.onOptionsSelect(optionsCurrentItems[0], optionsCurrentItems[1], optionsCurrentItems[2]);
			}
			dismiss();
			return;
		}
	}

	public interface OnOptionsSelectListener {
		public void onOptionsSelect(int options1, int option2, int options3);
	}

	public void setOnoptionsSelectListener(
			OnOptionsSelectListener optionsSelectListener) {
		this.optionsSelectListener = optionsSelectListener;
	}
	
	public interface OnOptionsChangedListener{
		public void onOptionsChanged(int position);
	}
	
	public void setOnOptionsChangedListener(OnOptionsChangedListener onOptionsChangedListener){
		this.onOptionsChangedListener = onOptionsChangedListener;
	}

	@Override
	public void onItemChanged(int newValue) {
		onOptionsChangedListener.onOptionsChanged(newValue);
		
	}
}
