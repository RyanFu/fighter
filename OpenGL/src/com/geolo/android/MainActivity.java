package com.geolo.android;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private MyGLSurfaceView myGLSurfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myGLSurfaceView  = new MyGLSurfaceView(this);
		setContentView(myGLSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		myGLSurfaceView.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		myGLSurfaceView.onResume();
	}
}