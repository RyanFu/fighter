package cn.com.uangel.adsys.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志及输出控制类，日志功能根据后期需求加
 * 
 * @author Tree
 * 
 */
public class Log {
	public static String level = "debug";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS - ");

	public static void error(Throwable t) {
		t.printStackTrace();
	}

	/**
	 * 输出异常及原因
	 * 
	 * @param t
	 *            异常
	 * @param errorMsg
	 *            异常原因
	 */
	public static void error(Throwable t, String errorMsg) {
		t = new Throwable(errorMsg, t);
		t.printStackTrace();
	}

	/** 只有当level为debug才输出 */
	public static void debug(Object debugStr) {
		if ("debug".equals(level)) {
			System.out.println(sdf.format(new Date()) + debugStr);
		}
	}

	public static void info(String infoStr) {
		if ("info".equals(level) || "debug".equals(level)) {
			System.out.println(sdf.format(new Date()) + infoStr);
		}
	}

}
