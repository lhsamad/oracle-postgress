package com.audible.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class DBUtil {

  public static Connection getPostgresConnection() throws Exception {
    String url = ConfigUtil.getConfig().getString("postgres.url");
    String user = ConfigUtil.getConfig().getString("postgres.user");
    String pass = ConfigUtil.getConfig().getString("postgres.pass");
    Properties props = new Properties();
    props.setProperty("user", user);
    props.setProperty("password", pass);
    props.setProperty("ssl", "true");
    props.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
    return DriverManager.getConnection(url, props);
  }

  public static Connection getOracleConnection() throws Exception {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String url = ConfigUtil.getConfig().getString("oracle.url");
    String user = ConfigUtil.getConfig().getString("oracle.user");
    String pass = ConfigUtil.getConfig().getString("oracle.pass");
    Connection connection =  DriverManager.getConnection(url, user, pass);
    return connection;
  }


  public static List<Map<String, Object>> query(Connection conn, String query, boolean close) throws Throwable {
    try {
      Statement stmt = ((Connection) conn).createStatement();
      ResultSet rs = stmt.executeQuery(query);
      boolean res = rs.next();
      List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
      ResultSetMetaData meta = rs.getMetaData();
      int numberOfColumns = meta.getColumnCount();
      while (res) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        for (int i = 1; i <= numberOfColumns; ++i) {
          String name = meta.getColumnName(i);
          Object value = rs.getObject(i);
          if (value instanceof BigDecimal) {
            int scale = meta.getScale(i);
            if (scale > 0) {
              value = new Long((((BigDecimal) value).longValue()));
            } else {
              value = new Long(((BigDecimal) value).longValue());
            }
          }
          if (value instanceof BigInteger) {
            value = new Long(((BigInteger) value).longValue());
          } else if (value instanceof oracle.sql.CLOB) {
            value = rs.getString(i);
          } else if (value instanceof oracle.sql.BLOB) {
            value = rs.getObject(i);
          }
          map.put(name, value);
        }
        ret.add(map);
        res = rs.next();
      }
      rs.close();
      stmt.close();
      rs = null;
      stmt = null;
      return ret;
    } catch (Throwable t) {
      throw t;
    } finally {
      if(close) {
        close(conn);
      }
    }
  }

  public boolean execute(Connection conn, String query) throws Throwable {
    boolean total = false;
    try {
      Statement stmt = conn.createStatement();
      total = stmt.execute(query);
      stmt.close();
    } catch (Throwable t) {
      throw t;
    } finally {
      if (conn instanceof Connection) {
        conn.close();
      }
    }
    return total;
  }

  public static void commit(Connection conn) {
    if (conn != null) {
      try {
        conn.commit();
      } catch (Throwable t) {
        System.err.println(
            "${new Date()} - WARNING: error commiting connection - stacktrace:" + ExceptionUtils
                .getStackTrace(t));
      }
    }
  }

  public static void rollback(Connection conn) {
    if (conn != null) {
      try {
        conn.rollback();
      } catch (Throwable t) {
        System.err.println(
            "${new Date()} - WARNING: error rolling back connection - stacktrace:" + ExceptionUtils
                .getStackTrace(t));
      }
    }
  }

  public static void close(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (Throwable t) {
        System.err.println(
            "${new Date()} - WARNING: error closing connection - stacktrace:" + ExceptionUtils
                .getStackTrace(t));
      }
    }
  }

}
