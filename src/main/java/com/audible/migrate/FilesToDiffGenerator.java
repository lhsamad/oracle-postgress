package com.audible.migrate;

import com.audible.model.Table;
import com.audible.utils.ConfigUtil;
import com.audible.utils.DBUtil;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class FilesToDiffGenerator {

  public void generateTableDiffs(List<Table> tables) throws Throwable {
    for (Table table : tables) {
      System.out.println("Processing table >> " + table.getName());
      System.out.println("Sql >> "+table.getOracleSql());
      writeResultSetToFile(DBUtil.getPostgresConnection(), table.getPostgresSql(), table.getName(), true);
      writeResultSetToFile(DBUtil.getOracleConnection(), table.getOracleSql(), table.getName(), false);
    }
  }

  private void writeResultSetToFile(Connection conn, String sql, String tablename, Boolean postgres) throws Throwable {
    FileWriter fw = new FileWriter(getFileWriter(postgres, tablename));
    List<Map<String, Object>> fields = DBUtil.query(conn, sql, true);
    for (Map<String, Object> map : fields) {
      StringBuilder builder = new StringBuilder();
      for (String key : map.keySet()) {
        Object object = map.get(key);
        if (object != null) {
          builder.append(object).append(" ");
        } else {
          builder.append("").append(" ");
        }
      }
      fw.write(builder.toString() + " \n");
    }
    if (fw != null) {
      fw.close();
    }
  }

  public String getFileWriter(boolean postgres, String tablename) throws Throwable {
    if (postgres) {
      return ConfigUtil.getConfig().getString("output.data.dir") + "/postgres/" + tablename + ".txt";
    } else {
      return ConfigUtil.getConfig().getString("output.data.dir") + "/oracle/" + tablename + ".txt";
    }
  }


}
