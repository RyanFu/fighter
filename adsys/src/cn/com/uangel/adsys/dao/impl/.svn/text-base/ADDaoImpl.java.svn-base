package cn.com.uangel.adsys.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import cn.com.uangel.adsys.dao.ADDao;
import cn.com.uangel.adsys.entity.AD;
import cn.com.uangel.adsys.entity.ADArs;
import cn.com.uangel.adsys.entity.ADPhoneType;
import cn.com.uangel.adsys.entity.ADShowInfo;
import cn.com.uangel.adsys.entity.ADShowType;
import cn.com.uangel.adsys.entity.ADZone;
import cn.com.uangel.adsys.entity.AdStatInfo;
import cn.com.uangel.adsys.entity.AdTypePrice;
import cn.com.uangel.adsys.entity.ClickInfo;
import cn.com.uangel.adsys.entity.RequestInfo;
import cn.com.uangel.adsys.entity.StatisticsByCrowd;
import cn.com.uangel.adsys.entity.StatisticsByGeography;
import cn.com.uangel.adsys.entity.StatisticsByTimeInterval;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.DoubleUtil;
import cn.com.uangel.adsys.util.JdbcUtil;

public class ADDaoImpl implements ADDao {

	@Override
	public void deleteById(int id) {

	}

	@Override
	public void insert(AD ad) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into ad (name,start_date,end_date,type,");
			if (ad.getMax_pay_perday() != null) {
				sql.append("max_pay_perday,");
			}
			sql.append("description,app_platform,");
			sql.append("show_type,show_src,img_word_type,title,img_path,pay_mode,mem_id,state) ");
			sql.append("values (?,?,?,");
			if (ad.getMax_pay_perday() != null) {
				sql.append("?,");
			}
			sql.append("?,?,?,?,?,?,?,?,?,?,?)");

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

	@Override
	public List<AD> selectADsByMemberId(AD condition, int start, int rows) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AD> adList = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "SELECT a.*,coalesce(b.`展示次数`,0) AS 展示次数, ";
			sql += "coalesce(c.`点击次数`,0) AS 点击次数, ";
			sql += "coalesce(coalesce(c.`点击次数`,0) / coalesce(b.`展示次数`,0),0) * 100 AS 点击率, ";
			sql += "coalesce(e.`金额`,0) AS 金额 ";
			sql += "FROM ad a ";
			sql += "LEFT JOIN ";
			sql += "(SELECT ad_id,COUNT(id) AS 展示次数 ";
			sql += "FROM adshow_info ";
			sql += "GROUP BY ad_id) b ";
			sql += "ON a.id=b.ad_id ";
			sql += "LEFT JOIN ";
			sql += "(SELECT ad_id,COUNT(id) AS 点击次数 ";
			sql += "FROM click_info ";
			sql += "GROUP BY ad_id) c ";
			sql += "ON a.id=c.ad_id ";
			sql += "LEFT JOIN ";
			sql += "(SELECT aa.id,SUM(bb.cur_cpm)/1000 AS 金额 ";
			sql += "FROM ad aa ";
			sql += "LEFT JOIN adshow_info bb ";
			sql += "ON aa.id=bb.ad_id ";
			sql += "WHERE aa.pay_mode='按印象付费' ";
			sql += "GROUP BY aa.id ";
			sql += "UNION ";
			sql += "SELECT aa.id,SUM(bb.cur_cpc) AS 金额 ";
			sql += "FROM ad aa ";
			sql += "LEFT JOIN click_info bb ";
			sql += "ON aa.id=bb.ad_id ";
			sql += "WHERE aa.pay_mode='按单次点击付费' ";
			sql += "GROUP BY aa.id) e ";
			sql += "ON a.id=e.id ";
			sql += "WHERE a.mem_id=? ";
			if (condition.getState() != null) {
				if (condition.getState().equals("可用")) {
					sql += " AND ( state='运行中' OR state='暂停' ) ";
				} else {
					sql += "AND a.state=? ";
				}
			}

			// System.out.println(sql + " args: " + condition.getId() + ":" +
			// condition.getState());

			ps = con.prepareStatement(sql);

			int psIndex = 1;
			ps.setInt(psIndex++, condition.getMem_id());
			if (condition.getState() != null) {
				if (!condition.getState().equals("可用")) {
					ps.setString(psIndex++, condition.getState());
				}
			}

			rs = ps.executeQuery();

			adList = new ArrayList<AD>();
			while (rs.next()) {
				AD ad = new AD();
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
				ad.setPut_gender(rs.getString(index++));
				ad.setPut_crowd(rs.getString(index++));
				ad.setPut_time(rs.getString(index++));
				ad.setZoneType(rs.getString(index++));
				ad.setShowTimes(rs.getInt(index++));
				ad.setClickTimes(rs.getInt(index++));
				ad.setClickRate(DoubleUtil.round(rs.getDouble(index++), 2));
				ad.setCost(DoubleUtil.round(rs.getDouble(index++), 4));
				adList.add(ad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return adList;
	}

	@Override
	public AD selectById(int id) {
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
			ad.setPut_gender(rs.getString(index++));
			ad.setPut_crowd(rs.getString(index++));
			ad.setPut_time(rs.getString(index++));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return ad;
	}

	@Override
	public void insertArs(int adId, String[] ars) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into ad_ars (ad_id,ars_name) values (?,?)");

			ps = con.prepareStatement(sql.toString());

			for (int i = 0; ars != null && i < ars.length; i++) {
				int index = 1;
				ps.setInt(index++, adId);
				ps.setString(index++, ars[i]);

				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void insertPhoneTypes(int adId, String[] phoneTypes) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into ad_phonetype (ad_id,phone_name) values (?,?)");

			ps = con.prepareStatement(sql.toString());

			for (int i = 0; phoneTypes != null && i < phoneTypes.length; i++) {
				int index = 1;
				ps.setInt(index++, adId);
				ps.setString(index++, phoneTypes[i]);

				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void update(AD ad) {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
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

			StringBuilder adShowTypeSql = new StringBuilder();
			adShowTypeSql
					.append("insert into ad_show_type (ad_id,show_type_name,title,ad_info_url,click_effect,ad_detail,");
			adShowTypeSql.append("click_url,pay_mode,price) values (?,?,?,?,?,?,?,?,?)");

			ps2 = con.prepareStatement(adShowTypeSql.toString());

			List<ADShowType> adShowTypes = ad.getShowTypes();

			for (int i = 0; adShowTypes != null && i < adShowTypes.size(); i++) {
				ADShowType ast = adShowTypes.get(i);
				int j = 1;
				ps2.setInt(j++, ad.getId());
				ps2.setString(j++, ast.getShow_type_name());
				ps2.setString(j++, ast.getTitle());
				ps2.setString(j++, ast.getAd_info_url());
				ps2.setString(j++, ast.getClick_effect());
				ps2.setString(j++, ast.getAd_detail());
				ps2.setString(j++, ast.getClick_url());
				ps2.setString(j++, ast.getPay_mode());
				ps2.setDouble(j++, ast.getPrice());

				ps2.addBatch();
			}

			ps2.executeBatch();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps2);
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void deleteAllArsByAdId(int adId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from ad_ars where ad_id=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, adId);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void deleteAllPhoneTypesByAdId(int adId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from ad_phonetype where ad_id=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, adId);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public List<AdStatInfo> selectStatInfo(int[] appIds, java.util.Date startDate, java.util.Date endDate,
			String statWay) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		// appIds=new int[]{1,2,3};
		List<AdStatInfo> statList = null;
		String dateFormat = "01".equals(statWay) ? "%Y-%m-%d" : "%Y-%m";

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "SELECT a.`日期`,coalesce(b.`展示次数`,0) AS 展示次数, ";
			sql += "coalesce(c.`点击次数`,0) AS 点击次数, ";
			sql += "coalesce(coalesce(c.`点击次数`,0)/coalesce(b.`展示次数`,0),0)*100 AS 点击率, ";
			sql += "coalesce(b.`CPM佣金`,0) AS CPM佣金, ";
			sql += "coalesce(c.`CPC佣金`,0) AS CPC佣金 ";
			sql += " FROM ";
			sql += "(SELECT DATE_FORMAT(request_time,'" + dateFormat + "') AS 日期,COUNT(id) AS 请求次数 ";
			sql += "FROM request_info ";
			sql += "WHERE ad_id IN (?";
			for (int i = 1; i < appIds.length; i++) {
				sql += ",?";
			}
			sql += ") ";
			if (startDate != null) {
				sql += " AND request_time > ? ";
			}
			if (endDate != null) {
				sql += " AND request_time < ? ";
			}
			sql += "GROUP BY DATE_FORMAT(request_time,'" + dateFormat + "') ) a ";
			sql += "LEFT JOIN ";
			sql += "(SELECT DATE_FORMAT(show_time,'" + dateFormat + "') AS 日期,COUNT(id) AS 展示次数, ";
			sql += "COALESCE(SUM(cur_cpm),0) AS 'CPM佣金' ";
			sql += "FROM adshow_info ";
			sql += "WHERE ad_id IN (?";
			for (int i = 1; i < appIds.length; i++) {
				sql += ",?";
			}
			sql += ") ";
			if (startDate != null) {
				sql += " AND show_time > ? ";
			}
			if (endDate != null) {
				sql += " AND show_time < ? ";
			}
			sql += "GROUP BY DATE_FORMAT(show_time,'" + dateFormat + "') ) b ";
			sql += "ON a.`日期`=b.`日期` ";
			sql += "LEFT JOIN ";
			sql += "(SELECT DATE_FORMAT(click_time,'" + dateFormat + "') AS 日期,COUNT(id) AS 点击次数, ";
			sql += "COALESCE(SUM(cur_cpc),0) AS 'CPC佣金' ";
			sql += "FROM click_info ";
			sql += "WHERE ad_id IN (?";
			for (int i = 1; i < appIds.length; i++) {
				sql += ",?";
			}
			sql += ") ";
			if (startDate != null) {
				sql += " AND click_time > ? ";
			}
			if (endDate != null) {
				sql += " AND click_time < ? ";
			}
			sql += "GROUP BY DATE_FORMAT(click_time,'" + dateFormat + "') ) c ";
			sql += "ON a.`日期`=c.`日期` ";
			// sql += "LEFT JOIN ";
			// sql += "(SELECT cc.`日期`,coalesce(aa.CPM,0) + coalesce(bb.CPC,0)
			// AS 消费金额 ";
			// sql += "FROM ";
			// sql += "(SELECT DISTINCT DATE_FORMAT(a.request_time,'" +
			// dateFormat + "') AS 日期 ";
			// sql += "FROM request_info a ";
			// sql += "INNER JOIN ad b ";
			// sql += "ON a.ad_id=b.id ";
			// sql += "WHERE b.id IN (?";
			// for (int i = 1; i < appIds.length; i++) {
			// sql += ",?";
			// }
			// sql += ") ";
			// if (startDate != null) {
			// sql += " AND a.request_time > ? ";
			// }
			// if (endDate != null) {
			// sql += " AND a.request_time < ? ";
			// }
			// sql += "GROUP BY DATE_FORMAT(a.request_time,'" + dateFormat +
			// "'),b.pay_mode) cc ";
			// sql += "LEFT JOIN ";
			// sql += "(SELECT DATE_FORMAT(a.show_time,'" + dateFormat + "') AS
			// 日期, ";
			// sql += "SUM(a.cur_cpm)/1000 AS CPM,b.pay_mode ";
			// sql += "FROM adshow_info a ";
			// sql += "INNER JOIN ad b ";
			// sql += "ON a.ad_id=b.id ";
			// sql += "WHERE b.id IN (?";
			// for (int i = 1; i < appIds.length; i++) {
			// sql += ",?";
			// }
			// sql += ") ";
			// if (startDate != null) {
			// sql += " AND a.show_time > ? ";
			// }
			// if (endDate != null) {
			// sql += " AND a.show_time < ? ";
			// }
			// sql += "AND b.pay_mode='按印象付费' ";
			// sql += "GROUP BY DATE_FORMAT(a.show_time,'" + dateFormat +
			// "'),b.pay_mode) aa ";
			// sql += "ON cc.`日期`=aa.`日期` ";
			// sql += "LEFT JOIN ";
			// sql += "(SELECT DATE_FORMAT(a.click_time,'" + dateFormat + "') AS
			// 日期, ";
			// sql += "SUM(a.cur_cpc) AS CPC,b.pay_mode ";
			// sql += "FROM click_info a ";
			// sql += "INNER JOIN ad b ";
			// sql += "ON a.ad_id=b.id ";
			// sql += "WHERE b.id IN (?";
			// for (int i = 1; i < appIds.length; i++) {
			// sql += ",?";
			// }
			// sql += ") ";
			// if (startDate != null) {
			// sql += " AND a.click_time > ? ";
			// }
			// if (endDate != null) {
			// sql += " AND a.click_time < ? ";
			// }
			// sql += "AND b.pay_mode='按单次点击付费' ";
			// sql += "GROUP BY DATE_FORMAT(a.click_time,'" + dateFormat +
			// "'),b.pay_mode) bb ";
			// sql += "ON cc.`日期`=bb.`日期` ) d ";
			// sql += "ON a.`日期`=d.`日期` ";

			ps = con.prepareStatement(sql);

			int psIndex = 1;
			for (int j = 0; j < 3; j++) {
				ps.setInt(psIndex++, appIds[0]);
				for (int i = 1; i < appIds.length; i++) {
					ps.setInt(psIndex++, appIds[i]);
				}
				if (startDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(startDate));
				}
				if (endDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(endDate));
				}
			}

			rs = ps.executeQuery();

			statList = new ArrayList<AdStatInfo>();
			while (rs.next()) {
				AdStatInfo stat = new AdStatInfo();
				int index = 1;
				stat.setDate(rs.getString(index++));
				stat.setShow_times(rs.getInt(index++));
				stat.setClick_times(rs.getInt(index++));
				stat.setClick_rate(DoubleUtil.round(rs.getDouble(index++), 2));
				stat.setCpmPay(DoubleUtil.round(rs.getDouble(index++), 4));
				stat.setCpcPay(DoubleUtil.round(rs.getDouble(index++), 4));
				statList.add(stat);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return statList;
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

	@Override
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
			ps.setDouble(index++, adType.getCur_money());
			ps.setString(index++, adType.getIs_newest());
			ps.setDate(index++, getSQLDate(adType.getChange_time()));
			ps.executeUpdate();

			// 取得自动生成的字段的值
			rs = ps.getGeneratedKeys();
			rs.next();
			adType.setId(rs.getInt(1));

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public AdTypePrice selectAdTypePriceById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		AdTypePrice adTypePrice = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM adtype_price WHERE id=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setInt(indexset++, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				adTypePrice = new AdTypePrice();
				adTypePrice.setId(rs.getInt(index++));
				adTypePrice.setAd_type(rs.getString(index++));
				adTypePrice.setCur_money(rs.getDouble(index++));
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

	@Override
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
			ps.setDouble(index++, adType.getCur_money());
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

	@Override
	public AdTypePrice deleteAdTypePriceByIsNewest(String isNewest) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		AdTypePrice adTypePrice = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM adtype_price WHERE is_newest=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setString(indexset++, isNewest);

			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				adTypePrice = new AdTypePrice();
				adTypePrice.setId(rs.getInt(index++));
				adTypePrice.setAd_type(rs.getString(index++));
				adTypePrice.setCur_money(rs.getDouble(index++));
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

	@Override
	public AdTypePrice selectAdTypePriceByType(String type) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		AdTypePrice adTypePrice = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM adtype_price WHERE ad_type=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setString(indexset++, type);

			rs = ps.executeQuery();
			while (rs.next()) {
				int index = 1;
				adTypePrice = new AdTypePrice();
				adTypePrice.setId(rs.getInt(index++));
				adTypePrice.setAd_type(rs.getString(index++));
				adTypePrice.setCur_money(DoubleUtil.round(rs.getDouble(index++), 4));
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

	@Override
	public AD choseOneOKAD(String ars) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<AD> okADs = new ArrayList<AD>();
		AD okAD = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql
					.append("SELECT a.id,a.show_type,a.img_word_type,a.show_src,a.title,a.img_path,a.pay_mode,a.mem_id FROM ad a ");
			sql.append("INNER JOIN member b ");
			sql.append("ON a.mem_id = b.id ");
			sql.append("LEFT JOIN( ");
			sql.append("SELECT b.pay_mode,a.ad_id,SUM(a.cur_cpm/1000) AS 金额,DATE_FORMAT(a.show_time,'%Y-%m-%d') ");
			sql.append("FROM adshow_info a ");
			sql.append("INNER JOIN ad b ");
			sql.append("ON a.ad_id=b.id ");
			sql.append("WHERE b.pay_mode='按印象付费' ");
			sql.append("AND DATE_FORMAT(a.show_time,'%Y-%m-%d')=CURDATE() ");
			sql.append("GROUP BY a.ad_id, DATE_FORMAT(a.show_time,'%Y-%m-%d') ");
			sql.append("UNION ");
			sql.append("SELECT b.pay_mode,a.ad_id,SUM(a.cur_cpc) AS 金额,DATE_FORMAT(a.click_time,'%Y-%m-%d') ");
			sql.append("FROM click_info a ");
			sql.append("INNER JOIN ad b ");
			sql.append("ON a.ad_id=b.id ");
			sql.append("WHERE b.pay_mode='按单次点击付费' ");
			sql.append("AND DATE_FORMAT(a.click_time,'%Y-%m-%d')=CURDATE() ");
			sql.append("GROUP BY a.ad_id,DATE_FORMAT(a.click_time,'%Y-%m-%d') ) c ");
			sql.append("ON a.id=c.ad_id ");
			sql.append("WHERE a.state='运行中' ");
			sql.append("AND a.id IN (SELECT ad_id FROM ad_ars WHERE ars_name=?) ");
			sql.append("AND b.ad_balance > 0.5 AND CURDATE() < end_date ");
			sql.append("AND (coalesce(c.`金额`,0)<coalesce(a.max_pay_perday,100000000) OR a.max_pay_perday=0) "); // 如果每日最高消费为null
			// ，
			// 或者为0
			// ，
			// 当做1亿
			// ，
			// 应该够用了

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setString(indexset++, ars);

			rs = ps.executeQuery();
			while (rs.next()) {
				AD ad = new AD();
				ad.setId(rs.getInt("id"));
				ad.setShow_type(rs.getString("show_type"));
				ad.setImg_word_type(rs.getString("img_word_type"));
				ad.setShow_src(rs.getString("show_src"));
				ad.setTitle(rs.getString("title"));
				ad.setImg_path(rs.getString("img_path"));
				ad.setPay_mode(rs.getString("pay_mode"));
				ad.setMem_id(rs.getInt("mem_id"));
				okADs.add(ad);
			}

			if (okADs.size() > 0) {
				okAD = okADs.get(new Random().nextInt(okADs.size()));
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return okAD;
	}

	// 一小时120次以内true，> 120 false(暂定)
	@Override
	public boolean testImeiShowTooMuch(String imei, String appID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM adshow_info WHERE show_time > ? ").append(
					" AND show_time < ? AND imei=? AND app_id=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			java.util.Date nowTime = new java.util.Date();
			ps.setTimestamp(indexset++, getSQLTimestamp(new java.util.Date(nowTime.getTime() - 1000 * 60 * 60)));
			ps.setTimestamp(indexset++, getSQLTimestamp(new java.util.Date(nowTime.getTime())));
			ps.setString(indexset++, imei);
			ps.setString(indexset++, appID);

			rs = ps.executeQuery();
			rs.next();

			int showCount = rs.getInt(1);
			System.out.println("showCount: " + showCount);
			if (showCount >= 120) {
				return true;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return false;
	}

	@Override
	public boolean testImeiClickTooMuch(String imei, String appID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM click_info WHERE click_time > ? ").append(
					" AND click_time < ? AND imei=? AND app_id=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			java.util.Date nowTime = new java.util.Date();
			ps.setTimestamp(indexset++, getSQLTimestamp(new java.util.Date(nowTime.getTime() - 1000 * 60 * 60)));
			ps.setTimestamp(indexset++, getSQLTimestamp(new java.util.Date(nowTime.getTime())));
			ps.setString(indexset++, imei);
			ps.setString(indexset++, appID);

			rs = ps.executeQuery();
			rs.next();

			int showCount = rs.getInt(1);
			System.out.println("clickCount: " + showCount);
			if (showCount >= 20) {
				return true;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return false;
	}

	@Override
	public boolean testAppIDExist(String appID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM app WHERE pid=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setString(indexset, appID);

			rs = ps.executeQuery();
			rs.next();

			int count = rs.getInt(1);
			if (count > 0) {
				return true;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return false;
	}

	@Override
	public void insertRequestInfo(String appID, int adID, String imei) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO request_info (app_id,ad_id,request_time,imei) VALUES (?,?,?,?)");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, appID);
			ps.setInt(index++, adID);
			ps.setTimestamp(index++, getSQLTimestamp(new java.util.Date()));
			ps.setString(index++, imei);
			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void insertShowInfo(String appID, int adID, double cur_cpm, String imei) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO adshow_info (app_id,ad_id,show_time,cur_cpm,imei) VALUES (?,?,?,?,?)");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, appID);
			ps.setInt(index++, adID);
			ps.setTimestamp(index++, getSQLTimestamp(new java.util.Date()));
			ps.setDouble(index++, cur_cpm);
			ps.setString(index++, imei);
			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void insertClickInfo(String appID, int adID, double cur_cpc, String imei) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO click_info (app_id,ad_id,click_time,cur_cpc,imei) VALUES (?,?,?,?,?)");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, appID);
			ps.setInt(index++, adID);
			ps.setTimestamp(index++, getSQLTimestamp(new java.util.Date()));
			ps.setDouble(index++, cur_cpc);
			ps.setString(index++, imei);
			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void updateADsState(int[] ids, String newState) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ad SET state=? WHERE id=?");

			ps = con.prepareStatement(sql.toString());

			for (int i = 0; ids != null && i < ids.length; i++) {
				int index = 1;
				ps.setString(index++, newState);
				ps.setInt(index++, ids[i]);

				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public boolean testAppCouldDelete(String appID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT count(*) FROM request_info WHERE app_id=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setString(indexset++, appID);

			rs = ps.executeQuery();
			rs.next();

			int showCount = rs.getInt(1);
			if (showCount == 0) {
				return true;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return false;
	}

	@Override
	public boolean testADBalance(int memID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ad_balance FROM member WHERE id=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setInt(indexset++, memID);

			rs = ps.executeQuery();
			rs.next();

			double ad_balance = rs.getDouble(1);
			if (ad_balance < 0.5) {
				return true;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return false;
	}

	@Override
	public int selectCurShowCount() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int showCount = 0;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) from adshow_info");

			ps = con.prepareStatement(sql.toString());

			rs = ps.executeQuery();
			rs.next();

			showCount = rs.getInt(1);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return showCount;
	}

	@Override
	public void deleteAllProvincesByAdId(int adId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from ad_zone where ad_id=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, adId);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void insertProvinces(int adId, String[] provinces) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			if (provinces != null && provinces[0].equals("-1")) {
				sql.append("insert into ad_zone (ad_id,zone_name,zone_code) values (?,?,?)");
				ps = con.prepareStatement(sql.toString());
				ps.setInt(1, adId);
				ps.setString(2, provinces[1] + ":" + provinces[2] + ":" + provinces[3]);
				ps.setString(3, "-1");
				ps.executeUpdate();
			} else {
				sql.append("insert into ad_zone (ad_id,zone_code) values (?,?)");

				ps = con.prepareStatement(sql.toString());

				for (int i = 0; provinces != null && i < provinces.length; i++) {
					int index = 1;
					ps.setInt(index++, adId);
					ps.setString(index++, provinces[i]);

					ps.addBatch();
				}

				ps.executeBatch();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void updateAdPutWayInfo(int adId, String crowd, String gender, String time) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE ad SET put_gender=?,put_crowd=?,put_time=? WHERE id=?");

			ps = con.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, gender);
			ps.setString(index++, crowd);
			ps.setString(index++, time);
			ps.setInt(index++, adId);
			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public String[] selectArsByAdId(int adId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String[] ars = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ars_name FROM ad_ars WHERE ad_id=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setInt(indexset++, adId);

			rs = ps.executeQuery();

			List<String> temp = new ArrayList<String>();
			while (rs.next()) {
				temp.add(rs.getString(1));
			}
			ars = new String[temp.size()];
			for (int i = 0; i < temp.size(); i++) {
				ars[i] = temp.get(i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return ars;
	}

	@Override
	public String[] selectPhoneTypesByAdId(int adId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String[] phoneTypes = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT phone_name FROM ad_phonetype WHERE ad_id=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setInt(indexset++, adId);

			rs = ps.executeQuery();

			List<String> temp = new ArrayList<String>();
			while (rs.next()) {
				temp.add(rs.getString(1));
			}
			phoneTypes = new String[temp.size()];
			for (int i = 0; i < temp.size(); i++) {
				phoneTypes[i] = temp.get(i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return phoneTypes;
	}

	@Override
	public String[] selectZonesByAdId(int adId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String[] zones = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT zone_code,zone_name FROM ad_zone WHERE ad_id=?");

			ps = con.prepareStatement(sql.toString());

			int indexset = 1;
			ps.setInt(indexset++, adId);

			rs = ps.executeQuery();

			List<String> temp = new ArrayList<String>();
			while (rs.next()) {
				String code = rs.getString(1);
				// 判断是否是区域地理位置选择
				if (!"-1".equals(code)) {
					temp.add(code);
				} else {
					temp.add(rs.getString(2));
				}
			}
			zones = new String[temp.size()];
			for (int i = 0; i < temp.size(); i++) {
				zones[i] = temp.get(i);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return zones;
	}

	@Override
	public void deleteAllADShowType(int adId) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("delete from ad_show_type where ad_id=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setInt(index++, adId);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
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
	// public List<StatisticsByCrowd> selectStatisticsByCrowd(int[] appIds,
	// java.util.Date startDate,
	// java.util.Date endDate, String[] userCrowd) {
	// PreparedStatement ps = null;
	// ResultSet rs = null;
	// boolean a = false, b = false, c = false, d = false;
	// appIds=new int[]{1,2,3};
	// List<StatisticsByCrowd> listStatisticsByCrowd = null;
	// try {
	// Connection con = ConnectionProvider.getConnection();
	// StringBuffer sql = new StringBuffer();
	//
	// for (int i = 0; i < userCrowd.length; i++) {
	// if ("1".equals(userCrowd[i])) {
	// a = true;
	// } else if ("2".equals(userCrowd[i])) {
	// b = true;
	// } else if ("3".equals(userCrowd[i])) {
	// c = true;
	// } else if ("4".equals(userCrowd[i])) {
	// d = true;
	// }
	// }
	// System.out.println(a);
	// System.out.println(b);
	// System.out.println(c);
	// System.out.println(d);
	// if (a) {
	// sql.append("(SELECT COALESCE(a.`用户群`,'8') AS 用户群, ");
	// sql.append("COALESCE(b.`展示次数`,'0') AS 展示次数, ");
	// sql.append("COALESCE(c.`点击次数`,'0') AS 点击次数, ");
	// sql.append("COALESCE(COALESCE(c.`点击次数`,0)/COALESCE(b.`展示次数`,0),0)*100 AS
	// '点击率（%）', ");
	// sql.append("COALESCE(b.`CPM消费金额`,'0') AS CPM消费金额, ");
	// sql.append("COALESCE(c.`CPC消费金额`,'0') AS CPC消费金额 ");
	// sql.append("FROM ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'8') AS 用户群, ");
	// sql.append("COUNT(rr.id) AS 请求次数 ");
	// sql.append("FROM ");
	// sql.append("request_info rr ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON rr.app_id=p.pid ");
	//
	// sql.append("WHERE rr.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND rr.request_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND rr.request_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=8 ");
	// sql.append(" ) a ");
	// sql.append("LEFT JOIN ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'8') AS 用户群, ");
	// sql.append("COUNT(ss.id) AS 展示次数 , ");
	// sql.append("SUM(ss.cur_cpm) AS CPM消费金额 ");
	// sql.append("FROM ");
	// sql.append("adshow_info ss ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON ss.app_id=p.pid ");
	//
	// sql.append("WHERE ss.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND ss.show_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND ss.show_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=8) AS b ");
	// sql.append("ON a.`用户群`= b.`用户群` ");
	// sql.append("LEFT JOIN ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'8') AS 用户群, ");
	// sql.append("COALESCE(COUNT(cc.id),'0') AS 点击次数 , ");
	// sql.append("SUM(cc.cur_cpc) AS CPC消费金额 ");
	// sql.append("FROM click_info cc ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON cc.app_id=p.pid ");
	//
	// sql.append("WHERE cc.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND cc.click_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND cc.click_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=8 ) AS c ");
	// sql.append("ON a.`用户群`= c.`用户群`) ");
	// }
	//
	// if (b) {
	// if (a) {
	// sql.append("UNION ALL ");
	// }
	//
	// sql.append("(SELECT COALESCE(a.`用户群`,'4') AS 用户群, ");
	// sql.append("COALESCE(b.`展示次数`,'0') AS 展示次数, ");
	// sql.append("COALESCE(c.`点击次数`,'0') AS 点击次数, ");
	// sql.append("COALESCE(COALESCE(c.`点击次数`,0)/COALESCE(b.`展示次数`,0),0)*100 AS
	// '点击率（%）', ");
	// sql.append("COALESCE(b.`CPM消费金额`,'0') AS CPM消费金额, ");
	// sql.append("COALESCE(c.`CPC消费金额`,'0') AS CPC消费金额 ");
	// sql.append("FROM ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'4') AS 用户群, ");
	// sql.append("COUNT(rr.id) AS 请求次数 ");
	// sql.append("FROM ");
	// sql.append("request_info rr ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON rr.app_id=p.pid ");
	//
	// sql.append("WHERE rr.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND rr.request_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND rr.request_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=4 ");
	// sql.append(" ) a ");
	// sql.append("LEFT JOIN ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'4') AS 用户群, ");
	// sql.append("COUNT(ss.id) AS 展示次数 ,");
	// sql.append("SUM(ss.cur_cpm) AS CPM消费金额 ");
	// sql.append("FROM ");
	// sql.append("adshow_info ss ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON ss.app_id=p.pid ");
	//
	// sql.append("WHERE ss.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND ss.show_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND ss.show_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=4) AS b ");
	// sql.append("ON a.`用户群`= b.`用户群` ");
	// sql.append("LEFT JOIN ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'4') AS 用户群, ");
	// sql.append("COUNT(cc.id) AS 点击次数 , ");
	// sql.append("SUM(cc.cur_cpc) AS CPC消费金额 ");
	// sql.append("FROM click_info cc ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON cc.app_id=p.pid ");
	//
	// sql.append("WHERE cc.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND cc.click_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND cc.click_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=4 ) AS c ");
	// sql.append("ON a.`用户群`= c.`用户群`) ");
	// }
	//
	// if (c) {
	// if (a || b) {
	// sql.append("UNION ALL ");
	// }
	//
	// sql.append("(SELECT COALESCE(a.`用户群`,'2') AS 用户群, ");
	// sql.append("COALESCE(b.`展示次数`,'0') AS 展示次数, ");
	// sql.append("COALESCE(c.`点击次数`,'0') AS 点击次数, ");
	// sql.append("COALESCE(COALESCE(c.`点击次数`,0)/coalesce(b.`展示次数`,0),0)*100 AS
	// 点击率, ");
	// sql.append("COALESCE(b.`CPM消费金额`,'0') AS CPM消费金额, ");
	// sql.append("COALESCE(c.`CPC消费金额`,'0') AS CPC消费金额 ");
	// sql.append("FROM ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'2') AS 用户群, ");
	// sql.append("COUNT(rr.id) AS 请求次数 ");
	// sql.append("FROM ");
	// sql.append("request_info rr ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON rr.app_id=p.pid ");
	//
	// sql.append("WHERE rr.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND rr.request_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND rr.request_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=2 ");
	// sql.append(" ) a ");
	// sql.append("LEFT JOIN ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'2') AS 用户群, ");
	// sql.append("COUNT(ss.id) AS 展示次数 , ");
	// sql.append("SUM(ss.cur_cpm) AS CPM消费金额 ");
	// sql.append("FROM ");
	// sql.append("adshow_info ss ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON ss.app_id=p.pid ");
	//
	// sql.append("WHERE ss.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND ss.show_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND ss.show_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=2) AS b ");
	// sql.append("ON a.`用户群`= b.`用户群` ");
	// sql.append("LEFT JOIN ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'2') AS 用户群, ");
	// sql.append("COUNT(cc.id) AS 点击次数 , ");
	// sql.append("SUM(cc.cur_cpc) AS CPC消费金额 ");
	// sql.append("FROM click_info cc ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON cc.app_id=p.pid ");
	//
	// sql.append("WHERE cc.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND cc.click_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND cc.click_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=2 ) AS c ");
	// sql.append("ON a.`用户群`= c.`用户群`) ");
	// }
	// if (d) {
	// if (a || b || c) {
	// sql.append("UNION ALL ");
	// }
	//
	// sql.append("(SELECT COALESCE(a.`用户群`,'1') AS 用户群, ");
	// sql.append("COALESCE(b.`展示次数`,'0') AS 展示次数, ");
	// sql.append("COALESCE(c.`点击次数`,'0') AS 点击次数, ");
	// sql.append("COALESCE(COALESCE(c.`点击次数`,0)/COALESCE(b.`展示次数`,0),0)*100 AS
	// 点击率, ");
	// sql.append("COALESCE(b.`CPM消费金额`,'0') AS CPM消费金额, ");
	// sql.append("COALESCE(c.`CPC消费金额`,'0') AS CPC消费金额 ");
	// sql.append("FROM ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'1') AS 用户群, ");
	// sql.append("COUNT(rr.id) AS 请求次数 ");
	// sql.append("FROM ");
	// sql.append("request_info rr ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON rr.app_id=p.pid ");
	//
	// sql.append("WHERE rr.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND rr.request_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND rr.request_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=1 ");
	// sql.append(" ) a ");
	// sql.append("LEFT JOIN ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'1') AS 用户群, ");
	// sql.append("COUNT(ss.id) AS 展示次数 , ");
	// sql.append("SUM(ss.cur_cpm) AS CPM消费金额 ");
	// sql.append("FROM ");
	// sql.append("adshow_info ss ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON ss.app_id=p.pid ");
	//
	// sql.append("WHERE ss.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND ss.show_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND ss.show_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=1) AS b ");
	// sql.append("ON a.`用户群`= b.`用户群` ");
	// sql.append("LEFT JOIN ");
	// sql.append("(SELECT COALESCE(p.app_crowd,'1') AS 用户群, ");
	// sql.append("COUNT(cc.id) AS 点击次数 , ");
	// sql.append("SUM(cc.cur_cpc) AS CPC消费金额 ");
	// sql.append("FROM ");
	// sql.append("click_info cc ");
	// sql.append("INNER JOIN ");
	// sql.append("app p ");
	// sql.append("ON cc.app_id=p.pid ");
	//
	// sql.append("WHERE cc.ad_id IN (?");
	// for (int i = 1; i < appIds.length; i++) {
	// sql.append(",?");
	// }
	// sql.append(") ");
	// if (startDate != null) {
	// sql.append(" AND cc.click_time > ? ");
	// }
	// if (endDate != null) {
	// sql.append(" AND cc.click_time < ? ");
	// }
	//
	// sql.append("AND p.app_crowd=1 ) AS c ");
	// sql.append("ON a.`用户群`= c.`用户群`) ");
	// }
	//
	//			
	// ps = con.prepareStatement(sql.toString());
	//
	// int psIndex = 1;
	// for (int k = 0; k < userCrowd.length; k++) {
	//
	// for (int j = 0; j < 3; j++) {
	// ps.setInt(psIndex++, appIds[0]);
	// for (int i = 1; i < appIds.length; i++) {
	// ps.setInt(psIndex++, appIds[i]);
	// }
	// if (startDate != null) {
	// ps.setTimestamp(psIndex++, getSQLTimestamp(startDate));
	// }
	// if (endDate != null) {
	// ps.setTimestamp(psIndex++, getSQLTimestamp(endDate));
	// }
	// }
	// }
	//
	//			
	// rs = ps.executeQuery();
	//
	// // System.out.println(sql.toString());
	// listStatisticsByCrowd = new ArrayList<StatisticsByCrowd>();
	// while (rs.next()) {
	// int index = 1;
	// StatisticsByCrowd sb = new StatisticsByCrowd();
	// sb.setCrowd(rs.getString(index++));
	// sb.setShow_times(rs.getInt(index++));
	// sb.setClick_times(rs.getInt(index++));
	// sb.setClick_rate(DoubleUtil.round(rs.getDouble(index++), 4));
	// sb.setCpmPay(DoubleUtil.round(rs.getDouble(index++), 4));
	// sb.setCpcPay(DoubleUtil.round(rs.getDouble(index++), 4));
	//				
	//				
	// listStatisticsByCrowd.add(sb);
	// }
	//
	//
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// } finally {
	// JdbcUtil.close(rs, ps);
	// }
	// return listStatisticsByCrowd;
	// }
	public List<StatisticsByCrowd> selectStatisticsByCrowd(int[] appIds, java.util.Date startDate,
			java.util.Date endDate, String[] userCrowd) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		// appIds = new int[] { 3,8,9 };
		// userCrowd=new String[]{"1","2","4","8"};
		List<StatisticsByCrowd> listStatisticsByCrowd = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT aa.`用户群`, ");
			sql.append("COALESCE(bb.`展示次数`,0) AS 展示次数 ,COALESCE(cc.`点击次数`,0) AS 点击次数 , ");
			sql.append("coalesce(coalesce(cc.`点击次数`,0)/coalesce(bb.`展示次数`,0),0)*100 AS '点击率（%）', ");
			sql.append("COALESCE(bb.`CPM消费金额`,0) AS CPM消费金额 , ");
			sql.append("COALESCE(cc.`CPC消费金额`,0) AS  CPC消费金额 ");
			sql.append("FROM ");
			sql.append("(SELECT b.app_crowd AS 用户群,COUNT(a.id) AS 请求次数 ");
			sql.append("FROM ");
			sql.append("(SELECT * FROM request_info ");
			sql.append("WHERE ad_id IN (? ");

			for (int i = 1; i < appIds.length; i++) {
				sql.append(",?");
			}
			sql.append(")) a ");
			sql.append("INNER JOIN app b ");
			sql.append("ON a.app_id=b.pid ");
			sql.append("WHERE b.app_crowd IN (? ");
			for (int i = 1; i < userCrowd.length; i++) {
				sql.append(",?");
			}
			sql.append(" ) ");

			if (startDate != null) {
				sql.append(" AND a.request_time > ? ");
			}
			if (endDate != null) {
				sql.append(" AND a.request_time < ? ");
			}

			sql.append("GROUP BY b.app_crowd ) aa ");
			sql.append("LEFT JOIN ");
			sql.append("(SELECT b.app_crowd AS  用户群 , COUNT(a.id) AS 展示次数 , ");
			sql.append("SUM(a.cur_cpm) AS CPM消费金额 ");
			sql.append("FROM ");
			sql.append("(SELECT * FROM adshow_info ");
			sql.append("WHERE ad_id IN (? ");

			for (int i = 1; i < appIds.length; i++) {
				sql.append(",?");
			}
			sql.append(")) a ");

			sql.append("INNER JOIN app b ");
			sql.append("ON a.app_id=b.pid ");
			sql.append("WHERE b.app_crowd IN (? ");

			for (int i = 1; i < userCrowd.length; i++) {
				sql.append(",?");
			}
			sql.append(" ) ");

			if (startDate != null) {
				sql.append(" AND a.show_time > ? ");
			}
			if (endDate != null) {
				sql.append(" AND a.show_time < ? ");
			}

			sql.append("GROUP BY b.app_crowd ) bb ");
			sql.append("ON aa.`用户群`=bb.`用户群` ");
			sql.append("LEFT JOIN ");
			sql.append("(SELECT b.app_crowd AS  用户群 , COUNT(a.id) AS 点击次数 , ");
			sql.append("SUM(a.cur_cpc) AS CPC消费金额  ");
			sql.append("FROM ");
			sql.append("(SELECT * FROM click_info ");
			sql.append("WHERE ad_id IN (? ");

			for (int i = 1; i < appIds.length; i++) {
				sql.append(",?");
			}
			sql.append(")) a ");

			sql.append("INNER JOIN app b ");
			sql.append("ON a.app_id=b.pid ");
			sql.append("WHERE b.app_crowd IN (? ");

			for (int i = 1; i < userCrowd.length; i++) {
				sql.append(",?");
			}
			sql.append(" ) ");

			if (startDate != null) {
				sql.append(" AND a.click_time > ? ");
			}
			if (endDate != null) {
				sql.append(" AND a.click_time < ? ");
			}

			sql.append("GROUP BY b.app_crowd ) cc ");
			sql.append("ON aa.`用户群`=cc.`用户群` ");

			ps = con.prepareStatement(sql.toString());

			int psIndex = 1;

			for (int j = 0; j < 3; j++) {
				ps.setInt(psIndex++, appIds[0]);
				for (int k = 1; k < appIds.length; k++) {
					ps.setInt(psIndex++, appIds[k]);
				}
				ps.setString(psIndex++, userCrowd[0]);
				for (int l = 1; l < userCrowd.length; l++) {
					ps.setString(psIndex++, userCrowd[l]);
				}
				if (startDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(startDate));
				}
				if (endDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(endDate));
				}
			}

			System.out.println(sql);

			rs = ps.executeQuery();

			// System.out.println(sql.toString());
			listStatisticsByCrowd = new ArrayList<StatisticsByCrowd>();
			while (rs.next()) {
				int index = 1;
				StatisticsByCrowd sb = new StatisticsByCrowd();
				sb.setCrowd(rs.getString(index++));
				sb.setShow_times(rs.getInt(index++));
				sb.setClick_times(rs.getInt(index++));
				sb.setClick_rate(DoubleUtil.round(rs.getDouble(index++), 4));
				sb.setCpmPay(DoubleUtil.round(rs.getDouble(index++), 4));
				sb.setCpcPay(DoubleUtil.round(rs.getDouble(index++), 4));

				listStatisticsByCrowd.add(sb);
			}

			// System.out.println("listStatisticsByCrowd:"
			// +listStatisticsByCrowd.size());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listStatisticsByCrowd;
	}

	@Override
	public List<StatisticsByGeography> selectStatisticsByGeography(int[] appIds, java.util.Date startDate,
			java.util.Date endDate, String[] ck_province) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		//
		for (int i = 0; i < appIds.length; i++) {
			System.out.println(appIds[i] + "---");
		}
		// for(int i=0;i<ck_province.length;i++){
		// System.out.println(ck_province[i]+"---");
		// }
		// appIds=new int[]{8};
		// ck_province=new String[]{"110000"};
		List<StatisticsByGeography> listStatisticsByGeography = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT coalesce(a.`地区`,'其它') AS 地区, ");
			sql.append("coalesce(b.`展示次数`,0) AS 展示次数, ");
			sql.append("coalesce(c.`点击次数`,0) AS 点击次数, ");
			sql.append("coalesce(coalesce(c.`点击次数`,0)/coalesce(b.`展示次数`,0),0)*100 AS '点击率（%）', ");
			sql.append("coalesce(b.`CPM佣金`,0) AS CPM佣金, ");
			sql.append("coalesce(c.`CPC佣金`,0) AS CPC佣金 ");
			sql.append("FROM ");
			sql.append("(SELECT COALESCE(zone,'其它') AS 地区,COUNT(id) AS 请求次数 ");
			sql.append("FROM request_info ");
			sql.append("WHERE ad_id IN (? ");
			for (int i = 1; i < appIds.length; i++) {
				sql.append(",?");
			}
			sql.append(") ");
			sql.append(" AND zone IN (?");
			for (int j = 1; j < ck_province.length; j++) {
				sql.append(",?");
			}
			sql.append(") ");
			if (startDate != null) {
				sql.append(" AND request_time > ? ");
			}
			if (endDate != null) {
				sql.append(" AND request_time < ? ");
			}
			sql.append("GROUP BY zone ");
			sql.append(" ) a ");
			sql.append("LEFT JOIN ");
			sql.append("(SELECT COALESCE(zone ,'其它')AS 地区,COUNT(id) AS 展示次数 , ");
			sql.append(" COALESCE(SUM(cur_cpm),0) AS 'CPM佣金' ");
			sql.append("FROM adshow_info ");

			sql.append("WHERE ad_id IN (? ");
			for (int i = 1; i < appIds.length; i++) {
				sql.append(",?");
			}
			sql.append(" ) ");
			sql.append(" AND zone IN (?");
			for (int j = 1; j < ck_province.length; j++) {
				sql.append(",?");
			}
			sql.append(") ");
			if (startDate != null) {
				sql.append(" AND show_time > ? ");
			}
			if (endDate != null) {
				sql.append(" AND show_time < ? ");
			}

			sql.append("GROUP BY zone ");
			sql.append(" ) b ");
			sql.append("ON coalesce(a.`地区`,'其它')=coalesce(b.`地区`,'其它') ");
			sql.append("LEFT JOIN ");
			sql.append("(SELECT COALESCE(zone,'其它') AS 地区,COUNT(id) AS 点击次数, ");
			sql.append("COALESCE(SUM(cur_cpc),0) AS 'CPC佣金' ");
			sql.append("FROM click_info ");

			sql.append("WHERE ad_id IN (? ");
			for (int i = 1; i < appIds.length; i++) {
				sql.append(",?");
			}
			sql.append(") ");
			sql.append(" AND zone IN (?");
			for (int j = 1; j < ck_province.length; j++) {
				sql.append(",?");
			}
			sql.append(") ");
			if (startDate != null) {
				sql.append(" AND click_time > ? ");
			}
			if (endDate != null) {
				sql.append(" AND click_time < ? ");
			}

			sql.append("GROUP BY zone ");
			sql.append(" ) c ");
			sql.append("ON coalesce(a.`地区`,'其它')=coalesce(c.`地区`,'其它'); ");
			// sql.append("LEFT JOIN ");
			// sql.append("(SELECT zone AS 地区, ");
			// sql.append("SUM(d.cur_cpm/1000) AS 'CPM佣金' ");
			// sql.append("FROM adshow_info d ");
			// sql.append("INNER JOIN ad e ");
			// sql.append("ON d.ad_id=e.id ");
			// sql.append("WHERE e.pay_mode='按印象付费' AND d.ad_id IN (? ");
			//			
			// for (int i = 1; i < appIds.length; i++) {
			// sql.append(",?");
			// }
			// sql.append(") ");
			// sql.append(" AND zone IN (?");
			// for(int j=1;j<ck_province.length;j++){
			// sql.append(",?");
			// }
			// sql.append(") ");
			// if (startDate != null) {
			// sql.append(" AND d.show_time > ? ");
			// }
			// if (endDate != null) {
			// sql.append(" AND d.show_time < ? ");
			// }
			//			
			// // sql.append("GROUP BY zone ");
			// sql.append(" ) f ");
			// sql.append("ON coalesce(a.`地区`,'其它')=coalesce(f.`地区`,'其它') ");
			// sql.append("LEFT JOIN ");
			// sql.append("(SELECT zone AS 地区, ");
			// sql.append("SUM(g.cur_cpc) AS 'CPC佣金' ");
			// sql.append("FROM click_info g ");
			// sql.append("INNER JOIN ad h ");
			// sql.append("ON g.ad_id=h.id ");
			// sql.append("WHERE h.pay_mode='按单次点击付费' AND g.ad_id IN (? ");
			//			
			// for (int i = 1; i < appIds.length; i++) {
			// sql.append(",?");
			// }
			// sql.append(") ");
			// sql.append(" AND zone IN (?");
			// for(int j=1;j<ck_province.length;j++){
			// sql.append(",?");
			// }
			// sql.append(") ");
			// if (startDate != null) {
			// sql.append(" AND g.click_time > ? ");
			// }
			// if (endDate != null) {
			// sql.append(" AND g.click_time < ? ");
			// }
			//			
			// sql.append("GROUP BY zone ");
			// sql.append(" ) i ");
			// sql.append("ON coalesce(a.`地区`,'其它')=coalesce(i.`地区`,'其它'); ");
			ps = con.prepareStatement(sql.toString());
			int psIndex = 1;
			for (int j = 0; j < 3; j++) {
				ps.setInt(psIndex++, appIds[0]);
				for (int i = 1; i < appIds.length; i++) {
					ps.setInt(psIndex++, appIds[i]);
				}
				ps.setString(psIndex++, ck_province[0]);
				for (int k = 1; k < ck_province.length; k++) {
					ps.setString(psIndex++, ck_province[k]);
				}
				if (startDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(startDate));
				}
				if (endDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(endDate));
				}
			}
			rs = ps.executeQuery();
			listStatisticsByGeography = new ArrayList<StatisticsByGeography>();
			while (rs.next()) {
				int index = 1;
				StatisticsByGeography sbg = new StatisticsByGeography();
				sbg.setGeography(rs.getString(index++));
				sbg.setShow_times(rs.getInt(index++));
				sbg.setClick_times(rs.getInt(index++));
				sbg.setClick_rate(DoubleUtil.round(rs.getDouble(index++), 4));
				sbg.setCpmPay(DoubleUtil.round(rs.getDouble(index++), 4));
				sbg.setCpcPay(DoubleUtil.round(rs.getDouble(index++), 4));
				listStatisticsByGeography.add(sbg);

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listStatisticsByGeography;
	}

	@Override
	public List<StatisticsByTimeInterval> selectStatisticsByTimeInterval(int[] appIds, java.util.Date startDate,
			java.util.Date endDate, String[] showtime) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean am = false;
		boolean pm = false;
		boolean night = false;
		boolean smallHour = false;

		List<StatisticsByTimeInterval> listStatisticsByTimeInterval = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			for (int i = 0; i < showtime.length; i++) {
				if ("1".equals(showtime[i])) {
					am = true;
				} else if ("2".equals(showtime[i])) {
					pm = true;
				} else if ("3".equals(showtime[i])) {
					night = true;
				} else if ("4".equals(showtime[i])) {
					smallHour = true;
				}
			}
			if (am) {
				sql.append("(SELECT a.`时间段`,coalesce(b.`展示次数`,0) AS 展示次数, ");
				sql.append("coalesce(c.`点击次数`,0) AS 点击次数, ");
				sql.append("coalesce(coalesce(c.`点击次数`,0)/coalesce(b.`展示次数`,0),0)*100 AS 点击率, ");
				sql.append("COALESCE(b.`CPM消费金额`,'0') AS CPM消费金额, ");
				sql.append("COALESCE(c.`CPC消费金额`,'0') AS CPC消费金额");
				sql.append(" FROM ");
				sql.append("(SELECT COALESCE(DATE_FORMAT(request_time,'上午'),'上午') AS 时间段,COUNT(id) AS 请求次数 ");
				sql.append("FROM request_info ");
				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND request_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND request_time < ? ");
				}
				sql
						.append("AND DATE_FORMAT(request_time,'%T')<= '11:59:59'  AND DATE_FORMAT(request_time,'%T') >= '06:00:00') a ");
				sql.append("LEFT JOIN ");
				sql
						.append("(SELECT COALESCE(DATE_FORMAT(show_time,'上午'),'上午') AS 时间段,COUNT(id) AS 展示次数,SUM(cur_cpm) AS CPM消费金额 ");
				sql.append("FROM adshow_info ");
				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND show_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND show_time < ? ");
				}
				sql
						.append("AND DATE_FORMAT(show_time,'%T')<= '11:59:59'  AND DATE_FORMAT(show_time,'%T') >= '06:00:00' ) b ");
				sql.append("ON a.`时间段`=b.`时间段` ");
				sql.append("LEFT JOIN ");
				sql
						.append("(SELECT COALESCE(DATE_FORMAT(click_time,'上午'),'上午') AS 时间段,COUNT(id) AS 点击次数 ,SUM(cur_cpc) AS CPC消费金额 ");
				sql.append("FROM click_info ");
				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND click_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND click_time < ? ");
				}
				sql
						.append("AND DATE_FORMAT(click_time,'%T')<= '11:59:59'  AND DATE_FORMAT(click_time,'%T') >= '06:00:00' ) c ");
				sql.append("ON a.`时间段`=c.`时间段` ) ");
			}
			if (pm) {
				if (am) {
					sql.append("UNION ALL ");
				}

				sql.append("(SELECT a.`时间段`,coalesce(b.`展示次数`,0) AS 展示次数, ");
				sql.append("coalesce(c.`点击次数`,0) AS 点击次数, ");
				sql.append("coalesce(coalesce(c.`点击次数`,0)/coalesce(b.`展示次数`,0),0)*100 AS 点击率, ");
				sql.append("COALESCE(b.`CPM消费金额`,'0') AS CPM消费金额, ");
				sql.append("COALESCE(c.`CPC消费金额`,'0') AS CPC消费金额 ");
				sql.append(" FROM ");
				sql.append("(SELECT COALESCE(DATE_FORMAT(request_time,'下午'),'下午') AS 时间段,COUNT(id) AS 请求次数 ");
				sql.append("FROM request_info ");

				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND request_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND request_time < ? ");
				}

				sql
						.append("AND DATE_FORMAT(request_time,'%T')<= '17:59:59'  AND DATE_FORMAT(request_time,'%T') >= '12:00:00') a ");
				sql.append("LEFT JOIN ");
				sql
						.append("(SELECT COALESCE(DATE_FORMAT(show_time,'下午'),'下午') AS 时间段,COUNT(id) AS 展示次数 ,SUM(cur_cpm) AS CPM消费金额 ");
				sql.append("FROM adshow_info ");

				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND show_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND show_time < ? ");
				}

				sql
						.append("AND DATE_FORMAT(show_time,'%T')<= '17:59:59'  AND DATE_FORMAT(show_time,'%T') >= '12:00:00' ) b ");
				sql.append("ON a.`时间段`=b.`时间段` ");
				sql.append("LEFT JOIN ");
				sql
						.append("(SELECT COALESCE(DATE_FORMAT(click_time,'下午'),'下午') AS 时间段,COUNT(id) AS 点击次数 ,SUM(cur_cpc) AS CPC消费金额 ");
				sql.append("FROM click_info ");

				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND click_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND click_time < ? ");
				}

				sql
						.append("AND DATE_FORMAT(click_time,'%T')<= '17:59:59'  AND DATE_FORMAT(click_time,'%T') >= '12:00:00' ) c ");
				sql.append("ON a.`时间段`=c.`时间段` ) ");
			}

			if (night) {
				if (am || pm) {
					sql.append("UNION ALL ");
				}

				sql.append("(SELECT a.`时间段`,coalesce(b.`展示次数`,0) AS 展示次数, ");
				sql.append("coalesce(c.`点击次数`,0) AS 点击次数, ");
				sql.append("coalesce(coalesce(c.`点击次数`,0)/coalesce(b.`展示次数`,0),0)*100 AS 点击率,");
				sql.append("COALESCE(b.`CPM消费金额`,'0') AS CPM消费金额, ");
				sql.append("COALESCE(c.`CPC消费金额`,'0') AS CPC消费金额 ");
				sql.append(" FROM ");
				sql.append("(SELECT coalesce(DATE_FORMAT(request_time,'晚上'),'晚上') AS 时间段,COUNT(id) AS 请求次数 ");
				sql.append("FROM request_info ");

				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND request_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND request_time < ? ");
				}

				sql
						.append("AND DATE_FORMAT(request_time,'%T')<= '23:59:59'  AND DATE_FORMAT(request_time,'%T') >= '18:00:00') a ");
				sql.append("LEFT JOIN ");
				sql
						.append("(SELECT COALESCE(DATE_FORMAT(show_time,'晚上'),'晚上') AS 时间段,COUNT(id) AS 展示次数 ,SUM(cur_cpm) AS CPM消费金额 ");
				sql.append("FROM adshow_info ");

				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND show_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND show_time < ? ");
				}

				sql
						.append("AND DATE_FORMAT(show_time,'%T')<= '23:59:59'  AND DATE_FORMAT(show_time,'%T') >= '18:00:00' ) b ");
				sql.append("ON a.`时间段`=b.`时间段` ");
				sql.append("LEFT JOIN ");
				sql
						.append("(SELECT COALESCE(DATE_FORMAT(click_time,'晚上'),'晚上') AS 时间段,COUNT(id) AS 点击次数 ,SUM(cur_cpc) AS CPC消费金额 ");
				sql.append("FROM click_info ");

				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND click_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND click_time < ? ");
				}

				sql
						.append("AND DATE_FORMAT(click_time,'%T')<= '23:59:59'  AND DATE_FORMAT(click_time,'%T') >= '18:00:00' ) c ");
				sql.append("ON a.`时间段`=c.`时间段` )");
			}
			if (smallHour) {
				if (am || pm || night) {
					sql.append("UNION ALL ");
				}

				sql.append("(SELECT a.`时间段`,coalesce(b.`展示次数`,0) AS 展示次数, ");
				sql.append("coalesce(c.`点击次数`,0) AS 点击次数, ");
				sql.append("coalesce(coalesce(c.`点击次数`,0)/coalesce(b.`展示次数`,0),0)*100 AS 点击率, ");
				sql.append("COALESCE(b.`CPM消费金额`,'0') AS CPM消费金额, ");
				sql.append("COALESCE(c.`CPC消费金额`,'0') AS CPC消费金额");
				sql.append(" FROM ");
				sql.append("(SELECT coalesce(DATE_FORMAT(request_time,'凌晨'),'凌晨') AS 时间段,COUNT(id) AS 请求次数 ");
				sql.append("FROM request_info ");

				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND request_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND request_time < ? ");
				}

				sql
						.append("AND DATE_FORMAT(request_time,'%T')<= '05:59:59'  AND DATE_FORMAT(request_time,'%T') >= '00:00:00') a ");
				sql.append("LEFT JOIN ");
				sql
						.append("(SELECT COALESCE(DATE_FORMAT(show_time,'凌晨'),'凌晨') AS 时间段,COUNT(id) AS 展示次数 ,SUM(cur_cpm) AS CPM消费金额 ");
				sql.append("FROM adshow_info ");

				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND show_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND show_time < ? ");
				}

				sql
						.append("AND DATE_FORMAT(show_time,'%T')<= '05:59:59'  AND DATE_FORMAT(show_time,'%T') >= '00:00:00' ) b ");
				sql.append("ON a.`时间段`=b.`时间段` ");
				sql.append("LEFT JOIN ");
				sql
						.append("(SELECT COALESCE(DATE_FORMAT(click_time,'凌晨'),'凌晨') AS 时间段,COUNT(id) AS 点击次数 ,SUM(cur_cpc) AS CPC消费金额 ");
				sql.append("FROM click_info ");

				sql.append("WHERE ad_id IN (? ");
				for (int i = 1; i < appIds.length; i++) {
					sql.append(",?");
				}
				sql.append(" ) ");
				if (startDate != null) {
					sql.append(" AND click_time > ? ");
				}
				if (endDate != null) {
					sql.append(" AND click_time < ? ");
				}

				sql
						.append("AND DATE_FORMAT(click_time,'%T')<= '05:59:59'  AND DATE_FORMAT(click_time,'%T') >= '00:00:00' ) c ");
				sql.append("ON a.`时间段`=c.`时间段` )");
			}

			ps = con.prepareStatement(sql.toString());

			// 传值
			int psIndex = 1;
			for (int k = 0; k < showtime.length; k++) {
				for (int j = 0; j < 3; j++) {
					ps.setInt(psIndex++, appIds[0]);
					for (int i = 1; i < appIds.length; i++) {
						ps.setInt(psIndex++, appIds[i]);
					}
					if (startDate != null) {
						ps.setTimestamp(psIndex++, getSQLTimestamp(startDate));
					}
					if (endDate != null) {
						ps.setTimestamp(psIndex++, getSQLTimestamp(endDate));
					}
				}
			}
			rs = ps.executeQuery();
			listStatisticsByTimeInterval = new ArrayList<StatisticsByTimeInterval>();

			while (rs.next()) {
				int index = 1;
				StatisticsByTimeInterval sbti = new StatisticsByTimeInterval();
				sbti.setTimeInterval(rs.getString(index++));
				sbti.setShow_times(rs.getInt(index++));
				sbti.setClick_times(rs.getInt(index++));
				sbti.setClick_rate(DoubleUtil.round(rs.getDouble(index++), 4));
				sbti.setCpmPay(DoubleUtil.round(rs.getDouble(index++), 4));
				sbti.setCpcPay(DoubleUtil.round(rs.getDouble(index++), 4));
				listStatisticsByTimeInterval.add(sbti);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listStatisticsByTimeInterval;
	}

	@Override
	public Vector<AD> selectAllADs() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<AD> adList = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "SELECT * FROM ad ";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			adList = new Vector<AD>();
			while (rs.next()) {
				AD ad = new AD();
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
				ad.setPut_gender(rs.getString(index++));
				ad.setPut_crowd(rs.getString(index++));
				ad.setPut_time(rs.getString(index++));
				adList.add(ad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return adList;
	}

	@Override
	public Vector<AD> selectAllRunningADs() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<AD> adList = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "SELECT * FROM ad WHERE state='运行中' ";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			adList = new Vector<AD>();
			while (rs.next()) {
				AD ad = new AD();
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
				ad.setPut_gender(rs.getString(index++));
				ad.setPut_crowd(rs.getString(index++));
				ad.setPut_time(rs.getString(index++));
				adList.add(ad);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return adList;
	}

	@Override
	public Vector<ADArs> selectAllADArs() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Vector<ADArs> adArsList = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "SELECT a.* FROM ad_ars a,ad b WHERE b.state='运行中' AND a.ad_id=b.id ";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			adArsList = new Vector<ADArs>();

			while (rs.next()) {
				int index = 1;
				ADArs adArs = new ADArs();
				adArs.setId(rs.getInt(index++));
				adArs.setAd_id(rs.getInt(index++));
				adArs.setArs_name(rs.getString(index++));
				adArsList.add(adArs);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return adArsList;
	}

	@Override
	public Vector<ADShowType> selectAllADShowTypes() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<ADShowType> adShowTypeList = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(System.currentTimeMillis());

			String sql = "SELECT a.* FROM ad_show_type a, ad b WHERE b.state ='运行中' and a.ad_id = b.id and b.start_date <= ? and b.end_date >= ?; ";

			ps = con.prepareStatement(sql);

			ps.setString(1, date);
			ps.setString(2, date);

			rs = ps.executeQuery();

			adShowTypeList = new Vector<ADShowType>();

			while (rs.next()) {
				ADShowType adShowType = new ADShowType();
				int index = 1;
				adShowType.setId(rs.getInt(index++));
				adShowType.setAd_id(rs.getInt(index++));
				adShowType.setShow_type_name(rs.getString(index++));
				adShowType.setTitle(rs.getString(index++));
				adShowType.setAd_info_url(rs.getString(index++));
				adShowType.setClick_effect(rs.getString(index++));
				adShowType.setAd_detail(rs.getString(index++));
				adShowType.setClick_url(rs.getString(index++));
				adShowType.setPay_mode(rs.getString(index++));
				adShowType.setPrice(DoubleUtil.round(rs.getDouble(index++), 4));
				adShowTypeList.add(adShowType);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return adShowTypeList;
	}

	@Override
	public Vector<ADZone> selectAllADZones() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<ADZone> adZoneList = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "SELECT a.* FROM ad_zone a, ad b WHERE b.state ='运行中' AND a.ad_id = b.id;  ";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			adZoneList = new Vector<ADZone>();

			while (rs.next()) {

				ADZone adZone = new ADZone();
				int index = 1;
				adZone.setId(rs.getInt(index++));
				adZone.setAd_id(rs.getInt(index++));
				adZone.setZone_name(rs.getString(index++));
				adZone.setZone_code(rs.getString(index++));
				adZoneList.add(adZone);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return adZoneList;
	}

	@Override
	public Vector<ADPhoneType> selectAllPhoneTypes() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Vector<ADPhoneType> adPhoneTypeList = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "SELECT a.* FROM ad_phonetype a,ad b WHERE b.state='运行中' AND a.ad_id=b.id ";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			adPhoneTypeList = new Vector<ADPhoneType>();
			while (rs.next()) {
				int index = 1;
				ADPhoneType adPhoneType = new ADPhoneType();
				adPhoneType.setId(rs.getInt(index++));
				adPhoneType.setAd_id(rs.getInt(index++));
				adPhoneType.setPhone_name(rs.getString(index++));
				adPhoneType.setPhone_code(rs.getString(index++));
				adPhoneTypeList.add(adPhoneType);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return adPhoneTypeList;
	}

	@Override
	public void inserAbundantRequestInfo(Vector<RequestInfo> data) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql
					.append("insert into request_info (app_id,ad_id,request_time,imei,zone,show_type_id) values (?,?,?,?,?,?)");

			ps = con.prepareStatement(sql.toString());

			for (RequestInfo ri : data) {
				int index = 1;
				ps.setString(index++, ri.getApp_id());
				ps.setInt(index++, ri.getAd_id());
				ps.setTimestamp(index++, getSQLTimestamp(ri.getRequest_time()));
				ps.setString(index++, ri.getImei());
				ps.setString(index++, ri.getZone());
				ps.setInt(index++, ri.getShow_type_id());

				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void inserAbundantClickInfo(Vector<ClickInfo> data) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql
					.append("insert into click_info (app_id,ad_id,click_time,cur_cpc,imei,zone,show_type_id,cpc_income) values (?,?,?,?,?,?,?,?)");

			ps = con.prepareStatement(sql.toString());

			for (ClickInfo ci : data) {
				int index = 1;
				ps.setString(index++, ci.getApp_id());
				ps.setInt(index++, ci.getAd_id());
				ps.setTimestamp(index++, getSQLTimestamp(ci.getClick_time()));
				ps.setDouble(index++, ci.getCur_cpc());
				ps.setString(index++, ci.getImei());
				ps.setString(index++, ci.getZone());
				ps.setInt(index++, ci.getShow_type_id());
				ps.setDouble(index++, ci.getCpc_income());

				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void inserAbundantShowInfo(Vector<ADShowInfo> data) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql
					.append("insert into adshow_info (app_id,ad_id,show_time,cur_cpm,imei,zone,show_type_id,cpm_income) values (?,?,?,?,?,?,?,?)");

			ps = con.prepareStatement(sql.toString());

			for (ADShowInfo si : data) {
				int index = 1;
				ps.setString(index++, si.getApp_id());
				ps.setInt(index++, si.getAd_id());
				ps.setTimestamp(index++, getSQLTimestamp(si.getShow_time()));
				ps.setDouble(index++, si.getCur_cpm());
				ps.setString(index++, si.getImei());
				ps.setString(index++, si.getZone());
				ps.setInt(index++, si.getShow_type_id());
				ps.setDouble(index++, si.getCpm_income());

				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void updateADsState(Vector<AD> ads) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update ad set state=? where id=?");

			ps = con.prepareStatement(sql.toString());

			for (AD ad : ads) {
				int index = 1;
				ps.setString(index++, ad.getState());
				ps.setInt(index++, ad.getId());

				ps.addBatch();
			}

			ps.executeBatch();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public void updateEndADs(String date) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("update ad set state='结束' where (state='运行中' or state='暂停') and end_date <= ? ");

			ps = con.prepareStatement(sql.toString());

			ps.setString(1, date);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

}
