package cn.com.uangel.magictower_test;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class MainMenu extends GameView{

	public MainMenu(Context context, MainGame mainGame) {
		super(context);
		// TODO Auto-generated constructor stub
		System.out.println("this is MainMenu!");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onKeyDown(int keyCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyUp(int keyCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyLongPress(int keyCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void reCycle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void refurbish() {
		// TODO Auto-generated method stub
		
	}

}
