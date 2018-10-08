package com.audible.model;

public class ForeignKeyMeta {

  private String name;
  private String primaryTable;
  private String primaryColumn;
  private String foreignTable;
  private String foreignColumn;

  public ForeignKeyMeta(String name, String primaryTable, String primaryColumn,
      String foreignTable, String foreignColumn) {
    this.name = name;
    this.primaryTable = primaryTable;
    this.primaryColumn = primaryColumn;
    this.foreignTable = foreignTable;
    this.foreignColumn = foreignColumn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrimaryTable() {
    return primaryTable;
  }

  public void setPrimaryTable(String primaryTable) {
    this.primaryTable = primaryTable;
  }

  public String getPrimaryColumn() {
    return primaryColumn;
  }

  public void setPrimaryColumn(String primaryColumn) {
    this.primaryColumn = primaryColumn;
  }

  public String getForeignTable() {
    return foreignTable;
  }

  public void setForeignTable(String foreignTable) {
    this.foreignTable = foreignTable;
  }

  public String getForeignColumn() {
    return foreignColumn;
  }

  public void setForeignColumn(String foreignColumn) {
    this.foreignColumn = foreignColumn;
  }

  @Override
  public String toString() {
    return "ForeignKeyMeta{" +
        "name='" + name + '\'' +
        ", primaryTable='" + primaryTable + '\'' +
        ", primaryColumn='" + primaryColumn + '\'' +
        ", foreignTable='" + foreignTable + '\'' +
        ", foreignColumn='" + foreignColumn + '\'' +
        '}';
  }
}
