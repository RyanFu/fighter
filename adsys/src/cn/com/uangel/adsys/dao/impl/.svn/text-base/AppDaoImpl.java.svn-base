package cn.com.uangel.adsys.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import cn.com.uangel.adsys.dao.AppDao;
import cn.com.uangel.adsys.entity.App;
import cn.com.uangel.adsys.entity.AppGroundStatInfo;
import cn.com.uangel.adsys.entity.AppStatInfo;
import cn.com.uangel.adsys.util.ConnectionProvider;
import cn.com.uangel.adsys.util.DoubleUtil;
import cn.com.uangel.adsys.util.JdbcUtil;

public class AppDaoImpl implements AppDao {

	@Override
	public void insert(App app) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("insert into app (app_name,app_type,pid,create_time,mem_id,app_platform, ");
			sql.append("pakage_name,app_crowd,app_gender,apk_url,app_state) ");
			sql.append("values (?,?,?,?,?,?,?,?,?,?,?)");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, app.getApp_name());
			ps.setString(index++, app.getApp_type());
			ps.setString(index++, app.getPid());
			ps.setTimestamp(index++, getSQLTimestamp(app.getCreate_time()));
			ps.setInt(index++, app.getMem_id());
			ps.setString(index++, app.getApp_platform());
			ps.setString(index++, app.getPakage_name());
			ps.setString(index++, app.getApp_crowd());
			ps.setString(index++, app.getApp_gender());
			ps.setString(index++, app.getApk_url());
			ps.setString(index++, app.getApp_state());

			ps.executeUpdate();

			// 取得自动生成的字段的值
			rs = ps.getGeneratedKeys();
			rs.next();
			app.setId(rs.getInt(1));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public List<App> selectAppsByMemId(int memId, String state) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<App> appList = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "select * from app where mem_id=? ";
			if (state != null) {
				sql += "and app_state=? ";
			}

			ps = con.prepareStatement(sql);

			int psIndex = 1;
			ps.setInt(psIndex++, memId);
			if (state != null) {
				ps.setString(psIndex++, state);
			}

			rs = ps.executeQuery();

			appList = new ArrayList<App>();
			while (rs.next()) {
				App app = new App();
				int index = 1;
				app.setId(rs.getInt(index++));
				app.setApp_name(rs.getString(index++));
				app.setApp_type(rs.getString(index++));
				app.setPid(rs.getString(index++));
				app.setCreate_time(rs.getTimestamp(index++));
				app.setMem_id(rs.getInt(index++));
				app.setApp_platform(rs.getString(index++));
				app.setPakage_name(rs.getString(index++));
				app.setApp_crowd(rs.getString(index++));
				app.setApp_gender(rs.getString(index++));
				app.setApk_url(rs.getString(index++));
				app.setApp_state(rs.getString(index++));
				appList.add(app);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return appList;
	}

	@Override
	public List<AppStatInfo> selectStatInfo(String[] appIds, java.util.Date startDate, java.util.Date endDate,
			String statWay) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		// appIds=new String[]{"2889dcd19fe0cf7e18941f6a9777b"};
		List<AppStatInfo> statList = null;
		String dateFormat = "01".equals(statWay) ? "%Y-%m-%d" : "%Y-%m";

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "SELECT a.`日期`,a.`请求次数`,coalesce(b.`展示次数`,0) AS 展示次数,"
					+ "coalesce(c.`点击次数`,0) AS 点击次数,coalesce(b.`CPM佣金`,0) AS CPM佣金,"
					+ "coalesce(c.`CPC佣金`,0) AS CPC佣金,"
					+ "((coalesce(b.`CPM佣金`,0) + coalesce(c.`CPC佣金`,0))) AS '收入金额(￥)' " + "FROM "
					+ "(SELECT DATE_FORMAT(request_time,'" + dateFormat
					+ "') AS 日期,COUNT(id) AS 请求次数 FROM request_info " + "WHERE app_id IN (?";
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
			sql += " GROUP BY DATE_FORMAT(request_time,'" + dateFormat + "') ) a LEFT JOIN "
					+ "(SELECT DATE_FORMAT(show_time,'" + dateFormat
					+ "') AS 日期,COUNT(id) AS 展示次数,COALESCE(SUM(cpm_income),0) AS 'CPM佣金' "
					+ "FROM adshow_info WHERE app_id IN (?";
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
			sql += " GROUP BY DATE_FORMAT(show_time,'" + dateFormat + "') ) b " + "ON a.`日期`=b.`日期`" + "LEFT JOIN "
					+ "(SELECT DATE_FORMAT(click_time,'" + dateFormat
					+ "') AS 日期,COUNT(id) AS 点击次数, COALESCE(SUM(cpc_income),0) AS 'CPC佣金' " + "FROM click_info "
					+ "WHERE app_id IN (?";
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
			sql += "GROUP BY DATE_FORMAT(click_time,'" + dateFormat + "') ) c " + "ON a.`日期`=c.`日期` ";
			// LEFT JOIN (SELECT DATE_FORMAT(d.show_time,'" + dateFormat +
			// "') AS 日期, "
			// +
			// "SUM(d.cur_cpm/1000) AS 'CPM佣金' FROM adshow_info d INNER JOIN ad e "
			// + "ON d.ad_id=e.id "
			// + "WHERE e.pay_mode='按印象付费' AND d.app_id IN (?";
			// for (int i = 1; i < appIds.length; i++) {
			// sql += ",?";
			// }
			// sql += ") ";
			// if (startDate != null) {
			// sql += " AND d.show_time > ? ";
			// }
			// if (endDate != null) {
			// sql += " AND d.show_time < ? ";
			// }
			// sql += " GROUP BY DATE_FORMAT(d.show_time,'" + dateFormat +
			// "') ) f "
			// + "ON a.`日期`=f.`日期` LEFT JOIN (SELECT DATE_FORMAT(g.click_time,'"
			// + dateFormat + "') AS 日期, "
			// +
			// "SUM(g.cur_cpc) AS 'CPC佣金' FROM click_info g INNER JOIN ad h ON g.ad_id=h.id "
			// + "WHERE h.pay_mode='按单次点击付费' AND g.app_id IN (?";
			// for (int i = 1; i < appIds.length; i++) {
			// sql += ",?";
			// }
			// sql += ") ";
			// if (startDate != null) {
			// sql += " AND g.click_time > ? ";
			// }
			// if (endDate != null) {
			// sql += " AND g.click_time < ? ";
			// }
			// sql += "GROUP BY DATE_FORMAT(g.click_time,'" + dateFormat +
			// "') ) i ON a.`日期`=i.`日期`";

			ps = con.prepareStatement(sql);

			int psIndex = 1;
			for (int j = 0; j < 3; j++) {
				ps.setString(psIndex++, appIds[0]);
				for (int i = 1; i < appIds.length; i++) {
					ps.setString(psIndex++, appIds[i]);
				}
				if (startDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(startDate));
				}
				if (endDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(endDate));
				}
			}

			rs = ps.executeQuery();

			statList = new ArrayList<AppStatInfo>();
			while (rs.next()) {
				AppStatInfo stat = new AppStatInfo();
				int index = 1;
				stat.setDate(rs.getString(index++));
				stat.setRequest_times(rs.getInt(index++));
				stat.setShow_times(rs.getInt(index++));
				stat.setClick_times(rs.getInt(index++));
				stat.setCpm_commision(DoubleUtil.round(rs.getDouble(index++), 4));
				stat.setCpc_commision(DoubleUtil.round(rs.getDouble(index++), 4));
				stat.setTotal_income(DoubleUtil.round(rs.getDouble(index++), 4));
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

	@Override
	public void deleteAppByAppID(String appID) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM app WHERE pid=?");

			ps = con.prepareStatement(sql.toString());

			int index = 1;
			ps.setString(index++, appID);

			ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
	}

	@Override
	public int selectMemberIDByAppID(String appID) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int memID = -1;

		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "SELECT mem_id FROM app WHERE pid=? ";

			ps = con.prepareStatement(sql);

			int psIndex = 1;
			ps.setString(psIndex++, appID);

			rs = ps.executeQuery();

			rs.next();
			if (rs.getRow() != 0) {
				memID = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return memID;
	}

	@Override
	public void updateById(App app) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE app SET app_name=?,app_type=?,pid=?,create_time=?,mem_id=?,");
			sql.append("app_platform=?,pakage_name=?,app_crowd=?,app_gender=?,apk_url=?,");
			sql.append("app_state=? WHERE id=?");
			ps = con.prepareStatement(sql.toString());
			int index = 1;
			ps.setString(index++, app.getApp_name());
			ps.setString(index++, app.getApp_type());
			ps.setString(index++, app.getPid());
			ps.setTimestamp(index++, getSQLTimestamp(app.getCreate_time()));
			ps.setInt(index++, app.getMem_id());
			ps.setString(index++, app.getApp_platform());
			ps.setString(index++, app.getPakage_name());
			ps.setString(index++, app.getApp_crowd());
			ps.setString(index++, app.getApp_gender());
			ps.setString(index++, app.getApk_url());
			ps.setString(index++, app.getApp_state());
			ps.setInt(index++, app.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
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
	public App selectById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		App app = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "select * from app where id=? ";

			ps = con.prepareStatement(sql);

			int psIndex = 1;
			ps.setInt(psIndex++, id);

			rs = ps.executeQuery();
			app = new App();
			while (rs.next()) {

				int index = 1;
				app.setId(rs.getInt(index++));
				app.setApp_name(rs.getString(index++));
				app.setApp_type(rs.getString(index++));
				app.setPid(rs.getString(index++));
				app.setCreate_time(rs.getTimestamp(index++));
				app.setMem_id(rs.getInt(index++));
				app.setApp_platform(rs.getString(index++));
				app.setPakage_name(rs.getString(index++));
				app.setApp_crowd(rs.getString(index++));
				app.setApp_gender(rs.getString(index++));
				app.setApk_url(rs.getString(index++));
				app.setApp_state(rs.getString(index++));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return app;
	}

	@Override
	public List<AppGroundStatInfo> selectGroundStatInfo(String[] appIds, Date startDate, Date endDate, String statWay) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		// appIds =new String[]{"28762fe2e1d845a7a3e21a08b557b"};
		List<AppGroundStatInfo> groundStatList = null;

		try {
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT coalesce(a.`地区`,'其它'),a.`请求次数`,coalesce(b.`展示次数`,0) AS 展示次数, ");
			sql.append("coalesce(c.`点击次数`,0) AS 点击次数,coalesce(b.`CPM佣金`,0) AS CPM佣金, ");
			sql.append("coalesce(c.`CPC佣金`,0) AS CPC佣金, ");
			sql.append("((coalesce(b.`CPM佣金`,0) + coalesce(c.`CPC佣金`,0))) AS '收入金额(￥)' ");
			sql.append("FROM ");
			sql.append("(SELECT zone AS 地区,COUNT(id) AS 请求次数 ");
			sql.append("FROM request_info ");
			sql.append("WHERE app_id IN (?");
			for (int i = 1; i < appIds.length; i++) {
				sql.append(",?");
			}
			sql.append(")");
			if (startDate != null) {
				sql.append(" AND request_time > ? ");
			}
			if (endDate != null) {
				sql.append(" AND request_time < ? ");
			}
			sql.append("GROUP BY zone ) a ");
			sql.append("LEFT JOIN ");
			sql.append("(SELECT zone AS 地区,COUNT(id) AS 展示次数,COALESCE(SUM(cpm_income),0) AS 'CPM佣金'  ");
			sql.append("FROM adshow_info ");
			sql.append("WHERE app_id IN (?");
			for (int i = 1; i < appIds.length; i++) {
				sql.append(",?");
			}
			sql.append(")");
			if (startDate != null) {
				sql.append(" AND show_time > ? ");
			}
			if (endDate != null) {
				sql.append(" AND show_time < ? ");
			}
			sql.append("GROUP BY zone ) b ");
			sql.append("ON coalesce(a.`地区`,'其它')=coalesce(b.`地区`,'其它') ");
			sql.append("LEFT JOIN ");
			sql.append("(SELECT zone AS 地区,COUNT(id) AS 点击次数,COALESCE(SUM(cpc_income),0) AS 'CPC佣金' ");
			sql.append("FROM click_info ");
			sql.append("WHERE app_id IN (?");
			for (int i = 1; i < appIds.length; i++) {
				sql.append(",?");
			}
			sql.append(")");
			if (startDate != null) {
				sql.append(" AND click_time > ? ");
			}
			if (endDate != null) {
				sql.append(" AND click_time < ? ");
			}
			sql.append("GROUP BY zone ) c ");
			sql.append("ON coalesce(a.`地区`,'其它')=coalesce(c.`地区`,'其它')  ORDER BY a.`地区` ASC");
			// sql.append("LEFT JOIN ");
			// sql.append("(SELECT zone AS 地区, ");
			// sql.append("SUM(d.cur_cpm/1000) AS 'CPM佣金' ");
			// sql.append("FROM adshow_info d ");
			// sql.append("INNER JOIN ad e ");
			// sql.append("ON d.ad_id=e.id ");
			// sql.append("WHERE e.pay_mode='按印象付费' AND d.app_id IN (? ");
			// for (int i = 1; i < appIds.length; i++) {
			// sql.append(",?");
			// }
			// sql.append(")");
			// if (startDate != null) {
			// sql.append(" AND d.show_time > ? ");
			// }
			// if (endDate != null) {
			// sql.append(" AND d.show_time < ? ");
			// }
			// sql.append("GROUP BY zone ) f ");
			// sql.append("ON coalesce(a.`地区`,'其它')=coalesce(f.`地区`,'其它') ");
			// sql.append("LEFT JOIN ");
			// sql.append("(SELECT zone AS 地区, ");
			// sql.append("SUM(g.cur_cpc) AS 'CPC佣金' ");
			// sql.append("FROM click_info g ");
			// sql.append("INNER JOIN ad h ");
			// sql.append("ON g.ad_id=h.id ");
			// sql.append("WHERE h.pay_mode='按单次点击付费' AND g.app_id IN (? ");
			// for (int i = 1; i < appIds.length; i++) {
			// sql.append(",?");
			// }
			// sql.append(")");
			// if (startDate != null) {
			// sql.append(" AND g.click_time > ? ");
			// }
			// if (endDate != null) {
			// sql.append(" AND g.click_time < ? ");
			// }
			// sql.append("GROUP BY zone ) i ");
			// sql.append("ON coalesce(a.`地区`,'其它')=coalesce(i.`地区`,'其它'); ");
			ps = con.prepareStatement(sql.toString());

			int psIndex = 1;
			for (int j = 0; j < 3; j++) {
				ps.setString(psIndex++, appIds[0]);
				for (int i = 1; i < appIds.length; i++) {
					ps.setString(psIndex++, appIds[i]);
				}
				if (startDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(startDate));
				}
				if (endDate != null) {
					ps.setTimestamp(psIndex++, getSQLTimestamp(endDate));
				}
			}

			rs = ps.executeQuery();
			groundStatList = new ArrayList<AppGroundStatInfo>();
			while (rs.next()) {
				AppGroundStatInfo stat = new AppGroundStatInfo();
				int index = 1;
				stat.setZone(rs.getString(index++));
				stat.setRequest_times(rs.getInt(index++));
				stat.setShow_times(rs.getInt(index++));
				stat.setClick_times(rs.getInt(index++));
				stat.setCpm_commision(DoubleUtil.round(rs.getDouble(index++), 4));
				stat.setCpc_commision(DoubleUtil.round(rs.getDouble(index++), 4));
				stat.setTotal_income(DoubleUtil.round(rs.getDouble(index++), 4));
				groundStatList.add(stat);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return groundStatList;
	}

	@Override
	public boolean selectPackagenameExist(String packageName, String pid) {
		boolean result = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = ConnectionProvider.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from app where pakage_name=?");
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, packageName);
			rs = ps.executeQuery();
			while (rs.next()) {
				String appPid = rs.getString(4);// 获取memid
				if (pid.equals(appPid)) {
					result = false;
				} else {
					result = true;
				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e);

		} finally {
			JdbcUtil.close(ps);
		}
		return result;
	}

	@Override
	public Vector<App> selectAllApps() {
		Vector<App> appList = new Vector<App>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		App app = null;
		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "select * from app where app_state='通过' ";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				app = new App();
				int index = 1;
				app.setId(rs.getInt(index++));
				app.setApp_name(rs.getString(index++));
				app.setApp_type(rs.getString(index++));
				app.setPid(rs.getString(index++));
				app.setCreate_time(rs.getTimestamp(index++));
				app.setMem_id(rs.getInt(index++));
				app.setApp_platform(rs.getString(index++));
				app.setPakage_name(rs.getString(index++));
				app.setApp_crowd(rs.getString(index++));
				app.setApp_gender(rs.getString(index++));
				app.setApk_url(rs.getString(index++));
				app.setApp_state(rs.getString(index++));
				appList.add(app);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return appList;
	}
	
	@Override
	public List<App> selectByAppState(String app_state) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		List <App> listApp=null;
		App app=null;
		try {
			Connection con = ConnectionProvider.getConnection();

			String sql = "select * from app where app_state=? ";

			ps = con.prepareStatement(sql);

			int psIndex = 1;
			ps.setString(psIndex++, app_state);

			rs = ps.executeQuery();
			
			listApp =new ArrayList<App>();
			while (rs.next()) {
				app = new App();
				int index = 1;
				app.setId(rs.getInt(index++));
				app.setApp_name(rs.getString(index++));
				app.setApp_type(rs.getString(index++));
				app.setPid(rs.getString(index++));
				app.setCreate_time(rs.getTimestamp(index++));
				app.setMem_id(rs.getInt(index++));
				app.setApp_platform(rs.getString(index++));
				app.setPakage_name(rs.getString(index++));
				app.setApp_crowd(rs.getString(index++));
				app.setApp_gender(rs.getString(index++));
				app.setApk_url(rs.getString(index++));
				app.setApp_state(rs.getString(index++));
				listApp.add(app);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return listApp;
	}

}
