package cn.com.uangel.adsdk.views;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.uangel.adsdk.entity.Advertisement;
import cn.com.uangel.adsdk.socketrequest.ClientSocketRequest;
import cn.com.uangel.adsdk.util.ClickListner;
import cn.com.uangel.adsdk.util.MyAnimation;

/**
 * 
 * @author dev 条幅型广告
 */
public class AdView_Banner extends RelativeLayout {

	private static final int adType = 1; // 代表条幅型
	private int textcolor;// 文字颜色
	private int bgcolor;// 背景颜色
	private int refreshTime = 0; // 自动刷新时间
	private boolean isTest = false; // 是否 测试
	private TextView adTitle = null;
	private ImageView image = null;
	private ImageView closeImg = null;
	private Drawable bg = null;
	private Context mcontext = null;
	private Advertisement ad;
	private int default_time = 6000;
	private long record_click_time;
	private boolean canClose = false; // 是否可以关闭
	private boolean isRunning = true; // 是否在循环请求数据

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				layoutChoise(3, ad);
				break;
			case 1:
				layoutChoise(2, ad);
				break;
			case -1:
				adTitle.setText("");
				isRunning = false;
				Log.i("UangelAD", "网络连接失败！");
				onDestroy();
				// Toast.makeText(mcontext, "网络连接失败！", 0).show();
				break;
			}
		};
	};

	public AdView_Banner(Context context) {
		super(context);
		mcontext = context;

		adTitle = new TextView(context);
		image = new ImageView(context);

		setFocusable(true);
		setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
		setClickable(true);

		setBackgroundColor(Color.BLUE);
	}

	/**
	 * @param context
	 *            活动对象
	 * @param bgColor
	 *            背景颜色
	 * @param textColor
	 *            为文字颜色
	 * @param isTest
	 *            是否是测试
	 * @param RequestInterval
	 *            请求间隔
	 * @param canClose
	 *            是否可以关闭
	 */
	public AdView_Banner(Context context, int bgColor, int textColor, boolean isTest, int RequestInterval,
			boolean canClose) {
		this(context, null, 0);

		Toast.makeText(mcontext, "手机生产商: " + Build.MANUFACTURER, 0).show();

		mcontext = context;
		this.isTest = isTest;
		textcolor = textColor;
		bgcolor = bgColor;
		this.canClose = canClose;

		adTitle = new TextView(context);
		adTitle.setText("广告加载中……");
		image = new ImageView(context);
		closeImg = new ImageView(context);
		closeImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isRunning = false;
				onDestroy();
			}
		});
		try {
			closeImg.setBackgroundDrawable(Drawable.createFromStream(mcontext.getAssets().open("close_btn.png"), null));
		} catch (IOException e) {
			e.printStackTrace();
		}
		RelativeLayout.LayoutParams lp_adTitle = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lp_adTitle.setMargins(6, 11, 0, 0);
		lp_adTitle.addRule(RelativeLayout.CENTER_HORIZONTAL);
		addView(adTitle, lp_adTitle);
		setFocusable(true);
		setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
		setClickable(true);

		setRequestInterval(RequestInterval);

		delayedAction(100);
	}

	public AdView_Banner(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AdView_Banner(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);

		mcontext = paramContext;
		adTitle = new TextView(paramContext);
		image = new ImageView(paramContext);

		RelativeLayout.LayoutParams lp_adTitle = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		lp_adTitle.setMargins(6, 11, 0, 0);
		lp_adTitle.addRule(RelativeLayout.CENTER_HORIZONTAL);
		addView(adTitle, lp_adTitle);
		setFocusable(true);
		setDescendantFocusability(262144);
		setClickable(true);
		String str;
		if (paramAttributeSet != null) {
			str = "http://schemas.android.com/apk/res/" + paramContext.getPackageName();

			if (paramAttributeSet.getAttributeBooleanValue(str, "testing", false)) {
				isTest = true;// 判断是测试还是正式使用
			}
			textcolor = paramAttributeSet.getAttributeUnsignedIntValue(str, "textColor", 0xffffff);
			bgcolor = paramAttributeSet.getAttributeUnsignedIntValue(str, "backgroundColor", 0x000000);
			canClose = paramAttributeSet.getAttributeBooleanValue(str, "canClose", false);
			if (canClose) {
				closeImg = new ImageView(mcontext);
				closeImg.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						isRunning = false;
						onDestroy();
					}
				});
				try {
					closeImg.setBackgroundDrawable(Drawable.createFromStream(mcontext.getAssets().open("close_btn.png"), null));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			int a = paramAttributeSet.getAttributeIntValue(str, "refreshInterval", 60);
			setRequestInterval(a);
			setTextColor(textcolor);
			setBackgroundColor(bgcolor);
			delayedAction(100);
		}
	}

	protected void delayedAction(long delay) {
		handler.postDelayed(new Runnable() {
			public void run() {
				new Thread() {
					@Override
					public void run() {
						Looper.prepare();
						while (isRunning) {
							try {
								Message msg = handler.obtainMessage();
								if (isTest) {
									msg.what = 0;
									ad = new Advertisement();
									Bitmap bitmap = BitmapFactory.decodeStream(mcontext.getAssets().open("logo.png"));
									Drawable d = new BitmapDrawable(bitmap);
									ad.setImages(d);
									ad.setAdTitle("测试广告！！！");
								} else {
									int titleOrImage = -1;
									Vector<Advertisement> ads = ClientSocketRequest.getIntence().queryServerData(
											mcontext, adType);
									if (ads != null) {
										ad = ads.get(0);
										titleOrImage = ad.getTitleOrImg();
									}
									msg.what = titleOrImage;
								}
								msg.obj = ad;
								handler.sendMessage(msg);
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								try {
									Thread.sleep(refreshTime);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}.start();
			}
		}, delay);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			topLeftScaleAnimation();
		} else if (event.getAction() == MotionEvent.ACTION_UP) {

			if (ad == null) {
				return false;
			}
			if (!isTest) {
				ad.setClickWhichView(AdView_Banner.this);
				ClickListner.onClick(mcontext, ad);
				if (System.currentTimeMillis() - record_click_time > 10000) { // 限制恶意点击
					ClientSocketRequest.getIntence().sendClickInfo(ad);
				}
			} else {
				Uri uri = Uri.parse("http://www.baidu.com");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				mcontext.startActivity(intent);
			}

			record_click_time = System.currentTimeMillis();
		}
		return super.onTouchEvent(event);
	}

	private synchronized void layoutChoise(int value, Advertisement ad) {
		if (!isRunning) {
			return;
		}
		Random rd = new Random();
		int num = rd.nextInt(3) + 1;
		if (getChildCount() > 0) {
			removeAllViews();
		}
		Rect rect = new Rect(0, 0, getScreenWidth(mcontext), 88);
		if (bg == null) {
			bg = DrawRect(rect, bgcolor);
			setBackgroundDrawable(bg);
		}
		if (1 == num) {
			MyAnimationView();
		} else if (2 == num) {
			animationAphoView();
		} else {
			animationListView();
		}

		switch (value) {
		case 1: // 仅标题
			adTitle.setText(ad.getAdTitle());
			if (ad.getAdTitle().length() >= 15) {
				adTitle.setTextSize(14);
			} else {
				adTitle.setTextSize(17);
			}
			adTitle.setTextColor(textcolor);
			RelativeLayout.LayoutParams lp_text1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			lp_text1.setMargins(6, 11, 0, 0);
			lp_text1.addRule(RelativeLayout.CENTER_HORIZONTAL);
			addView(adTitle, lp_text1);
			break;
		case 2: // 仅图片
			image.setBackgroundDrawable(ad.getImages());
			RelativeLayout.LayoutParams lp_logo2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					58);
			lp_logo2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			addView(image, lp_logo2);
			if (canClose) {
				RelativeLayout.LayoutParams lp_close = new RelativeLayout.LayoutParams(24, 24);
				lp_close.setMargins(10, 15, 10, 20);
				lp_close.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				addView(closeImg, lp_close);
			}
			break;
		case 3: // 文字+图片
			image.setBackgroundDrawable(ad.getImages());
			RelativeLayout.LayoutParams lp_logo = new RelativeLayout.LayoutParams(46, 46);
			lp_logo.setMargins(6, 6, 2, 0);
			lp_logo.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			addView(image, lp_logo);

			adTitle.setText(ad.getAdTitle());
			if (ad.getAdTitle().length() >= 15) {
				adTitle.setTextSize(14);
			} else {
				adTitle.setTextSize(17);
			}
			adTitle.setTextColor(textcolor);
			RelativeLayout.LayoutParams lp_text = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			lp_text.setMargins(80, 11, 0, 0);
			lp_text.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			addView(adTitle, lp_text);
			if (canClose) {
				RelativeLayout.LayoutParams lp_close = new RelativeLayout.LayoutParams(24, 24);
				lp_close.setMargins(10, 15, 10, 20);
				lp_close.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				addView(closeImg, lp_close);
			}
			break;
		}

	}

	public static Drawable DrawRect(Rect paramRect, int bgColor) {

		Bitmap localBitmap = Bitmap.createBitmap(paramRect.width(), paramRect.height(), Bitmap.Config.ARGB_8888);
		Canvas localCanvas = new Canvas(localBitmap);

		Paint localPaint = new Paint();
		localPaint.setColor(0);
		localPaint.setAntiAlias(true);
		localCanvas.drawRect(paramRect, localPaint);

		// 这里只做透明度的渐变，从ee到66
		int fromColor = Color.argb(0Xee, Color.red(bgColor), Color.green(bgColor), Color.blue(bgColor));
		int toColor = Color.argb(0X66, Color.red(bgColor), Color.green(bgColor), Color.blue(bgColor));

		int[] colorarray = new int[] { fromColor, toColor };

		GradientDrawable gradient = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colorarray);

		gradient.setBounds(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
		gradient.draw(localCanvas);

		return (Drawable) new BitmapDrawable(localBitmap);

	}

	// 设置字体颜色
	protected final void setTextColor(int paramInt) {
		this.textcolor = (0xFF000000 | paramInt);
		invalidate();
	}

	// 获取字体颜色
	protected final int getTextColor() {
		return this.textcolor;
	}

	// 最快刷新时间为6秒
	protected final void setRequestInterval(int paramInt) {
		if (paramInt > 6000) {
			refreshTime = paramInt;
		} else {
			refreshTime = default_time;
		}
	}

	public static int getScreenWidth(Context context) {
		WindowManager winManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = winManager.getDefaultDisplay();
		return display.getWidth();
	}

	/*
	 * 回收视图
	 */
	public void onDestroy() {
		handler = null;
		bg = null;
		adTitle = null;
		image = null;
		if (getChildCount() > 0) {
			removeAllViews();
		}
		setBackgroundDrawable(null);
		System.out.println("View is Destroy");
	}

	// 翻转动画
	void animationListView() {
		// ViewAnimation animation = new ViewAnimation();
		// startAnimation(animation);
		scrollIn();
		// scrollOut();
	}

	void MyAnimationView() {// 向右伸展
		MyAnimation animation = new MyAnimation();
		startAnimation(animation);
	}

	void animationAphoView() {// 渐变动画
		AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
		localAlphaAnimation.setDuration(1500L);
		localAlphaAnimation.setFillAfter(true);
		localAlphaAnimation.setInterpolator(new DecelerateInterpolator());
		startAnimation(localAlphaAnimation);
	}

	private void topLeftScaleAnimation() {

		boolean rm = false;
		AnimationSet localAnimationSet = new AnimationSet(true);
		float f1 = 20.0F;
		float f2 = 20.0F;

		ScaleAnimation localScaleAnimation2 = new ScaleAnimation(1.0F, 1.2F, 1.0F, 1.2F, f1, f2);
		localScaleAnimation2.setDuration(200L);
		localAnimationSet.addAnimation(localScaleAnimation2);

		ScaleAnimation localScaleAnimation1 = new ScaleAnimation(1.2F, 0.001F, 1.2F, 0.001F, f1, f2);
		localScaleAnimation1.setDuration(299L);
		localScaleAnimation1.setStartOffset(200L);
		localAnimationSet.addAnimation(localScaleAnimation1);
		postDelayed(new Thread() {
			public final void run() {

			}
		}, 1000L);

		if (!rm) {
			startAnimation(localAnimationSet);
			rm = true;
		}
		if (rm) {
			startAnimation(localAnimationSet);
			rm = false;
		}
		return;
	}

	// 条幅型向下滚动动画
	private void scrollIn() {
		AnimationSet utilAnimationSet = new AnimationSet(true);
		TranslateAnimation utilTranslate1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		AlphaAnimation alph = new AlphaAnimation(0.5f, 1.0f);
		utilTranslate1.setDuration(1000L);
		alph.setDuration(1000L);
		utilAnimationSet.addAnimation(utilTranslate1);
		utilAnimationSet.addAnimation(alph);
		utilAnimationSet.setFillAfter(true);
		startAnimation(utilAnimationSet);
		return;
	}

	// private void scrollOut() {
	// AnimationSet utilAnimationSet = new AnimationSet(true);
	// TranslateAnimation utilTranslate1 = new TranslateAnimation(0.0f, 0.0f,
	// 0.0f, 60);
	// AlphaAnimation alph = new AlphaAnimation(0.5f, 1.0f);
	// utilTranslate1.setDuration(1000L);
	// alph.setDuration(1000L);
	// utilAnimationSet.addAnimation(utilTranslate1);
	// utilAnimationSet.addAnimation(alph);
	// utilAnimationSet.setFillAfter(true);
	// startAnimation(utilAnimationSet);
	// return;
	// }

}
