package cn.com.uangel.adsys.dao;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import cn.com.uangel.adsys.entity.App;
import cn.com.uangel.adsys.entity.AppGroundStatInfo;
import cn.com.uangel.adsys.entity.AppStatInfo;

public interface AppDao {
	public void insert(App app);

	public void updateById(App app);

	public App selectById(int id);

	/** 通过memID和状态查询程序，state为null代表查询全部状态 */
	public List<App> selectAppsByMemId(int memId, String state);

	public List<AppStatInfo> selectStatInfo(String[] appIds, Date startDate, Date endDate, String statWay);

	public List<AppGroundStatInfo> selectGroundStatInfo(String[] appIds, Date startDate, Date endDate, String statWay);

	public void deleteAppByAppID(String appID);

	public int selectMemberIDByAppID(String appID);

	public boolean selectPackagenameExist(String packageName,String memId);

	public Vector<App> selectAllApps();
	
	public List<App> selectByAppState(String app_state);
}
