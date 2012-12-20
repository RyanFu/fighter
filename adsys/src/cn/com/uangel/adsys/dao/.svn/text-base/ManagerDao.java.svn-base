package cn.com.uangel.adsys.dao;

import java.sql.Date;
import java.util.List;

import cn.com.uangel.adsys.entity.AdTypePrice;
import cn.com.uangel.adsys.entity.CommisionInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalEdit;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeEdit;
import cn.com.uangel.adsys.entity.RechargeInfo;

public interface ManagerDao {
	/**
	 * 
	 * @param state
	 *            审批的状态
	 * @param startTime
	 *            开始时间之后的内容
	 * @param endTime
	 *            结束时间之后的内容
	 * @param bod_number
	 *            充值订单号
	 * 
	 * @return 充值记录实体
	 * 
	 */
	public List<RechargeInfo> selectRechargeByState(String state, String startTime, String endTime, String ord_number);

	/**
	 * 
	 * @param id
	 *            充值记录id
	 * @return 编辑充值记录页面信息
	 */
	public List<RechargeEdit> editRechargeById(int id);

	/**
	 * 
	 * @param rech
	 *            充值记录实体
	 */
	public void updateRecharge(RechargeInfo rech);

	/**
	 * 
	 * @param id
	 *            充值记录id
	 * @return
	 */
	public RechargeInfo selectRechargeById(int id);

	/**
	 * 
	 * @param state
	 *            审批的状态
	 * @param startTime
	 *            开始时间之后的内容
	 * @param endTime
	 *            结束时间之后的内容
	 * @param bod_number
	 *            提现订单号
	 * 
	 * @return 提现记录实体
	 * 
	 */
	public List<PaypalInfo> selectPaypalInfoByCondition(String state, String startTime, String endTime,
			String bod_number);

	/**
	 * 
	 * @param id
	 *            提现id
	 * @return 提现实体类
	 */
	public PaypalInfo selectPaypalInfoById(int id);

	/**
	 * 
	 * @param pay
	 *            提现实体类
	 */
	public void updatePaypalInfo(PaypalInfo pay);

	/**
	 * 
	 * @param id
	 *            提现记录id
	 * @return 提现记录编辑页面信息
	 */
	public List<PaypalEdit> editPaypalById(int id);

	/**
	 * 
	 * @param id
	 * @return 会员的所有信息
	 */
	public Member selectById(int id);

	/**
	 * 
	 * @return 查询提现记录最后一条记录的成交信息
	 */
	public Date selectPayDate(int memId);

	/**
	 * 
	 * @param comm
	 *            插入雇佣金信息
	 */
	public void insertComm(CommisionInfo comm);

	/**
	 * 
	 * @param commision
	 *            更新雇佣金信息
	 */
	public void updateCommision(CommisionInfo commision);

	/**
	 * 
	 * @param rechAcc
	 *            插入公司的银行账户
	 */
	public void insertManageBank(RechargeAccount rechAcc);

	/**
	 * 
	 * @param rechAcc
	 *            更新公司的银行账户
	 */
	public void updateManageBank(RechargeAccount rechAcc);

	/**
	 * 
	 * @param id
	 *            根据id删除公司的银行账户
	 */
	public void deleteManageBankById(int id);

	/**
	 * 根据id查询公司银行账户
	 * 
	 * @return 一条银行账户
	 */
	public RechargeAccount selectManageBankById(int id);

	/**
	 * 
	 * @return 所有银行账户
	 * 
	 */
	public List<RechargeAccount> selectManageBank();

	public void insertAdTypePrice(AdTypePrice adType);

	public void deleteAdTypePriceById(int id);

	/**
	 * 
	 * @param isNewest
	 *            0：代表旧的 1：代表新的
	 */
	public AdTypePrice selectAdTypePriceByIsNewest(String ad_type);
	/**
	 * 
	 * @param adType
	 *               更新一条价格记录
	 */
	public void updateAdTypePrice(AdTypePrice adType);
	/**
	 * 
	 * @param id
	 * @return  根据id查找价格记录
	 */
	public AdTypePrice selectAdTypePriceById(int id);
	/**
	 * 更新价格记录isNewest所有值为0（旧记录）
	 */
	public void updateAdTypePriceAll();
	/**
	 * 
	 * @param ad_type
	 *        查询是否存在CPC/CPM的记录
	 * @return
	 */
	public boolean selectAdTypePriceByType(String ad_type);

	/** 更新会员 */
	public void update(Member mem);
	
	/** 通过id删除提现记录 */
	public void deletePaypalById(int id);
	
	/** 通过id删除充值记录 */
	public void deleteRechargeById(int id);
	
	/** 登录时用 */
	public Member selectByEmailAndPassword(String email, String password);
}
