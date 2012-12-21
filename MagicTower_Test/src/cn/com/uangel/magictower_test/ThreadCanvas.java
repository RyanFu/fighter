package cn.com.uangel.magictower_test;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

public class ThreadCanvas extends View implements Runnable {

	private String m_Tag = "ThreadCanvas_Tag";
	
	public ThreadCanvas(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 绘制界面
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		if(MainGame.getMainView() != null){
			MainGame.getMainView().onDraw(canvas);
		}else {
			Log.i(m_Tag,"null");
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(yarin.GAME_LOOP);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			refurbish();//更新显示
			postInvalidate();//刷新屏幕
		}
	}
	//刷新界面
	public void refurbish(){
		if(MainGame.getMainView() != null){
			MainGame.getMainView().refurbish();
		}
	}
	//开启线程
	public void start(){
		Thread t = new Thread(this);
		t.start();
	}

	boolean onKeyDown(int keyCode) {
		if(MainGame.getMainView() != null){
			MainGame.getMainView().onKeyDown(keyCode);
		}else {
			Log.i(m_Tag,"null");
		}
		return true;
	}

	public boolean onKeyUp(int keyCode) {
		if(MainGame.getMainView() != null){
			MainGame.getMainView().onKeyUp(keyCode);
		}else {
			Log.i(m_Tag,"null");
		}
		return true;
	}
	
	
	
}
