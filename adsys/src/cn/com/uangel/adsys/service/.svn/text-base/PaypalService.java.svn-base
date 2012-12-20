package cn.com.uangel.adsys.service;

import java.util.List;

import cn.com.uangel.adsys.entity.CommisionInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeInfo;

public interface PaypalService {
	/**
	 * 添加提现信息
	 */
	public void addPaypalInfo(PaypalInfo paypal);
	/**
	 * 添加发放雇佣金信息
	 */
	public void addCommisionInfo(CommisionInfo comm);
	/**
	 * 添加充值信息
	 */
	public void addRechargeInfo(RechargeInfo rech);
	/**
	 * 获得提现记录
	 */
	public PaypalInfo getPaypalById(int paypalId);
	/**
	 * 获得充值记录
	 */
	public RechargeInfo getRechargeById(int rechargeId);
	/**
	 * 获得发放雇佣金记录
	 */
	public CommisionInfo getCommisionById(int commId);

	/**
	 * 更新提现信息
	 */
	public void modifyPaypalInfo(PaypalInfo paypal);
		
	/**
	 * 更新发放雇佣金信息
	 */
	public void modifyCommisionInfo(CommisionInfo comm);
	
	/**
	 * 更新充值信息
	 */
	public void modifyRechargeInfo(RechargeInfo rech);
	
	/**
	 * 删除提现信息
	 */
	public void removePaypalInfoById(int paypalId);
		
	/**
	 * 删除发放雇佣金信息
	 */
	public void removeCommisionInfoById(int commId);
	
	/**
	 * 删除充值信息
	 */
	public void removeRechargeInfoById(int rechargeId);
	

	public List<RechargeInfo> getRechargesByMemId(int memId);
	
	public List<PaypalInfo> getPaypalByMemId(int memId);
	
	public List<CommisionInfo> getCommisionByMemId(int memId);
	
	public List<CommisionInfo> getCommisionByMemIdAndDate(int memId,CommisionInfo comm);
	
	/**
	 * 得到所有充值的账户
	 */
	public List<RechargeAccount> getAllRechargeAccount();
	/**
	 * 得到会员信息
	 * @param id
	 * @return
	 */
	public Member getById(int id);
	
}
