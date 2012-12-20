package cn.com.uangel.adsys.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.com.uangel.adsys.dao.AdCheckDao;
import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.DoubleUtil;
import cn.com.uangel.adsys.util.JdbcUtil;

public class AdCheckDaoImpl implements AdCheckDao{

	public AD selectAdById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		AD ad = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "select * from ad where id=?";

			// logger.debug(sql);

			ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			rs.next();
			ad = new AD();
			int index = 1;
			ad.setId(rs.getInt(index++));
			ad.setName(rs.getString(index++));
			ad.setStart_date(rs.getDate(index++));
			ad.setEnd_date(rs.getDate(index++));
			ad.setType(rs.getString(index++));
			ad.setMax_pay_perday(DoubleUtil.round(rs.getDouble(index++), 4));
			ad.setDescription(rs.getString(index++));
			ad.setApp_platform(rs.getString(index++));
			ad.setShow_type(rs.getString(index++));
			ad.setShow_src(rs.getString(index++));
			ad.setImg_word_type(rs.getString(index++));
			ad.setTitle(rs.getString(index++));
			ad.setImg_path(rs.getString(index++));
			ad.setPay_mode(rs.getString(index++));
			ad.setMem_id(rs.getInt(index++));
			ad.setState(rs.getString(index++));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return ad;
	}

	public List<AD> selectAdToShow(String state) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		AD ad = null;

		List<AD> listAd=null;
		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "select * from ad where state=?";

			// logger.debug(sql);

			ps = con.prepareStatement(sql);

			ps.setString(1, state);

			rs = ps.executeQuery();

			listAd=new ArrayList<AD>();
			while(rs.next()){
				ad = new AD();
				int index = 1;
				ad.setId(rs.getInt(index++));
				ad.setName(rs.getString(index++));
				ad.setStart_date(rs.getDate(index++));
				ad.setEnd_date(rs.getDate(index++));
				ad.setType(rs.getString(index++));
				ad.setMax_pay_perday(DoubleUtil.round(rs.getDouble(index++), 4));
				ad.setDescription(rs.getString(index++));
				ad.setApp_platform(rs.getString(index++));
				ad.setShow_type(rs.getString(index++));
				ad.setShow_src(rs.getString(index++));
				ad.setImg_word_type(rs.getString(index++));
				ad.setTitle(rs.getString(index++));
				ad.setImg_path(rs.getString(index++));
				ad.setPay_mode(rs.getString(index++));
				ad.setMem_id(rs.getInt(index++));
				ad.setState(rs.getString(index++));
				listAd.add(ad);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		
		return listAd;
	}

	public void updateAd(AD ad) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update ad set name=?,start_date=?,end_date=?,type=?,");
			if (ad.getMax_pay_perday() != null) {
				sql.append("max_pay_perday=?,");
			}
			sql.append("description=?,app_platform=?,show_type=?,show_src=?,img_word_type=?,");
			sql.append("title=?,img_path=?,pay_mode=?,mem_id=?,state=? where id=?");
			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, ad.getName());
			ps.setDate(index++, getSQLDate(ad.getStart_date()));
			ps.setDate(index++, getSQLDate(ad.getEnd_date()));
			ps.setString(index++, ad.getType());
			if (ad.getMax_pay_perday() != null) {
				ps.setDouble(index++, DoubleUtil.round(ad.getMax_pay_perday(), 4));
			}
			ps.setString(index++, ad.getDescription());
			ps.setString(index++, ad.getApp_platform());
			ps.setString(index++, ad.getShow_type());
			ps.setString(index++, ad.getShow_src());
			ps.setString(index++, ad.getImg_word_type());
			ps.setString(index++, ad.getTitle());
			ps.setString(index++, ad.getImg_path());
			ps.setString(index++, ad.getPay_mode());
			ps.setInt(index++, ad.getMem_id());
			ps.setString(index++, ad.getState());
			ps.setInt(index++, ad.getId());

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
	public List<ADShowType> selectADShowTypesByADID(int adId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ADShowType> showTypes = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM ad_show_type WHERE ad_id=?");

			ps = con.prepareStatement(sql.toString());

			ps.setInt(1, adId);

			rs = ps.executeQuery();

			showTypes = new ArrayList<ADShowType>();
			while (rs.next()) {
				ADShowType ast = new ADShowType();
				int index = 1;
				ast.setId(rs.getInt(index++));
				ast.setAd_id(rs.getInt(index++));
				ast.setShow_type_name(rs.getString(index++));
				ast.setTitle(rs.getString(index++));
				ast.setAd_info_url(rs.getString(index++));
				ast.setClick_effect(rs.getString(index++));
				ast.setAd_detail(rs.getString(index++));
				ast.setClick_url(rs.getString(index++));
				ast.setPay_mode(rs.getString(index++));
				ast.setPrice(DoubleUtil.round(rs.getDouble(index++), 4));
				showTypes.add(ast);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return showTypes;
	}

	@Override
	public void updateAdsByMem_id(int mem_id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ad set state='运行中' WHERE mem_id=? AND state='暂停' ");
			
			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, mem_id);
			
			
			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		
	}

	

}
