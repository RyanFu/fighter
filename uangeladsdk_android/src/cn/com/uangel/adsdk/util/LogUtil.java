package cn.com.uangel.adsdk.util;

import android.util.Log;

public class LogUtil {
	public static void clientError(String message)
	  {
	    Log.e("AdMob SDK", message);
	    throw new IllegalArgumentException(message);
	  }
	
	public static void LogPrint(String title,String message)
	  {
	    System.out.println(title+":  "+ message);
	    throw new IllegalArgumentException(message);
	  }

}
