package com.audible.model;

import java.util.ArrayList;
import java.util.List;

public class TableMeta {

  public TableMeta(String table) {
    this.table = table;
  }

  private String table;
  private List<String> columns = new ArrayList<>();
  private List<PrimaryKeyMeta> primaryKeys = new ArrayList<>();
  private List<ForeignKeyMeta> foreignKeys = new ArrayList<>();



  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public List<String> getColumns() {
    return columns;
  }

  public void setColumns(List<String> columns) {
    this.columns = columns;
  }

  public List<PrimaryKeyMeta> getPrimaryKeys() {
    return primaryKeys;
  }

  public void setPrimaryKeys(List<PrimaryKeyMeta> primaryKeys) {
    this.primaryKeys = primaryKeys;
  }

  public List<ForeignKeyMeta> getForeignKeys() {
    return foreignKeys;
  }

  public void setForeignKeys(List<ForeignKeyMeta> foreignKeys) {
    this.foreignKeys = foreignKeys;
  }

  @Override
  public String toString() {
    return "TableMeta{" +
        "table='" + table + '\'' +
        ", columns=" + columns +
        ", primaryKeys=" + primaryKeys +
        ", foreignKeys=" + foreignKeys +
        '}';
  }
}
