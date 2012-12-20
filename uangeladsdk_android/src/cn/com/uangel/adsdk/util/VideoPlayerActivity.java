package cn.com.uangel.adsdk.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.WindowManager;
import cn.com.uangel.adsdk.util.VideoView.MySizeChangeLinstener;

public class VideoPlayerActivity extends Activity {

	private boolean isChangedVideo = false;
	private ProgressDialog progressDialog = null;

	public class MovieInfo {
		String displayName;
		String path;
	}

	private int playedTime;
	private VideoView vv = null;
	private static int screenWidth = 0;
	private static int screenHeight = 0;

	/**
	 * 用Handler来更新UI
	 */
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// 关闭ProgressDialog
			progressDialog.dismiss();
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		vv = new VideoView(this);
		setContentView(vv);
		progressDialog = ProgressDialog.show(this, "加载数据...",
				"请耐心等待...", true, false);
		vv.setOnErrorListener(new OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {

				vv.stopPlayback();

				new AlertDialog.Builder(VideoPlayerActivity.this).setTitle(
						"对不起").setMessage("您所播的视频格式不正确，播放已停止。")
						.setPositiveButton("知道了",
								new AlertDialog.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										vv.stopPlayback();
									}
								}).setCancelable(false).show();
				return false;
			}
		});

		vv.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				handler.sendEmptyMessage(0);   
			}
		});

		vv.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				CommunalData.is_vedio = true;
				finish();

			}
		});

		vv.setMySizeChangeLinstener(new MySizeChangeLinstener() {
			@Override
			public void doMyThings() {
				// TODO Auto-generated method stub
				setVideoScale(SCREEN_FULL);
			}
		});

		getScreenSize();
		System.out.println("开始加载");
		String s = "http://forum.ea3w.com/coll_ea3w/attach/2008_10/12237832415.3gp";
		vv.setVideoURI(Uri.parse(s));
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		getScreenSize();
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		playedTime = vv.getCurrentPosition();
		vv.pause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (!isChangedVideo) {
			vv.seekTo(playedTime);
			vv.start();
		} else {
			isChangedVideo = false;
		}

		if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}

		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (vv.isPlaying()) {
			vv.stopPlayback();
		}
		if(progressDialog.isShowing()){
			progressDialog.dismiss();
		}
		super.onDestroy();
	}

	private void getScreenSize() {
		Display display = getWindowManager().getDefaultDisplay();
		screenHeight = display.getHeight();
		screenWidth = display.getWidth();
	}

	private final static int SCREEN_FULL = 0;
	private final static int SCREEN_DEFAULT = 1;

	private void setVideoScale(int flag) {
		switch (flag) {
		case SCREEN_FULL:
			vv.setVideoScale(screenWidth, screenHeight);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			break;

		case SCREEN_DEFAULT:
			int videoWidth = vv.getVideoWidth();
			int videoHeight = vv.getVideoHeight();
			int mWidth = screenWidth;
			int mHeight = screenHeight - 25;
			if (videoWidth > 0 && videoHeight > 0) {
				if (videoWidth * mHeight > mWidth * videoHeight) {
					mHeight = mWidth * videoHeight / videoWidth;
				} else if (videoWidth * mHeight < mWidth * videoHeight) {
					mWidth = mHeight * videoWidth / videoHeight;
				}
			}

			vv.setVideoScale(mWidth, mHeight);

			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

			break;
		}
	}
}
