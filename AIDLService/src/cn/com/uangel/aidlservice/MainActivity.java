package cn.com.uangel.aidlservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

	private Button start ,stop=null;
	private Intent service =null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button)findViewById(R.id.button1);
        stop = (Button)findViewById(R.id.button2);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 
		if(service == null){
			service = new Intent(this, MyService.class);
		}
		if(v==start){
			startService(service);
		}else if(v==stop){
			stopService(service);
		}
	}

    
}
