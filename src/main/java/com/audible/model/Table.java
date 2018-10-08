package com.audible.model;

public class Table {

  private String name;
  private String sql;

  public String getPostgresSql() {
    return sql.trim()+"\n";
  }

  public String getOracleSql() {
    return sql.replace("booker.", "").trim()+"\n";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

}
