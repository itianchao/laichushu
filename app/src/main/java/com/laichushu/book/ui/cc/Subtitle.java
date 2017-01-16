package com.laichushu.book.ui.cc;

import com.laichushu.book.bean.otherbean.BaseBookEntity;
import com.laichushu.book.global.ConstantValue;
import com.laichushu.book.retrofit.ApiDownBack;
import com.laichushu.book.retrofit.ApiStores;
import com.laichushu.book.retrofit.AppClient;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 
 * 字幕处理类
 * 
 * @author CC视频
 *
 */
public class Subtitle {

	private final String REG = "\\d+\\r\\n(\\d{2}:\\d{2}:\\d{2},\\d{3}) --> (\\d{2}:\\d{2}:\\d{2},\\d{3})\\r\\n(.*?)\\r\\n\\r\\n";

	private int start;
	private int end;
	private String content;

	private List<Subtitle> subtitles;

	/**
	 * 字幕初始化监听器
	 *
	 */
	public interface OnSubtitleInitedListener {

		public void onInited(Subtitle subtitle);
	}

	private OnSubtitleInitedListener onSubtitleInitedListener;

	private Subtitle() {
	}

	public Subtitle(OnSubtitleInitedListener onSubtitleInitedListener) {
		this.onSubtitleInitedListener = onSubtitleInitedListener;
		this.subtitles = new ArrayList<Subtitle>();
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setResource() {

	}

	public void initSubtitleResource(final String url) {
		ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);
		Call<ResponseBody> call = apiStores.downloadFile(url);
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				if (response.isSuccessful()) {
					try {
						String s = response.body().string().toString();
						parseSubtitleStr(s);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {

				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {

			}
		});
	}

	public String getSubtitleByTime(long time) {
		for (Subtitle subtitle : subtitles) {
			if (subtitle.getStart() <= time && time <= subtitle.getEnd()) {
				return subtitle.getContent();
			}
		}
		return "";
	}

	private void parseSubtitleStr(String results) {
		Pattern pattern = Pattern.compile(REG);
		Matcher matcher = pattern.matcher(results);
		while (matcher.find()) {
			Subtitle subtitle = new Subtitle();
			subtitle.setStart(parseTime(matcher.group(1)));
			subtitle.setEnd(parseTime(matcher.group(2)));
			subtitle.setContent(matcher.group(3));
			subtitles.add(subtitle);
		}

		onSubtitleInitedListener.onInited(this);
	}

	private int parseTime(String timeStr) {
		int nReturn = 0;
		String[] times = timeStr.split(",");
		int nMs = Integer.parseInt(times[1]);
		String[] time = times[0].split(":");
		int nH = Integer.parseInt(time[0]);
		int nM = Integer.parseInt(time[1]);
		int nS = Integer.parseInt(time[2]);
		nReturn += nS * 1000;
		nReturn += nM * 60 * 1000;
		nReturn += nH * 60 * 60 * 1000;
		nReturn += nMs;
		return nReturn;
	}
}
