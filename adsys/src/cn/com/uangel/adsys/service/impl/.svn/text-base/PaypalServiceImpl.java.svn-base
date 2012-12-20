package cn.com.uangel.adsys.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.com.uangel.adsys.dao.impl.PaypalDaoImpl;
import cn.com.uangel.adsys.entity.CommisionInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeInfo;
import cn.com.uangel.adsys.service.PaypalService;
import cn.com.uangel.adsys.util.ConnectionProvider;

public class PaypalServiceImpl implements PaypalService{

	RechargeInfo rech=null;
	@Override
	public void addCommisionInfo(CommisionInfo comm) {
		try {
			ConnectionProvider.beginTransaction();
			
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
	public void addPaypalInfo(PaypalInfo paypal) {
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			pdi.insertPaypal(paypal);
			StringBuffer cashoutNum=new StringBuffer();
			cashoutNum.append("TX");
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
			cashoutNum.append(df.format(paypal.getApply_time()));
			String cashoutLast=paypal.getId()+"";
			for(int j=1;j<=6-cashoutLast.length();j++){
				cashoutNum.append("0");
			}
			cashoutNum.append(paypal.getId());
			paypal.setBod_number(cashoutNum.toString());
			pdi.updatePaypal(paypal);
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
	public void addRechargeInfo(RechargeInfo rech) {
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			pdi.insertRecharge(rech);
			StringBuffer orderNum=new StringBuffer();
			orderNum.append("CZ");
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
			orderNum.append(df.format(rech.getRecharge_time()));
			String orderLast=rech.getId()+"";
			for(int i=1;i<=6-orderLast.length();i++){
				orderNum.append("0");
			}
			orderNum.append(rech.getId());
			rech.setOrd_number(orderNum.toString());
			pdi.updateRecharge(rech);
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
	public CommisionInfo getCommisionById(int commId) {
		try {
			ConnectionProvider.beginTransaction();
			
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public PaypalInfo getPaypalById(int paypalId) {
		// TODO Auto-generated method stub
		PaypalInfo paypal=null;
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			paypal=pdi.selectPaypalById(paypalId);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return paypal;
	}

	@Override
	public RechargeInfo getRechargeById(int rechargeId) {
		// TODO Auto-generated method stub
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			rech=pdi.selectRechargeById(rechargeId);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		
		return rech;
	}

	@Override
	public void modifyCommisionInfo(CommisionInfo comm) {
		// TODO Auto-generated method stub
		try {
			ConnectionProvider.beginTransaction();
			
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
	public void modifyPaypalInfo(PaypalInfo paypal) {
		// TODO Auto-generated method stub
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			PaypalInfo paypalInfo=pdi.selectPaypalById(paypal.getId());
			paypalInfo.setApply_amount(paypal.getApply_amount());
			pdi.updatePaypal(paypalInfo);
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
	public void modifyRechargeInfo(RechargeInfo rech ) {
		// TODO Auto-generated method stub
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			RechargeInfo rechargeInfo=pdi.selectRechargeById(rech.getId());
			rechargeInfo.setRecharge_count(rech.getRecharge_count());
			pdi.updateRecharge(rechargeInfo);
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
	public void removeCommisionInfoById(int commId) {
		// TODO Auto-generated method stub
		try {
			ConnectionProvider.beginTransaction();
			
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
	public void removePaypalInfoById(int paypalId) {
		// TODO Auto-generated method stub
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			pdi.deletePaypalById(paypalId);
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
	public void removeRechargeInfoById(int rechargeId) {
		// TODO Auto-generated method stub
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			pdi.deleteRechargeById(rechargeId);
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
	public List<RechargeInfo> getRechargesByMemId(int memId) {
		List<RechargeInfo> listRecharge=null;
		PaypalDaoImpl pdi=null;
		try {
			ConnectionProvider.beginTransaction();
			pdi=new PaypalDaoImpl();
			listRecharge=pdi.selectRechargeByMemId(memId);
			
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return listRecharge;
	}

	@Override
	public List<PaypalInfo> getPaypalByMemId(int memId) {
		List<PaypalInfo> listPaypal=null;
		PaypalDaoImpl pdi=null;
		
		try {
			ConnectionProvider.beginTransaction();
			pdi=new PaypalDaoImpl();
			listPaypal=pdi.selectPaypalByMemId(memId);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return listPaypal;
	}

	@Override
	public List<CommisionInfo> getCommisionByMemId(int memId) {
		List<CommisionInfo> listComm=null;
		PaypalDaoImpl pdi=null;
		
		try {
			ConnectionProvider.beginTransaction();
			pdi=new PaypalDaoImpl();
			listComm=pdi.selectCommisionByMemId(memId);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return listComm;
	}

	@Override
	public List<CommisionInfo> getCommisionByMemIdAndDate(int memId, CommisionInfo comm) {
		List<CommisionInfo> listSearch=null;
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			listSearch=pdi.selectCommisionByMemIdAndDate(memId, comm);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		
		return listSearch;
	}

	@Override
	public List<RechargeAccount> getAllRechargeAccount() {
		List<RechargeAccount> listRechAcc=null;
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			listRechAcc=pdi.selectRechargeAccount();
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		
		return listRechAcc;
	}

	@Override
	public Member getById(int id) {
		Member member=null;
		try {
			ConnectionProvider.beginTransaction();
			PaypalDaoImpl pdi=new PaypalDaoImpl();
			member=pdi.selectById(id);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return member;
	}
	
}
