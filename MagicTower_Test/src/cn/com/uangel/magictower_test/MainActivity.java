package cn.com.uangel.magictower_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class MainActivity extends Activity {

	private ThreadCanvas mThreadCanvas = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MainGame(this);
        mThreadCanvas = new ThreadCanvas(this);
        setContentView(mThreadCanvas);
    }
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mThreadCanvas.requestFocus();
		mThreadCanvas.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		mThreadCanvas.onKeyDown(keyCode);
		return false;
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		mThreadCanvas.onKeyUp(keyCode);
		return false;
	}
    

   

    
}
