package com.laichushu.book.ui.cc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.bokecc.sdk.mobile.exception.ErrorCode;
import com.bokecc.sdk.mobile.play.DWMediaPlayer;
import com.laichushu.book.R;
import com.laichushu.book.ui.activity.FindCourseVideoDetailActivity;
import com.laichushu.book.utils.ToastUtil;
import com.laichushu.book.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 视频播放界面
 * 
 * @author CC视频
 * 
 */
public class MediaPlayFragment extends Fragment implements
		DWMediaPlayer.OnBufferingUpdateListener,
		DWMediaPlayer.OnInfoListener,
		DWMediaPlayer.OnPreparedListener, DWMediaPlayer.OnErrorListener,
		MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, SensorEventListener {

	private boolean networkConnected = true;
	private DWMediaPlayer player;
	private Subtitle subtitle;
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private ProgressBar bufferProgressBar;
	private SeekBar skbProgress;
	private ImageView playOp, backPlayList;
	private TextView videoIdText, playDuration, videoDuration;
	private Button screenSizeBtn, definitionBtn, subtitleBtn;
	private PopMenu screenSizeMenu, definitionMenu, subtitleMenu;
	private LinearLayout playerTopLayout, volumeLayout;
	private LinearLayout playerBottomLayout;
	private AudioManager audioManager;
	private VerticalSeekBar volumeSeekBar;
	private int currentVolume;
	private int maxVolume;
	private TextView subtitleText;

	private boolean isLocalPlay;
	private boolean isPrepared;
	private Map<String, Integer> definitionMap;

	private Handler playerHandler;
	private Timer timer = new Timer();
	private TimerTask timerTask, networkInfoTimerTask;

	private int currentScreenSizeFlag = 1;
	private int currrentSubtitleSwitchFlag = 0;
	private int currentDefinition = 0;

	private boolean firstInitDefinition = true;

	private String path;

	private Boolean isPlaying;
	// 当player未准备好，并且当前activity经过onPause()生命周期时，此值为true
	private boolean isFreeze = false;
	private boolean isSurfaceDestroy = false;

	int currentPosition;
	private Dialog dialog;

	private String[] definitionArray;
	private final String[] screenSizeArray = new String[] { "满屏", "100%", "75%", "50%" };
	private final String[] subtitleSwitchArray = new String[] { "开启", "关闭" };
	private final String subtitleExampleURL = "http://dev.bokecc.com/static/font/example.utf8.srt";

	private GestureDetector detector;
	private float scrollTotalDistance, scrollCurrentPosition;
	private int lastPlayPosition, currentPlayPosition;
	private String videoId;
	private RelativeLayout  rlPlay;
	private WindowManager wm;
	private ImageView ivFullscreen;
	private FindCourseVideoDetailActivity mActivity;
	private static final String VIDEOID = "videoId";
	private static final String ISLOCALPLAY = "isLocalPlay";

	// 隐藏界面的线程
	private Runnable hidePlayRunnable = new Runnable() {
		@Override
		public void run() {
			setLayoutVisibility(View.GONE, false);
		}
	};

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.videoId = getArguments().getString(VIDEOID);
		this.isLocalPlay = getArguments().getBoolean(ISLOCALPLAY);
		this.mActivity = (FindCourseVideoDetailActivity) getActivity();
	}

	public MediaPlayFragment() {
		//在该Fragment的构造函数中注册mTouchListener的回调
		if(mListener!=null && getActivity()!=null){
			((FindCourseVideoDetailActivity)getActivity()).registerMyTouchListener(mListener);
			((FindCourseVideoDetailActivity)getActivity()).registerMyBackListener(mListener);
		}
	}
	private FindCourseVideoDetailActivity.MyListener mListener = new FindCourseVideoDetailActivity.MyListener(){
		@Override
		public void onTouchEvent(KeyEvent event) {
			// 监测音量变化

			if (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN
					|| event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {

				int volume = audioManager
						.getStreamVolume(AudioManager.STREAM_MUSIC);
				if (currentVolume != volume) {
					currentVolume = volume;
					volumeSeekBar.setProgress(currentVolume);
				}

				if (isPrepared) {
					setLayoutVisibility(View.VISIBLE, true);
				}
			}
		}

		@Override
		public void onBackPressed() {
			mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	};
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		sensorManager = (SensorManager) UIUtil.getContext().getSystemService(Context.SENSOR_SERVICE);

		wm = (WindowManager)UIUtil.getContext().getSystemService(Context.WINDOW_SERVICE);

		detector = new GestureDetector(UIUtil.getContext(), new MyGesture());
		View view = UIUtil.inflate(R.layout.video_view);
		initView(view);

		initPlayHander();

		initPlayInfo();

		initScreenSizeMenu();

		if (!isLocalPlay) {
			initNetworkTimerTask();
		}
		return view;
	}

	private void initNetworkTimerTask() {
		networkInfoTimerTask = new TimerTask() {
			@Override
			public void run() {
				ConnectivityManager cm = (ConnectivityManager) UIUtil.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		        if (networkInfo != null && networkInfo.isAvailable()) {
		        	if (!networkConnected) {
		        		timerTask = new TimerTask() {
		        			@Override
		        			public void run() {

		        				if (!isPrepared) {
		        					return;
		        				}

		        				playerHandler.sendEmptyMessage(0);
		        			}
		        		};
		        		timer.schedule(timerTask, 0, 1000);
		        	}
		        	networkConnected = true;
		        } else {
		            networkConnected = false;
		            timerTask.cancel();
		        }

			}
		};

		timer.schedule(networkInfoTimerTask, 0, 600);
	}

	private void initView(View view) {
		rlPlay = (RelativeLayout) view.findViewById(R.id.rl_play);

		rlPlay.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (!isPrepared) {
					return true;
				}

				resetHideDelayed();

				// 事件监听交给手势类来处理
				detector.onTouchEvent(event);
				return true;
			}
		});

		rlPlay.setClickable(true);
		rlPlay.setLongClickable(true);
		rlPlay.setFocusable(true);

		surfaceView = (SurfaceView)view. findViewById(R.id.playerSurfaceView);
		playOp = (ImageView) view.findViewById(R.id.btnPlay);
		backPlayList = (ImageView) view.findViewById(R.id.backPlayList);
		bufferProgressBar = (ProgressBar) view.findViewById(R.id.bufferProgressBar);

		videoIdText = (TextView) view.findViewById(R.id.videoIdText);
		playDuration = (TextView) view.findViewById(R.id.playDuration);
		videoDuration = (TextView) view.findViewById(R.id.videoDuration);
		playDuration.setText(ParamsUtil.millsecondsToStr(0));
		videoDuration.setText(ParamsUtil.millsecondsToStr(0));

		screenSizeBtn = (Button) view.findViewById(R.id.playScreenSizeBtn);
		definitionBtn = (Button) view.findViewById(R.id.definitionBtn);
		subtitleBtn = (Button) view.findViewById(R.id.subtitleBtn);

		audioManager = (AudioManager) UIUtil.getContext().getSystemService(Context.AUDIO_SERVICE);
		maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

		volumeSeekBar = (VerticalSeekBar) view.findViewById(R.id.volumeSeekBar);
		volumeSeekBar.setThumbOffset(2);

		volumeSeekBar.setMax(maxVolume);
		volumeSeekBar.setProgress(currentVolume);
		volumeSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

		skbProgress = (SeekBar) view.findViewById(R.id.skbProgress);
		skbProgress.setOnSeekBarChangeListener(onSeekBarChangeListener);

		playerTopLayout = (LinearLayout) view.findViewById(R.id.playerTopLayout);
		volumeLayout = (LinearLayout) view.findViewById(R.id.volumeLayout);
		playerBottomLayout = (LinearLayout) view.findViewById(R.id.playerBottomLayout);

		ivFullscreen = (ImageView) view.findViewById(R.id.iv_fullscreen);

		ivFullscreen.setOnClickListener(onClickListener);
		playOp.setOnClickListener(onClickListener);
		backPlayList.setOnClickListener(onClickListener);
		screenSizeBtn.setOnClickListener(onClickListener);
		definitionBtn.setOnClickListener(onClickListener);
		subtitleBtn.setOnClickListener(onClickListener);

		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); //2.3及以下使用，不然出现只有声音没有图像的问题
		surfaceHolder.addCallback(this);

		subtitleText = (TextView) view.findViewById(R.id.subtitleText);
	}

	private void initPlayHander() {
		playerHandler = new Handler() {
			public void handleMessage(Message msg) {

				if (player == null) {
					return;
				}

				// 刷新字幕
//				subtitleText.setText(subtitle.getSubtitleByTime(player
//						.getCurrentPosition()));

				// 更新播放进度
				currentPlayPosition = player.getCurrentPosition();
				int duration = player.getDuration();

				if (duration > 0) {
					long pos = skbProgress.getMax() * currentPlayPosition / duration;
					playDuration.setText(ParamsUtil.millsecondsToStr(player
							.getCurrentPosition()));
					skbProgress.setProgress((int) pos);
				}
			}
		};

		// 通过定时器和Handler来更新进度
		timerTask = new TimerTask() {
			@Override
			public void run() {

				if (!isPrepared) {
					return;
				}

				playerHandler.sendEmptyMessage(0);
			}
		};

	}

	private void initPlayInfo() {
		timer.schedule(timerTask, 0, 1000);
		isPrepared = false;
		player = new DWMediaPlayer();
		player.reset();
		player.setOnErrorListener(this);
		player.setOnVideoSizeChangedListener(this);
		player.setOnInfoListener(this);
		videoIdText.setText("");
		try {

			if (!isLocalPlay) {// 播放线上视频
				lastPlayPosition = DataSet.getVideoPosition(videoId);
				player.setVideoPlayInfo(videoId, ConfigUtil.USERID, ConfigUtil.API_KEY, UIUtil.getContext());
				// 设置默认清晰度
				player.setDefaultDefinition(DWMediaPlayer.NORMAL_DEFINITION);

			} else {// 播放本地已下载视频

				if (Environment.MEDIA_MOUNTED.equals(Environment
						.getExternalStorageState())) {
					path = Environment.getExternalStorageDirectory()
							+ "/".concat(ConfigUtil.DOWNLOAD_DIR).concat("/")
									.concat(videoId).concat(".mp4");
					if (!new File(path).exists()) {
						return;
					}

					player.setDataSource(path);
				}
			}

			player.prepareAsync();

		} catch (IllegalArgumentException e) {
			Log.e("player error", e.getMessage());
		} catch (SecurityException e) {
			Log.e("player error", e.getMessage());
		} catch (IllegalStateException e) {
			Log.e("player error", e + "");
		} catch (IOException e) {
			Log.e("player error", e.getMessage());
		}

		// 设置视频字幕
		subtitle = new Subtitle(new Subtitle.OnSubtitleInitedListener() {

			@Override
			public void onInited(Subtitle subtitle) {
				// 初始化字幕控制菜单
				initSubtitleSwitchpMenu(subtitle);
			}
		});
		subtitle.initSubtitleResource(subtitleExampleURL);

	}

	private void initScreenSizeMenu() {
		screenSizeMenu = new PopMenu(mActivity, R.drawable.popdown,
				currentScreenSizeFlag);
		screenSizeMenu.addItems(screenSizeArray);
		screenSizeMenu.setOnItemClickListener(new PopMenu.OnItemClickListener() {
					@Override
					public void onItemClick(int position) {
						// 提示已选择的屏幕尺寸
						ToastUtil.showToast(screenSizeArray[position]);

						LayoutParams params = getScreenSizeParams(position);
						params.addRule(RelativeLayout.CENTER_IN_PARENT);
						surfaceView.setLayoutParams(params);
					}
				});
	}

	private LayoutParams getScreenSizeParams(int position) {
		currentScreenSizeFlag = position;
		int width = 600;
		int height = 400;
		if (isPortrait()) {
			width = wm.getDefaultDisplay().getWidth();
			height = wm.getDefaultDisplay().getHeight() * 2 / 5; //TODO 根据当前布局更改
		} else {
			width = wm.getDefaultDisplay().getWidth();
			height = wm.getDefaultDisplay().getHeight();
		}
		
		
		String screenSizeStr = screenSizeArray[position];
		if (screenSizeStr.indexOf("%") > 0) {// 按比例缩放
			int vWidth = player.getVideoWidth();
			if (vWidth == 0) {
				vWidth = 600;
			}

			int vHeight = player.getVideoHeight();
			if (vHeight == 0) {
				vHeight = 400;
			}

			if (vWidth > width || vHeight > height) {
				float wRatio = (float) vWidth / (float) width;
				float hRatio = (float) vHeight / (float) height;
				float ratio = Math.max(wRatio, hRatio);

				width = (int) Math.ceil((float) vWidth / ratio);
				height = (int) Math.ceil((float) vHeight / ratio);
			} else {
				float wRatio = (float) width / (float) vWidth;
				float hRatio = (float) height / (float) vHeight;
				float ratio = Math.min(wRatio, hRatio);

				width = (int) Math.ceil((float) vWidth * ratio);
				height = (int) Math.ceil((float) vHeight * ratio);
			}

			
			int screenSize = ParamsUtil.getInt(screenSizeStr.substring(0, screenSizeStr.indexOf("%")));
			width = (width * screenSize) / 100;
			height = (height * screenSize) / 100;
		} 

		LayoutParams params = new LayoutParams(width, height);
		return params;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			player.setAudioStreamType(AudioManager.STREAM_MUSIC);
			player.setOnBufferingUpdateListener(this);
			player.setOnPreparedListener(this);
			player.setDisplay(holder);
			player.setScreenOnWhilePlaying(true);
			if (isSurfaceDestroy) {
				if (isLocalPlay) {
					player.setDataSource(path);
				}
				player.prepareAsync();
			}
		} catch (Exception e) {
			Log.e("videoPlayer", "error", e);
		}
		Log.i("videoPlayer", "surface created");
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		holder.setFixedSize(width, height);

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (player == null) {
			return;
		}
		if (isPrepared) {
			currentPosition = player.getCurrentPosition();
		}

		isPrepared = false;
		isSurfaceDestroy = true;

		player.stop();
		player.reset();

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		isPrepared = true;
		if (!isFreeze) {
			if(isPlaying == null || isPlaying.booleanValue()){

				playOp.setImageResource(R.drawable.btn_play);
			}
		}

		if (currentPosition > 0) {
			player.seekTo(currentPosition);
		} else {
			if (lastPlayPosition > 0) {
				player.seekTo(lastPlayPosition);
			}
		}
		
		definitionMap = player.getDefinitions();
		if (!isLocalPlay) {
			initDefinitionPopMenu();
		}

		bufferProgressBar.setVisibility(View.GONE);
		setSurfaceViewLayout();
		videoDuration.setText(ParamsUtil.millsecondsToStr(player.getDuration()));
	}
	
	// 设置surfaceview的布局
	private void setSurfaceViewLayout() {
		LayoutParams params = getScreenSizeParams(currentScreenSizeFlag);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		surfaceView.setLayoutParams(params);
	}

	private void initDefinitionPopMenu() {
		
		if(definitionMap.size() > 1 && firstInitDefinition){
			currentDefinition = 1;
			firstInitDefinition = false;
		}
		
		definitionMenu = new PopMenu(mActivity, R.drawable.popup, currentDefinition);
		// 设置清晰度列表
		definitionArray = new String[] {};
		definitionArray = definitionMap.keySet().toArray(definitionArray);

		definitionMenu.addItems(definitionArray);
		definitionMenu.setOnItemClickListener(new PopMenu.OnItemClickListener() {

			@Override
			public void onItemClick(int position) {
				try {

					currentDefinition = position;
					int definitionCode = definitionMap
							.get(definitionArray[position]);

					if (isPrepared) {
						currentPosition = player.getCurrentPosition();
						if (player.isPlaying()) {
							isPlaying = true;
						} else {
							isPlaying = false;
						}
					}

					setLayoutVisibility(View.GONE, false);
					bufferProgressBar.setVisibility(View.VISIBLE);
					
					player.reset();
					
					player.setDefinition(UIUtil.getContext(),
							definitionCode);

				} catch (IOException e) {
					Log.e("player error", e.getMessage());
				}

			}
		});
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		skbProgress.setSecondaryProgress(percent);
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			resetHideDelayed();
			
			switch (v.getId()) {
			case R.id.btnPlay:
				if (!isPrepared) {
					return;
				}
				changePlayStatus();
				break;

			case R.id.backPlayList:
				if (isPortrait()) {
					mActivity.finish();
				} else {
					mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				break;
			case R.id.iv_fullscreen:
				if (isPortrait()) {//横屏
					setVisibility(View.GONE);
					mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				} else {//竖屏
					setVisibility(View.VISIBLE);
					mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
				break;
			case R.id.playScreenSizeBtn:
				screenSizeMenu.showAsDropDown(v);
				break;
			case R.id.subtitleBtn:
				subtitleMenu.showAsDropDown(v);
				break;
			case R.id.definitionBtn:
				if(!isLocalPlay){
					definitionMenu.showAsDropDown(v);
				}
				break;
			}
		}
	};
	//显示隐藏布局
	private void setVisibility(int flag) {
//		rl.setVisibility(flag);
//		hrv.setVisibility(flag);
		mActivity.getContentLay().setVisibility(flag);
	}

	OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener() {
		int progress = 0;

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			if (networkConnected || isLocalPlay) {
				player.seekTo(progress);
				playerHandler.postDelayed(hidePlayRunnable, 5000);
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			playerHandler.removeCallbacks(hidePlayRunnable);
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			if (networkConnected || isLocalPlay) {
				this.progress = progress * player.getDuration() / seekBar.getMax();
			}
		}
	};

	OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
			currentVolume = progress;
			volumeSeekBar.setProgress(progress);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			playerHandler.removeCallbacks(hidePlayRunnable);
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			playerHandler.postDelayed(hidePlayRunnable, 5000);
		}

	};

	// 控制播放器面板显示
	private boolean isDisplay = false;

	private void initSubtitleSwitchpMenu(Subtitle subtitle) {
		this.subtitle = subtitle;
		subtitleMenu = new PopMenu(mActivity, R.drawable.popup, currrentSubtitleSwitchFlag);
		subtitleMenu.addItems(subtitleSwitchArray);
		subtitleMenu.setOnItemClickListener(new PopMenu.OnItemClickListener() {

			@Override
			public void onItemClick(int position) {
				switch (position) {
				case 0:// 开启字幕
					currentScreenSizeFlag = 0;
					subtitleText.setVisibility(View.VISIBLE);
					break;
				case 1:// 关闭字幕
					currentScreenSizeFlag = 1;
					subtitleText.setVisibility(View.GONE);
					break;
				}
			}
		});
	}

	private void setLayoutVisibility(int visibility, boolean isDisplay) {
		if (player == null) {
			return;
		}

		if (player.getDuration() <= 0) {
			return;
		}

		playerHandler.removeCallbacks(hidePlayRunnable);

		this.isDisplay = isDisplay;

		if (isDisplay) {
			playerHandler.postDelayed(hidePlayRunnable, 5000);
		}

		playerTopLayout.setVisibility(visibility);
		playerBottomLayout.setVisibility(visibility);

		if (isPortrait()) {
			volumeLayout.setVisibility(View.GONE);
			screenSizeBtn.setVisibility(View.GONE);
			definitionBtn.setVisibility(View.GONE);
			subtitleBtn.setVisibility(View.GONE);
		} else {
			volumeLayout.setVisibility(visibility);
			screenSizeBtn.setVisibility(visibility);
			definitionBtn.setVisibility(visibility);
			subtitleBtn.setVisibility(visibility);
		}
	}

	private Handler alertHandler = new Handler() {

		AlertDialog.Builder builder;
		AlertDialog.OnClickListener onClickListener = new AlertDialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
//				mActivity.finish();
			}

		};

		@Override
		public void handleMessage(Message msg) {

			String message = "";
			boolean isSystemError = false;
			if (ErrorCode.INVALID_REQUEST.Value() == msg.what) {
				message = "无法播放此视频，请检查视频状态";
			} else if (ErrorCode.NETWORK_ERROR.Value() == msg.what) {
				message = "无法播放此视频，请检查网络状态";
			} else if (ErrorCode.PROCESS_FAIL.Value() == msg.what) {
				message = "无法播放此视频，请检查帐户信息";
			} else {
				isSystemError = true;
			}

			if (!isSystemError) {
				builder = new AlertDialog.Builder(mActivity);
				dialog = builder.setTitle("提示").setMessage(message)
						.setPositiveButton("OK", onClickListener)
						.setCancelable(false).show();
			}

			super.handleMessage(msg);
		}

	};

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Message msg = new Message();
		msg.what = what;
		if (alertHandler != null) {
			alertHandler.sendMessage(msg);
		}
		return false;
	}


	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
		setSurfaceViewLayout();
	}

	// 重置隐藏界面组件的延迟时间
	private void resetHideDelayed() {
		playerHandler.removeCallbacks(hidePlayRunnable);
		playerHandler.postDelayed(hidePlayRunnable, 5000);
	}

	private class MyGesture extends SimpleOnGestureListener {

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return super.onSingleTapUp(e);
		}

		@Override
		public void onLongPress(MotionEvent e) {
			super.onLongPress(e);
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			if (!isDisplay) {
				setLayoutVisibility(View.VISIBLE, true);
			}
			scrollTotalDistance += distanceX;

			float duration = (float) player.getDuration();

			WindowManager wm = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);

			float width = wm.getDefaultDisplay().getWidth() * 0.75f; // 设定总长度是多少，此处根据实际调整

			float currentPosition = scrollCurrentPosition - (float) duration
					* scrollTotalDistance / width;

			if (currentPosition < 0) {
				currentPosition = 0;
			} else if (currentPosition > duration) {
				currentPosition = duration;
			}

			player.seekTo((int) currentPosition);

			playDuration.setText(ParamsUtil
					.millsecondsToStr((int) currentPosition));
			int pos = (int) (skbProgress.getMax() * currentPosition / duration);
			skbProgress.setProgress(pos);

			return super.onScroll(e1, e2, distanceX, distanceY);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			return super.onFling(e1, e2, velocityX, velocityY);
		}

		@Override
		public void onShowPress(MotionEvent e) {
			super.onShowPress(e);
		}

		@Override
		public boolean onDown(MotionEvent e) {

			scrollTotalDistance = 0f;

			scrollCurrentPosition = (float) player.getCurrentPosition();

			return super.onDown(e);
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			if (!isDisplay) {
				setLayoutVisibility(View.VISIBLE, true);
			}
			changePlayStatus();
			return super.onDoubleTap(e);
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			return super.onDoubleTapEvent(e);
		}
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			if (isDisplay) {
				setLayoutVisibility(View.GONE, false);
			} else {
				setLayoutVisibility(View.VISIBLE, true);
			}
			return super.onSingleTapConfirmed(e);
		}

	}

	private void changePlayStatus() {
		if (player.isPlaying()) {
			player.pause();
			playOp.setImageResource(R.drawable.btn_play);

		} else {
			player.start();
			playOp.setImageResource(R.drawable.btn_pause);
		}
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		switch(what) {
		case DWMediaPlayer.MEDIA_INFO_BUFFERING_START:
			if (player.isPlaying()) {
				bufferProgressBar.setVisibility(View.VISIBLE);
			}
			break;
		case DWMediaPlayer.MEDIA_INFO_BUFFERING_END:
			bufferProgressBar.setVisibility(View.GONE);
			break;
		}
		return false;
	}

	// 获得当前屏幕的方向
	private boolean isPortrait() {
		int mOrientation = mActivity.getResources().getConfiguration().orientation;
		if ( mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
			return false;
		} else{
			return true;
		}
	}
	private int mX, mY, mZ;
	private long lastTimeStamp = 0;
	private Calendar mCalendar;
	private SensorManager sensorManager;

	@Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null) {
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            int x = (int) event.values[0];
            int y = (int) event.values[1];
            int z = (int) event.values[2];
            mCalendar = Calendar.getInstance();
            long stamp = mCalendar.getTimeInMillis() / 1000l;

            int second = mCalendar.get(Calendar.SECOND);// 53

            int px = Math.abs(mX - x);
            int py = Math.abs(mY - y);
            int pz = Math.abs(mZ - z);

            int maxvalue = getMaxValue(px, py, pz);
            if (maxvalue > 2 && (stamp - lastTimeStamp) > 1) {
                lastTimeStamp = stamp;
                Log.d("demo", " sensor isMoveorchanged....");
				mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }
            mX = x;
            mY = y;
            mZ = z;
        }
    }

    /**
     * 获取一个最大值
     *
     * @param px
     * @param py
     * @param pz
     * @return
     */
    private int getMaxValue(int px, int py, int pz) {
        int max = 0;
        if (px > py && px > pz) {
            max = px;
        } else if (py > px && py > pz) {
            max = py;
        } else if (pz > px && pz > py) {
            max = pz;
        }

        return max;
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	@Override
	public void onResume() {
		if (isFreeze) {
			isFreeze = false;
			if (isPrepared) {
				player.start();
			}
		} else {
			if (isPlaying != null && isPlaying.booleanValue() && isPrepared) {
				player.start();
			}
		}
		super.onResume();
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onPause() {
		if (isPrepared) {
			// 如果播放器prepare完成，则对播放器进行暂停操作，并记录状态
			if (player.isPlaying()) {
				isPlaying = true;
			} else {
				isPlaying = false;
			}
			player.pause();
		} else {
			// 如果播放器没有prepare完成，则设置isFreeze为true
			isFreeze = true;
		}

		super.onPause();
	}
	
	@Override
	public void onStop() {
		sensorManager.unregisterListener(this);
		super.onStop();
	}
	
	@Override
	public void onDestroy() {
		timerTask.cancel();
		
		playerHandler.removeCallbacksAndMessages(null);
		playerHandler = null;
		
		alertHandler.removeCallbacksAndMessages(null);
		alertHandler = null;
		
		if (currentPlayPosition > 0 ) {
			if (lastPlayPosition > 0) {
				DataSet.updateVideoPosition(videoId, currentPlayPosition);
			} else {
				DataSet.insertVideoPosition(videoId, currentPlayPosition);
			}
		}
		
		if (player != null) {
			player.reset();
			player.release();
			player = null;
		}
		if (dialog != null) {
			dialog.dismiss();
		}
		if (!isLocalPlay) {
			networkInfoTimerTask.cancel();
		}
		if(mListener!=null && getActivity()!=null){
			((FindCourseVideoDetailActivity)getActivity()).unRegisterMyTouchListener(mListener);
			((FindCourseVideoDetailActivity)getActivity()).unRegisterMyBackListener(mListener);
		}
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		
		super.onConfigurationChanged(newConfig);
		
		if (isPrepared) {
			// 刷新界面
			setLayoutVisibility(View.GONE, false);
			setLayoutVisibility(View.VISIBLE, true);
		}
		
		if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			ivFullscreen.setImageResource(R.drawable.fullscreen_close);
			setVisibility(View.VISIBLE);
		} else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
			mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			ivFullscreen.setImageResource(R.drawable.fullscreen_open);
			setVisibility(View.GONE);
		}
		
		setSurfaceViewLayout();
	}
}
