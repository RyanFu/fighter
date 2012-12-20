package cn.com.uangel.adsys.service;

import java.util.Date;
import java.util.List;

import cn.com.uangel.adsys.entity.App;
import cn.com.uangel.adsys.entity.AppGroundStatInfo;
import cn.com.uangel.adsys.entity.AppStatInfo;

public interface AppService {
	public void addAppWithBacicInfo(App app);

	public List<App> getAppsByMemId(int memId, String state);
	
	public List<AppStatInfo> getStatInfo(String[] appIds, Date startDate, Date endDate, String statWay);
	
	public List<AppGroundStatInfo> getGroundStatInfo(String[] appIds, Date startDate, Date endDate, String statWay);
	
	public void removeByAppID(String appID);
	
	public void modifyAppByAppID(App app);
	
	public void modifyApp(App app);
	
	public App getAppById(int id);
	
	public App getAppByIdAndChangeState(int id);
	
	public boolean isPackageNameExist(String packageName,String pid);
	/**
	 * 
	 * @param state
	 * 				 待审批的应用
	 * @return 		 待审批的应用list
	 */
	public List<App> getAppByState(String state);
}
