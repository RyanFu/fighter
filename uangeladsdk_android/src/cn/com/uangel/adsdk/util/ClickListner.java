package cn.com.uangel.adsdk.util;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import cn.com.uangel.adsdk.entity.Advertisement;

public class ClickListner {

	public static void onClick(Context context, Advertisement clickResult) {
		switch (clickResult.getClick_result()) {
		case 1:// 浏览网页
			Uri uriIe = Uri.parse(clickResult.getWeb_url());
			Intent Ie = new Intent(Intent.ACTION_VIEW, uriIe);
			context.startActivity(Ie);
			break;
		case 2:// 下载应用
			Uri uriLoad = Uri.parse(clickResult.getWeb_url());
			Intent Iload = new Intent(Intent.ACTION_VIEW, uriLoad);
			context.startActivity(Iload);
			break;
		case 3:// 打电话
			Intent Icall = new Intent();
			Icall.setAction("android.intent.action.DIAL");
			Icall.setData(Uri.parse("tel:" + clickResult.getTel()));
			context.startActivity(Icall);
			break;
		case 4:// 发信息
			Intent Isms = new Intent();
			Isms.setAction("android.intent.action.SENDTO");
			Isms.setData(Uri.parse("smsto:" + clickResult.getTel()));
			Isms.putExtra("sms_body", clickResult.getSms());
			context.startActivity(Isms);
			break;
		case 5:// 发邮件
			Intent intentEm = new Intent(Intent.ACTION_SEND);
			intentEm.putExtra(Intent.EXTRA_EMAIL, clickResult.getEmail()); // 设置收件箱
			intentEm.putExtra(Intent.EXTRA_SUBJECT, clickResult.getEmail_title()); // 设置标题内容
			intentEm.putExtra(Intent.EXTRA_TEXT, clickResult.getEmail_content()); // 设置正文
			intentEm.setType("application/octet-stream"); // 其他的均使用流当做二进制数据来发送
			context.startActivity(intentEm); // 调用系统的mail客户端进行发送
			break;
		case 6:// 地图
			Intent mapIt = new Intent(context, BaiduMapActivity.class);
			mapIt.putExtra("lat", clickResult.getLat());
			mapIt.putExtra("lon", clickResult.getLon());
			mapIt.putExtra("addressName", clickResult.getWeb_url());
			context.startActivity(mapIt);
			break;
		case 7:// 视屏
			Intent videoI = new Intent();
			videoI.setClass(context, VideoPlayerActivity.class);
			videoI.putExtra("vedio", clickResult.getVideo_url());
			CommunalData.is_vedio = false;
			context.startActivity(videoI);
			break;
		case 8:// 音乐
			String[] musicInfo = clickResult.getMp3_url().split("¿");
			playMusic(context, musicInfo[1]);
			break;
		case 9:// 显示 全屏图片
			// Intent fullI = new Intent();
			// fullI.setClass(context, AdView_FullActivity.class);
			// context.startActivity(fullI);

			ViewGroup popView = new LinearLayout(context);
			popView.setBackgroundDrawable(clickResult.getClickFullImg());
			final PopupWindow mPopupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);

			mPopupWindow.showAtLocation(clickResult.getClickWhichView(), Gravity.CENTER, 0, 0);

			final Handler h = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					mPopupWindow.dismiss();
				}
			};

			h.postDelayed(new Runnable() {
				public void run() {
					h.sendEmptyMessage(0);
				}
			}, 5000);

			break;
		}
	}

	private static void playMusic(Context context, String map_url) {
		CommunalData.is_vedio = false;
		MediaPlayer mMediaPlayer = new MediaPlayer();
		
		mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				CommunalData.is_vedio = true;
				if (mp != null) {
					mp.stop();
					mp.release();
					mp = null;
				}
			}
		});
		try {
			mMediaPlayer.setDataSource(map_url);
			mMediaPlayer.prepare();
			mMediaPlayer.start();// TODO 有问题，只能播放几秒
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
