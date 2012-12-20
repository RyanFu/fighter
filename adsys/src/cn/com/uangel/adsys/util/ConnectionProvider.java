package cn.com.uangel.adsys.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接类，所有service方法开始是应调时beginTransaction()方法，结束时应调用endTransaction()方法
 * 
 * @author Tree
 * 
 */
public class ConnectionProvider {
	public static String CONFIG_FILE = "database.properties";
	private static Properties config = new Properties();

	static {
		try {
			InputStream in = ConnectionProvider.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
			config.load(in);
			in.close();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new java.lang.ExceptionInInitializerError(t);
		}
	}

	private static ThreadLocal<Connection> connections = new ThreadLocal<Connection>();

	public static Connection getConnection() throws SQLException {
		Connection con = connections.get();
		if (con == null) {
			Log.debug("No transaction started for thread:" + Thread.currentThread().getName());
			throw new SQLException("No transaction started");
		}

		return con;
	}

	public static void beginTransaction() throws SQLException {
		Connection con = connections.get();

		if (con == null) {
			con = openConnection();
			con.setAutoCommit(false);
			connections.set(con);

			Log.debug("Start a new Transaction for thread:" + Thread.currentThread().getName());
		}
	}

	public static void endTransaction(boolean shouldCommit) throws SQLException {
		Connection con = connections.get();
		if (con == null) {
			Log.debug("No transaction started for thread:" + Thread.currentThread().getName());
			throw new SQLException("No transaction started");
		} else {
			try {
				if (shouldCommit) {
					con.commit();
					Log.debug("Commit transaction for thread:" + Thread.currentThread().getName());
				} else {
					con.rollback();
					Log.debug("Rollback transaction for thread:" + Thread.currentThread().getName());
				}
			} finally {
				releaseConnection();
			}
		}
	}

	public static Connection openConnection() {
		try {
			Class.forName(config.getProperty("driver"));
			return DriverManager.getConnection(config.getProperty("url"), config.getProperty("user"), config
					.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void releaseConnection() {
		Log.debug("Release connection for thread:" + Thread.currentThread().getName());

		Connection con = connections.get();
		if (con != null) {
			JdbcUtil.close(con);
		}
		connections.remove();
	}
}
