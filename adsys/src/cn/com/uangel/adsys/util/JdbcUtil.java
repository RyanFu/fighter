package cn.com.uangel.adsys.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcUtil {
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
        }
    }

    public static void close(Statement st) {
        try {
            st.close();
        } catch (Exception e) {
        }
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (Exception e) { 
        }
    }

    public static void close(Statement st, Connection con) {
        close(st);
        close(con);
    }

    public static void close(ResultSet rs, Statement st, Connection con) {
        close(rs);
        close(st, con);
    }

    public static void close(ResultSet rs, Statement st) {
        close(rs);
        close(st);
    }
}