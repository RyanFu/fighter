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
import java.util.Vector;

import cn.com.uangel.adsys.dao.PaypalDao;
import cn.com.uangel.adsys.entity.CommisionInfo;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.entity.PaypalInfo;
import cn.com.uangel.adsys.entity.RechargeAccount;
import cn.com.uangel.adsys.entity.RechargeInfo;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.DoubleUtil;
import cn.com.uangel.adsys.util.JdbcUtil;

public class PaypalDaoImpl implements PaypalDao {
	/**
	 * 财务管理类
	 * 
	 * @author dao
	 * 
	 */
	PreparedStatement ps = null;
	ResultSet rs = null;
	Member member = null;
	Connection con = null;

	// private Integer mem_id;
	// private String commision_num;
	// private String bank_name;
	// private String bank_num;
	// private Date start_time;
	// private Date end_time;
	// private Double income;
	// private Double deduct_count;
	// private Double full_count;
	// private Double poundage;
	// private Double real_count;
	// private String state;
	@Override
	public void deleteCommisionById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from commision_info where id=?");

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

	// private Integer mem_id;
	// private Date apply_time;
	// private String bod_number;
	// private Double apply_amount;
	// private Double allow_amount;
	// private String state;
	// private Date pay_date;
	@Override
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

	// private Integer mem_id;
	// private Date recharge_time;
	// private String ord_number;
	// private Double recharge_count=0.0;
	// private Double real_count=0.0;
	// private String state;
	@Override
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

	@Override
	public void insertPaypal(PaypalInfo paypal) {
		try {
			con = ConnectionProvider.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("insert into paypal_info (mem_id,apply_time,");
			sql.append("bod_number,apply_amount,allow_amount,state,pay_date) values (?,?,?,?,?,?,?)");
			ps = con.prepareStatement(sql.toString());
			int index = 1;
			ps.setInt(index++, paypal.getMem_id());
			ps.setTimestamp(index++, getSQLTimestamp(paypal.getApply_time()));
			ps.setString(index++, paypal.getBod_number());
			ps.setDouble(index++, DoubleUtil.round(paypal.getApply_amount(), 4));
			ps.setDouble(index++, DoubleUtil.round(paypal.getAllow_amount(), 4));
			ps.setString(index++, paypal.getState());
			ps.setTimestamp(index++, getSQLTimestamp(paypal.getPay_date()));
			ps.executeUpdate();
			// 取得自动生成的字段的值
			rs = ps.getGeneratedKeys();
			rs.next();
			paypal.setId(rs.getInt(1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
		}

	}

	@Override
	public void insertCommision(CommisionInfo commision) {

	}

	@Override
	public void insertRecharge(RechargeInfo rech) {
		try {
			con = ConnectionProvider.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("insert into recharge_info (mem_id,recharge_time,");
			sql.append("ord_number,recharge_count,real_count,state,bank_num) values (?,?,?,?,?,?,?)");
			ps = con.prepareStatement(sql.toString());
			int index = 1;
			ps.setInt(index++, rech.getMem_id());
			ps.setTimestamp(index++, getSQLTimestamp(rech.getRecharge_time()));
			ps.setString(index++, rech.getOrd_number());
			ps.setDouble(index++, DoubleUtil.round(rech.getRecharge_count(), 4));
			ps.setDouble(index++, DoubleUtil.round(rech.getReal_count(), 4));
			ps.setString(index++, rech.getState());
			ps.setString(index++, rech.getBank_num());
			ps.executeUpdate();
			// 取得自动生成的字段的值
			rs = ps.getGeneratedKeys();
			rs.next();
			rech.setId(rs.getInt(1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
		}
	}

	@Override
	public CommisionInfo selectCommisionById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from commision_info where id=?";
			CommisionInfo comm = new CommisionInfo();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			int index = 1;
			comm.setId(rs.getInt(index++));
			comm.setMem_id(rs.getInt(index++));
			comm.setCommision_num(rs.getString(index++));
			comm.setBank_name(rs.getString(index++));
			comm.setBank_num(rs.getString(index++));
			comm.setStart_time(rs.getTimestamp(index++));
			comm.setEnd_time(rs.getTimestamp(index++));
			comm.setIncome(DoubleUtil.round(rs.getDouble(index++), 4));
			comm.setDeduct_count(DoubleUtil.round(rs.getDouble(index++), 4));
			comm.setFull_count(DoubleUtil.round(rs.getDouble(index++), 4));
			comm.setPoundage(DoubleUtil.round(rs.getDouble(index++), 4));
			comm.setReal_count(DoubleUtil.round(rs.getDouble(index++), 4));
			comm.setState(rs.getString(index++));

			return comm;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public PaypalInfo selectPaypalById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from paypal_info where id=?";
			PaypalInfo pay = new PaypalInfo();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			int index = 1;
			pay.setId(rs.getInt(index++));
			pay.setMem_id(rs.getInt(index++));
			pay.setApply_time(rs.getTimestamp(index++));
			pay.setBod_number(rs.getString(index++));
			pay.setApply_amount(DoubleUtil.round(rs.getDouble(index++), 4));
			pay.setAllow_amount(DoubleUtil.round(rs.getDouble(index++), 4));
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

	@Override
	public RechargeInfo selectRechargeById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from recharge_info where id=?";
			RechargeInfo rech = new RechargeInfo();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			int index = 1;
			rech.setId(rs.getInt(index++));
			rech.setMem_id(rs.getInt(index++));
			rech.setRecharge_time(rs.getTimestamp(index++));
			rech.setOrd_number(rs.getString(index++));
			rech.setRecharge_count(DoubleUtil.round(rs.getDouble(index++), 4));
			rech.setReal_count(DoubleUtil.round(rs.getDouble(index++), 4));
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

	@Override
	public void updatePaypal(PaypalInfo paypal) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update paypal_info set mem_id=?,apply_time=?,bod_number=?,");
			sql.append("apply_amount=?,allow_amount=?,state=?,pay_date=? where id=?");
			ps = con.prepareStatement(sql.toString());

			int index = 1;

			ps.setInt(index++, paypal.getMem_id());
			ps.setTimestamp(index++, getSQLTimestamp(paypal.getApply_time()));
			ps.setString(index++, paypal.getBod_number());
			ps.setDouble(index++, DoubleUtil.round(paypal.getApply_amount(), 4));
			ps.setDouble(index++, DoubleUtil.round(paypal.getAllow_amount(), 4));
			ps.setString(index++, paypal.getState());
			ps.setTimestamp(index++, getSQLTimestamp(paypal.getPay_date()));
			ps.setInt(index++, paypal.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
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
			ps.setString(index++, commision.getCommision_num());
			ps.setString(index++, commision.getBank_name());
			ps.setString(index++, commision.getBank_num());
			ps.setTimestamp(index++, getSQLTimestamp(commision.getStart_time()));
			ps.setTimestamp(index++, getSQLTimestamp(commision.getEnd_time()));
			ps.setDouble(index++, DoubleUtil.round(commision.getIncome(), 4));
			ps.setDouble(index++, DoubleUtil.round(commision.getDeduct_count(), 4));
			ps.setDouble(index++, DoubleUtil.round(commision.getFull_count(), 4));
			ps.setDouble(index++, DoubleUtil.round(commision.getPoundage(), 4));
			ps.setDouble(index++, DoubleUtil.round(commision.getReal_count(), 4));
			ps.setString(index++, commision.getState());
			ps.setInt(index++, commision.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
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
			ps.setDouble(index++, DoubleUtil.round(rech.getRecharge_count(), 4));
			ps.setDouble(index++, DoubleUtil.round(rech.getReal_count(), 4));
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

	@Override
	public List<RechargeInfo> selectRechargeByMemId(int memId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<RechargeInfo> listRech = null;
		try {

			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from recharge_info where mem_id=?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, memId);
			rs = ps.executeQuery();
			listRech = new ArrayList<RechargeInfo>();
			while (rs.next()) {
				RechargeInfo rech = new RechargeInfo();
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

	@Override
	public List<PaypalInfo> selectPaypalByMemId(int memId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<PaypalInfo> listPaypal = null;
		try {

			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from paypal_info where mem_id=?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, memId);
			rs = ps.executeQuery();
			listPaypal = new ArrayList<PaypalInfo>();
			while (rs.next()) {
				PaypalInfo paypal = new PaypalInfo();
				int index = 1;
				paypal.setId(rs.getInt(index++));
				paypal.setMem_id(rs.getInt(index++));
				paypal.setApply_time(rs.getTimestamp(index++));
				paypal.setBod_number(rs.getString(index++));
				paypal.setApply_amount(DoubleUtil.round(rs.getDouble(index++), 4));
				paypal.setAllow_amount(DoubleUtil.round(rs.getDouble(index++), 4));
				paypal.setState(rs.getString(index++));
				paypal.setPay_date(rs.getTimestamp(index++));
				listPaypal.add(paypal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listPaypal;
	}

	@Override
	public List<CommisionInfo> selectCommisionByMemId(int memId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CommisionInfo> listComm = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from commision_info where mem_id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, memId);
			rs = ps.executeQuery();
			listComm = new ArrayList<CommisionInfo>();
			while (rs.next()) {
				CommisionInfo comm = new CommisionInfo();
				int index = 1;
				comm.setId(rs.getInt(index++));
				comm.setMem_id(rs.getInt(index++));
				comm.setCommision_num(rs.getString(index++));
				comm.setBank_name((rs.getString(index++)));
				comm.setBank_num((rs.getString(index++)));
				comm.setStart_time(rs.getDate(index++));
				comm.setEnd_time(rs.getDate(index++));
				comm.setIncome(DoubleUtil.round(rs.getDouble(index++), 4));
				comm.setDeduct_count(DoubleUtil.round(rs.getDouble(index++), 4));
				comm.setFull_count(DoubleUtil.round(rs.getDouble(index++), 4));
				comm.setPoundage(DoubleUtil.round(rs.getDouble(index++), 4));
				comm.setReal_count(DoubleUtil.round(rs.getDouble(index++), 4));
				comm.setState(rs.getString(index++));
				listComm.add(comm);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listComm;
	}

	@Override
	public List<CommisionInfo> selectCommisionByMemIdAndDate(int memId, CommisionInfo comm) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CommisionInfo> listComm = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from commision_info where mem_id=?");
			if (comm.getStart_time() != null) {
				sql.append(" and start_time > ?");
			}
			if (comm.getEnd_time() != null) {
				sql.append(" and end_time < ?");
			}
			if (comm.getCommision_num() != null) {
				sql.append(" and commision_num=?");
			}

			ps = con.prepareStatement(sql.toString());
			int indexAppen = 1;
			ps.setInt(indexAppen++, memId);
			if (comm.getStart_time() != null) {
				ps.setTimestamp(indexAppen++, getSQLTimestamp(comm.getStart_time()));
			}
			if (comm.getEnd_time() != null) {
				ps.setTimestamp(indexAppen++, getSQLTimestamp(comm.getEnd_time()));
			}
			if (comm.getCommision_num() != null) {
				ps.setString(indexAppen++, comm.getCommision_num());
			}
			rs = ps.executeQuery();
			listComm = new ArrayList<CommisionInfo>();
			while (rs.next()) {
				CommisionInfo commSearch = new CommisionInfo();
				int index = 1;
				commSearch.setId(rs.getInt(index++));
				commSearch.setMem_id(rs.getInt(index++));
				commSearch.setCommision_num(rs.getString(index++));
				commSearch.setBank_name(rs.getString(index++));
				commSearch.setBank_num(rs.getString(index++));
				commSearch.setStart_time(rs.getDate(index++));
				commSearch.setEnd_time(rs.getDate(index++));
				commSearch.setIncome(DoubleUtil.round(rs.getDouble(index++), 4));
				commSearch.setDeduct_count(DoubleUtil.round(rs.getDouble(index++), 4));
				commSearch.setFull_count(DoubleUtil.round(rs.getDouble(index++), 4));
				commSearch.setPoundage(DoubleUtil.round(rs.getDouble(index++), 4));
				commSearch.setReal_count(DoubleUtil.round(rs.getDouble(index++), 4));
				commSearch.setState(rs.getString(index++));
				listComm.add(commSearch);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listComm;
	}

	@Override
	public void insertRechargeAccount(RechargeAccount rechAccount) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RechargeAccount> selectRechargeAccount() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<RechargeAccount> listRechAccount = null;
		try {

			Connection con = ConnectionProvider.getConnection();
			String sql = "select * from recharge_account";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			listRechAccount = new ArrayList<RechargeAccount>();
			while (rs.next()) {
				RechargeAccount rechAcc = new RechargeAccount();
				int index = 1;
				rechAcc.setId(rs.getInt(index++));
				rechAcc.setOpen_name(rs.getString(index++));
				rechAcc.setBank_name(rs.getString(index++));
				rechAcc.setBank_num(rs.getString(index++));
				rechAcc.setBank_address(rs.getString(index++));
				listRechAccount.add(rechAcc);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listRechAccount;
	}

	@Override
	public void updateRechargeAccount(RechargeAccount rechAccount) {

	}

	@Override
	public Member selectById(int id) {
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
			member.setAd_balance(DoubleUtil.round(rs.getDouble(22), 4));
			member.setIncome_balance(DoubleUtil.round(rs.getDouble(23), 4));
			member.setState(rs.getString(24));
			member.setSer_num(rs.getString(25));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, st);
		}
		return member;
	}

	@Override
	public Vector<RechargeInfo> selectRechargeByTime(String time) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		RechargeInfo rech = null;
		Vector<RechargeInfo> infos = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM recharge_info WHERE recharge_time >=? AND recharge_time<=? AND state =3 ");
			infos = new Vector<RechargeInfo>();
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, time + " 00:00:00");
			ps.setString(2, time + " 23:59:59");
			rs = ps.executeQuery();
			while (rs.next()) {
				rech = new RechargeInfo();
				int index = 1;
				rech.setId(rs.getInt(index++));
				rech.setMem_id(rs.getInt(index++));
				rech.setRecharge_time(rs.getTimestamp(index++));
				rech.setOrd_number(rs.getString(index++));
				rech.setRecharge_count(DoubleUtil.round(rs.getDouble(index++), 4));
				rech.setReal_count(DoubleUtil.round(rs.getDouble(index++), 4));
				rech.setState(rs.getString(index++));
				rech.setBank_num(rs.getString(index++));
				infos.add(rech);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return infos;
	}
}
