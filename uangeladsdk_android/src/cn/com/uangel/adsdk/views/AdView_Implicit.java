package cn.com.uangel.adsdk.views;

/**
 * 
 * @author dev
 *  隐式广告
 *
 */

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import cn.com.uangel.adsdk.entity.Advertisement;
import cn.com.uangel.adsdk.socketrequest.ClientSocketRequest;
import cn.com.uangel.adsdk.views.EasingType.Type;

public class AdView_Implicit extends LinearLayout {

	// private static final String TAG = "Panel";

	MyAdapter adapter = null;
	Vector<Advertisement> adver = null;
	MyOnClickShowMessage myOnClickShowMessage = null;
	private boolean mIsShrinking;// 是否是收缩状态
	private int mPosition;// 位置
	private int mDuration;// 持续时间
	private boolean mLinearFlying;// 是否是直线滑动
	private Button mHandle;// SlidingDrawer的拖拽把手
	private LinearLayout mContent;// SlidingDrawer的内容
	private Drawable mOpenedHandle;// 打开的图片
	private Drawable mClosedHandle;// 关闭的图片
	private float mTrackX;// x的轨迹
	private float mTrackY;// y的轨迹
	private float mVelocity;// 速度
	private Context myContext = null;// 广告内容容器
	private ImageView image = null; // ListView 中图片控件
	private ListView listView = null;
	private boolean requestingFreshAd = false; // 是否 测试
	private int textcolor;// 文字颜色
	private TextView adTitle = null;// 图片加文字广告标题

	private Drawable btnBg1;
	private Drawable btnBg2;
	private Drawable btnBg3;
	private Drawable btnBg4;

	// private OnPanelListener panelListener;//监听器

	// 代表方向的四个常量
	public static final int TOP = 0;
	public static final int BOTTOM = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	// 枚举类型：状态
	private enum State {
		ABOUT_TO_ANIMATE, ANIMATING, READY, TRACKING, FLYING,
	};

	private State mState;// 状态
	private Interpolator mInterpolator;// 动画插入器（规定动画的播放速度）
	private GestureDetector mGestureDetector;// 手势监听类
	private int mContentHeight;// 内容的高度
	private int mContentWidth;// 内容的宽度
	private int mOrientation;// 抽屉的方向
	private PanelOnGestureListener mGestureListener;// 自定义的手势监听

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				adapter.setData(adver);
				adapter.notifyDataSetChanged();
				if (myOnClickShowMessage == null) {
					myOnClickShowMessage = new MyOnClickShowMessage(myContext, adver);
				} else {
					myOnClickShowMessage.setData(adver);
				}
				listView.setOnItemClickListener(myOnClickShowMessage);
				break;
			case 2:
				int which = msg.arg1 % 4;
				switch (which) {
				case 0:
					mHandle.setBackgroundDrawable(btnBg1);
					break;
				case 1:
					mHandle.setBackgroundDrawable(btnBg2);
					break;
				case 2:
					mHandle.setBackgroundDrawable(btnBg3);
					break;
				case 3:
					mHandle.setBackgroundDrawable(btnBg4);
					break;
				}
			}
		};
	};

	private Thread runningBgThread = new Thread() {
		@Override
		public void run() {
			int i = 1;
			while (true) {
				Message msg = handler.obtainMessage();
				msg.what = 2;
				msg.arg1 = i;
				handler.sendMessage(msg);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
	};

	public AdView_Implicit(Context context, int textColor, boolean paramBoolean) {
		this(context, null);
		setTextColor(textColor);
		requestingFreshAd = paramBoolean;
	}

	// 构造方法
	public AdView_Implicit(Context context, AttributeSet attrs) {
		super(context, attrs);
		myContext = context;
		setBackgroundColor(0x00000000);
		/*
		 * 获得自定义的属性集 R.styleable.Panel为自定义的属性文件， 该文件位于res\values目录下，名为attrs.xml
		 */
		// TypedArray a = context.obtainStyledAttributes(attrs,
		// R.styleable.Panel);
		/*
		 * 从属性集数组中检索符合要求的内容， getInteger（）方法的第一个参数为检索的索引， 第二个参数为如果检索的内容不存在，返回的默认值
		 */
		// 持续的时间
		mDuration = 750; // duration defaults to 750 ms
		// 显示的位置
		mPosition = BOTTOM; // position defaults to BOTTOM
		// 手势类型是否为滑动
		mLinearFlying = false; // linearFlying defaults to false

		// 返回一个初始化状态的typearray，以备再利用
		// a.recycle();
		// 三目运算符，如果前面条件其中一个为真，则返回VERTICAL，否则返回HORIZONTAL

		mOrientation = (mPosition == TOP || mPosition == BOTTOM) ? VERTICAL : HORIZONTAL;
		// 设置方向
		setOrientation(mOrientation);
		// 设置状态
		mState = State.READY;
		// 得到监听器的引用
		//		
		int textcolor = 0;
		String str;
		if (attrs != null) {
			str = "http://schemas.android.com/apk/res/" + context.getPackageName();

			if (attrs.getAttributeBooleanValue(str, "testingImplicit", false)) {
				// 判断是测试还是正式使用
				requestingFreshAd = true;
			}
			textcolor = attrs.getAttributeUnsignedIntValue(str, "textColorImplicit", 0xffffff);
			setTextColor(textcolor);
		}

		mGestureListener = new PanelOnGestureListener();

		mGestureDetector = new GestureDetector(mGestureListener);
		// 设置不支持长按
		mGestureDetector.setIsLongpressEnabled(false);

		// 初始化视图
		init();

		setInterpolator(new ElasticInterpolator(Type.OUT, 1.5f, 0.5f));

	}

	/**
	 * Sets the listener that receives a notification when the panel becomes
	 * open/close.
	 * 
	 * @param onPanelListener
	 *            The listener to be notified when the panel is opened/closed.
	 */
	// public void setOnPanelListener(OnPanelListener onPanelListener) {
	// panelListener = onPanelListener;
	// }
	/**
	 * Gets Panel's mHandle
	 * 
	 * @return Panel's mHandle
	 */
	public View getHandle() {
		return mHandle;
	}

	/**
	 * Gets Panel's mContent
	 * 
	 * @return Panel's mContent
	 */
	public View getContent() {
		return mContent;
	}

	/**
	 * Sets the acceleration curve for panel's animation.
	 * 
	 * @param i
	 *            The interpolator which defines the acceleration curve
	 */
	public void setInterpolator(Interpolator i) {
		mInterpolator = i;
	}

	/**
	 * Set the opened state of Panel.
	 * 
	 * @param open
	 *            True if Panel is to be opened, false if Panel is to be closed.
	 * @param animate
	 *            True if use animation, false otherwise.
	 * 
	 */
	public void setOpen(boolean open, boolean animate) {
		// 逻辑异或，两个值相同为false，不同为true
		if (isOpen() ^ open) {
			mIsShrinking = !open;
			if (animate) {
				mState = State.ABOUT_TO_ANIMATE;
				if (!mIsShrinking) {
					// this could make flicker so we test mState in
					// dispatchDraw()
					// to see if is equal to ABOUT_TO_ANIMATE
					mContent.setVisibility(VISIBLE);
				}
				post(startAnimation);
			} else {
				mContent.setVisibility(open ? VISIBLE : GONE);
				postProcess();
			}
		}
	}

	/**
	 * Returns the opened status for Panel.
	 * 
	 * @return True if Panel is opened, false otherwise.
	 */
	public boolean isOpen() {
		// 相当于三目运算符：mContent.getVisibility() == VISIBLE ? true:false
		return mContent.getVisibility() == VISIBLE;
	}

	// 初始化视图
	protected void init() {
		// 上部按钮
		mHandle = new Button(myContext);
		try {
			btnBg1 = Drawable.createFromStream(myContext.getAssets().open("rckf_bassrun1.png"), null);
			btnBg2 = Drawable.createFromStream(myContext.getAssets().open("rckf_bassrun2.png"), null);
			btnBg3 = Drawable.createFromStream(myContext.getAssets().open("rckf_bassrun3.png"), null);
			btnBg4 = Drawable.createFromStream(myContext.getAssets().open("rckf_bassrun4.png"), null);
			mHandle.setBackgroundDrawable(btnBg1);
			runningBgThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LinearLayout.LayoutParams llpLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		addView(mHandle, llpLayoutParams);
		if (mHandle == null) {
			throw new RuntimeException("Your Panel must have a View for mHandle");
		}
		mHandle.setOnTouchListener(touchListener);

		// List内容
		mContent = new LinearLayout(myContext);
		mContent.setOrientation(LinearLayout.VERTICAL);
		mContent.setBackgroundColor(Color.LTGRAY);
		LinearLayout.LayoutParams lpLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		// 显示几条广告
		listView = new ListView(myContext);
		listView.setCacheColorHint(0);
		updateListFromSocket();
		adapter = new MyAdapter(myContext, adver);// 添加到适配器
		listView.setAdapter(adapter);// 设置适配器
		// 初始化点击事件
		myOnClickShowMessage = new MyOnClickShowMessage(myContext, adver);
		listView.setOnItemClickListener(myOnClickShowMessage);

		// 设置底部按钮的布局
		LinearLayout linearLayout = new LinearLayout(myContext);
		linearLayout.setGravity(Gravity.CENTER);

		// 底部按钮比重
		LinearLayout.LayoutParams btnWidth = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, 1);

		Button pickUpBut = new Button(myContext);
		pickUpBut.setOnTouchListener(touchListener);
		pickUpBut.setText("收起");
		pickUpBut.setLayoutParams(btnWidth);
		Button moreBut = new Button(myContext);
		moreBut.setText("更多");
		moreBut.setLayoutParams(btnWidth);
		moreBut.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateListFromSocket();// 点击更多更新list

			}
		});

		linearLayout.addView(pickUpBut);
		linearLayout.addView(moreBut);

		mContent.addView(listView);
		mContent.addView(linearLayout);

		addView(mContent, lpLayoutParams);

		if (mContent == null) {
			throw new RuntimeException("Your Panel must have a View for mContent");
		}

		mContent.setVisibility(GONE);

	}

	// 更新list
	private void updateListFromSocket() {
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				new Thread() {

					@Override
					public void run() {
						try {
							Message msg = new Message();
							if (requestingFreshAd) {
								msg.what = 1;
								adver = new Vector<Advertisement>();
								for (int i = 0; i < 3; i++) {
									Random ran = new Random();
									int item = ran.nextInt(3) + 1;
									Advertisement ad = new Advertisement();
									ad.setAdTitle("Uangel 广告平台" + "测试  " + item);
									ad.setDetail("北京安捷乐通信技术有限公司制作");
									ad.setTitleOrImg(2);
									ad.setClick_result(1);
									ad.setWeb_url("http://ad.uangel.com.cn");
									adver.add(ad);
								}

							} else {
								long requestFrontTime = System.currentTimeMillis();
								adver = ClientSocketRequest.getIntence().queryServerData(myContext, 3);
								System.out.println("请求Socket服务器的时间" + (System.currentTimeMillis() - requestFrontTime));
								msg.what = 1;
							}
							handler.sendMessage(msg);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}.start();

			}

		}, 100);

	}

	// ListView 适配器
	class MyAdapter extends BaseAdapter {
		Context mylistContext;
		Vector<Advertisement> myadver = null;

		public MyAdapter(Context context, Vector<Advertisement> adver) {
			mylistContext = context;
			setData(adver);
		}

		public void setData(Vector<Advertisement> adver) {
			myadver = adver;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LinearLayout outLayout = new LinearLayout(mylistContext);
			AbsListView.LayoutParams abs = new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, 70);
			outLayout.setLayoutParams(abs);
			if (myadver != null) {
				switch (myadver.get(position).getTitleOrImg()) {
				case 0:

					image = new ImageView(mylistContext);
					image.setBackgroundDrawable(myadver.get(position).getImages());
					RelativeLayout.LayoutParams lp_logo = new RelativeLayout.LayoutParams(46, 46);
					lp_logo.setMargins(6, 6, 2, 0);
					lp_logo.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

					adTitle = new TextView(mylistContext);
					String title = myadver.get(position).getAdTitle();
					adTitle.setText(title);
					if (title.length() >= 15) {
						adTitle.setTextSize(14);
					} else {
						adTitle.setTextSize(17);
					}
					adTitle.setTextColor(getTextColor());
					RelativeLayout.LayoutParams lp_text = new RelativeLayout.LayoutParams(
							ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
					lp_text.setMargins(80, 11, 0, 0);
					lp_text.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

					RelativeLayout reLayout = new RelativeLayout(mylistContext);
					reLayout.addView(image, lp_logo);
					reLayout.addView(adTitle, lp_text);
					outLayout.addView(reLayout);
					break;

				case 1:
					image = new ImageView(mylistContext);
					image.setBackgroundDrawable(myadver.get(position).getImages());
					RelativeLayout.LayoutParams lp_logo2 = new RelativeLayout.LayoutParams(
							ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);

					lp_logo2.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
					reLayout = new RelativeLayout(mylistContext);
					reLayout.addView(image, lp_logo2);
					image.setSaveEnabled(true);
					outLayout.addView(reLayout);
					// LinearLayout.LayoutParams lp_text2 = new
					// LinearLayout.LayoutParams(
					// ViewGroup.LayoutParams.FILL_PARENT,
					// ViewGroup.LayoutParams.FILL_PARENT);
					// // lp_text2.setMargins(0, 0, 0, 0);
					// // outLayout.setOrientation(LinearLayout.VERTICAL);
					// image = new ImageView(mylistContext);
					// //
					//image.setBackgroundDrawable(myadver.get(position).getImages
					// ());
					//image.setImageDrawable(myadver.get(position).getImages());
					//					
					// outLayout.addView(image, lp_text2);
					break;
				case 2:
					adTitle = new TextView(mylistContext);
					String testTitle = myadver.get(position).getAdTitle();
					adTitle.setText(testTitle);
					if (testTitle.length() >= 15) {
						adTitle.setTextSize(14);
					} else {
						adTitle.setTextSize(17);
					}
					adTitle.setTextColor(getTextColor());
					RelativeLayout.LayoutParams lp_text1 = new RelativeLayout.LayoutParams(
							ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
					lp_text1.setMargins(6, 11, 0, 0);
					adTitle.setPadding(20, 10, 0, 0);
					lp_text1.addRule(RelativeLayout.CENTER_HORIZONTAL);
					outLayout.addView(adTitle, lp_text1);
					break;
				}

			} else {
				System.out.println("Vector<Advertisement> 广告集合是空的");
			}

			return outLayout;
		}

		@Override
		public int getCount() {
			int count = 0;
			if (myadver != null) {
				count = myadver.size();
			}
			return count;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}

	// List点击事件
	class MyOnClickShowMessage implements OnItemClickListener {

		Context mylistContext;
		Vector<Advertisement> myadver = null;

		public MyOnClickShowMessage(Context context, Vector<Advertisement> adver) {
			mylistContext = context;
			setData(adver);
		}

		public void setData(Vector<Advertisement> adver) {
			myadver = adver;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Advertisement oneAdDetailInformation = myadver.get(position);

			AdView_Implicit_Dialog adView_Implicit_Dialog = new AdView_Implicit_Dialog(mylistContext,
					oneAdDetailInformation, textcolor);
			adView_Implicit_Dialog.show();
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		mContentWidth = mContent.getWidth();
		mContentHeight = mContent.getHeight();
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {

		// String name = getResources().getResourceEntryName(getId());
		// Log.d(TAG, name + " ispatchDraw " + mState);
		// this is why 'mState' was added:
		// avoid flicker before animation start
		if (mState == State.ABOUT_TO_ANIMATE && !mIsShrinking) {
			int delta = mOrientation == VERTICAL ? mContentHeight : mContentWidth;
			if (mPosition == LEFT || mPosition == TOP) {
				delta = -delta;
			}
			if (mOrientation == VERTICAL) {
				canvas.translate(0, delta);
			} else {
				canvas.translate(delta, 0);
			}
		}
		if (mState == State.TRACKING || mState == State.FLYING) {
			canvas.translate(mTrackX, mTrackY);
		}
		super.dispatchDraw(canvas);
	}

	private float ensureRange(float v, int min, int max) {
		v = Math.max(v, min);
		v = Math.min(v, max);
		return v;
	}

	OnTouchListener touchListener = new OnTouchListener() {
		int initX;
		int initY;
		boolean setInitialPosition;

		public boolean onTouch(View v, MotionEvent event) {
			// Log.d(TAG, "state: " + mState + " x: " + event.getX() + " y: " +
			// event.getY());

			int action = event.getAction();
			if (action == MotionEvent.ACTION_DOWN) {
				initX = 0;
				initY = 0;
				if (mContent.getVisibility() == GONE) {
					// since we may not know content dimensions we use factors
					// here
					if (mOrientation == VERTICAL) {
						initY = mPosition == TOP ? -1 : 1;
					} else {
						initX = mPosition == LEFT ? -1 : 1;
					}
				}
				setInitialPosition = true;
			} else {
				if (action == MotionEvent.ACTION_UP) {
					if (isOpen()) {
						// try {
						//mHandle.setBackgroundDrawable(Drawable.createFromStream
						// (myContext.getAssets().open(
						// "splash_loading0.png"), null));
						// } catch (IOException e) {
						// e.printStackTrace();
						// }
					} else {
						// try {
						//mHandle.setBackgroundDrawable(Drawable.createFromStream
						// (myContext.getAssets().open(
						// "winad_elfin_ban_bottom_reverse.png"), null));
						// } catch (IOException e) {
						// e.printStackTrace();
						// }
						// mHandle.setText("");
					}
				}
				if (setInitialPosition) {
					// now we know content dimensions, so we multiply factors...
					initX *= mContentWidth;
					initY *= mContentHeight;
					// ... and set initial panel's position
					mGestureListener.setScroll(initX, initY);
					setInitialPosition = false;
					// for offsetLocation we have to invert values
					initX = -initX;
					initY = -initY;
				}
				// offset every ACTION_MOVE & ACTION_UP event
				event.offsetLocation(initX, initY);
			}
			if (!mGestureDetector.onTouchEvent(event)) {
				if (action == MotionEvent.ACTION_UP) {
					// tup up after scrolling
					post(startAnimation);
				}
			}

			return false;
		}
	};

	Runnable startAnimation = new Runnable() {
		public void run() {
			// this is why we post this Runnable couple of lines above:
			// now its save to use mContent.getHeight() && mContent.getWidth()
			TranslateAnimation animation;
			int fromXDelta = 0, toXDelta = 0, fromYDelta = 0, toYDelta = 0;
			if (mState == State.FLYING) {
				mIsShrinking = (mPosition == TOP || mPosition == LEFT) ^ (mVelocity > 0);
			}
			int calculatedDuration;
			if (mOrientation == VERTICAL) {
				int height = mContentHeight;
				if (!mIsShrinking) {
					fromYDelta = mPosition == TOP ? -height : height;
				} else {
					toYDelta = mPosition == TOP ? -height : height;
				}
				if (mState == State.TRACKING) {
					if (Math.abs(mTrackY - fromYDelta) < Math.abs(mTrackY - toYDelta)) {
						mIsShrinking = !mIsShrinking;
						toYDelta = fromYDelta;
					}
					fromYDelta = (int) mTrackY;
				} else if (mState == State.FLYING) {
					fromYDelta = (int) mTrackY;
				}
				// for FLYING events we calculate animation duration based on
				// flying velocity
				// also for very high velocity make sure duration >= 20 ms
				if (mState == State.FLYING && mLinearFlying) {
					calculatedDuration = (int) (1000 * Math.abs((toYDelta - fromYDelta) / mVelocity));
					calculatedDuration = Math.max(calculatedDuration, 20);
				} else {
					calculatedDuration = mDuration * Math.abs(toYDelta - fromYDelta) / mContentHeight;
				}
			} else {
				int width = mContentWidth;
				if (!mIsShrinking) {
					fromXDelta = mPosition == LEFT ? -width : width;
				} else {
					toXDelta = mPosition == LEFT ? -width : width;
				}
				if (mState == State.TRACKING) {
					if (Math.abs(mTrackX - fromXDelta) < Math.abs(mTrackX - toXDelta)) {
						mIsShrinking = !mIsShrinking;
						toXDelta = fromXDelta;
					}
					fromXDelta = (int) mTrackX;
				} else if (mState == State.FLYING) {
					fromXDelta = (int) mTrackX;
				}
				// for FLYING events we calculate animation duration based on
				// flying velocity
				// also for very high velocity make sure duration >= 20 ms
				if (mState == State.FLYING && mLinearFlying) {
					calculatedDuration = (int) (1000 * Math.abs((toXDelta - fromXDelta) / mVelocity));
					calculatedDuration = Math.max(calculatedDuration, 20);
				} else {
					calculatedDuration = mDuration * Math.abs(toXDelta - fromXDelta) / mContentWidth;
				}
			}

			mTrackX = mTrackY = 0;
			if (calculatedDuration == 0) {
				mState = State.READY;
				if (mIsShrinking) {
					mContent.setVisibility(GONE);
				}
				postProcess();
				return;
			}

			animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
			// mHandle.layout(0, 144, mHandle.getRight(), mHandle.getBottom());
			animation.setDuration(calculatedDuration);
			animation.setAnimationListener(animationListener);
			// animation.setFillAfter(true);
			if (mState == State.FLYING && mLinearFlying) {
				animation.setInterpolator(new LinearInterpolator());
			} else if (mInterpolator != null) {
				animation.setInterpolator(mInterpolator);
			}
			startAnimation(animation);
		}
	};

	private AnimationListener animationListener = new AnimationListener() {
		public void onAnimationEnd(Animation animation) {
			mState = State.READY;
			if (mIsShrinking) {
				// mContent.setVisibility(INVISIBLE);
				mContent.setVisibility(GONE);
				// try {
				// mHandle.setBackgroundDrawable(Drawable.createFromStream(
				// myContext.getAssets().open(
				// "splash_loading0.png"), null));
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				// System.out.println(mHandle.getTop() + "=" +
				// mHandle.getBottom() + "=" + mHandle.getRight());
			} else {
				// try {
				// mHandle.setBackgroundDrawable(Drawable.createFromStream(
				// myContext.getAssets().open(
				// "winad_elfin_ban_bottom_reverse.png"), null));
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				// mHandle.setText("");
			}
			postProcess();
		}

		public void onAnimationRepeat(Animation animation) {
		}

		public void onAnimationStart(Animation animation) {
			mState = State.ANIMATING;
		}
	};

	// 更改手柄图片
	private void postProcess() {
		if (mIsShrinking && mClosedHandle != null) {
			mHandle.setBackgroundDrawable(mClosedHandle);
		} else if (!mIsShrinking && mOpenedHandle != null) {
			mHandle.setBackgroundDrawable(mOpenedHandle);
		}

	}

	class PanelOnGestureListener implements OnGestureListener {
		float scrollY;
		float scrollX;

		public void setScroll(int initScrollX, int initScrollY) {
			scrollX = initScrollX;
			scrollY = initScrollY;
		}

		public boolean onDown(MotionEvent e) {
			scrollX = scrollY = 0;
			if (mState != State.READY) {
				// we are animating or just about to animate
				return false;
			}
			mState = State.ABOUT_TO_ANIMATE;
			mIsShrinking = mContent.getVisibility() == VISIBLE;
			if (!mIsShrinking) {
				// this could make flicker so we test mState in dispatchDraw()
				// to see if is equal to ABOUT_TO_ANIMATE
				mContent.setVisibility(VISIBLE);
			}
			return true;
		}

		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			mState = State.FLYING;
			mVelocity = mOrientation == VERTICAL ? velocityY : velocityX;
			post(startAnimation);
			return true;
		}

		public void onLongPress(MotionEvent e) {
			// not used
		}

		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			mState = State.TRACKING;
			float tmpY = 0, tmpX = 0;
			if (mOrientation == VERTICAL) {
				scrollY -= distanceY;
				if (mPosition == TOP) {
					tmpY = ensureRange(scrollY, -mContentHeight, 0);
				} else {
					tmpY = ensureRange(scrollY, 0, mContentHeight);
				}
			} else {
				scrollX -= distanceX;
				if (mPosition == LEFT) {
					tmpX = ensureRange(scrollX, -mContentWidth, 0);
				} else {
					tmpX = ensureRange(scrollX, 0, mContentWidth);
				}
			}
			if (tmpX != mTrackX || tmpY != mTrackY) {
				mTrackX = tmpX;
				mTrackY = tmpY;
				invalidate();
			}
			return true;
		}

		public void onShowPress(MotionEvent e) {
			// not used
		}

		// clickUp 事件
		public boolean onSingleTapUp(MotionEvent e) {
			// simple tap: click
			post(startAnimation);
			return true;
		}
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

}
