package cn.com.uangel.adsys.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADArs;
import cn.com.uangel.adsys.entity.ADPhoneType;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.ADZone;
import cn.com.uangel.adsys.entity.App;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.server.entity.HourRequestTimes;
import cn.com.uangel.adsys.service.SocketServerService;
import cn.com.uangel.adsys.service.impl.SocketServerServiceImpl;
import cn.com.uangel.adsys.util.DoubleUtil;
import cn.com.uangel.adsys.util.Log;

public class DataManager {

	private Vector<AD> allRunningADs;
	public Vector<Member> allMembers;
	public Vector<Member> allMembersBak;// 当日所以用户信息的备份，用于计算当日用户的花费和收入
	private Vector<App> allApps;
	private Vector<ADShowType> allADShowTypes;
	private Vector<ADZone> allADZones;
	private Vector<ADArs> allADArs;
	private Vector<ADPhoneType> allADPhoneTypes;
	private Vector<String> requestInfoList; // 用于缓存请求信息字符串
	private Vector<String> showInfoList; // 用于缓存显示信息字符串
	private Vector<String> clickInfoList; // 用于缓存点击信息字符串
	private SimpleDateFormat daySDF;
	private SimpleDateFormat hourSDF;
	private SimpleDateFormat secondSDF;
	private SaveToDB saveToDBTask;

	// TODO 可以定期清理一下内部次数为0的数据
	/** 应用记录每小时每个Imei请求次数 */
	private Map<String, HourRequestTimes> imeiReqeustTimes;
	private Map<String, HourRequestTimes> imeiClickTimes;

	private Thread saveRequestStringThread;// 用于将缓存队列中的信息存到文件中的线程，请求
	private Thread saveShowStringThread;// 用于将缓存队列中的信息存到文件中的线程，显示
	private Thread saveClickStringThread;// 用于将缓存队列中的信息存到文件中的线程，点击

	private boolean isSaveRequestStringThreadRunning = false;
	private boolean isSaveShowStringThreadRunning = false;
	private boolean isSaveClickStringThreadRunning = false;

	/** CPM与CPC的权值比，用于竞价排名 */
	public static final int CPMVSCPC = 15;
	/** 广告主付费一次，开发者得到的比例 */
	public static final double DEVELOPER_GET_RATE = 0.8;

	/** 存储请求信息数据的文件路径 */
	public static final String REQUEST_INFO_PATH = "D:\\adData\\request\\";
	public static final String SHOW_INFO_PATH = "D:\\adData\\show\\";
	public static final String CLICK_INFO_PATH = "D:\\adData\\click\\";
	// public static final String REQUEST_INFO_PATH =
	// "/root/adsysServer/adData/request/";
	// public static final String SHOW_INFO_PATH =
	// "/root/adsysServer/adData/show/";
	// public static final String CLICK_INFO_PATH =
	// "/root/adsysServer/adData/click/";

	private String currentRequestInfoPath;
	private String currentShowInfoPath;
	private String currentClickInfoPath;

	private SocketServerService sss;

	public void init() {
		initProperties();
		loadingNewestData();
		Log.info("今日数据库最新数据初始化完毕！");

		// 开启任务：每天凌晨2点保存前一天的请求数据
		Timer timer = new Timer();
		saveToDBTask = this.new SaveToDB();
		timer.schedule(saveToDBTask, 60 * 60 * 1000, 12 * 60 * 60 * 1000);
		Log.info("存储每日请求数据到数据库的任务开启完毕！");
	}

	// 初始化类属性
	private void initProperties() {
		requestInfoList = new Vector<String>();
		showInfoList = new Vector<String>();
		clickInfoList = new Vector<String>();
		imeiReqeustTimes = new HashMap<String, HourRequestTimes>();
		imeiClickTimes = new HashMap<String, HourRequestTimes>();
		daySDF = new SimpleDateFormat("yyyy_MM_dd");
		hourSDF = new SimpleDateFormat("yyyy_MM_dd_HH");
		secondSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	// 初始化广告数据
	private void loadingNewestData() {
		if (sss == null) {
			sss = new SocketServerServiceImpl();
		}

		allRunningADs = sss.getAllRunningADs();
		allMembers = sss.getAllMembers();
		allMembersBak = sss.getAllMembers();
		allADShowTypes = sss.getAllADShowTypes();
		allADZones = sss.getAllADZones();
		allADArs = sss.getAllADArs();
		allADPhoneTypes = sss.getAllPhoneTypes();
		allApps = sss.getAllApps();

		// sss.loadingNewestData(allRunningADs, allMembers, allMembersBak,
		// allADShowTypes, allADZones, allADArs,
		// allADPhoneTypes, allApps);
		//		
		// public void loadingNewestData(Vector<AD> allRunningADs,
		// Vector<Member> allMembers, Vector<Member> allMembersBak,
		// Vector<ADShowType> allADShowTypes, Vector<ADZone> allADZones,
		// Vector<ADArs> allADArs,
		// Vector<ADPhoneType> allADPhoneTypes, Vector<App> allApps);
	}

	public void startSaveRequestStringThread() {
		saveRequestStringThread = new Thread() {
			@Override
			public void run() {
				synchronized (DataManager.this) {
					isSaveRequestStringThreadRunning = true;
					FileWriter fw = null;
					try {
						Date d = new Date();
						String folderName = daySDF.format(d);
						String fileName = hourSDF.format(d);
						String folderPath = REQUEST_INFO_PATH + folderName;
						File folder = new File(folderPath);
						if (!folder.exists()) {
							folder.mkdir();
						}
						// urd： UangelRequestData.
						currentRequestInfoPath = folderPath + File.separator + fileName + ".urd";
						File f = new File(currentRequestInfoPath);
						fw = new FileWriter(f, true);

						// 如果缓存队列中有数据，则将其存入文件中
						while (requestInfoList.size() > 0) {
							fw.write(requestInfoList.get(0));
							requestInfoList.remove(0);
						}
					} catch (Exception e) {
						Log.error(e);
					} finally {
						try {
							fw.close();
						} catch (IOException e) {
						}
						isSaveRequestStringThreadRunning = false;
					}
				}
			}
		};
		saveRequestStringThread.start();
	}

	public void startSaveShowStringThread() {
		saveShowStringThread = new Thread() {
			@Override
			public void run() {
				synchronized (DataManager.this) {
					isSaveShowStringThreadRunning = true;
					FileWriter fw = null;
					try {
						Date d = new Date();
						String folderName = daySDF.format(d);
						String fileName = hourSDF.format(d);
						String folderPath = SHOW_INFO_PATH + folderName;
						File folder = new File(folderPath);
						if (!folder.exists()) {
							folder.mkdir();
						}
						// usd： UangelShowData.
						currentShowInfoPath = folderPath + File.separator + fileName + ".usd";
						File f = new File(currentShowInfoPath);
						fw = new FileWriter(f, true);

						// 如果缓存队列中有数据，则将其存入文件中
						while (showInfoList.size() > 0) {
							fw.write(showInfoList.get(0));
							showInfoList.remove(0);
						}
					} catch (Exception e) {
						Log.error(e);
					} finally {
						try {
							fw.close();
						} catch (IOException e) {
						}
						isSaveShowStringThreadRunning = false;
					}
				}
			}
		};
		saveShowStringThread.start();
	}

	public void startSaveClickStringThread() {
		saveClickStringThread = new Thread() {
			@Override
			public void run() {
				synchronized (DataManager.this) {
					isSaveClickStringThreadRunning = true;
					FileWriter fw = null;
					try {
						Date d = new Date();
						String folderName = daySDF.format(d);
						String fileName = hourSDF.format(d);
						String folderPath = CLICK_INFO_PATH + folderName;
						File folder = new File(folderPath);
						if (!folder.exists()) {
							folder.mkdir();
						}
						// ucd： UangelClickData.
						currentClickInfoPath = folderPath + File.separator + fileName + ".ucd";
						File f = new File(currentClickInfoPath);
						fw = new FileWriter(f, true);

						// 如果缓存队列中有数据，则将其存入文件中
						while (clickInfoList.size() > 0) {
							fw.write(clickInfoList.get(0));
							clickInfoList.remove(0);
						}
					} catch (Exception e) {
						Log.error(e);
					} finally {
						try {
							fw.close();
						} catch (IOException e) {
						}
						isSaveClickStringThreadRunning = false;
					}
				}
			}
		};
		saveClickStringThread.start();
	}

	public String testImeiAndPackage(String appId, String imei, String packageName) {
		// 测试每个imei单位时间内请求次数
		String hour = hourSDF.format(new Date());
		if (imeiReqeustTimes.containsKey(imei)) {
			HourRequestTimes hrt = imeiReqeustTimes.get(imei);
			Log.debug("IMEI: " + imei + " RequestTimes: " + hrt.getTimes());
			if (hour.equals(hrt.getHour())) {
				int times = hrt.getTimes();
				if (times > 240) {
					return "4"; // 请求次数过多，暂定每小时240次
				}
				hrt.setTimes(times + 1);
			} else {
				hrt.setHour(hour);
				hrt.setTimes(0);
			}
		} else {
			imeiReqeustTimes.put(imei, new HourRequestTimes(hour, 1));
		}

		App app = getAppByAppID(appId);
		if (!"通过".equals(app.getApp_state())) {
			return "1"; // 此应用暂时还问通过
		}
		if (app.getPakage_name() == null) {
			return "2"; // 包名为空
		}
		if (!app.getPakage_name().equals(packageName)) {
			return "3"; // 此appID已经绑定其他应用
		}
		return "0"; // No problem 没问题！
	}

	public boolean testImeiClickTimes(String appID, String imei) {
		String hour = hourSDF.format(new Date());
		if (imeiClickTimes.containsKey(imei)) {
			HourRequestTimes hrt = imeiClickTimes.get(imei);
			Log.debug("IMEI: " + imei + " ClickTimes: " + hrt.getTimes());
			if (hour.equals(hrt.getHour())) {
				int times = hrt.getTimes();
				if (times > 60) {
					return true; // 请求次数过多，暂定每小时60次
				}
				hrt.setTimes(times + 1);
			} else {
				hrt.setHour(hour);
				hrt.setTimes(0);
			}
		} else {
			imeiClickTimes.put(imei, new HourRequestTimes(hour, 1));
		}
		return false;
	}

	/** 选出满足请求条件的广告 */
	public Vector<ADShowType> getOKADs(String typeCode, String zoneCode, double lon, double lat, String phoneType,
			String arsName, String appID, String netType, int wannerCount) {
		Vector<ADShowType> okShowTypes = allADShowTypes;

		okShowTypes = filtratePause(okShowTypes);

		if (typeCode != null) {
			okShowTypes = filtrateShowType(okShowTypes, typeCode);
		}
		if (zoneCode != null) {
			okShowTypes = filtrateZone(okShowTypes, zoneCode, lon, lat);
		}
		if (phoneType != null) {
			okShowTypes = filtratePhoneType(okShowTypes, phoneType);
		}
		if (arsName != null) {
			okShowTypes = filtrateArs(okShowTypes, arsName);
		}
		if (appID != null) {
			okShowTypes = filtrateCrowdAndGender(okShowTypes, appID);
		}

		okShowTypes = filtrateTime(okShowTypes);

		if (netType != null) {
			okShowTypes = filtrateNet(okShowTypes, netType);
		}

		Log.debug("满足请求条件的广告： ");
		for (ADShowType ast : okShowTypes) {
			Log.debug(ast);
		}

		// 根据竞价排名，随机选出wannerCount条合适的广告
		List<Integer> okADsPriceWeight = new ArrayList<Integer>();
		List<Integer> copy = new ArrayList<Integer>();
		Random ran = new Random();
		for (int i = 0; i < okShowTypes.size(); i++) {
			int weight = 0;
			ADShowType ast = okShowTypes.get(i);
			if ("按印象付费".equals(ast.getPay_mode())) {
				weight = ran.nextInt((int) (ast.getPrice() * 10000));
				okADsPriceWeight.add(weight);
				copy.add(weight);
			} else {
				weight = ran.nextInt((int) (ast.getPrice() * CPMVSCPC * 10000));
				okADsPriceWeight.add(weight);
				copy.add(weight);
			}
		}
		Collections.sort(copy);

		Vector<ADShowType> ret = new Vector<ADShowType>();
		if (copy.size() >= wannerCount) {
			for (int i = 1; i <= wannerCount; i++) {
				ret.add(okShowTypes.get(okADsPriceWeight.indexOf(copy.get(copy.size() - i))));
			}
		} else {
			for (int i = 1; i <= copy.size(); i++) {
				ret.add(okShowTypes.get(okADsPriceWeight.indexOf(copy.get(copy.size() - i))));
			}
		}

		return ret;
	}

	/** 解析请求字符串 */
	public synchronized String parseRequestMessage(String msg) throws Exception {
		String[] msgs = msg.split("‖");
		String appID = msgs[0];
		String imei = msgs[1];
		String arsName = msgs[2];
		String phoneType = msgs[3];// 手机型号
		String netType = msgs[4];
		String typeCode = msgs[5];// 1:条幅型，2：插屏型，3：悬浮型
		String packageName = msgs[6];
		String zoneCode = msgs[7];
		double longitude = Double.parseDouble(msgs[8]);
		double latitude = Double.parseDouble(msgs[9]);

		String testIP = testImeiAndPackage(appID, imei, packageName);
		if ("1".equals(testIP)) {
			return "This appication is not passed temporarily";
		} else if ("2".equals(testIP)) {
			return "The package name is null";
		} else if ("3".equals(testIP)) {
			return "This appID has used in other application";
		} else if ("4".equals(testIP)) {
			// TODO 最后讨论一下，此时的请求记录是否有必要存到数据库中
			return "Request too many times for a moment";
		}

		phoneType = parsePhoneType(phoneType);

		int adCount = 0;// 请求广告的数量
		if ("3".equals(typeCode)) {
			adCount = 3;
		} else {
			adCount = 1;
		}
		Vector<ADShowType> okADs = getOKADs(typeCode, zoneCode, longitude, latitude, phoneType, arsName, appID,
				netType, adCount);
		Log.debug("typeCode : " + typeCode + " okADs num: " + okADs.size());

		String retMsg = "";

		if (okADs != null) {
			// 封装RequestInfo字符串
			for (ADShowType okAD : okADs) {
				String saveRequestMsg = appID + "︴" + okAD.getAd_id() + "︴" + secondSDF.format(new Date()) + "︴" + imei
						+ "︴" + zoneCode + "︴" + okAD.getId() + "\n";

				// 将封装好的字符串加入缓存队里中
				requestInfoList.add(saveRequestMsg);

				if (!isSaveRequestStringThreadRunning) {
					startSaveRequestStringThread();
				}

				Double cpmPrice = 0.0;

				AD ad = getADByAdID(okAD.getAd_id());
				Member ador = getMemByAdID(ad.getMem_id());// 广告主

				Log.debug("paymode: " + okAD.getPay_mode() + "  余额： " + ador.getAd_balance());
				if ("按印象付费".equals(okAD.getPay_mode())) {
					cpmPrice = okAD.getPrice() / 1000;
					// 扣除广告出本次请求的CPM费用，这里只起判定如果广告余额<0.5，让此广告主的广告置为暂停状态，并不允许再显示
					ador.setAd_balance(DoubleUtil.subtract(ador.getAd_balance(), cpmPrice));
					if (ador.getAd_balance() < 0.5) {
						for (AD a : allRunningADs) {
							if (a.getMem_id().equals(ador.getId())) {
								a.setState("暂停");
							}
						}
					}
					// 给开发者相应的分红，此功能放在凌晨更新时实现
					// Member developer = getMemByAppID(appID);
					// developer.setIncome_balance(cpmPrice * DEVELOPER_GET_RATE
					// + developer.getIncome_balance());
				}

				// 封装ShowInfo字符串
				String saveShowMsg = appID + "︴" + okAD.getAd_id() + "︴" + secondSDF.format(new Date()) + "︴"
						+ cpmPrice + "︴" + imei + "︴" + zoneCode + "︴" + okAD.getId() + "\n";
				showInfoList.add(saveShowMsg);
				if (!isSaveShowStringThreadRunning) {
					startSaveShowStringThread();
				}
				retMsg += okAD.toString() + "︴";
			}
		}

		return okADs == null || retMsg.length() == 0 ? "No ADs" : retMsg.substring(0, retMsg.length() - 1);
	}

	public synchronized String parseClickMessage(String msg) throws Exception {
		Log.debug("click: " + msg);

		String[] msgs = msg.split("‖");
		String appID = msgs[0];
		String adID = msgs[1];
		String imei = msgs[2];
		String zoneCode = msgs[3];
		// String showTypeID = msgs[4];

		if (testImeiClickTimes(appID, imei)) {
			return "Click too many times for a moment";
		}

		ADShowType okAD = getADShowTypeByADID(Integer.parseInt(adID));

		if (okAD != null) {
			Double cpcPrice = 0.0;

			AD ad = getADByAdID(okAD.getAd_id());
			Member mem = getMemByAdID(ad.getMem_id());

			Log.debug("paymode: " + okAD.getPay_mode() + "  余额： " + mem.getAd_balance());
			if ("按单次点击付费".equals(okAD.getPay_mode())) {
				cpcPrice = okAD.getPrice();
				// 扣除广告出本次请求的CPC费用
				mem.setAd_balance(DoubleUtil.subtract(mem.getAd_balance(), cpcPrice));
				if (mem.getAd_balance() < 0.5) {
					for (AD a : allRunningADs) {
						if (a.getMem_id().equals(mem.getId())) {
							a.setState("暂停");
						}
					}
				}
				// 给开发者相应的分红
				Member developer = getMemByAppID(appID);
				developer.setIncome_balance(cpcPrice * DEVELOPER_GET_RATE + developer.getIncome_balance());
			}

			// 封装ClickInfo字符串
			String saveClickMsg = appID + "︴" + okAD.getAd_id() + "︴" + secondSDF.format(new Date()) + "︴" + cpcPrice
					+ "︴" + imei + "︴" + zoneCode + "︴" + okAD.getId() + "\n";
			clickInfoList.add(saveClickMsg);
			if (!isSaveClickStringThreadRunning) {
				startSaveClickStringThread();
			}
		}

		return "click: " + okAD.getTitle();
	}

	// 讲手机生产厂商的英文转成中文
	private String parsePhoneType(String phoneType) {
		if ("samsung".equalsIgnoreCase(phoneType)) {
			phoneType = "三星";
		} else if ("zte".equalsIgnoreCase(phoneType)) {
			phoneType = "中兴";
		} else if ("motorola".equalsIgnoreCase(phoneType)) {
			phoneType = "摩托罗拉";
		} else if ("htc".equalsIgnoreCase(phoneType)) {
			phoneType = "HTC";
		} else if ("sony ericsson".equalsIgnoreCase(phoneType)) {
			phoneType = "索尼爱立信";// 未测试
		} else if ("LG".equalsIgnoreCase(phoneType)) {
			phoneType = "LG";// 未测试
		} else if ("lenovo".equalsIgnoreCase(phoneType)) {
			phoneType = "联想";// 未测试
		} else if ("gionee".equalsIgnoreCase(phoneType)) {
			phoneType = "金立";// 未测试
		} else if ("huawei".equalsIgnoreCase(phoneType)) {
			phoneType = "华为";// 未测试
		} else {
			phoneType = "其它";
		}
		return phoneType;
	}

	private ADShowType getADShowTypeByADID(int AdId) {
		for (ADShowType ast : allADShowTypes) {
			if (ast.getAd_id().equals(AdId)) {
				return ast;
			}
		}
		return null;
	}

	private App getAppByAppID(String appId) {
		for (App app : allApps) {
			if (app.getPid().equals(appId)) {
				return app;
			}
		}
		return null;
	}

	private AD getADByAdID(int adId) {
		for (AD ad : allRunningADs) {
			if (ad.getId().equals(adId)) {
				return ad;
			}
		}
		return null;
	}

	private Member getMemByAdID(int memId) {
		for (Member mem : allMembers) {
			if (mem.getId().equals(memId)) {
				return mem;
			}
		}
		return null;
	}

	private Member getMemByAppID(String appId) {
		for (App app : allApps) {
			if (appId.equals(app.getPid())) {
				return getMemByAdID(app.getMem_id());
			}
		}
		return null;
	}

	// 过滤掉运行过程中由于余额不足暂停的广告
	private Vector<ADShowType> filtratePause(Vector<ADShowType> original) {
		Vector<ADShowType> okADShowTypes = new Vector<ADShowType>();
		for (ADShowType ast : original) {
			for (AD ad : allRunningADs) {
				if (ad.getId().equals(ast.getAd_id()) && !"暂停".equals(ad.getState())) {
					okADShowTypes.add(ast);
				}
			}
		}
		return okADShowTypes;
	}

	// 找出满足展现方式的ADShowTypes(条幅型，插屏型，悬浮型)
	private Vector<ADShowType> filtrateShowType(Vector<ADShowType> original, String typeCode) {
		Vector<ADShowType> okADShowTypes = new Vector<ADShowType>();
		String type = "";
		if ("1".equals(typeCode)) {
			type = "条幅型";
		} else if ("2".equals(typeCode)) {
			type = "插屏型";
		} else if ("3".equals(typeCode)) {
			type = "悬浮型";
		}
		for (ADShowType ast : original) {
			String showType = ast.getShow_type_name().split(":")[0];
			if (showType.equals(type)) {
				okADShowTypes.add(ast);
			}
		}
		return okADShowTypes;
	}

	// 找出满足地理条件的ADShowTypes
	private Vector<ADShowType> filtrateZone(Vector<ADShowType> original, String zoneCode, double lon, double lat) {
		Vector<ADShowType> okADShowTypes = new Vector<ADShowType>();
		for (ADShowType ast : original) {
			for (ADZone adZone : allADZones) {
				if (adZone.getAd_id().equals(ast.getAd_id())) {
					if (adZone.getZone_code().equals(zoneCode)) {
						okADShowTypes.add(ast);
					} else if (Integer.parseInt(adZone.getZone_code()) == -1) {
						String[] zoneInfo = adZone.getZone_name().split(":");
						double adLon = Double.parseDouble(zoneInfo[0]); // 设定的中心经度
						double adLat = Double.parseDouble(zoneInfo[1]); // 设定的中心纬度
						double adRadius = Double.parseDouble(zoneInfo[2]); // 设定的半径
						double pathLength = Math.sqrt(Math.pow((lon - adLon) * 85390, 2)
								+ Math.pow((lat - adLat) * 111000, 2));
						Log.debug("实际经纬度： " + lon + ", " + lat);
						Log.debug("广告经纬度： " + adLon + ", " + adLat);

						Log.debug("adRadius: " + adRadius);
						Log.debug("pathLength: " + pathLength);// TODO Wifi 测试 误差1400米左右
						if (pathLength < adRadius) {
							okADShowTypes.add(ast);
						}
					}
				}
			}
		}
		return okADShowTypes;
	}

	// 找出满足手机类型的ADShowTypes
	private Vector<ADShowType> filtratePhoneType(Vector<ADShowType> original, String phoneType) {
		Vector<ADShowType> okADShowTypes = new Vector<ADShowType>();
		for (ADShowType ast : original) {
			for (ADPhoneType apt : allADPhoneTypes) {
				if (apt.getPhone_name().equals(phoneType) && apt.getAd_id().equals(ast.getAd_id())) {
					okADShowTypes.add(ast);
				}
			}
		}
		return okADShowTypes;
	}

	// 找出满足运营商的ADShowTypes
	private Vector<ADShowType> filtrateArs(Vector<ADShowType> original, String arsName) {
		Vector<ADShowType> okADShowTypes = new Vector<ADShowType>();
		for (ADShowType ast : original) {
			for (ADArs ars : allADArs) {
				if (ars.getArs_name().equals(arsName) && ars.getAd_id().equals(ast.getAd_id())) {
					okADShowTypes.add(ast);
				}
			}
		}
		return okADShowTypes;
	}

	// 找出满足 用户群 和 性别 条件的ADShowTypes
	private Vector<ADShowType> filtrateCrowdAndGender(Vector<ADShowType> original, String appID) {
		Vector<ADShowType> okADShowTypes = new Vector<ADShowType>();
		App app = getAppByAppID(appID);
		for (ADShowType ast : original) {
			AD ad = getADByAdID(ast.getAd_id());
			// 满足性别条件
			if (ad.getPut_gender().equals("0") || app.getApp_gender().equals("0")
					|| app.getApp_gender().equals(ad.getPut_gender())) {
				// String appCrowd = app.getApp_crowd();
				// 满足用户群条件
				if (ad.getPut_crowd().equals("15")) {
					okADShowTypes.add(ast);
				} else {
					int adCrowdInt = Integer.parseInt(ad.getPut_crowd());
					int appCrowdInt = Integer.parseInt(app.getApp_crowd());
					for (int i = 0; i < 4; i++) {
						if ((adCrowdInt >> i) % 2 == 1 && (appCrowdInt >> i) % 2 == 1) {
							okADShowTypes.add(ast);
							break;
						}
					}
				}
			}
		}
		return okADShowTypes;
	}

	// 找出满足时间段条件的ADShowTypes(上午，下午等)
	private Vector<ADShowType> filtrateTime(Vector<ADShowType> original) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		int hour = Integer.parseInt(sdf.format(new Date()));
		if (hour >= 6 && hour < 12) {
			hour = 8;
		} else if (hour >= 12 && hour < 18) {
			hour = 4;
		} else if (hour >= 18 && hour < 24) {
			hour = 2;
		} else if (hour >= 0 && hour < 6) {
			hour = 1;
		}

		Vector<ADShowType> okADShowTypes = new Vector<ADShowType>();
		for (ADShowType ast : original) {
			AD ad = getADByAdID(ast.getAd_id());
			int adTimes = Integer.parseInt(ad.getPut_time());
			if (adTimes == 15) {
				okADShowTypes.add(ast);
			} else {
				for (int i = 0; i < 4; i++) {
					if ((adTimes >> i) % 2 == 1 && (hour >> i) % 2 == 1) {
						okADShowTypes.add(ast);
						break;
					}
				}
			}
		}
		return okADShowTypes;
	}

	// 找出满足网络条件的ADShowTypes(Wifi，普通2G，3G) netType 0:非Wifi；1：Wifi
	public Vector<ADShowType> filtrateNet(Vector<ADShowType> original, String netType) {
		if ("1".equals(netType)) {
			return original;
		}
		Vector<ADShowType> okADShowTypes = new Vector<ADShowType>();
		for (ADShowType ast : original) {
			String[] showTypeName = ast.getShow_type_name().split(":");
			if ("插屏型".equals(showTypeName[0])) {
				okADShowTypes.add(ast);
			} else {
				String clickEffect = ast.getClick_effect();
				if ("播放音乐".equals(clickEffect) || "全屏图片".equals(clickEffect) || "视频动画".equals(clickEffect)) {
					okADShowTypes.add(ast);
				}
			}
		}
		return okADShowTypes;
	}

	public String loadRequestInfo() throws Exception {
		if (currentRequestInfoPath == null) {
			String date = hourSDF.format(new Date());
			// urd： UangelRequestData.
			currentRequestInfoPath = REQUEST_INFO_PATH + date + ".urd";
		}
		File f = new File(currentRequestInfoPath);
		if (!f.exists()) {
			return "";
		}

		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder result = new StringBuilder();
		String s;
		while ((s = br.readLine()) != null) {
			result.append(s);
		}
		br.close();
		fr.close();

		return result.toString();
	}

	// 当出现未知异常时，保存当前内存中的数据到数据库
	public void saveDataWhenException() {
		String yesterday = daySDF.format(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
		sss.synDatabase(allRunningADs, allMembers, allMembersBak, yesterday, REQUEST_INFO_PATH, SHOW_INFO_PATH,
				CLICK_INFO_PATH, DEVELOPER_GET_RATE);
		String today = daySDF.format(System.currentTimeMillis());
		sss.synDatabase(allRunningADs, allMembers, allMembersBak, today, REQUEST_INFO_PATH, SHOW_INFO_PATH,
				CLICK_INFO_PATH, DEVELOPER_GET_RATE);
		// 前一天和今日的数据已存入数据库，把前一天和今日的数据备份之后从当日文件夹删除即可。
	}

	class SaveToDB extends TimerTask { // 凌晨2点将昨日数据存入数据库，并更新当前内存中的数据
		@Override
		public void run() {
			synchronized (DataManager.this) {
				long start = System.currentTimeMillis();
				// 昨天字符串
				// String yesterday = daySDF.format(System.currentTimeMillis() -
				// 24 * 60 * 60 * 1000);
				String yesterday = daySDF.format(System.currentTimeMillis()); // 测试用
				Log.info(yesterday + "'s Task Start at " + start);

				// 同步前一天的信息到数据库。
				sss.synDatabase(allRunningADs, allMembers, allMembersBak, yesterday, REQUEST_INFO_PATH, SHOW_INFO_PATH,
						CLICK_INFO_PATH, DEVELOPER_GET_RATE);
				Log.info("同步前一天的信息到数据库成功");

				// 昨天是最后一天的广告的状态置为结束
				sss.updateEndADs(yesterday);
				Log.info("昨天是最后一天的广告的状态置为结束成功");

				// 重新初始化
				Log.info("读取最新数据");
				loadingNewestData();

				Log.info(yesterday + "'s Task End. Use " + (System.currentTimeMillis() - start) + " ms.");
			}
		}
	}

}
