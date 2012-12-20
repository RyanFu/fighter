package cn.com.uangel.adsys.test;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

public class MainSend {
	public static void main(String[] args) throws Exception {
		try {
			  //String deviceToken = "fb4bfe9d23ae70a3183fab9ad0e61ad16e558d1c8ed5076063b32b3f64abc0a4";
			  String deviceToken = "c7e715d51a04aedf3ed003dcbff72384cf0d5f8e0bf8c7e9a499b037791a8339";

			  PayLoad payLoad = new PayLoad();
			  payLoad.addAlert("我的push测试");
			  payLoad.addBadge(1);
			  //payLoad.addSound("default");
						
			  PushNotificationManager pushManager = PushNotificationManager.getInstance();
			  pushManager.addDevice("iPod", deviceToken);
						
			  //Connect to APNs
			  String host= "gateway.sandbox.push.apple.com";
			  int port = 2195;
//			  String certificatePath= "/Users/jcjc/Desktop/push_p.p12";
//			  String certificatePath= "C:\\church.p12";
			  String certificatePath= "C:\\HanatourDev.p12";
//			  String certificatePassword= "UaNgEl67717556";
			  String certificatePassword= "hanatourUaNgEl(4";
			  pushManager.initializeConnection(host,port, certificatePath,certificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
						
			  //Send Push
			  Device client = pushManager.getDevice("iPod");
			  pushManager.sendNotification(client, payLoad);
			  pushManager.stopConnection();

			  pushManager.removeDevice("iPod");
			 }
			 catch (Exception e) {
			  e.printStackTrace();
			 }

	}
}
