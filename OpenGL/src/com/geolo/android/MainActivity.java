package com.geolo.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends Activity {

	private MyGLSurfaceView myGLSurfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myGLSurfaceView  = (MyGLSurfaceView)findViewById(R.id.myview);
		setContentView(R.layout.main);
		Log.d("fighter", "---------->>>>>>>>>>>>");
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(myGLSurfaceView!=null){
			myGLSurfaceView.onPause();
		}
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(myGLSurfaceView!=null){
			myGLSurfaceView.onResume();
		}
		
	}
}