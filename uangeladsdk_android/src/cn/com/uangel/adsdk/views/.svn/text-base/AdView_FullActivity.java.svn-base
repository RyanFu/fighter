package cn.com.uangel.adsdk.views;

import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import cn.com.uangel.adsdk.entity.Advertisement;
import cn.com.uangel.adsdk.socketrequest.ClientSocketRequest;
import cn.com.uangel.adsdk.util.Util;

public class AdView_FullActivity extends Activity {
	Class<?> clazz;
	Vector<Advertisement> adver = null;
	ImageView image = null;
	long time = 0;
	private boolean requestingFreshAd = false;// 是否是测试 false :否 true: 是

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0://正式运营
				System.out.println("image.setBackgroundDrawable：");
				image.setBackgroundDrawable(adver.get(0).getImages());
				break;

			case 1://测试
				
				image.setBackgroundColor(Color.BLUE);
				break;
			case 2://显示完广告跳转Activity
				System.out.println("正式运营：");
				intentClass(clazz);
				break;
			}
		};

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Util.initAll(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getFullPicture();
		image = new ImageView(this);
		setContentView(image);

		Intent intent = getIntent();
		String clazzName = intent.getStringExtra("AfterAdClazzN");
		try {
			clazz = Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

	}

	private void intentClass(Class<?> clazz) {

		Intent intentAtherClass = new Intent();
		intentAtherClass.setClass(AdView_FullActivity.this, clazz);
		startActivity(intentAtherClass);
		finish();
	}


	private void getFullPicture() {
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				new Thread() {

					@Override
					public void run() {

						Message msg =null;
						try {
							msg = handler.obtainMessage();
							if (!requestingFreshAd) {
								
								msg.what = 0;
								adver = ClientSocketRequest.getIntence().queryServerData(null,2);
								
								System.out.println("adver.get(0).getImages():"+adver.get(0).getImages());
							} else {
								System.out.println("true");
								msg.what = 1;
								adver = new Vector<Advertisement>();

								Advertisement ad = new Advertisement();
								ad.setAdString("test");
								adver.add(ad);

							}
							if (adver != null && adver.get(0) != null && adver.get(0).getImages() != null) {
								
								handler.sendMessage(msg);
								Thread.sleep(5000);
							}
							msg.what=2;
							handler.sendMessage(msg);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}.start();
			}
		}, 10);
	}

}
