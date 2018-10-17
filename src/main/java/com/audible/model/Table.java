package com.audible.model;

public class Table {

  private String name;
  private String oracleSql;
  private String postgresSql;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOracleSql() {
    return oracleSql;
  }

  public void setOracleSql(String oracleSql) {
    this.oracleSql = oracleSql;
  }

  public String getPostgresSql() {
    return postgresSql;
  }

  public void setPostgresSql(String postgresSql) {
    this.postgresSql = postgresSql;
  }
}
