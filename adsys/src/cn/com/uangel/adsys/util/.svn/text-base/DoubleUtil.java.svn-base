package cn.com.uangel.adsys.util;

import java.math.BigDecimal;

public class DoubleUtil {

	/**
	 * 提供精确的小数位处理。
	 * 
	 * @param v
	 *            原数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 处理结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	/**
	 * 减法 v1 - v2
	 */
	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 加法 v1 + v2
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	public static void main(String[] args) {
		System.out.println(round(4.710959999999999, 4));
		System.out.println(50 - 0.3);
		System.out.println(50 + 0.3);
	}
}
