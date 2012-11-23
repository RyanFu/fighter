package cn.com.uangel.lockscreen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

@SuppressLint("NewApi")
public class LockActivity extends Activity {
	static final int RESULT_ENABLE = 1;

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		System.out.println("onCreate");
		DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		ComponentName localComponentName = new ComponentName(this,
				LockReceiver.class);
		System.out.println("resault:      "+localDevicePolicyManager.isAdminActive(localComponentName));
		if (localDevicePolicyManager.isAdminActive(localComponentName)) {
			localDevicePolicyManager.lockNow();
			finish();//没这句的话 每次解锁完成都会重新再把应用锁住 ，所以必须加上
		} else {
			System.out.println("-------------oncreat");
			Intent intent = new Intent(
					DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
			// 权限列表
			// EXTRA_DEVICE_ADMIN参数中说明了用到哪些权限，
			intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
					localComponentName);
			// 描述(additional explanation)
			// EXTRA_ADD_EXPLANATION参数为附加的说明
			intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
					"--小马坏，小马爱，小马瓜瓜怪--");
			startActivity(intent);
			finish();//不加这个会导致应用激活完成后死机 且不能锁手机
		}
	}
}
