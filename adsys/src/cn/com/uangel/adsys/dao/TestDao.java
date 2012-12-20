package cn.com.uangel.adsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADArs;
import cn.com.uangel.adsys.entity.MSBook;
import cn.com.uangel.adsys.entity.MSHanatourPushInfo;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.JdbcUtil;

public class TestDao {
	public void insertAd(AD ad) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into ad (name) values (?)");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, ad.getName());

			ps.executeUpdate();

			// 取得自动生成的字段的值
			rs = ps.getGeneratedKeys();
			rs.next();
			ad.setId(rs.getInt(1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	public void insertAdArs(ADArs adArs) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into ad_ars (ad_id,ars_name) values (?,?)");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, adArs.getAd_id());
			ps.setString(index++, adArs.getArs_name());

			ps.executeUpdate();

			// 取得自动生成的字段的值
			rs = ps.getGeneratedKeys();
			rs.next();
			adArs.setId(rs.getInt(1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	
	public List<MSBook> selectAllMSBook(String tableName){
		return selectAllMSBook(0, 0, tableName);
	}
	
	public List<MSBook> selectAllMSBook(int folderID, int levelID, String tableName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MSBook> books = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			// sql.append("SELECT * FROM ms_book WHERE b_is_folder=? AND b_folder_level=?");
			sql.append("SELECT * FROM " + tableName + " order by seq desc, seq_sub");

			ps = con.prepareStatement(sql.toString());

//			ps.setInt(1, folderID);
//			ps.setInt(2, levelID);

			rs = ps.executeQuery();

			books = new ArrayList<MSBook>();
			while (rs.next()) {
				MSBook book = new MSBook();
				int index = 1;
				book.setId(rs.getInt(index++));
				book.setSeq(rs.getInt(index++));
				book.setSeq_sub(rs.getInt(index++));
				book.setSkid(rs.getString(index++));
				book.setName(rs.getString(index++));
				book.setTitle(rs.getString(index++));
				book.setB_title_name(rs.getString(index++));
				book.setCover_l(rs.getString(index++));
				book.setCoverLName(rs.getString(index++));
				book.setCover_c(rs.getString(index++));
				book.setCoverCName(rs.getString(index++));
				book.setCover_r(rs.getString(index++));
				book.setCoverRName(rs.getString(index++));
				book.setCover_main(rs.getString(index++));
				book.setCoverMName(rs.getString(index++));
				book.setBody(rs.getString(index++));
				book.setB_body_name(rs.getString(index++));
				book.setBodySize(rs.getInt(index++));
				book.setB_body_folder(rs.getString(index++));
				book.setB_is_hidden(rs.getInt(index++));
				book.setCTime(rs.getString(index++));
				book.setMTime(rs.getString(index++));
				book.setB_d_time(rs.getString(index++));
				book.setB_desc(rs.getString(index++));
				book.setB_page(rs.getInt(index++));
				book.setB_is_downloaded(rs.getInt(index++));
				book.setB_is_folder(rs.getInt(index++));
				book.setB_folder_id(rs.getInt(index++));
				
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return books;
	}
	
	public MSBook selectMSBookBySerialCode(int folderID, int levelID, String tableName, String serialCode) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		MSBook book = null;
		
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			// sql.append("SELECT * FROM ms_book WHERE b_is_folder=? AND b_folder_level=?");
			sql.append("SELECT * FROM " + tableName + " WHERE b_serial_code = ?");

			ps = con.prepareStatement(sql.toString());

			ps.setString(1, serialCode);

			rs = ps.executeQuery();

			while (rs.next()) {
				book = new MSBook();
				int index = 1;
				book.setId(rs.getInt(index++));
				book.setSeq(rs.getInt(index++));
				book.setSeq_sub(rs.getInt(index++));
				book.setSkid(rs.getString(index++));
				book.setName(rs.getString(index++));
				book.setTitle(rs.getString(index++));
				book.setB_title_name(rs.getString(index++));
				book.setCover_l(rs.getString(index++));
				book.setCoverLName(rs.getString(index++));
				book.setCover_c(rs.getString(index++));
				book.setCoverCName(rs.getString(index++));
				book.setCover_r(rs.getString(index++));
				book.setCoverRName(rs.getString(index++));
				book.setCover_main(rs.getString(index++));
				book.setCoverMName(rs.getString(index++));
				book.setBody(rs.getString(index++));
				book.setB_body_name(rs.getString(index++));
				book.setBodySize(rs.getInt(index++));
				book.setB_body_folder(rs.getString(index++));
				book.setB_is_hidden(rs.getInt(index++));
				book.setCTime(rs.getString(index++));
				book.setMTime(rs.getString(index++));
				book.setB_d_time(rs.getString(index++));
				book.setB_desc(rs.getString(index++));
				book.setB_page(rs.getInt(index++));
				book.setB_is_downloaded(rs.getInt(index++));
				book.setB_is_folder(rs.getInt(index++));
				book.setB_folder_id(rs.getInt(index++));
				book.setB_serial_code(rs.getString(index++));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return book;
	}
	
	public void insertTokenInfo(String token, String deviceType) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into magazine_hanatour_push (token,device_type) ");
			sql.append("values (?,?)");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, token);
			ps.setString(index++, deviceType);

			ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	
	public void insertChurchTokenInfo(String token, String deviceType) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into magazine_church_push (token,device_type) ");
			sql.append("values (?,?)");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, token);
			ps.setString(index++, deviceType);

			ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	
	public boolean hanatourTokenIsExist(String token) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from magazine_hanatour_push where token=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, token);

			rs = ps.executeQuery();
			
			rs.next();
			int count = rs.getInt(1);
			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	
	public boolean churchTokenIsExist(String token) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select count(*) from magazine_church_push where token=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, token);

			rs = ps.executeQuery();
			
			rs.next();
			int count = rs.getInt(1);
			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}
	
	public List<MSHanatourPushInfo> selectAllTokenInfo() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MSHanatourPushInfo> tokenInfos = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM magazine_hanatour_push");

			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			tokenInfos = new ArrayList<MSHanatourPushInfo>();
			while (rs.next()) {
				MSHanatourPushInfo tokenInfo = new MSHanatourPushInfo();
				int index = 1;
				tokenInfo.setId(rs.getInt(index++));
				tokenInfo.setToken(rs.getString(index++));
				tokenInfo.setDevice_type(rs.getString(index++));
				tokenInfo.setCurrent_book_count(rs.getInt(index++));
				
				tokenInfos.add(tokenInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return tokenInfos;
	}
	
	public List<MSHanatourPushInfo> selectAllTokenInfoByTableName(String tableName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MSHanatourPushInfo> tokenInfos = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM " + tableName);

			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();

			tokenInfos = new ArrayList<MSHanatourPushInfo>();
			while (rs.next()) {
				MSHanatourPushInfo tokenInfo = new MSHanatourPushInfo();
				int index = 1;
				tokenInfo.setId(rs.getInt(index++));
				tokenInfo.setToken(rs.getString(index++));
				tokenInfo.setDevice_type(rs.getString(index++));
				tokenInfo.setCurrent_book_count(rs.getInt(index++));
				
				tokenInfos.add(tokenInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return tokenInfos;
	}
}
