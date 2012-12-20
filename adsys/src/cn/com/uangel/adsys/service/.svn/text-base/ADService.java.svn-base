package cn.com.uangel.adsys.service;

import java.util.Date;
import java.util.List;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.AdCheckOtherInfo;
import cn.com.uangel.adsys.entity.AdStatInfo;
import cn.com.uangel.adsys.entity.StatisticsByCrowd;
import cn.com.uangel.adsys.entity.StatisticsByGeography;
import cn.com.uangel.adsys.entity.StatisticsByTimeInterval;

public interface ADService {
	public void addAdWithBacicInfo(AD ad);

	public void modifyAdPutWays(int adId, String[] ars, String[] phoneTypes, String[] provinces, String[] userCrowd,
			String gender, String[] showtime);

	public void modify(AD ad);

	public List<AD> getADsByCondition(AD condition);

	public AD getAD(int adId);

	public AD modifyAdBacicInfo(AD newAD);

	public List<AdStatInfo> getStatInfo(int[] appIds, Date startDate, Date endDate, String statWay);

	public List<ADShowType> getADShowTypesByADID(int adID);
	
	public AdCheckOtherInfo getAdCheckOtherInfo(int id);

	public void addRequestInfo(String appID, int adID, String imei);

	public String getOKAD(String appID, String ars, String imei);

	public void addClickInfo(int adID, String appID, String imei);

	public void modifyADsState(int[] ids, String newState);

	public boolean testAppIDCouldDelete(String appID);

	public boolean testADBalance(int memID);

	public int getCurShowCount();

	public List<String[]> getPutwayInfosById(int adID);

	public List<StatisticsByCrowd> getStatisticsByCrowd(int[] appIds, Date startDate, Date endDate, String[] userCrowd);

	public List<StatisticsByGeography> getStatisticsByGeography(int[] appIds, Date startDate, Date endDate,
			String[] ck_province);

	public List<StatisticsByTimeInterval> getStatisticsByTimeInterval(int[] appIds, Date startDate, Date endDate,
			String[] showtime);

}
