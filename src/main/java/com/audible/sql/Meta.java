package com.audible.sql;

public class Meta {

  public static final String CONSTRAINTS =
      "SELECT\n"
          + "    tc.constraint_name \"PK_NAME\", \n"
          + "    tc.table_name \"PKTABLE_NAME\", \n"
          + "\tkcu.column_name \"PKCOLUMN_NAME\",\n"
          + "    ccu.table_name \"FKTABLE_NAME\",\n"
          + "    ccu.column_name \"FKCOLUMN_NAME\"\n"
          + "FROM\n"
          + "    information_schema.table_constraints AS tc\n"
          + "    LEFT JOIN information_schema.key_column_usage AS kcu ON tc.constraint_name = kcu.constraint_name\n"
          + "    LEFT JOIN information_schema.constraint_column_usage AS ccu ON ccu.constraint_name = tc.constraint_name\n"
          + "WHERE constraint_type = '${key_type}'\n"
          + "AND upper(tc.table_name) = '${table_name}';";

  public static final String COLUMNS =
      "select cols.column_name\n"
          + "from information_schema.columns cols\n"
          + "where cols.table_catalog='${catalog}' \n"
          + "and upper(cols.table_name) = '${table_name}'";

  public static final String INDEXS =
      "select tablename, indexname, indexdef\n"
          + "from pg_indexes\n"
          + "where upper(tablename) = '${table_name}'";

}
