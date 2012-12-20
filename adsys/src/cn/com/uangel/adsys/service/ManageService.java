package cn.com.uangel.adsys.service;

import java.util.List;

import cn.com.uangel.adsys.entity.AdTypePrice;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalEdit;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeEdit;
import cn.com.uangel.adsys.entity.RechargeInfo;

public interface ManageService {

	public List<RechargeInfo> getRechargesByCondition(String state,String startTime,String endTime,String bod_number);
	
	public List<RechargeEdit> getEditRechargeById(int id);
	
	public void modifyRechargeEdit(RechargeInfo rech);
	
	public List<PaypalEdit> getEditPaypalById(int id);
	
	public void modifyPaypalInfo(PaypalInfo pay);
	
	public List<RechargeAccount> getRechargeAccount();
	/**
	 * 
	 * @param id
	 *              删除公司银行账户
	 */
	public void removeRechAccBy(int id);
	/**
	 * 
	 * @param rechAcc
	 *               添加公司银行账户
	 */
	public void addRechAcc(RechargeAccount rechAcc);
	/**
	 * 
	 * @param rechAcc
	 *                 更新公司银行账户
	 */
	public void modifyRechAcc(RechargeAccount rechAcc);
	/**
	 * 
	 * @param id
	 *         得到公司银行账户某条信息
	 * @return
	 */
	public RechargeAccount getRechAcc(int id);
	/**
	 *            查出最新价格记录
	 * @return  
	 */
	public AdTypePrice getAdTypePriceByIsNew(String ad_type);
	/**
	 * 
	 * @param adType 
	 *               增加一条最新价格记录
	 */
	public void addAdTypePrice(AdTypePrice adType);
	/**
	 * 删除记录
	 */
	public void deleteAdTypePricById(int id);
	/**
	 * 将表中旧的记录的is_newest 状态改为0 代表为旧的记录
	 */
	public void modifyAdTypePriceIsNew();
	
	public void modifyAdTypePrice(AdTypePrice adType,String ad_type);
	
	public AdTypePrice getAdTypePriceById(int id);
	
	public Member getAdBalanceByRechId(int id);
	
	public Member getIncomeBalanceByPaypalId(int id);
	
	/**
	 * 删除提现信息
	 */
	public void removePaypalInfoById(int paypalId);
	
	/**
	 * 删除充值信息
	 */
	public void removeRechargeInfoById(int rechargeId);
	
	public Member getByEmailAndPassword(String email, String password);
}
