package cn.com.uangel.adsys.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import cn.com.uangel.adsys.dao.MemberDao;
import cn.com.uangel.adsys.entity.Member;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.DoubleUtil;
import cn.com.uangel.adsys.util.JdbcUtil;

public class MemberDaoImpl implements MemberDao {
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Member member = null;
	Connection con = null;

	@Override
	public void deleteById(int id) {
		member = new Member();
		try {
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete from member where id=?");
			ps = con.prepareStatement(sql.toString());
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
		}

	}

	@Override
	public void deleteByIds(int[] ids) {
		// member= new Member();
		// try {
		// con= ConnectionProvider.getConnection();
		// StringBuffer sql=new StringBuffer();
		// sql.append("delete from member where id=?");
		// ps=con.prepareStatement(sql.toString());
		// for (int i = 0; i < ids.length; i++) {
		// ps.setInt(1, ids[i]);
		// }
		//				
		// ps.executeUpdate();
		//				
		// } catch (Exception e) {
		// throw new RuntimeException(e);
		// } finally {
		// JdbcUtil.close(ps);
		// }
	}

	@Override
	public boolean emailIsRegist(String email) {
		try {
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from member where email=?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {

				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			JdbcUtil.close(ps);
		}
		return false;
	}

	@Override
	public void insert(Member mem) {

		try {
			member = new Member();
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into member (email,password,regist_time,name,telephone,");
			sql.append("cellphone,account_type,qq,com_name,com_homepage,com_address,zipcode,");
			sql.append("account_attr,invoice_able,get_money_mode,open_bank,account_number,");
			sql.append("open_name,address_bank,zipcode_bank,ad_balance,income_balance,state,");
			sql.append("ser_num,pay_mode) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			mem.setState("2");
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
			/** 企业网址 */
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
			ps.setString(25, mem.getPay_mode());
			ps.executeUpdate();
			// 取得自动生成的字段的值
			rs = ps.getGeneratedKeys();
			rs.next();
			mem.setId(rs.getInt(1));

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
		}

	}

	@Override
	public Member selectByEmailAndPassword(String email, String password) {
		member = new Member();

		try {
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from member where email=? and password=?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
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
				member.setPay_mode(rs.getString(26));
				return member;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
		}

		return null;
	}

	@Override
	public Member selectById(int id) {

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
			member.setPay_mode(rs.getString(26));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, st);
		}
		return member;
	}

	@Override
	public void update(Member mem) {
		try {
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update member set email=?,password=?,regist_time=?,name=?,telephone=?,");
			sql.append("cellphone=?,account_type=?,qq=?,com_name=?,com_homepage=?,com_address=?,zipcode=?,");
			sql.append("account_attr=?,invoice_able=?,get_money_mode=?,open_bank=?,account_number=?,");
			sql.append("open_name=?,address_bank=?,zipcode_bank=?,ad_balance=?,income_balance=?,state=?,");
			sql.append("ser_num=?,pay_mode=? where id=?");

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
			ps.setString(25, mem.getPay_mode());
			ps.setInt(26, mem.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps);
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

	// DoubleUtil.round(, 4)
	@Override
	public Member selectByEmail(String email) {
		try {
			member = new Member();
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from member where email=?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
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
				member.setPay_mode(rs.getString(26));
				return member;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, st);
		}
		return null;
	}

	@Override
	public Vector<Member> selectAllMembers() {
		Member member = null;
		Vector<Member> memberList = null;
		try {
			con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from member ");
			ps = con.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			memberList = new Vector<Member>();
			while (rs.next()) {
				member = new Member();
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
				member.setPay_mode(rs.getString(26));
				memberList.add(member);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, st);
		}
		return memberList;
	}

	@Override
	public void updateMoney(Vector<Member> mems) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update member set ad_balance=?,income_balance=? where id=?");

			ps = con.prepareStatement(sql.toString());

			for (Member mem : mems) {
				int index = 1;
				ps.setDouble(index++, DoubleUtil.round(mem.getAd_balance(), 4));
				ps.setDouble(index++, DoubleUtil.round(mem.getIncome_balance(), 4));
				ps.setInt(index++, mem.getId());

				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}

	}

}
