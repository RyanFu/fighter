package com.geolo.android;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.geolo.android.polygon.MyCube;
import com.geolo.android.polygon.Polygon;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class MyRenderer implements Renderer {

	private Polygon mPolygon ;//¶à±ßÐÎ
	private Context mContext;

	public MyRenderer(Context context){
		this.mContext = context;
		mPolygon = new MyCube(context);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		gl.glClearColor(0, 0, 0, 1);
		gl.glEnable(GL10.GL_DEPTH_TEST);	
		gl.glClearDepthf(1.0f);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glCullFace(GL10.GL_BACK);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		float radio = (float)width/height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-radio, radio, -1, 1, 1, 100);
		mPolygon.initData(gl);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);
		mPolygon.draw(gl);
	}

}
