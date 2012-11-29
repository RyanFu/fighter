package cn.com.uangel.aidlclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.uangel.aidlservice.IMyService;

public class MainActivity extends Activity implements OnClickListener{

	private Button bindaidl =null;
	private Button show=null;
	private IMyService tsIMyService=null;
	private TextView result=null;
	
	private ServiceConnection connection=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			tsIMyService=null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			System.out.println("sertice:"+service);
			if(service!=null){
				tsIMyService=IMyService.Stub.asInterface(service);
				Toast.makeText(MainActivity.this, "绑定服务！", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(MainActivity.this, "请检查服务是否启动！", Toast.LENGTH_SHORT).show();
			}
			
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindaidl =(Button)findViewById(R.id.button1);
        show =(Button)findViewById(R.id.button2);
        result = (TextView)findViewById(R.id.restult);
        bindaidl.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==bindaidl){
			System.out.println("bind");
			Intent service =new Intent(IMyService.class.getName());
			bindService(service, connection, BIND_AUTO_CREATE);
		}else if (v==show){
			try {
				if(tsIMyService!=null){
					String res=tsIMyService.getValue();
					result.setText(res);
				}else {
					Toast.makeText(MainActivity.this, "找不到服务！", Toast.LENGTH_SHORT).show();
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		unbindService(connection);
		super.onDestroy();
	}

	

    
}
