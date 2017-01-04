package vseConnector; /******************************************************************************/
/*           VSE/ESA Connector Framework - Example Code                       */
/******************************************************************************/
/*                                                                            */
/* MODULE NAME : JdbcExample.java                                             */
/*                                                                            */
/* DESCRIPTIVE NAME : Shows how to issue SQL requests against VSAM data.      */
/*                                                                            */
/* RELEASE : VSE/ESA Version 2.6.0                                            */
/*                                                                            */
/* COPYRIGHT NOTICE: Copyright (C) 2000, 2001.  IBM Corporation.              */
/*                                                                            */
/*   This file is used to demonstrate how to utilize IBM Corporation's        */
/*   VSE/ESA Connector Framework Java classes.                                */
/*                                                                            */
/*   You have a royalty-free right to use, modify, reproduce and              */
/*   distribute this demonstration file (including any modified               */
/*   version), provided that you agree that IBM Corporation                   */
/*   has no warranty, implied or otherwise, or liability                      */
/*   for this demonstration file or any modified version.                     */
/*                                                                            */
/* COMPILER :                                                                 */
/*                                                                            */
/*      Java 1.3   or higher                                                  */
/*                                                                            */
/******************************************************************************/
/*                                                                            */
/* CHANGE ACTIVITY :                                                          */
/*                                                                            */
/*                                                                            */
/******************************************************************************/
/*

Description of the VSAM Clusters used for this Sample:
------------------------------------------------------

The FlightOrdering Servlet uses two VSAM Clusters:

1.) FLIGHT.ORDERING.FLIGHTS (FLIGHTS)  - KSDS

Both are defined (as default) in the VSESP.USER.CATALOG (VSESPUC).

The FLIGHT.ORDERING.FLIGHTS Cluster:
-----------------------------------

The Record Layout of the FLIGHTS Cluster is defined as follows:

Offset Length Type      Key    Field Name     Description
-------------------------------------------------------------------
0      4      UNSIGNED  yes    FLIGHT_NUMBER  Flight Number
4      20     STRING    no     START          Start
24     20     STRING    no     DESTINATION    Destination
44     5      STRING    no     DEPARTURE      Departure (hh:mm)
49     5      STRING    no     ARRIVAL         arrival    (hh:mm)
54     4      UNSIGNED  no     SEATS          Seats
58     4      UNSIGNED  no     RESERVED       Seats reserved
62     4      PACKED    no     PRICE          Price
66     20     STRING    no     AIRLINE        Airline

The Key of the Cluster is a 4 Byte field at Offset 0.
The Record Length is 86 bytes.
The Record Layout is reflected in the Map FLIGHTS_MAP

The following Job can be used to define the cluster:

* $$ JOB JNM=DEFINE,CLASS=0,DISP=D
// JOB DEFINE CLUSTERS FOR FLIGHT ORDERING SERVLET
// EXEC IDCAMS,SIZE=AUTO
 DEFINE CLUSTER ( -
        NAME (FLIGHT.ORDERING.FLIGHTS                      ) -
        CYLINDERS(2        2          ) -
        SHAREOPTIONS (2) -
        RECORDSIZE (86     86    ) -
        VOLUMES (DOSRES SYSWK1 ) -
        NOREUSE -
        INDEXED -
        FREESPACE (15 7) -
        KEYS (4   0     ) -
        NOCOMPRESSED -
        TO (99366  )) -
        DATA (NAME (FLIGHT.ORDERING.FLIGHTS.DATA                  ) -
        CONTROLINTERVALSIZE (4096  )) -
        INDEX (NAME (FLIGHT.ORDERING.FLIGHTS.INDEX               )) -
        CATALOG (VSESP.USER.CATALOG                         )
  IF LASTCC NE 0 THEN CANCEL JOB
/*
// OPTION STDLABEL=ADD
// DLBL FLIGHTS,'FLIGHT.ORDERING.FLIGHTS',,VSAM,CAT=VSESPUC
/*
// EXEC IESVCLUP,SIZE=AUTO
A FLIGHT.ORDERING.FLIGHTS                      FLIGHTS VSESPUC
/*
/&
* $$ EOJ

*/

import java.io.*;
import java.math.BigDecimal;
import java.sql.*;

/**
 * Class JdbcExample.
 * @version 1.0
 *
 */
public class ABREJdbcAccessClient
{
  // Default port number.
  static String   vsePort     = "2893";

  // Names of the Clusters and Maps.
  static String vsamCatalog    = "ABRE.UCAT";
  static String clusterName = "P.VEX.DELTA";
  static String mapName = "MAPDELTA";

  /**
   * Main method of the sample. It is assumed that there is a
   * VSEVsamMap defined for the FLIGHTS cluster that describes
   * the data fields. For an example of how to create a map for
   * a given VSAM cluster, please refer to the example in
   * VsamDataMapping.java
   */
  public static void main(String argv[]) throws IOException
  {
    String vseHostName = "10.49.103.238", userID = "connect", password = "connect";

    java.sql.Driver  jdbcDriver = null;
    java.sql.Connection  jdbcCon = null;
    java.sql.PreparedStatement stmt = null;
    java.sql.PreparedStatement pstmt = null;

      /* Step 1: Create an instance of the VSAM JDBC Driver and */
      /* get a host connection */
    try
    {
         /* Create an instance of the JDBC driver */
      jdbcDriver = (java.sql.Driver) Class.forName("com.ibm.vse.jdbc.VsamJdbcDriver").newInstance();

      // Build the URL to use to connect
      String url = "jdbc:vsam:"+vseHostName;

      // Assign properties for the driver
      java.util.Properties prop = new java.util.Properties();
      prop.put("port", vsePort);
      prop.put("user", userID);
      prop.put("password", password);

      // Connect to the driver
      System.out.println("Connecting to the host...");
      jdbcCon = DriverManager.getConnection(url, prop);
    }
    catch (Throwable t)
    {
      System.out.println("Exception when trying to connect to the JDBC driver.");
      System.out.println(t);
    }

      /* Step 2: Display a list of database rows (that in fact are */
      /* VSAM records) */
    try
    {
      String sql = "SELECT * FROM " + vsamCatalog + "\\" + clusterName + "\\" + mapName;

      // Get a statement
      stmt = jdbcCon.prepareStatement(sql);
      //stmt.setString(1, "");
      // Execute the query
      System.out.println("Executing the SQL SELECT statement...");
      System.out.println(sql);
      java.sql.ResultSet rs = stmt.executeQuery();

      // Walk through the results
      ResultSetMetaData rsmd = rs.getMetaData();
      while (rs.next()) {
        System.out.println("Col 1: name=" + rsmd.getColumnName(1) + ", '" + rs.getString(1) + "'");
        System.out.println("Col 2: name=" + rsmd.getColumnName(2) + ", '" + rs.getString(2) + "'");
        System.out.println("Col 3: name=" + rsmd.getColumnName(3) + ", '" + rs.getString(3) + "'");
        System.out.println("Col 4: name=" + rsmd.getColumnName(4) + ", '" + rs.getString(4) + "'");
        System.out.println("Col 5: name=" + rsmd.getColumnName(5) + ", '" + rs.getString(5) + "'");
        System.out.println("Col 6: name=" + rsmd.getColumnName(6) + ", '" + rs.getString(6) + "'");
        System.out.println("Col 7: name=" + rsmd.getColumnName(7) + ", '" + rs.getString(7) + "'");
        System.out.println("Col 8: name=" + rsmd.getColumnName(8) + ", '" + rs.getString(8) + "'");
        System.out.println("Col 9: name=" + rsmd.getColumnName(9) + ", '" + rs.getString(9) + "'");
      }
      System.out.println("Got Database rows: " + rs.getRow());
      rs.close();
      stmt.close();
    }
    catch (SQLException t)
    {
      System.out.println("SQLException when trying to issue the SELECT");
      System.out.println(t);
      t.printStackTrace();
    }

      /* Step 3: Add a new flight to the database */
    /*
    try {
      pstmt = jdbcCon.prepareStatement(
        "INSERT INTO "+vsamCatalog+"\\"+ clusterName +"\\"+ mapName +
          " (FLIGHT_NUMBER,START,DESTINATION,DEPARTURE,ARRIVAL,PRICE,AIRLINE)"+
          " VALUES(?,?,?,?,?,?,?)");

      // Set the values for the new database row
      pstmt.setInt(1,   398);
      pstmt.setString(2,"Honolulu");
      pstmt.setString(3,"Bankok");
      pstmt.setString(4,"07:30");
      pstmt.setString(5,"22:45");
      pstmt.setInt(6,   1500);
      pstmt.setString(7,"NeverComeBack");

      // Execute the query
      System.out.println("Adding new row to the database...");
      num = pstmt.executeUpdate();
      if (num == 1)
        System.out.println("Row added to database.");
      else
        System.out.println("*** Error adding new row. ***");
      pstmt.close();
    }
    catch (SQLException t)
    {
      System.out.println("SQLException when trying to issue the INSERT");
      System.out.println(t);
    }
*/
      /* Step 4: Delete this row from the database */
      /*
    try {
      pstmt = jdbcCon.prepareStatement(
        "DELETE FROM "+vsamCatalog+"\\"+ clusterName +"\\"+ mapName +
          " WHERE FLIGHT_NUMBER=?");

      pstmt.setInt(1,398);

      // execute the query
      System.out.println("Deleting row from database...");
      num = pstmt.executeUpdate();
      if (num == 1)
        System.out.println("Flight 398 deleted from database.");
      else
        System.out.println("*** Error deleting flight 398 ***");
      pstmt.close();
    }
    catch (SQLException t)
    {
      System.out.println("SQLException when trying to issue the DELETE");
      System.out.println(t);
    }
    */
  }
}
