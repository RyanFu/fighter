package cn.com.uangel.adsdk.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * 
 * @author 获取手机网络状态
 * 
 */
public class NetstateReceiver extends BroadcastReceiver {
	TelephonyManager mTelephonyMgr = null;
	WifiManager wifiMgr = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (mTelephonyMgr == null) {
			mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		}

		if (wifiMgr == null) {
			wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		}

		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			if (networkInfo.getTypeName().contains("WIFI")) {// wifi
				CommunalData.net_type = "WIFI";
				CommunalData.net_state = true;

			} else if (networkInfo.getTypeName().contains("mobile")) {// 3G
				mTelephonyMgr.listen(new PhoneStateListener() {
					@Override
					public void onDataConnectionStateChanged(int state) {
						switch (state) {
						case TelephonyManager.DATA_DISCONNECTED:// 未连接上
						case TelephonyManager.DATA_CONNECTING:// 正在连接
						case TelephonyManager.DATA_SUSPENDED:// 挂起
							CommunalData.net_state = false;
							CommunalData.net_type = "3G";
							break;
						case TelephonyManager.DATA_CONNECTED:// 已连接
							CommunalData.net_state = true;
							CommunalData.net_type = "3G";
							break;
						}
					}

				}, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
			} else {
				CommunalData.net_state = false;
				CommunalData.net_type = "UNKNOW";
			}
		} else {
			CommunalData.net_state = false;
			CommunalData.net_type = "UNKNOW";
		}
		System.out.println("net_state: " + CommunalData.net_state);
		System.out.println("net_type: " + CommunalData.net_type);

		/*
		 * if (-1 == context
		 * .checkCallingOrSelfPermission("android.permission.INTERNET")) {
		 * 
		 * } else { LogUtil.clientError(
		 * "Cannot request an ad without Internet permissions!  Open manifest.xml and just before the final </manifest> tag add:  <uses-permission android:name=\"android.permission.INTERNET\" />"
		 * ); }
		 */
	}
}
