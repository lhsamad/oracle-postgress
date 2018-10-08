package com.audible.migrate;

import com.audible.model.ForeignKeyMeta;
import com.audible.model.PrimaryKeyMeta;
import com.audible.model.Table;
import com.audible.model.TableMeta;
import com.audible.sql.Meta;
import com.audible.utils.ConfigUtil;
import com.audible.utils.DBUtil;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class MetadataGenerator {

  public void generateTableMetaData(List<Table> tables) throws Throwable {
    for (Table table : tables) {
      TableMeta tableMetaOne = getMetaData(table.getName(), DBUtil.getOracleConnection(), false);
      writeMetaDataToFile(tableMetaOne, false);
      TableMeta tableMetaTwo = getMetaData(table.getName(), DBUtil.getPostgresConnection(), true);
      writeMetaDataToFile(tableMetaTwo, true);
    }
  }

  public static TableMeta getMetaData(String tablename, Connection conn, Boolean isPostgres) throws Throwable {
    DatabaseMetaData metadata = conn.getMetaData();
    TableMeta tableMeta = new TableMeta(tablename);
    getColumnsMetadata(tablename, tableMeta, metadata, conn, isPostgres);
    getPrimaryKeys(tablename, tableMeta, metadata, conn, isPostgres);
    getImportedKeys(tablename, tableMeta, metadata, conn, isPostgres);
    return tableMeta;
  }


  public static void getColumnsMetadata(String tablename, TableMeta tableMeta,
      DatabaseMetaData metadata, Connection conn, Boolean isPostgres)
      throws Throwable {

    if(!isPostgres) {
      ResultSet rs = metadata.getColumns(null, null, tablename, null);
      while (rs.next()) {
        tableMeta.getColumns().add(rs.getString("COLUMN_NAME"));
      }
    } else {
      List<Map<String, Object>> list = DBUtil.query(conn,
          Meta.COLUMNS.replace("${table_name}", tablename).replace("${catalog}", conn.getCatalog()),
          false);
      for (Map map : list) {
        tableMeta.getColumns().add((String) map.get("column_name"));
      }
    }
  }

  public static void getPrimaryKeys(String tablename, TableMeta tableMeta,
      DatabaseMetaData metadata, Connection conn, Boolean isPostgres)
      throws Throwable {
    if(!isPostgres) {
      ResultSet PK = metadata.getPrimaryKeys(null, null, tablename);
      while (PK.next()) {
        tableMeta.getPrimaryKeys().add(new PrimaryKeyMeta(PK.getString("PK_NAME"), PK.getString("COLUMN_NAME")));
      }
    }else {
      List<Map<String, Object>> list = DBUtil.query(conn,
          Meta.CONSTRAINTS.replace("${table_name}", tablename).replace("${key_type}", "PRIMARY KEY"), false);
      for(Map map : list){
        tableMeta.getPrimaryKeys().add(new PrimaryKeyMeta((String) map.get("PK_NAME"), (String) map.get("PKCOLUMN_NAME")));
      }
    }

  }

  public static void getImportedKeys(String tablename, TableMeta tableMeta,
      DatabaseMetaData metadata, Connection conn, Boolean isPostgres)
      throws Throwable {

    if(!isPostgres) {
      ResultSet FK = metadata.getImportedKeys(null, null, tablename);
      while (FK.next()) {
        ForeignKeyMeta foreignKeyMeta = new ForeignKeyMeta(
            FK.getString("PK_NAME"), FK.getString("PKTABLE_NAME"), FK.getString("PKCOLUMN_NAME"),
            FK.getString("FKTABLE_NAME"), FK.getString("FKCOLUMN_NAME")
        );
        tableMeta.getForeignKeys().add(foreignKeyMeta);
      }
    }else {
      List<Map<String, Object>> list = DBUtil.query(conn,
          Meta.CONSTRAINTS.replace("${table_name}", tablename).replace("${key_type}", "FOREIGN KEY"), false);
      for(Map map : list){
        ForeignKeyMeta foreignKeyMeta = new ForeignKeyMeta(
            (String) map.get("PK_NAME"), (String) map.get("PKTABLE_NAME"),
            (String) map.get("PKCOLUMN_NAME"),
            (String) map.get("FKTABLE_NAME"), (String) map.get("FKCOLUMN_NAME")
        );
        tableMeta.getForeignKeys().add(foreignKeyMeta);
      }
    }

  }

  private static void writeMetaDataToFile(TableMeta tableMeta, Boolean postgres) throws Throwable {
    FileWriter fw = new FileWriter(getFileWriter(postgres, tableMeta.getTable()));
    StringBuilder builder = new StringBuilder();
    builder.append(tableMeta.getTable()).append("\n ");

    for(String column : tableMeta.getColumns()){
      builder.append(column).append(" ");
    }
    builder.append("\n ");

    for(PrimaryKeyMeta keyMeta : tableMeta.getPrimaryKeys()){
      builder.append(keyMeta.getName()).append(" ").append(keyMeta.getColumn()).append("\n ");
    }

    for(ForeignKeyMeta keyMeta : tableMeta.getForeignKeys()){
      builder.append(keyMeta.getName()).append(" ")
             .append(keyMeta.getPrimaryTable()).append(" ")
             .append(keyMeta.getPrimaryColumn()).append(" ")
             .append(keyMeta.getForeignTable()).append(" ")
             .append(keyMeta.getForeignColumn()).append(" ")
             .append("\n ");
    }

    fw.write(builder.toString().toUpperCase() + " \n");

    if (fw != null) {
      fw.close();
    }
  }

  public static String getFileWriter(boolean postgres, String tablename) throws Throwable {
    if (postgres) {
      return ConfigUtil.getConfig().getString("output.data.dir").replace("diffs", "meta") + "/postgres/" + tablename + ".txt";
    } else {
      return ConfigUtil.getConfig().getString("output.data.dir").replace("diffs", "meta") + "/oracle/" + tablename + ".txt";
    }
  }

  public static void printGeneralMetadata(DatabaseMetaData metadata) throws Exception {
    System.out.println("Database Product Name: " + metadata.getDatabaseProductName());
    System.out.println("Database Product Version: " + metadata.getDatabaseProductVersion());
    System.out.println("Logged User: " + metadata.getUserName());
    System.out.println("JDBC Driver: " + metadata.getDriverName());
    System.out.println("Driver Version: " + metadata.getDriverVersion());
    System.out.println("\n");
  }

}