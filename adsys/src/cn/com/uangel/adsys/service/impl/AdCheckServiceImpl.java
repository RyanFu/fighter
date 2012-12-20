package cn.com.uangel.adsys.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.uangel.adsys.dao.ManagerDao;
import cn.com.uangel.adsys.dao.impl.AdCheckDaoImpl;
import cn.com.uangel.adsys.dao.impl.ManagerDaoImpl;
import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.AdCheckOtherInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.service.AdCheckService;
import cn.com.uangel.adsys.util.ConnectionProvider;

public class AdCheckServiceImpl implements AdCheckService{

	public AD getAdById(int id) {
		AdCheckDaoImpl acd=null;
		AD ad=null;
		
		try {
			ConnectionProvider.beginTransaction();
			acd=new AdCheckDaoImpl();
			ad=new AD();
			ad=acd.selectAdById(id);
			
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

	public List<AD> getAdByState(String state) {
		AdCheckDaoImpl acd=null;
		List<AD> listAd=null;
		try {
			ConnectionProvider.beginTransaction();
			acd=new AdCheckDaoImpl();
			listAd=new ArrayList<AD>();
			listAd=acd.selectAdToShow(state);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}                 
		return listAd;
	}

	public void modifyAd(AD ad) {
		AdCheckDaoImpl acd=null;
		try {
			ConnectionProvider.beginTransaction();
			acd=new AdCheckDaoImpl();
			AD upAd=acd.selectAdById(ad.getId());
			upAd.setState(ad.getState());
			acd.updateAd(upAd);
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
	public List<ADShowType> getADShowTypesByADID(int adID) {
		List<ADShowType> showTypes = null;
		AdCheckDaoImpl acd=null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			acd=new AdCheckDaoImpl();
			showTypes = acd.selectADShowTypesByADID(adID);

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
	public AdCheckOtherInfo getAdCheckOtherInfo(int id) {
		AdCheckDaoImpl acd=null;
		AdCheckOtherInfo aco=null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			aco=new AdCheckOtherInfo();
			acd=new AdCheckDaoImpl();
			
			AD returnAd=acd.selectAdById(id);
			
			int memId=returnAd.getMem_id();
			ManagerDao md=new ManagerDaoImpl();
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
