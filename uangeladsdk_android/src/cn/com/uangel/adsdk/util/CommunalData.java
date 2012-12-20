package cn.com.uangel.adsdk.util;

/**
 * @author azrael 定义所有公用数据
 */
public class CommunalData {
	public static String adView_Type="";//广告类型：条幅 —— 1  悬浮 —— 2 隐式 —— 3 全屏 —— 4
	
	public static boolean net_state = false;// 网络状况 网络不通时停止请求数据

	public static boolean is_vedio = false;// 是否在播放视屏 播放视频是线程停止葱花服务器端请求信息

	public static String net_type = "";//网络类型 wifi  3G

	public static String city_code = "";//城市代码
	
	public static double longitude = 0.0;// 经度
	
	public static double latitude = 0.0;// 纬度
	
	public static int default_time = 6000;//默认广告刷新时间
	
	public static String AppId = "";//应用ID
	
	public static String Imei = "";//机器号
	
	public static String service_prv = "";// 服务商
	
	public static String packagename = "";//应用包名
	
	public static String phone_type = "";//手机型号
	
	public static String[] province = { "北京", "天津", "河北", "山西", "内蒙", "辽宁",
			"吉林", "黑龙江", "上海", "江苏", "浙江", "安徽", "福建", "江西", "山东省", "河南", "湖北",
			"湖南", "广东", "广西", "海南", "重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃",
			"青海", "宁夏", "新疆", "台湾", "香港", "澳门", "其他" };

	public static String[] provinceCode = { "110000", "120000", "130000",
			"140000", "150000", "210000", "220000", "230000", "310000",
			"320000", "330000", "340000", "350000", "360000", "370000",
			"410000", "420000", "430000", "440000", "450000", "460000",
			"500000", "510000", "520000", "530000", "540000", "610000",
			"620000", "630000", "640000", "650000", "710000", "810000",
			"820000", "000000" };

}
