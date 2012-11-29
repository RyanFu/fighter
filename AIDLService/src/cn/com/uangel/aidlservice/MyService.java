package cn.com.uangel.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class MyService extends Service {
	public class MyServiceImpl extends IMyService.Stub {
		@Override
		public String getValue() throws RemoteException {
			return "Android/OPhone开发讲义";
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// onBind方法必须返回MyServiceImpl类的对象实例，否则客户端无法获得服务对象。
		return new MyServiceImpl();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Toast.makeText(this, "AIDL服务启动", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "AIDL服务关闭", Toast.LENGTH_SHORT).show();
	}
	

}