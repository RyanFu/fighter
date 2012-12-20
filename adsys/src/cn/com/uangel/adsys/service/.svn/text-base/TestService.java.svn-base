package cn.com.uangel.adsys.service;

import java.sql.SQLException;
import java.util.List;

import cn.com.uangel.adsys.dao.ADDao;
import cn.com.uangel.adsys.dao.TestDao;
import cn.com.uangel.adsys.dao.impl.ADDaoImpl;
import cn.com.uangel.adsys.dao.impl.MemberDaoImpl;
import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADArs;
import cn.com.uangel.adsys.entity.AdTypePrice;
import cn.com.uangel.adsys.entity.MSBook;
import cn.com.uangel.adsys.entity.MSHanatourPushInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.util.ConnectionProvider;

public class TestService {
	public void addAdAndArs(AD ad, ADArs adArs) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			// TestDao td = new TestDao();
			// td.insertAd(ad);
			// td.insertAdArs(adArs);

			Member member = new Member();
			member.setEmail("0");
			member.setPassword("0");
			// member.setRegist_time(2010-1-1);
			member.setName("0");
			member.setTelephone("0");
			member.setCellphone("0");
			member.setAccount_type("0");
			member.setQq("0");
			member.setCom_name("0");
			member.setCom_homepage("0");
			member.setCom_address("0");
			member.setZipcode("0");
			member.setAccount_attr("0");
			member.setInvoice_able("0");
			member.setGet_money_mode("0");
			member.setOpen_bank("0");
			member.setAccount_number("0");
			member.setOpen_name("0");
			member.setAddress_bank("0");
			member.setZipcode_bank("0");
			member.setAd_balance(0.0);
			member.setIncome_balance(0.0);
			member.setState("0");
			member.setSer_num("0");
			member.setId(4);
			MemberDaoImpl memDaoI = new MemberDaoImpl();
			memDaoI.insert(member);
			// memDaoI.deleteById(1);
			// memDaoI.update(member);

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

	public void addAd(AD ad) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			ADDao adDao = new ADDaoImpl();
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

	public void addPrice(AdTypePrice adType) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			ADDao adDao = new ADDaoImpl();
			adDao.insertAdTypePrice(adType);

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

	public List<MSBook> getAllMSBooks(int folderID, int levelID, String tableName) {
		List<MSBook> bookList = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			TestDao td = new TestDao();
			bookList = td.selectAllMSBook(folderID, levelID, tableName);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return bookList;
	}
	
	public MSBook getMSBooksBySerialCode(int folderID, int levelID, String tableName, String serialCode) {
		MSBook book = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			TestDao td = new TestDao();
			book = td.selectMSBookBySerialCode(folderID, levelID, tableName, serialCode);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return book;
	}
	
	public void addTokenInfo(String token, String deviceType) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			TestDao td = new TestDao();
			td.insertTokenInfo(token, deviceType);

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
	
	public void addChurchTokenInfo(String token, String deviceType) {
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			TestDao td = new TestDao();
			td.insertChurchTokenInfo(token, deviceType);

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
	
	public List<MSHanatourPushInfo> getAllTokenInfo() {
		List<MSHanatourPushInfo> tokenInfos = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			TestDao td = new TestDao();
			tokenInfos = td.selectAllTokenInfo();

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return tokenInfos;
	}
	
	public List<MSHanatourPushInfo> getAllTokenInfoByTableName(String tableName) {
		List<MSHanatourPushInfo> tokenInfos = null;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			TestDao td = new TestDao();
			tokenInfos = td.selectAllTokenInfoByTableName(tableName);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return tokenInfos;
	}
	
	public boolean hanatourTokenIsExist(String token) {
		boolean flag = false;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			TestDao td = new TestDao();
			flag = td.hanatourTokenIsExist(token);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return flag;
	}
	
	public boolean churchTokenIsExist(String token) {
		boolean flag = false;
		try {
			ConnectionProvider.beginTransaction();
			// Service 方法开始

			TestDao td = new TestDao();
			flag = td.churchTokenIsExist(token);

			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return flag;
	}
}
