package cn.com.uangel.magictower_test;

import android.content.Context;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public abstract class GameView extends View {

	public GameView(Context context) {
		super(context);
	}

	/**
	 * 绘图
	 */
	protected abstract void onDraw(Canvas canvas);

	/**
	 * 按键按下
	 * 
	 * @param keyCode
	 * @return null
	 */
	public abstract boolean onKeyDown(int keyCode);

	/**
	 * 按键弹起
	 */
	public abstract boolean onKeyUp(int keyCode);

	/**
	 * 按键长时间按下
	 */
	public abstract boolean onKeyLongPress(int keyCode);

	/**
	 * 触屏事件
	 */
	public abstract boolean onTouchEvent(MotionEvent event);

	/**
	 * 回收资源
	 */
	protected abstract void reCycle();

	/**
	 * 刷新
	 */
	protected abstract void refurbish();

}
