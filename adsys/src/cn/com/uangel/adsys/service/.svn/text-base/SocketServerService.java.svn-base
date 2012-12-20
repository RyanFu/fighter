package cn.com.uangel.adsys.service;

import java.util.Vector;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADArs;
import cn.com.uangel.adsys.entity.ADPhoneType;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.ADZone;
import cn.com.uangel.adsys.entity.App;
import cn.com.uangel.adsys.entity.Member;

public interface SocketServerService {

	/** 获得所有运行中的广告 */
	public Vector<AD> getAllRunningADs();

	public Vector<Member> getAllMembers();

	public Vector<ADShowType> getAllADShowTypes();

	public Vector<ADZone> getAllADZones();

	public Vector<ADArs> getAllADArs();

	public Vector<ADPhoneType> getAllPhoneTypes();

	public Vector<App> getAllApps();

	/**
	 * 同步前一天的信息到数据库
	 * 
	 * @param ads
	 *            Socket服务器端当前的广告信息
	 * @param curMems
	 *            Socket服务器端当前的用户信息
	 * @param backupMems
	 *            前一天刚刚导入时的用户信息
	 * @param date
	 *            同步的是哪一天的数据
	 * @param requestFolderPath
	 * @param showFolderPath
	 * @param clickFolderPath
	 * @param DEVELOPER_GET_RATE
	 *            广告主消费一次，开发者获得的比例
	 */
	public void synDatabase(Vector<AD> ads, Vector<Member> curMems, Vector<Member> backupMems, String date,
			String requestFolderPath, String showFolderPath, String clickFolderPath, double DEVELOPER_GET_RATE);

	/**
	 * 昨天是最后一天的广告的状态置为结束
	 * 
	 * @param date
	 *            昨天的字符串，如"2012-12-21"
	 */
	public void updateEndADs(String date);
}
