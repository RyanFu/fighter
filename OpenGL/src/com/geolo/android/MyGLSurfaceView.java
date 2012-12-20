package com.geolo.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

@SuppressLint({ "NewApi", "NewApi" })
public class MyGLSurfaceView extends GLSurfaceView {

	private MyRenderer myRenderer;

	public MyGLSurfaceView(Context context) {
		this(context,null);
	}

	public MyGLSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		myRenderer = new MyRenderer(context);
		this.setRenderer(myRenderer);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x=event.getX();
		float y=event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			System.out.println("x:------->>"+x +"y:------>>"+y);
			break;
		}
		return true;
	}
	
	

}
