package cn.com.uangel.lockscreen;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;


public class LockReceiver extends DeviceAdminReceiver {

	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		System.out.println("onreceiver");
	}

	public void onEnabled(Context context, Intent intent) {
		System.out.println("激活使用");
		super.onEnabled(context, intent);
	}

	public void onDisabled(Context context, Intent intent) {
		System.out.println("取消激活");
		super.onDisabled(context, intent);
	}
}