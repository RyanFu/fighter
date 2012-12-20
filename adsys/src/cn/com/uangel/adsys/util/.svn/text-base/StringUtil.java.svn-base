package cn.com.uangel.adsys.util;

import java.math.BigInteger;
import java.util.Random;

public class StringUtil {

	/** 把memID变成一个12位的字符串 */
	public static String getMemberIDString(int memID) {
		String idStr = String.valueOf(memID);
		if (idStr.length() > 12) {
			throw new RuntimeException("错误原因：ID大于12位");
		}
		String tarStr = "";
		for (int i = 0; i < 12 - idStr.length(); i++) {
			tarStr += "0";
		}
		tarStr += idStr;
		return tarStr;
	}

	/** 获取一个6位的随机数字符串 */
	public static String getRandomString() {
		Random ran = new Random();
		int ranNum = ran.nextInt(999999);
		String ranStr = String.valueOf(ranNum);
		String tarStr = "";
		for (int i = 0; i < 6 - ranStr.length(); i++) {
			tarStr += "0";
		}
		tarStr += ranStr;
		return tarStr;
	}

	/** 将35位的appNumStr变成16进制的字符串 */
	public static String getAppIDStr(String appNumStr) {
		if (appNumStr.length() != 35) {
			throw new RuntimeException("错误原因：appNum不等于35位");
		}
		BigInteger bi = new BigInteger(appNumStr);
		bi = bi.add(new BigInteger("111111111111222222"));// 12个1,6个2
		System.out.println(bi.toString());

		return bi.toString(16);
	}
	/** 将35位的appNumStr变成16进制的字符串 */
	public static String getRegisterCode(String regisStr) {
//		if (regisStr.length() != 35) {
//			throw new RuntimeException("错误原因：appNum不等于35位");
//		}
		BigInteger bi = new BigInteger(regisStr);
		bi = bi.add(new BigInteger("111111111111222222"));// 12个1,6个2
		System.out.println(bi.toString());

		return bi.toString(16);
	}

	/** 将pid还原成与该程序相关的信息的String[] */
	public static String[] parsePid(String pid) {
		BigInteger bi = new BigInteger(pid, 16);
		System.out.println(bi.toString());
		bi = bi.add(new BigInteger("111111111111222222").negate());
		String info = bi.toString();
		String[] infos = new String[4];
		infos[0] = info.substring(0, 13);
		infos[1] = info.substring(13, 15);
		infos[2] = info.substring(15, 17);
		infos[3] = Long.parseLong(info.substring(17, 29)) + "";
		 System.out.println("0: " + infos[0]);
		 System.out.println("1: " + infos[1]);
		 System.out.println("2: " + infos[2]);
		 System.out.println("3: " + infos[3]);

		return infos;
	}

	public static void main(String[] args) {
		System.out.println(getMemberIDString(1234));
		System.out.println(getRandomString());
		String str = "13128776985230401000000000001382774";
		String str2 = getAppIDStr(str);
		System.out.println(str2);
		parsePid(str2);
	}
}
