package com.audible.invoker;

import com.audible.utils.DBUtil;
import java.sql.Connection;

public class TestConnections {

  public static void main(String arg[]) throws Exception {

    Connection connection1 = DBUtil.getOracleConnection();
    System.out.println("Oracle Connection Made="+connection1);
    DBUtil.close(connection1);


    Connection connection2 = DBUtil.getPostgresConnection();
    System.out.println("Postgres Connection Made="+connection2);
    DBUtil.close(connection2);

  }

}