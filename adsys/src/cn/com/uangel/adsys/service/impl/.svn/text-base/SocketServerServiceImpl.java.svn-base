package cn.com.uangel.adsys.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import cn.com.uangel.adsys.dao.ADDao;
import cn.com.uangel.adsys.dao.AppDao;
import cn.com.uangel.adsys.dao.MemberDao;
import cn.com.uangel.adsys.dao.PaypalDao;
import cn.com.uangel.adsys.dao.impl.ADDaoImpl;
import cn.com.uangel.adsys.dao.impl.AppDaoImpl;
import cn.com.uangel.adsys.dao.impl.MemberDaoImpl;
import cn.com.uangel.adsys.dao.impl.PaypalDaoImpl;
import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADArs;
import cn.com.uangel.adsys.entity.ADPhoneType;
import cn.com.uangel.adsys.entity.ADShowInfo;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.ADZone;
import cn.com.uangel.adsys.entity.App;
import cn.com.uangel.adsys.entity.ClickInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.RechargeInfo;
import cn.com.uangel.adsys.entity.RequestInfo;
import cn.com.uangel.adsys.service.SocketServerService;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.DoubleUtil;
import cn.com.uangel.adsys.util.Log;

public class SocketServerServiceImpl implements SocketServerService {

	ADDao adDao = new ADDaoImpl();
	MemberDao mDao = new MemberDaoImpl();
	AppDao appDao = new AppDaoImpl();
	PaypalDao payDao = new PaypalDaoImpl();

	@Override
	public Vector<Member> getAllMembers() {
		Vector<Member> allMembers = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			allMembers = mDao.selectAllMembers();

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return allMembers;
	}

	@Override
	public Vector<AD> getAllRunningADs() {
		Vector<AD> adList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adList = adDao.selectAllRunningADs();

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return adList;
	}

	@Override
	public Vector<ADShowType> getAllADShowTypes() {
		Vector<ADShowType> adShowTypelist = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始
			adShowTypelist = adDao.selectAllADShowTypes();
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return adShowTypelist;
	}

	@Override
	public Vector<ADZone> getAllADZones() {
		Vector<ADZone> adZoneList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adZoneList = adDao.selectAllADZones();

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return adZoneList;
	}

	@Override
	public Vector<ADArs> getAllADArs() {
		Vector<ADArs> adArsList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adArsList = adDao.selectAllADArs();

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return adArsList;
	}

	@Override
	public Vector<ADPhoneType> getAllPhoneTypes() {
		Vector<ADPhoneType> adPhoneTypeList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adPhoneTypeList = adDao.selectAllPhoneTypes();

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return adPhoneTypeList;
	}

	@Override
	public Vector<App> getAllApps() {
		Vector<App> appList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			appList = appDao.selectAllApps();

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return appList;
	}

	private App getAppByAppID(Vector<App> allApps, String appId) {
		for (App app : allApps) {
			if (app.getPid().equals(appId)) {
				return app;
			}
		}
		return null;
	}

	private Member getMemByAdID(Vector<Member> allMembers, int memId) {
		for (Member mem : allMembers) {
			if (mem.getId().equals(memId)) {
				return mem;
			}
		}
		return null;
	}

	private AD getADByAdID(Vector<AD> allADs, int adId) {
		for (AD ad : allADs) {
			if (ad.getId().equals(adId)) {
				return ad;
			}
		}
		return null;
	}

	private Member getMemberByID(Vector<Member> target, int id) {
		for (Member mem : target) {
			if (mem.getId().equals(id)) {
				return mem;
			}
		}
		return null;
	}

	// 存储知道文件夹下的所有文件到数据库
	private final void saveFolder(String path, String type, Vector<Member> mems, Vector<App> apps, Vector<AD> ads,
			double DEVELOPER_GET_RATE) {
		File folder = new File(path);
		File[] files = folder.listFiles();
		SimpleDateFormat secondSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		BufferedReader br = null;
		InputStreamReader isr = null;
		FileInputStream fis = null;
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String strFileName = files[i].getAbsolutePath();
				Log.debug(strFileName);
				try {
					fis = new FileInputStream(strFileName);
					isr = new InputStreamReader(fis, "UTF-8");
					br = new BufferedReader(isr);
					String str = null;
					if ("request".equals(type)) {
						Vector<RequestInfo> yesterdayRequestInfos = new Vector<RequestInfo>();
						while ((str = br.readLine()) != null) {
							String[] data = str.split("︴");
							RequestInfo ri = new RequestInfo();
							ri.setApp_id(data[0]);
							ri.setAd_id(Integer.parseInt(data[1]));
							ri.setRequest_time(secondSDF.parse(data[2]));
							ri.setImei(data[3]);
							ri.setZone(data[4]);
							ri.setShow_type_id(Integer.parseInt(data[5]));
							yesterdayRequestInfos.add(ri);
						}
						adDao.inserAbundantRequestInfo(yesterdayRequestInfos);
					} else if ("show".equals(type)) {
						Vector<ADShowInfo> yesterdayShowInfos = new Vector<ADShowInfo>();
						while ((str = br.readLine()) != null) {
							String[] data = str.split("︴");
							ADShowInfo si = new ADShowInfo();
							si.setApp_id(data[0]);
							si.setAd_id(Integer.parseInt(data[1]));
							si.setShow_time(secondSDF.parse(data[2]));
							si.setCur_cpm(Double.parseDouble(data[3]));
							si.setImei(data[4]);
							si.setZone(data[5]);
							si.setShow_type_id(Integer.parseInt(data[6]));
							si.setCpm_income(si.getCur_cpm() * DEVELOPER_GET_RATE);
							yesterdayShowInfos.add(si);

							// 计算广告住花了多少钱
							AD ad = getADByAdID(ads, si.getAd_id());
							Member adMem = getMemByAdID(mems, ad.getMem_id());
							adMem.setAd_balance(adMem.getAd_balance() + si.getCur_cpm());

							// 计算开发者赚了多少钱
							App app = getAppByAppID(apps, si.getApp_id());
							Member devMem = getMemByAdID(mems, app.getMem_id());
							devMem.setIncome_balance(devMem.getIncome_balance()
									+ (si.getCur_cpm() * DEVELOPER_GET_RATE));
						}
						adDao.inserAbundantShowInfo(yesterdayShowInfos);
					} else if ("click".equals(type)) {
						Vector<ClickInfo> yesterdayClickInfos = new Vector<ClickInfo>();
						while ((str = br.readLine()) != null) {
							String[] data = str.split("︴");
							ClickInfo ci = new ClickInfo();
							ci.setApp_id(data[0]);
							ci.setAd_id(Integer.parseInt(data[1]));
							ci.setClick_time(secondSDF.parse(data[2]));
							ci.setCur_cpc(Double.parseDouble(data[3]));
							ci.setImei(data[4]);
							ci.setZone(data[5]);
							ci.setShow_type_id(Integer.parseInt(data[6]));
							ci.setCpc_income(ci.getCur_cpc() * DEVELOPER_GET_RATE);
							yesterdayClickInfos.add(ci);

							// 计算广告住花了多少钱
							AD ad = getADByAdID(ads, ci.getAd_id());
							Member adMem = getMemByAdID(mems, ad.getMem_id());
							adMem.setAd_balance(adMem.getAd_balance() + ci.getCur_cpc());

							// 计算开发者赚了多少钱
							App app = getAppByAppID(apps, ci.getApp_id());
							Member devMem = getMemByAdID(mems, app.getMem_id());
							devMem.setIncome_balance(devMem.getIncome_balance()
									+ (ci.getCur_cpc() * DEVELOPER_GET_RATE));
						}
						adDao.inserAbundantClickInfo(yesterdayClickInfos);
					} else {
						Log.error(new RuntimeException("type is wrong!"));
					}
				} catch (Exception e) {
					Log.error(e);
				} finally {
					try {
						fis.close();
					} catch (IOException e1) {
					}
					try {
						isr.close();
					} catch (IOException e1) {
					}
					try {
						br.close();
					} catch (Exception e) {
					}
				}
			}
		}

	}

	@Override
	public void synDatabase(Vector<AD> ads, Vector<Member> curMems, Vector<Member> backupMems, String date,
			String requestFolderPath, String showFolderPath, String clickFolderPath, double DEVELOPER_GET_RATE) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			Vector<Member> statMems = mDao.selectAllMembers();// 用于统计的
			Vector<App> apps = appDao.selectAllApps();
			Vector<AD> allADs = adDao.selectAllADs();

			// 将广告花费和收入置零，用于记录本次同步的数据的花费和收入情况
			for (Member mem : statMems) {
				mem.setAd_balance(0.0);
				mem.setIncome_balance(0.0);
			}

			// 更新请求，展示，点击信息，同时计算同步的数据的花费和收入情况
			saveFolder(requestFolderPath + File.separator + date, "request", statMems, apps, allADs, DEVELOPER_GET_RATE);
			saveFolder(showFolderPath + File.separator + date, "show", statMems, apps, allADs, DEVELOPER_GET_RATE);
			saveFolder(clickFolderPath + File.separator + date, "click", statMems, apps, allADs, DEVELOPER_GET_RATE);

			Vector<RechargeInfo> yesterdayRechargeInfo = payDao.selectRechargeByTime(date);
			Vector<Integer> rechargeUsersIDs = null; // 昨天充值的用户ID
			if (yesterdayRechargeInfo != null) {
				rechargeUsersIDs = new Vector<Integer>();
				for (RechargeInfo r : yesterdayRechargeInfo) {
					rechargeUsersIDs.add(r.getMem_id());
				}
			}

			Vector<AD> needChangeADs = new Vector<AD>();//需要改变状态的广告(状态为暂停且昨日没充值)
			for (AD ad : ads) {
				if ("暂停".equals(ad.getState()) && !rechargeUsersIDs.contains(ad.getMem_id())) {
					needChangeADs.add(ad);
				}
			}
			adDao.updateADsState(needChangeADs);
			Log.debug("更新广告状态成功");

			Vector<Member> dbMembers = mDao.selectAllMembers();
			Vector<Member> newestMems = new Vector<Member>(); // 要同步到数据库的Member信息

			for (int i = 0; i < curMems.size(); i++) {
				Member curMem = curMems.get(i);
				// Member bakMem = backupMems.get(i);
				Member dbMem = getMemberByID(dbMembers, curMem.getId());
				if (dbMem != null) {
					Member statMem = getMemberByID(statMems, curMem.getId());
					// 减去前一天花费的金额（备份值减去当前值）
					double adBalance = DoubleUtil.round(dbMem.getAd_balance() - statMem.getAd_balance(), 4);
					curMem.setAd_balance(adBalance);
					// 加上前一天收入的金额（当前值减去份值）
					double income = DoubleUtil.round(dbMem.getIncome_balance() + statMem.getIncome_balance(), 4);
					curMem.setIncome_balance(income);

					Log.debug("Member ID: " + curMem.getId() + " ,name: " + curMem.getName() + " 日期: " + date
							+ " 的花费为：" + DoubleUtil.round(statMem.getAd_balance(), 4) + ",收入为："
							+ DoubleUtil.round(statMem.getIncome_balance(), 4));
					newestMems.add(curMem);
				}
			}

			mDao.updateMoney(newestMems);
			Log.debug("更新用户广告支出和应用收入成功");

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateEndADs(String date) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adDao.updateEndADs(date);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
	}
}
