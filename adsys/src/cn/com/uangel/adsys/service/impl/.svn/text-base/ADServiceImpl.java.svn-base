package cn.com.uangel.adsys.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.uangel.adsys.dao.ADDao;
import cn.com.uangel.adsys.dao.AppDao;
import cn.com.uangel.adsys.dao.MemberDao;
import cn.com.uangel.adsys.dao.impl.ADDaoImpl;
import cn.com.uangel.adsys.dao.impl.AppDaoImpl;
import cn.com.uangel.adsys.dao.impl.MemberDaoImpl;
import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.AdCheckOtherInfo;
import cn.com.uangel.adsys.entity.AdStatInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.StatisticsByCrowd;
import cn.com.uangel.adsys.entity.StatisticsByGeography;
import cn.com.uangel.adsys.entity.StatisticsByTimeInterval;
import cn.com.uangel.adsys.service.ADService;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.DoubleUtil;

public class ADServiceImpl implements ADService {

	ADDao adDao = new ADDaoImpl();
	MemberDao md = new MemberDaoImpl();
	AppDao appDao = new AppDaoImpl();

	@Override
	public void addAdWithBacicInfo(AD ad) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adDao.insert(ad);

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
	public void modifyAdPutWays(int adId, String[] ars, String[] phoneTypes, String[] provinces, String[] userCrowd,
			String gender, String[] showtime) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adDao.deleteAllArsByAdId(adId);
			adDao.deleteAllPhoneTypesByAdId(adId);
			adDao.deleteAllProvincesByAdId(adId);

			adDao.insertArs(adId, ars);
			adDao.insertPhoneTypes(adId, phoneTypes);
			adDao.insertProvinces(adId, provinces);

			int crowd = 0;
			// 将选择的人群转换成二进制，如1100等
			for (int i = 0; i < userCrowd.length; i++) {
				if ("1".equals(userCrowd[i])) {
					crowd ^= 8;
				} else if ("2".equals(userCrowd[i])) {
					crowd ^= 4;
				} else if ("3".equals(userCrowd[i])) {
					crowd ^= 2;
				} else if ("4".equals(userCrowd[i])) {
					crowd ^= 1;
				}
			}
			/*
			 * System.out.println((crowd >> 3) % 2);
			 */
			int time = 0;
			for (int i = 0; i < showtime.length; i++) {
				if ("1".equals(showtime[i])) {
					time ^= 8;
				} else if ("2".equals(showtime[i])) {
					time ^= 4;
				} else if ("3".equals(showtime[i])) {
					time ^= 2;
				} else if ("4".equals(showtime[i])) {
					time ^= 1;
				}
			}
			
			adDao.updateAdPutWayInfo(adId, String.valueOf(crowd), gender, String.valueOf(time));

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
	public void modify(AD ad) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adDao.deleteAllADShowType(ad.getId());
			adDao.update(ad);

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
	public List<AD> getADsByCondition(AD condition) {
		List<AD> adList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adList = adDao.selectADsByMemberId(condition, -1, -1);

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
	public AD getAD(int adId) {
		AD ad = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			ad = adDao.selectById(adId);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return ad;
	}

	@Override
	public AD modifyAdBacicInfo(AD newAD) {
		AD oldAD = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			oldAD = adDao.selectById(newAD.getId());
			oldAD.setName(newAD.getName());
			oldAD.setType(newAD.getType());
			oldAD.setStart_date(newAD.getStart_date());
			oldAD.setEnd_date(newAD.getEnd_date());
			oldAD.setMax_pay_perday(newAD.getMax_pay_perday());
			oldAD.setApp_platform(newAD.getApp_platform());
			oldAD.setState(newAD.getState());

			adDao.update(oldAD);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return oldAD;
	}

	@Override
	public List<AdStatInfo> getStatInfo(int[] appIds, Date startDate, Date endDate, String statWay) {
		List<AdStatInfo> statList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			statList = adDao.selectStatInfo(appIds, startDate, endDate, statWay);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return statList;
	}

	@Override
	public void addRequestInfo(String appID, int adID, String imei) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adDao.insertRequestInfo(appID, adID, imei);

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
	public String getOKAD(String appID, String ars, String imei) {
		AD okAD = null;
		String objStr = "error";
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			// 判断appid是否存在
			if (adDao.testAppIDExist(appID)) {
				// 往请求信息表里插入一条数据
				okAD = adDao.choseOneOKAD(ars);
				if (okAD != null) { // 有满足条件的广告
					adDao.insertRequestInfo(appID, okAD.getId(), imei);

					// 根据appid，imei判断是否是恶意刷钱
					if (!adDao.testImeiShowTooMuch(imei, appID)) {
						String showType = okAD.getShow_type();
						if ("手机网络".equals(showType) || "Android程序".equals(showType) || "OPhone程序".equals(showType)) {
							okAD.setShow_type("1");
						} else if ("点击通话".equals(showType)) {
							okAD.setShow_type("2");
						} else if ("发送短信".equals(showType)) {
							okAD.setShow_type("3");
						} else {
							okAD.setShow_type("0");// 异常
						}

						String imgWordType = okAD.getImg_word_type();
						if ("仅广告文字".equals(imgWordType)) {
							okAD.setImg_word_type("1");
						} else if ("仅显示图片".equals(imgWordType)) {
							okAD.setImg_word_type("2");
						} else if ("显示文字＋图片".equals(imgWordType)) {
							okAD.setImg_word_type("3");
						} else {
							okAD.setImg_word_type("0");// 异常
						}

						objStr = okAD.getId() + "‖" + okAD.getShow_type() + "‖" + okAD.getImg_word_type() + "‖"
								+ okAD.getTitle() + "‖" + okAD.getImg_path() + "‖" + okAD.getShow_src();

						// 在showInfo表里插入一条记录
						double cur_cpm = adDao.selectAdTypePriceByType("CPM").getCur_money();
						adDao.insertShowInfo(appID, okAD.getId(), cur_cpm, imei);
						// 若是按印象付费，则扣除广告主CPM/1000的费用，并给开发者加上CPM/1000的费用
						System.out.println("payMode: " + okAD.getPay_mode());
						if ("按印象付费".equals(okAD.getPay_mode())) {
							Member member = md.selectById(okAD.getMem_id());
							System.out.println("aduser before: " + member.getAd_balance());
							member.setAd_balance(DoubleUtil.round(DoubleUtil.subtract(member.getAd_balance(),
									DoubleUtil.round(cur_cpm / 1000, 4)), 4));
							md.update(member);
							System.out.println("aduser after: " + member.getAd_balance());

							// 扣除费用后，若广告主的余额小于0.5，将其所有“运行中”的广告暂停
							if (member.getAd_balance() < 0.5) {
								AD condition = new AD();
								condition.setMem_id(member.getId());
								condition.setState("运行中");
								List<AD> adList = adDao.selectADsByMemberId(condition, -1, -1);
								int[] ids = new int[adList.size()];
								for (int i = 0; i < ids.length; i++) {
									ids[i] = adList.get(i).getId();
								}
								adDao.updateADsState(ids, "暂停");
							}

							// 给开发者加上CPM费用
							int programmerID = appDao.selectMemberIDByAppID(appID);
							if (programmerID != -1) {
								Member programmer = md.selectById(programmerID);
								System.out.println("programmer before: " + programmer.getIncome_balance());
								programmer.setIncome_balance(DoubleUtil.round(DoubleUtil.add(programmer
										.getIncome_balance(), DoubleUtil.round(cur_cpm / 1000, 4)), 4));
								md.update(programmer);
								System.out.println("programmer after: " + programmer.getIncome_balance());
							}
						}

					} else {
						objStr = "error: 恶意刷钱";
					}
				} else {
					objStr = "error: 没有合适广告";
				}
			} else {
				objStr = "error: AppID不存在";
			}

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
			return objStr;
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addClickInfo(int adID, String appID, String imei) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			// 判断appID是否存在
			if (adDao.testAppIDExist(appID)) {
				// 若是按单次点击付费，则扣除广告主CPC的费用
				AD ad = adDao.selectById(adID);
				if ("按单次点击付费".equals(ad.getPay_mode())) {
					if (!adDao.testImeiClickTooMuch(imei, appID)) {
						Member member = md.selectById(ad.getMem_id());
						if (member.getAd_balance() > 0.5) {
							// 在clickInfo表里插入一条记录
							double cur_cpc = adDao.selectAdTypePriceByType("CPC").getCur_money();
							adDao.insertClickInfo(appID, adID, cur_cpc, imei);

							System.out.println("before: " + member.getAd_balance());
							member.setAd_balance(DoubleUtil.round(DoubleUtil.subtract(member.getAd_balance(),
									DoubleUtil.round(cur_cpc, 4)), 4));
							md.update(member);
							System.out.println("after: " + member.getAd_balance());

							// 给开发者加上CPM费用
							int programmerID = appDao.selectMemberIDByAppID(appID);
							if (programmerID != -1) {
								Member programmer = md.selectById(programmerID);
								System.out.println("programmer before: " + programmer.getIncome_balance());
								programmer.setIncome_balance(DoubleUtil.round(DoubleUtil.add(programmer
										.getIncome_balance(), DoubleUtil.round(cur_cpc, 4)), 4));
								md.update(programmer);
								System.out.println("programmer after: " + programmer.getIncome_balance());
							}
						} else {
							// 扣除费用后，若广告主的余额小于0.5，将其所有“运行中”的广告暂停
							AD condition = new AD();
							condition.setMem_id(member.getId());
							condition.setState("运行中");
							List<AD> adList = adDao.selectADsByMemberId(condition, -1, -1);
							int[] ids = new int[adList.size()];
							for (int i = 0; i < ids.length; i++) {
								ids[i] = adList.get(i).getId();
							}
							adDao.updateADsState(ids, "暂停");
						}
					}
				}
			}

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
	public void modifyADsState(int[] ids, String newState) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			adDao.updateADsState(ids, newState);

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
	public boolean testAppIDCouldDelete(String appID) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			boolean flag = false;
			if (adDao.testAppIDExist(appID)) {
				if (adDao.testAppCouldDelete(appID)) {
					flag = true;
				}
			}

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
			return flag;
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean testADBalance(int memID) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			boolean flag = false;
			if (adDao.testADBalance(memID)) {
				flag = true;
			}

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
			return flag;
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getCurShowCount() {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			int showCount = 0;
			showCount = adDao.selectCurShowCount();

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
			return showCount;
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<String[]> getPutwayInfosById(int adID) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			List<String[]> infos = new ArrayList<String[]>();
			String[] ars = adDao.selectArsByAdId(adID);
			String[] phoneTypes = adDao.selectPhoneTypesByAdId(adID);
			String[] zones = adDao.selectZonesByAdId(adID);
			infos.add(ars);
			infos.add(phoneTypes);
			infos.add(zones);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
			return infos;
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<ADShowType> getADShowTypesByADID(int adID) {
		List<ADShowType> showTypes = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			showTypes = adDao.selectADShowTypesByADID(adID);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return showTypes;
	}

	@Override
	public List<StatisticsByCrowd> getStatisticsByCrowd(int[] appIds, Date startDate, Date endDate, String[] userCrowd) {
		List<StatisticsByCrowd> listStatisticsByCrowd=null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始


			listStatisticsByCrowd=adDao.selectStatisticsByCrowd(appIds, startDate, endDate, userCrowd);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return listStatisticsByCrowd;
	}

	@Override
	public List<StatisticsByGeography> getStatisticsByGeography(int[] appIds, Date startDate, Date endDate,
			String[] ck_province) {
		List<StatisticsByGeography> listStatisticsByGeography =null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			listStatisticsByGeography=adDao.selectStatisticsByGeography(appIds, startDate, endDate, ck_province);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return listStatisticsByGeography;
	}

	@Override
	public List<StatisticsByTimeInterval> getStatisticsByTimeInterval(int[] appIds, Date startDate, Date endDate,
			String[] showtime) {
		List<StatisticsByTimeInterval> listStatisticsByTimeInterval=null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			listStatisticsByTimeInterval=adDao.selectStatisticsByTimeInterval(appIds, startDate, endDate, showtime);
			

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return listStatisticsByTimeInterval;
	}

	@Override
	public AdCheckOtherInfo getAdCheckOtherInfo(int id) {
		ADDao acd=null;
		AdCheckOtherInfo aco=null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			aco=new AdCheckOtherInfo();
			acd=new ADDaoImpl();
			
			AD returnAd=acd.selectById(id);
			
			int memId=returnAd.getMem_id();
			MemberDao md=new MemberDaoImpl();
			Member mem=md.selectById(memId);

			
			aco.setAdId(returnAd.getId());
			aco.setAdName(returnAd.getName());
			aco.setAdType(returnAd.getType());
			aco.setAdBalance(mem.getAd_balance());
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return aco;
	}
}
