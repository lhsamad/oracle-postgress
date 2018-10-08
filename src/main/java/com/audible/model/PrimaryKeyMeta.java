package com.audible.model;

public class PrimaryKeyMeta {

  private String name;
  private String column;

  public PrimaryKeyMeta(String name, String column) {
    this.name = name;
    this.column = column;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  @Override
  public String toString() {
    return "PrimaryKeyMeta{" +
        "name='" + name + '\'' +
        ", column='" + column + '\'' +
        '}';
  }
}
