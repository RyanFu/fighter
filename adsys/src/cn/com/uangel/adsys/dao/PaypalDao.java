package cn.com.uangel.adsys.dao;

import java.util.List;
import java.util.Vector;

import cn.com.uangel.adsys.entity.CommisionInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeInfo;

public interface PaypalDao {
	/** 充值插入 */
	public void insertRecharge(RechargeInfo rech);

	/** 更新充值记录 */
	public void updateRecharge(RechargeInfo rech);

	/** 通过id查找充值 */
	public RechargeInfo selectRechargeById(int id);
	
	/** 通过时间查找充值 */
	public Vector<RechargeInfo> selectRechargeByTime(String time);
	
	/** 通过memId查找充值 */
	public List<RechargeInfo> selectRechargeByMemId(int memId);
	
	/** 通过id删除充值记录 */
	public void deleteRechargeById(int id);
	
	
	/** 提现插入 */
	public void insertPaypal(PaypalInfo paypal);

	/** 更新提现记录 */
	public void updatePaypal(PaypalInfo paypal);

	/** 通过id查提现记录 */
	public PaypalInfo selectPaypalById(int id);
	
	/** 通过memId查找提现记录 */
	public List<PaypalInfo> selectPaypalByMemId(int memId);
	
	
	/** 通过id删除提现记录 */
	public void deletePaypalById(int id);
	
	
	
	/** 雇佣金插入 */
	public void insertCommision(CommisionInfo commision);

	/** 更新雇佣金记录 */
	public void updateCommision(CommisionInfo commision);

	/** 通过id查雇佣金记录 */
	public CommisionInfo selectCommisionById(int id);
	
	/** 通过id删除雇佣金记录 */
	public void deleteCommisionById(int id);
	
	/** 通过memId查找雇佣金记录 */
	public List<CommisionInfo> selectCommisionByMemId(int memId);
	
	/** 通过memId查找雇佣金记录 */
	public List<CommisionInfo> selectCommisionByMemIdAndDate(int memId,CommisionInfo comm);
		
	/** 充值银行账号 */
	public void insertRechargeAccount(RechargeAccount rechAccount);

	/** 更新充值的银行账号 */
	public void updateRechargeAccount(RechargeAccount rechAccount);
	
	/** 查找所有银行账号信息 */
	public List<RechargeAccount> selectRechargeAccount();
	
	/** 通过id查 会员信息*/
	public Member selectById(int id);
	
}
