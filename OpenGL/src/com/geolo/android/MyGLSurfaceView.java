package com.geolo.android;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

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

}
