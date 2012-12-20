package cn.com.uangel.adsys.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.com.uangel.adsys.dao.ManagerDao;
import cn.com.uangel.adsys.entity.AdTypePrice;
import cn.com.uangel.adsys.entity.CommisionInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalEdit;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeEdit;
import cn.com.uangel.adsys.entity.RechargeInfo;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.DoubleUtil;
import cn.com.uangel.adsys.util.JdbcUtil;

public class ManagerDaoImpl implements ManagerDao{

	public List<RechargeInfo> selectRechargeByState(String state,String startTime,String endTime,String ord_number) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<RechargeInfo> listRech=null;
		try {
			
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("select * from recharge_info where state=?");
			
			if(startTime!=null&&!startTime.equals("")){
				sql.append(" and recharge_time > ?");
			}
			if(endTime!=null&&!endTime.equals("")){
				sql.append(" and recharge_time< ?");
			}
			
			if(ord_number!=null&&!ord_number.equals("")){
				sql.append(" and ord_number =?");
			}
			
			ps = con.prepareStatement(sql.toString());
			int indexApp=1;
			
			ps.setString(indexApp++, state);
			
			if(startTime!=null&&!startTime.equals("")){
				ps.setString(indexApp++, startTime);
			}
			if(endTime!=null&&!endTime.equals("")){
				ps.setString(indexApp++, endTime);
			}
			
			if(ord_number!=null&&!ord_number.equals("")){
				ps.setString(indexApp++, ord_number);
			}
			
			rs = ps.executeQuery();
			listRech=new ArrayList<RechargeInfo>();
			while(rs.next()){
				RechargeInfo rech=new RechargeInfo();
				int index = 1;
				rech.setId(rs.getInt(index++));
				rech.setMem_id(rs.getInt(index++));
				rech.setRecharge_time(rs.getTimestamp(index++));
				rech.setOrd_number(rs.getString(index++));
				rech.setRecharge_count(DoubleUtil.round(rs.getDouble(index++), 4));
				rech.setReal_count(DoubleUtil.round(rs.getDouble(index++), 4));
				rech.setState(rs.getString(index++));
				rech.setBank_num(rs.getString(index++));
				listRech.add(rech);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listRech;
	}
	
//	select * from recharge_info left join member ON recharge_info.mem_id=member.id

	public List<RechargeEdit> editRechargeById(int id){
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<RechargeEdit> listRech=null;
		try {
			
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("select a.id,a.recharge_time,a.ord_number,a.recharge_count,");
			sql.append("a.real_count,a.state,a.bank_num,b.email,b.name ");
			sql.append("from recharge_info a left join member b ON a.mem_id=b.id WHERE a.id=?");
			
			ps = con.prepareStatement(sql.toString());
			int indexApp=1;
			ps.setInt(indexApp++, id);
			rs = ps.executeQuery();
			listRech=new ArrayList<RechargeEdit>();
			while(rs.next()){
				RechargeEdit rechE=new RechargeEdit();
				
				int index = 1;
				rechE.setId(rs.getInt(index++));
				rechE.setRecharge_time(rs.getTimestamp(index++));
				rechE.setOrd_number(rs.getString(index++));
				rechE.setRecharge_count(DoubleUtil.round(rs.getDouble(index++), 4));
				rechE.setReal_count(DoubleUtil.round(rs.getDouble(index++), 4));
				rechE.setState(rs.getString(index++));
				rechE.setBank_num(rs.getString(index++));
				
				rechE.setEmail(rs.getString(index++));
				rechE.setName(rs.getString(index++));
				listRech.add(rechE);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listRech;
		
	}

	public void updateRecharge(RechargeInfo rech) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update recharge_info set mem_id=?,recharge_time=?,ord_number=?,");
			sql.append("recharge_count=?,real_count=?,state=?,bank_num=? where id=?");
			ps = con.prepareStatement(sql.toString());

			int index = 1;
			
			ps.setInt(index++, rech.getMem_id());
			ps.setTimestamp(index++, getSQLTimestamp(rech.getRecharge_time()));
			ps.setString(index++, rech.getOrd_number());
			ps.setDouble(index++, DoubleUtil.round(rech.getRecharge_count(),4));
			ps.setDouble(index++, DoubleUtil.round(rech.getReal_count(),4));
			ps.setString(index++, rech.getState());
			ps.setString(index++, rech.getBank_num());
			ps.setInt(index++, rech.getId());
			

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		
	}

	protected Date getSQLDate(java.util.Date d) {
		if (d == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(d);
		return Date.valueOf(dateStr);
	}

	protected Timestamp getSQLTimestamp(java.util.Date d) {
		if (d == null) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(d);
		return Timestamp.valueOf(dateStr);
	}

	public RechargeInfo selectRechargeById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from recharge_info where id=?";
			RechargeInfo rech=new RechargeInfo();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			int index = 1;
			rech.setId(rs.getInt(index++));
			rech.setMem_id(rs.getInt(index++));
			rech.setRecharge_time(rs.getTimestamp(index++));
			rech.setOrd_number(rs.getString(index++));
			rech.setRecharge_count(DoubleUtil.round(rs.getDouble(index++),4));
			rech.setReal_count(DoubleUtil.round(rs.getDouble(index++),4));
			rech.setState(rs.getString(index++));
			rech.setBank_num(rs.getString(index++));
			return rech;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	public List<PaypalInfo> selectPaypalInfoByCondition(String state, String startTime, String endTime,
			String bod_number) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<PaypalInfo> listPay=null;
		try {
			
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("select * from paypal_info where state=?");
			
			if(startTime!=null&&!startTime.equals("")){
				sql.append(" and apply_time > ?");
			}
			if(endTime!=null&&!endTime.equals("")){
				sql.append(" and apply_time< ?");
			}
			
			if(bod_number!=null&&!bod_number.equals("")){
				sql.append(" and bod_number =?");
			}
			
			ps = con.prepareStatement(sql.toString());
			int indexApp=1;
			
			ps.setString(indexApp++, state);
			
			if(startTime!=null&&!startTime.equals("")){
				ps.setString(indexApp++, startTime);
			}
			if(endTime!=null&&!endTime.equals("")){
				ps.setString(indexApp++, endTime);
			}
			
			if(bod_number!=null&&!bod_number.equals("")){
				ps.setString(indexApp++, bod_number);
			}
			
			rs = ps.executeQuery();
			listPay=new ArrayList<PaypalInfo>();
			while(rs.next()){
				PaypalInfo pay=new PaypalInfo();
				
				int index = 1;
				pay.setId(rs.getInt(index++));
				pay.setMem_id(rs.getInt(index++));
				pay.setApply_time(rs.getTimestamp(index++));
				pay.setBod_number(rs.getString(index++));
				pay.setApply_amount(DoubleUtil.round(rs.getDouble(index++),4));
				pay.setAllow_amount(DoubleUtil.round(rs.getDouble(index++),4));
				pay.setState(rs.getString(index++));
				pay.setPay_date(rs.getTimestamp(index++));
				listPay.add(pay);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listPay;
		
	}
//	SELECT a.id,a.apply_time,a.bod_number,a.apply_amount,a.allow_amount,a.state,a.pay_date,b.email,b.`name`,b.account_attr,b.invoice_able,b.account_number,b.income_balance FROM paypal_info a INNER JOIN member b ON a.mem_id=b.id WHERE a.id=1
	public PaypalInfo selectPaypalInfoById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from paypal_info where id=?";
			PaypalInfo pay=new PaypalInfo();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			int index = 1;
			pay.setId(rs.getInt(index++));
			pay.setMem_id(rs.getInt(index++));
			pay.setApply_time(rs.getTimestamp(index++));
			pay.setBod_number(rs.getString(index++));
			pay.setApply_amount(DoubleUtil.round(rs.getDouble(index++),4));
			pay.setAllow_amount(DoubleUtil.round(rs.getDouble(index++),4));
			pay.setState(rs.getString(index++));
			pay.setPay_date(rs.getTimestamp(index++));
			return pay;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	public void updatePaypalInfo(PaypalInfo pay) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update paypal_info set mem_id=?,apply_time=?,bod_number=?,");
			sql.append("apply_amount=?,allow_amount=?,state=?,pay_date=? where id=?");
			ps = con.prepareStatement(sql.toString());

			int index = 1;
			
			ps.setInt(index++, pay.getMem_id());
			ps.setTimestamp(index++, getSQLTimestamp(pay.getApply_time()));
			ps.setString(index++, pay.getBod_number());
			ps.setDouble(index++, DoubleUtil.round(pay.getApply_amount(),4));
			ps.setDouble(index++, DoubleUtil.round(pay.getAllow_amount(),4));
			ps.setString(index++, pay.getState());
			ps.setTimestamp(index++, getSQLTimestamp(pay.getPay_date()));
			ps.setInt(index++, pay.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		
	}

	public List<PaypalEdit> editPaypalById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<PaypalEdit> listPay=null;
		try {
			
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT a.id,a.apply_time,a.bod_number,a.apply_amount,");
			sql.append("a.allow_amount,a.state,a.pay_date,b.email,b.`name`,");
			sql.append("b.account_attr,b.invoice_able,b.account_number,");
			sql.append("b.income_balance FROM paypal_info a INNER JOIN member ");
			sql.append("b ON a.mem_id=b.id WHERE a.id=?");
			ps = con.prepareStatement(sql.toString());
			int indexApp=1;
			ps.setInt(indexApp++, id);
			rs = ps.executeQuery();
			listPay=new ArrayList<PaypalEdit>();
			while(rs.next()){
				PaypalEdit payE=new PaypalEdit();
				
				int index = 1;
				payE.setId(rs.getInt(index++));
				payE.setApply_time(rs.getTimestamp(index++));
				payE.setBod_number(rs.getString(index++));
				payE.setApply_amount(DoubleUtil.round(rs.getDouble(index++),4));
				payE.setAllow_amount(DoubleUtil.round(rs.getDouble(index++),4));
				payE.setState(rs.getString(index++));
				payE.setPay_date(rs.getTimestamp(index++));
				payE.setEmail(rs.getString(index++));
				payE.setName(rs.getString(index++));
				payE.setAccount_attr(rs.getString(index++));
				payE.setInvoice_able(rs.getString(index++));
				payE.setAccount_number(rs.getString(index++));
				payE.setIncome_balance(DoubleUtil.round(rs.getDouble(index++),4));
				listPay.add(payE);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listPay;
	}

	public Member selectById(int id) {
		Member member=null;
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			member = new Member();
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from member where id=").append(id);
			
			st = con.createStatement();
			rs = st.executeQuery(sql.toString());
			rs.next();
			member.setId(rs.getInt(1));
			member.setEmail(rs.getString(2));
			member.setPassword(rs.getString(3));
			member.setRegist_time(rs.getTimestamp(4));
			member.setName(rs.getString(5));
			member.setTelephone(rs.getString(6));
			member.setCellphone(rs.getString(7));
			member.setAccount_type(rs.getString(8));
			member.setQq(rs.getString(9));
			member.setCom_name(rs.getString(10));
			member.setCom_homepage(rs.getString(11));
			member.setCom_address(rs.getString(12));
			member.setZipcode(rs.getString(13));
			member.setAccount_attr(rs.getString(14));
			member.setInvoice_able(rs.getString(15));
			member.setGet_money_mode(rs.getString(16));
			member.setOpen_bank(rs.getString(17));
			member.setAccount_number(rs.getString(18));
			member.setOpen_name(rs.getString(19));
			member.setAddress_bank(rs.getString(20));
			member.setZipcode_bank(rs.getString(21));
			member.setAd_balance(DoubleUtil.round(rs.getDouble(22),4));
			member.setIncome_balance(DoubleUtil.round(rs.getDouble(23),4));
			member.setState(rs.getString(24));
			member.setSer_num(rs.getString(25));


		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, st);
		}
		return member;
	}
//	SELECT pay_date from paypal_info WHERE mem_id=6 AND state=3 ORDER BY pay_date DESC LIMIT 0,1
	public Date selectPayDate(int memId) {
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date date=null;
		try {
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT pay_date from paypal_info WHERE mem_id=? AND state=3 ORDER BY pay_date DESC LIMIT 0,1");
			
			ps = con.prepareStatement(sql.toString());
			
			int index = 1;
			ps.setInt(index++, memId);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				Timestamp time=rs.getTimestamp(1);
				if(time!=null){
					date= new Date(time.getTime());
				}
			}


		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return date;
	}

	public void insertComm(CommisionInfo comm) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO commision_info (mem_id,commision_num,bank_name,bank_num,start_time,");
			sql.append("end_time,income,deduct_count,full_count,poundage,real_count,state) VALUES");
			sql.append("(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps = con.prepareStatement(sql.toString());
			int index=1;
			ps.setInt(index++, comm.getMem_id());
			ps.setString(index++, comm.getCommision_num());
			ps.setString(index++, comm.getBank_name());
			ps.setString(index++, comm.getBank_num());
			ps.setTimestamp(index++, getSQLTimestamp(comm.getStart_time()));
			ps.setTimestamp(index++, getSQLTimestamp(comm.getEnd_time()));
			ps.setDouble(index++, DoubleUtil.round(comm.getIncome(),4));
			ps.setDouble(index++, DoubleUtil.round(comm.getDeduct_count(),4));
			ps.setDouble(index++, DoubleUtil.round(comm.getFull_count(),4));
			ps.setDouble(index++, DoubleUtil.round(comm.getPoundage(),4));
			ps.setDouble(index++, DoubleUtil.round(comm.getReal_count(),4));
			ps.setString(index++, comm.getState());
			
			
			ps.executeUpdate();
			// 取得自动生成的字段的值
			rs = ps.getGeneratedKeys();
			rs.next();
			comm.setId(rs.getInt(1));

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
		}
		
	}

	public void updateCommision(CommisionInfo commision) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update commision_info set mem_id=?,commision_num=?,bank_name=?,");
			sql.append("bank_num=?,start_time=?,end_time=?,income=?,deduct_count=?,");
			sql.append("full_count=?,poundage=?,real_count=?,state=? where id=?");
			ps = con.prepareStatement(sql.toString());

			int index = 1;
			
			ps.setInt(index++, commision.getMem_id());
			ps.setString(index++,commision.getCommision_num());
			ps.setString(index++, commision.getBank_name());
			ps.setString(index++, commision.getBank_num());
			ps.setTimestamp(index++, getSQLTimestamp(commision.getStart_time()));
			ps.setTimestamp(index++, getSQLTimestamp(commision.getEnd_time()));
			ps.setDouble(index++, DoubleUtil.round(commision.getIncome(),4));
			ps.setDouble(index++, DoubleUtil.round(commision.getDeduct_count(),4));
			ps.setDouble(index++, DoubleUtil.round(commision.getFull_count(),4));
			ps.setDouble(index++, DoubleUtil.round(commision.getPoundage(),4));
			ps.setDouble(index++, DoubleUtil.round(commision.getReal_count(),4));
			ps.setString(index++, commision.getState());
			ps.setInt(index++, commision.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		
	}

	
	
	
	public void deleteManageBankById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from recharge_account where id=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, id);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	
	
	public void insertManageBank(RechargeAccount rechAcc) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			
			
			StringBuffer sql = new StringBuffer();
			sql.append("insert into recharge_account (open_name,bank_name,");
			sql.append("bank_num,bank_address) values (?,?,?,?)");
			ps = con.prepareStatement(sql.toString());
			
			int index = 1;
			ps.setString(index++, rechAcc.getOpen_name());
			ps.setString(index++, rechAcc.getBank_name());
			ps.setString(index++, rechAcc.getBank_num());
			ps.setString(index++, rechAcc.getBank_address());
			ps.executeUpdate();
			// 取得自动生成的字段的值
			rs = ps.getGeneratedKeys();
			rs.next();
			rechAcc.setId(rs.getInt(1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	public List<RechargeAccount> selectManageBank() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		RechargeAccount rechAcc=null;
		List<RechargeAccount> rechAccList=null;
		try {
			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from recharge_account";
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rechAccList=new ArrayList<RechargeAccount>();
			while(rs.next()){
				rechAcc=new RechargeAccount();
				int index = 1;
				rechAcc.setId(rs.getInt(index++));
				rechAcc.setOpen_name(rs.getString(index++));
				rechAcc.setBank_name(rs.getString(index++));
				rechAcc.setBank_num(rs.getString(index++));
				rechAcc.setBank_address(rs.getString(index++));
				rechAccList.add(rechAcc);
			}
			
						
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return rechAccList;
	}
	public RechargeAccount selectManageBankById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		RechargeAccount rechAcc=null;
		try {
			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from recharge_account where id=?";
			rechAcc=new RechargeAccount();
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				int index = 1;
				rechAcc.setId(rs.getInt(index++));
				rechAcc.setOpen_name(rs.getString(index++));
				rechAcc.setBank_name(rs.getString(index++));
				rechAcc.setBank_num(rs.getString(index++));
				rechAcc.setBank_address(rs.getString(index++));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return rechAcc;
	}
//	private Integer id;
//	private String open_name;
//	private String bank_name;
//	private String bank_num;
//	private String bank_address;
	public void updateManageBank(RechargeAccount rechAcc) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update recharge_account set open_name=?,bank_name=?,bank_num=?,");
			sql.append("bank_address=? where id=?");
			ps = con.prepareStatement(sql.toString());

			int index = 1;
			
			
			ps.setString(index++, rechAcc.getOpen_name());
			ps.setString(index++, rechAcc.getBank_name());
			ps.setString(index++, rechAcc.getBank_num());
			ps.setString(index++, rechAcc.getBank_address());
			ps.setInt(index++, rechAcc.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	
	
	public void deleteAdTypePriceById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from adtype_price where id=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, id);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		
	}


	public void insertAdTypePrice(AdTypePrice adType) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO adtype_price (ad_type,cur_money,is_newest,change_time) VALUES (?,?,?,?)");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, adType.getAd_type());
			ps.setDouble(index++, DoubleUtil.round(adType.getCur_money(),4));
			ps.setString(index++, adType.getIs_newest());
			ps.setDate(index++, getSQLDate(adType.getChange_time()));
			ps.executeUpdate();
			
			// 取得自动生成的字段的值
			rs=ps.getGeneratedKeys();
			rs.next();
			adType.setId(rs.getInt(1));

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	
	public AdTypePrice selectAdTypePriceById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		AdTypePrice adTypePrice=null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM adtype_price WHERE id=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setInt(indexset++, id);

			rs=ps.executeQuery();
			while (rs.next()){
				int index=1;
				adTypePrice=new AdTypePrice();
				adTypePrice.setId(rs.getInt(index++));
				adTypePrice.setAd_type(rs.getString(index++));
				adTypePrice.setCur_money(DoubleUtil.round(rs.getDouble(index++),4));
				adTypePrice.setIs_newest(rs.getString(index++));
				adTypePrice.setChange_time(rs.getDate(index++));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return adTypePrice;
	}

	
	public void updateAdTypePrice(AdTypePrice adType) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE adtype_price SET ad_type=?,cur_money=?,is_newest=?,change_time=? WHERE id=?");

				ps = con.prepareStatement(sql.toString());
				int index = 1;
				ps.setString(index++, adType.getAd_type());
				ps.setDouble(index++, DoubleUtil.round(adType.getCur_money(),4));
				ps.setString(index++, adType.getIs_newest());
				ps.setDate(index++, getSQLDate(adType.getChange_time()));
				ps.setInt(index++, adType.getId());
				ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	
	

	public AdTypePrice selectAdTypePriceByIsNewest(String ad_type) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		AdTypePrice adTypePrice=null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM adtype_price WHERE ad_type=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setString(indexset++, ad_type);

			rs=ps.executeQuery();
			while (rs.next()){
				int index=1;
				adTypePrice=new AdTypePrice();
				adTypePrice.setId(rs.getInt(index++));
				adTypePrice.setAd_type(rs.getString(index++));
				adTypePrice.setCur_money(DoubleUtil.round(rs.getDouble(index++),4));
				adTypePrice.setIs_newest(rs.getString(index++));
				adTypePrice.setChange_time(rs.getDate(index++));
		   }

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return adTypePrice;
	}

	public void updateAdTypePriceAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE adtype_price SET is_newest=0");

			ps = con.prepareStatement(sql.toString());
			
			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		
	}

	public boolean selectAdTypePriceByType(String ad_type) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM adtype_price WHERE ad_type=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setString(indexset++, ad_type);

			rs=ps.executeQuery();
			while (rs.next()){
				return true;
		   }
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}

			
		return false;
	}

	public void update(Member mem) {
		PreparedStatement ps = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update member set email=?,password=?,regist_time=?,name=?,telephone=?,");
			sql.append("cellphone=?,account_type=?,qq=?,com_name=?,com_homepage=?,com_address=?,zipcode=?,");
			sql.append("account_attr=?,invoice_able=?,get_money_mode=?,open_bank=?,account_number=?,");
			sql.append("open_name=?,address_bank=?,zipcode_bank=?,ad_balance=?,income_balance=?,state=?,");
			sql.append("ser_num=? where id=?");
			
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, mem.getEmail());
			ps.setString(2, mem.getPassword());
			ps.setTimestamp(3, getSQLTimestamp(mem.getRegist_time()));
			ps.setString(4, mem.getName());
			ps.setString(5, mem.getTelephone());
			ps.setString(6, mem.getCellphone());
			ps.setString(7, mem.getAccount_type());
			ps.setString(8, mem.getQq());
			ps.setString(9, mem.getCom_name());
			ps.setString(10, mem.getCom_homepage());
			ps.setString(11, mem.getCom_address());
			ps.setString(12, mem.getZipcode());
			ps.setString(13, mem.getAccount_attr());
			ps.setString(14, mem.getInvoice_able());
			ps.setString(15, mem.getGet_money_mode());

			ps.setString(16, mem.getOpen_bank());
			ps.setString(17, mem.getAccount_number());
			ps.setString(18, mem.getOpen_name());
			ps.setString(19, mem.getAddress_bank());
			ps.setString(20, mem.getZipcode_bank());

			ps.setDouble(21, DoubleUtil.round(mem.getAd_balance(), 4));
			ps.setDouble(22, DoubleUtil.round(mem.getIncome_balance(), 4));
			ps.setString(23, mem.getState());
			ps.setString(24, mem.getSer_num());
			ps.setInt(25, mem.getId());
			
			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
		}
	}
	public void deletePaypalById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from paypal_info where id=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, id);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	public void deleteRechargeById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from recharge_info where id=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, id);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	public Member selectByEmailAndPassword(String email, String password) {
		Member member = new Member();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from member where email=? and password=?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()){
				member.setId(rs.getInt(1));
				member.setEmail(rs.getString(2));
				member.setPassword(rs.getString(3));
				member.setRegist_time(rs.getTimestamp(4));
				member.setName(rs.getString(5));
				member.setTelephone(rs.getString(6));
				member.setCellphone(rs.getString(7));
				member.setAccount_type(rs.getString(8));
				member.setQq(rs.getString(9));
				member.setCom_name(rs.getString(10));
				member.setCom_homepage(rs.getString(11));
				member.setCom_address(rs.getString(12));
				member.setZipcode(rs.getString(13));
				member.setAccount_attr(rs.getString(14));
				member.setInvoice_able(rs.getString(15));
				member.setGet_money_mode(rs.getString(16));
				member.setOpen_bank(rs.getString(17));
				member.setAccount_number(rs.getString(18));
				member.setOpen_name(rs.getString(19));
				member.setAddress_bank(rs.getString(20));
				member.setZipcode_bank(rs.getString(21));
				member.setAd_balance(DoubleUtil.round(rs.getDouble(22), 4));
				member.setIncome_balance(DoubleUtil.round(rs.getDouble(23), 4));
				member.setState(rs.getString(24));
				member.setSer_num(rs.getString(25));
				return member;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
		}

		return null;
	}
	
	
}
