package cn.com.uangel.adsys.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.com.uangel.adsys.dao.impl.AdCheckDaoImpl;
import cn.com.uangel.adsys.dao.impl.ManagerDaoImpl;
import cn.com.uangel.adsys.entity.AdTypePrice;
import cn.com.uangel.adsys.entity.CommisionInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalEdit;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeEdit;
import cn.com.uangel.adsys.entity.RechargeInfo;
import cn.com.uangel.adsys.service.ManageService;
import cn.com.uangel.adsys.util.ConnectionProvider;

public class ManageServiceImpl implements ManageService{

	

	public List<RechargeInfo> getRechargesByCondition(String state, String startTime, String endTime, String ord_number) {
		List<RechargeInfo> listRecharge=null;
		ManagerDaoImpl mdi=null;
		
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			listRecharge=mdi.selectRechargeByState(state, startTime, endTime, ord_number);
			
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
	
	public List<RechargeEdit> getEditRechargeById(int id) {
		List<RechargeEdit> listRecharge=null;
		ManagerDaoImpl mdi=null;
		
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			listRecharge=mdi.editRechargeById(id);
			
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

	public void modifyRechargeEdit(RechargeInfo rech) {
		ManagerDaoImpl mdi=null;
		
		try {
			ConnectionProvider.beginTransaction();
			if(rech!=null){
				int id=rech.getId();
				Double real_count=rech.getReal_count();
				String state=rech.getState();
				
				
				//更新充值记录
				mdi=new ManagerDaoImpl();
				RechargeInfo rechargeInfo=new RechargeInfo();
				rechargeInfo=mdi.selectRechargeById(id);//得到某条充值记录所有信息
				rechargeInfo.setReal_count(real_count);
				rechargeInfo.setState(state);
				mdi.updateRecharge(rechargeInfo);
				//更新会员表的款数
				Member member=mdi.selectById(rechargeInfo.getMem_id());
				//如果广告主余额小于0.5 则将其所有暂停的广告设置为运行中，否则广告状态不变
				if(member.getAd_balance()<0.5){
					AdCheckDaoImpl adCheckDaoImpl=new AdCheckDaoImpl();
					adCheckDaoImpl.updateAdsByMem_id(rechargeInfo.getMem_id());
				}
				//将用户充的钱加到余额里
				Double money=member.getAd_balance()+real_count;
				member.setAd_balance(money);
				mdi.update(member);
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
	

	public List<PaypalInfo> getPaypalByCondition(String state, String startTime, String endTime, String bod_number) {
		List<PaypalInfo> listPay=null;
		ManagerDaoImpl mdi=null;
		
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			listPay=mdi.selectPaypalInfoByCondition(state, startTime, endTime, bod_number);
			
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return listPay;
	}

	public List<PaypalEdit> getEditPaypalById(int id) {
		List<PaypalEdit> listPaypal=null;
		ManagerDaoImpl mdi=null;
		
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			listPaypal=mdi.editPaypalById(id);
			
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

	public void modifyPaypalInfo(PaypalInfo pay) {
		ManagerDaoImpl mdi=null;
		PaypalInfo paypal=null;
		
		try {
			ConnectionProvider.beginTransaction();
			/**
			 * 获取这条记录以前的充值记录的最后一条的成交时间
			 */
			mdi=new ManagerDaoImpl();
			PaypalInfo payInfoMemId=mdi.selectPaypalInfoById(pay.getId());//通过payId得到体现记录对象，用来获取mem_id
			Date startDate=mdi.selectPayDate(payInfoMemId.getMem_id());//得到提现记录最后一条内容的pay_date
			
			/**
			 * 提现记录更新，审批这条记录，将提现记录表更新
			 */
			paypal=new PaypalInfo();
			paypal=mdi.selectPaypalInfoById(pay.getId());
			paypal.setAllow_amount(pay.getAllow_amount());
			paypal.setPay_date(pay.getPay_date());
			paypal.setState(pay.getState());//3表示已验证成功
			mdi.updatePaypalInfo(paypal);
			
			/**
			 * 雇佣金记录自动生成
			 */
			
			
			//雇佣金基本信息获取
			CommisionInfo comm=new CommisionInfo();
			int memId=paypal.getMem_id();
			Member member=mdi.selectById(memId);
			Double incoMemany=member.getIncome_balance()-paypal.getAllow_amount();//会员收入金额-提现申请金额
			member.setIncome_balance(incoMemany);
			mdi.update(member);
			String bank_name=member.getOpen_bank();//银行名称
			String bank_num=member.getAccount_number();//银行账号
			Double income=member.getIncome_balance();//收入金额
			Date registDate=member.getRegist_time();//注册时间（开始时间）
			
			PaypalInfo paycomm=new PaypalInfo();
			paycomm = mdi.selectPaypalInfoById(pay.getId());//得到提现记录信息
			paycomm.getPay_date();//结束时间
			paycomm.getApply_time();//申请时间
			Double full_count=paycomm.getAllow_amount();//批准额度
			
			//开始添加一条雇佣金记录，设置个字段信息
			comm.setMem_id(memId);
			comm.setCommision_num("");
			comm.setBank_name(bank_name);
			comm.setBank_num(bank_num);
			//如果没有这条记录以前的充值记录的最后一条的成交时间，那么雇佣金的开始时间就是此用户注册时间
			if("".equals(startDate)||startDate==null){
				comm.setStart_time(registDate);
			}else {
				comm.setStart_time(startDate);
			}
			comm.setEnd_time(paycomm.getPay_date());
			comm.setIncome(income);//收入金额
//			comm.setDeduct_count(deduct_count);//扣除金额
			comm.setFull_count(full_count);//应付金额
			//comm.setPoundage(poundage);//手续费
			//comm.setReal_count(real_count);//实付金额
			comm.setState(3+"");//状态
			mdi.insertComm(comm);
			
			/**
			 * 生成流水账号
			 */
			StringBuffer commNum=new StringBuffer();
			commNum.append("GY");
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
			commNum.append(df.format(paypal.getApply_time()));
			String commString=comm.getId()+"";
			for(int j=1;j<=6-commString.length();j++){
				commNum.append("0");
			}
			commNum.append(comm.getId());
			
			
			
			comm.setCommision_num(commNum.toString());
			mdi.updateCommision(comm);
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

	public List<RechargeAccount> getRechargeAccount() {
		List<RechargeAccount> listRechAcc=null;
		ManagerDaoImpl mdi=null;
		
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			listRechAcc=mdi.selectManageBank();
			
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

	public void addRechAcc(RechargeAccount rechAcc) {
		ManagerDaoImpl mdi=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			mdi.insertManageBank(rechAcc);
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

	public RechargeAccount getRechAcc(int id) {
		RechargeAccount rechAcc=null;
		ManagerDaoImpl mdi=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			
			rechAcc=mdi.selectManageBankById(id);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return rechAcc;
	}

	public void modifyRechAcc(RechargeAccount rechAcc) {
		ManagerDaoImpl mdi=null;
		
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			mdi.updateManageBank(rechAcc);
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

	public void removeRechAccBy(int id) {
		ManagerDaoImpl mdi=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			mdi.deleteManageBankById(id);
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

	public void addAdTypePrice(AdTypePrice adType) {
		ManagerDaoImpl mdi=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			mdi.insertAdTypePrice(adType);
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

	public void deleteAdTypePricById(int id) {
		ManagerDaoImpl mdi=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			mdi.deleteAdTypePriceById(id);
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

	public AdTypePrice getAdTypePriceByIsNew(String ad_type) {
		ManagerDaoImpl mdi=null;
		AdTypePrice adType=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			adType=mdi.selectAdTypePriceByIsNewest(ad_type);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return adType;
	}

	public void modifyAdTypePriceIsNew() {
		ManagerDaoImpl mdi=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			mdi.updateAdTypePriceAll();
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

	public AdTypePrice getAdTypePriceById(int id) {
		ManagerDaoImpl mdi=null;
		AdTypePrice adType=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			adType=mdi.selectAdTypePriceById(id);
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return adType;
	}

	public void modifyAdTypePrice(AdTypePrice adType,String ad_type) {
		ManagerDaoImpl mdi=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			boolean isExist=mdi.selectAdTypePriceByType(ad_type);
			if(isExist){
				mdi.updateAdTypePrice(adType);
				System.out.println("存在cpc/cpm记录");
			}else {
				mdi.insertAdTypePrice(adType);
				System.out.println("不存在cpc/cpm记录");
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

	public Member getAdBalanceByRechId(int id) {
		ManagerDaoImpl mdi=null;
		Member mem=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();
			RechargeInfo rech=mdi.selectRechargeById(id);
			mem=mdi.selectById(rech.getMem_id());
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return mem;
	}

	public Member getIncomeBalanceByPaypalId(int id) {
		ManagerDaoImpl mdi=null;
		Member mem=null;
		try {
			ConnectionProvider.beginTransaction();
			mdi=new ManagerDaoImpl();


			PaypalInfo pay=mdi.selectPaypalInfoById(id);
			mem=mdi.selectById(pay.getMem_id());
			// Service 方法结束
			ConnectionProvider.endTransaction(true);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}
		return mem;
	}
	public void removePaypalInfoById(int paypalId) {
		// TODO Auto-generated method stub
		try {
			ConnectionProvider.beginTransaction();
			ManagerDaoImpl mdi=new ManagerDaoImpl();
			mdi.deletePaypalById(paypalId);
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
	
	public void removeRechargeInfoById(int rechargeId) {
		// TODO Auto-generated method stub
		try {
			ConnectionProvider.beginTransaction();
			ManagerDaoImpl mdi=new ManagerDaoImpl();
			mdi.deleteRechargeById(rechargeId);
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
	
	public Member getByEmailAndPassword(String email, String password) {
		Member member= null; 
		try {
			// Service 方法开始
			ConnectionProvider.beginTransaction();
			ManagerDaoImpl mdi=new ManagerDaoImpl();
			member = mdi.selectByEmailAndPassword(email, password);
			
			if (member != null && member.getEmail().equals(email) && member.getPassword().equals(password)&&member.getState().equals("manager")) {
				// Service 方法结束
				ConnectionProvider.endTransaction(true);
				return member;
			}
			ConnectionProvider.endTransaction(false);
		} catch (Throwable e) {
			try {
				ConnectionProvider.endTransaction(false);
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		}

		return null;
	}
}
