package cn.com.uangel.adsys.dao;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADArs;
import cn.com.uangel.adsys.entity.ADPhoneType;
import cn.com.uangel.adsys.entity.ADShowInfo;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.ADZone;
import cn.com.uangel.adsys.entity.AdStatInfo;
import cn.com.uangel.adsys.entity.AdTypePrice;
import cn.com.uangel.adsys.entity.ClickInfo;
import cn.com.uangel.adsys.entity.RequestInfo;
import cn.com.uangel.adsys.entity.StatisticsByCrowd;
import cn.com.uangel.adsys.entity.StatisticsByGeography;
import cn.com.uangel.adsys.entity.StatisticsByTimeInterval;

public interface ADDao {
	public void insert(AD ad);

	public void deleteById(int id);

	public void update(AD ad);

	public AD selectById(int id);

	/**
	 * 获取和某用户相关的广告，支持分页
	 * 
	 * @param memID
	 *            会员ID
	 * @param state
	 *            广告状态
	 * @param start
	 *            开始位置
	 * @param rows
	 *            每页行数
	 * @return 符合条件的广告集合
	 */
	public List<AD> selectADsByMemberId(AD condition, int start, int rows);

	public Vector<AD> selectAllRunningADs();

	public Vector<AD> selectAllADs();

	public Vector<ADShowType> selectAllADShowTypes();

	public Vector<ADZone> selectAllADZones();

	public Vector<ADArs> selectAllADArs();

	public Vector<ADPhoneType> selectAllPhoneTypes();

	/**
	 * 添加广告所选运营商
	 * 
	 * @param adId
	 *            广告ID
	 * @param ars
	 *            运营商
	 */
	public void insertArs(int adId, String[] ars);

	public void insertPhoneTypes(int adId, String[] phoneTypes);

	public void insertProvinces(int adId, String[] provinces);

	public void deleteAllArsByAdId(int adId);

	public void deleteAllPhoneTypesByAdId(int adId);

	public void deleteAllProvincesByAdId(int adId);

	public String[] selectArsByAdId(int adId);

	public String[] selectPhoneTypesByAdId(int adId);

	public String[] selectZonesByAdId(int adId);

	public List<AdStatInfo> selectStatInfo(int[] appIds, Date startDate, Date endDate, String statWay);

	public void insertAdTypePrice(AdTypePrice adType);

	public void deleteAdTypePriceById(int id);

	/**
	 * 
	 * @param isNewest
	 *            0：代表旧的 1：代表新的
	 */
	public AdTypePrice deleteAdTypePriceByIsNewest(String isNewest);

	public void updateAdTypePrice(AdTypePrice adType);

	/** 更新广告投放目标信息 */
	public void updateAdPutWayInfo(int adId, String crowd, String gender, String time);

	public AdTypePrice selectAdTypePriceById(int id);

	/**
	 * 得到最新广告价格记录
	 * 
	 * @param isNewest
	 *            0：代表旧的 1：代表新的
	 * @return
	 */
	public AdTypePrice selectAdTypePriceByType(String type);

	/**
	 * 选择一个满足条件的广告
	 * 
	 * @param ars
	 *            运营商
	 * @return
	 */
	public AD choseOneOKAD(String ars);

	/**
	 * 防止恶意刷钱
	 */
	public boolean testImeiShowTooMuch(String imei, String appID);

	/**
	 * 防止恶意刷钱
	 */
	public boolean testImeiClickTooMuch(String imei, String appID);

	/**
	 * 判断AppID是否存在
	 */
	public boolean testAppIDExist(String appid);

	/** 在请求信息表中插入一条数据 */
	public void insertRequestInfo(String appID, int adID, String imei);

	/*** 插入大量请求记录 */
	public void inserAbundantRequestInfo(Vector<RequestInfo> data);

	/*** 插入大量展示记录 */
	public void inserAbundantShowInfo(Vector<ADShowInfo> data);

	/*** 插入大量点击记录 */
	public void inserAbundantClickInfo(Vector<ClickInfo> data);

	/** 在显示信息表中插入一条数据 */
	public void insertShowInfo(String appID, int adID, double cur_cpm, String imei);

	/** 在显示点击表中插入一条数据 */
	public void insertClickInfo(String appID, int adID, double cur_cpc, String imei);

	/**
	 * 更新广告状态
	 */
	public void updateADsState(int[] ids, String newState);

	/**
	 * 判断app是否在请求记录表中有数据，若有则不可删除
	 */
	public boolean testAppCouldDelete(String appID);

	/**
	 * 判断广告账户余额是否小于0.5
	 */
	public boolean testADBalance(int memID);

	/**
	 * 获取当前的广告展示次数
	 */
	public int selectCurShowCount();

	public void deleteAllADShowType(int adId);

	/** 查询广告对应的展现方式 */
	public List<ADShowType> selectADShowTypesByADID(int adId);

	public List<StatisticsByCrowd> selectStatisticsByCrowd(int[] appIds, Date startDate, Date endDate,
			String[] userCrowd);

	public List<StatisticsByGeography> selectStatisticsByGeography(int[] appIds, Date startDate, Date endDate,
			String[] ck_province);

	public List<StatisticsByTimeInterval> selectStatisticsByTimeInterval(int[] appIds, Date startDate, Date endDate,
			String[] showtime);

	/** 同步Socket服务器端的广告信息到数据库 */
	public void updateADsState(Vector<AD> ads);

	/** 昨天是最后一天的广告的状态置为结束 */
	public void updateEndADs(String date);
}
