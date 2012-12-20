package cn.com.uangel.adsys.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.com.uangel.adsys.dao.ADDao;
import cn.com.uangel.adsys.dao.AppDao;
import cn.com.uangel.adsys.dao.impl.ADDaoImpl;
import cn.com.uangel.adsys.dao.impl.AppDaoImpl;
import cn.com.uangel.adsys.entity.App;
import cn.com.uangel.adsys.entity.AppGroundStatInfo;
import cn.com.uangel.adsys.entity.AppStatInfo;
import cn.com.uangel.adsys.service.AppService;
import cn.com.uangel.adsys.util.ConnectionProvider;

public class AppServiceImpl implements AppService {

	AppDao appDao = new AppDaoImpl();
	ADDao adDao = new ADDaoImpl();

	@Override
	public void addAppWithBacicInfo(App app) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			appDao.insert(app);

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
	public List<App> getAppsByMemId(int memId, String state) {
		List<App> appList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			appList = appDao.selectAppsByMemId(memId, state);

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

	@Override
	public List<AppStatInfo> getStatInfo(String[] appIds, Date startDate, Date endDate, String statWay) {
		List<AppStatInfo> statList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			statList = appDao.selectStatInfo(appIds, startDate, endDate, statWay);

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
	public void removeByAppID(String appID) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			if (adDao.testAppIDExist(appID)) {
				if (adDao.testAppCouldDelete(appID)) {
					appDao.deleteAppByAppID(appID);
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
	public void modifyAppByAppID(App app) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			int id=app.getId();
			String apkurl=app.getApk_url();
			String packageName=app.getPakage_name();
			String appState=app.getApp_state();
			App newApp=appDao.selectById(id);
			newApp.setPakage_name(packageName);
			newApp.setApk_url(apkurl);
			newApp.setApp_state(appState);
			appDao.updateById(newApp);
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
	public App getAppById(int id) {
		App app=null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始
			app=appDao.selectById(id);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return app;
	}

	@Override
	public List<AppGroundStatInfo> getGroundStatInfo(String[] appIds, Date startDate, Date endDate, String statWay) {
		List<AppGroundStatInfo> groundStatList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			groundStatList = appDao.selectGroundStatInfo(appIds, startDate, endDate, statWay);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return groundStatList;
	}

	@Override
	public boolean isPackageNameExist(String packageName,String pid) {
		boolean result=false;
		try {
			// Service 方法开始
			ConnectionProvider.beginTransaction();
			
			boolean isExist = appDao.selectPackagenameExist(packageName,pid);
			if (isExist) {
				result= true;
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
		return result;
	}

	@Override
	public App getAppByIdAndChangeState(int id) {
		App app=null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始
			app=appDao.selectById(id);
			app.setApp_state("未完成");
			appDao.updateById(app);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return app;
	}

	@Override
	public void modifyApp(App app) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始
			appDao.updateById(app);
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
	public List<App> getAppByState(String state) {
		// TODO Auto-generated method stub
		List<App> listApp=null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始
			listApp=appDao.selectByAppState(state);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return listApp;
	}
}
