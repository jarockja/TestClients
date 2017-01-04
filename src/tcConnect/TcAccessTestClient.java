package tcConnect;

import java.sql.*;
import java.util.Properties;

/**
 * Created by jarockja on 04.01.2017.
 */
public class TcAccessTestClient {

  private static Long lgNummer = Long.valueOf("1572003090");
  private static String driver = "TcaJdbc.JdbcDriver";
  private static String url_prefix = "jdbc:TCAJDBC";
  private static String jdbcHost = "10.49.103.238";
  private static String jdbcPort = "3021";
  private static String configDir = "D:/software/tcaccess";

  public static void main(String[] args) {
    Connection connection = null;
    Statement statement = null;
    try {
      String connectUrl = url_prefix +
        " /C:" + TcaJdbc.JdbcDriver.CON_TCPIP + // Verbindung mit TCPIP
        " /H:" + jdbcHost + // Verbindung mit Host IP-Adresse
        " /P:" + jdbcPort + // Verbindung mit Port
        " /L:N" + // Verbindung ohne Anmeldung
        " /S:tcajdbc.slc" + // Verbindung mit Hilfe von slc (Standard ist tcajdbc)
        " /D:" + TcaJdbc.JdbcDriver.DB_SQLENGINE + // SQL ENGINE
        " /Y:" + configDir; // ConfigDateienPfad

      Class.forName(driver);
      DriverManager.setLogWriter(null);
      Driver jdbcDriver = DriverManager.getDriver(connectUrl);
      System.out.println("Connection to url: " + connectUrl);
      connection = jdbcDriver.connect(connectUrl, new Properties());
      statement = connection.createStatement();
      System.out.println("Connection successful!");

      ResultSet rs = statement.executeQuery("select CDREES.SA820.SA820_001N, CDREES.SA820.SA820_002N, CDREES.SA820.SA820_003, CDREES.SA820.SA820_UUID"
        + " from CDREES.SA820 Where CDREES.SA820.SA820_001N = " + lgNummer + "820 AND CDREES.SA820.SA820_003 <> 'S'");

      ResultSetMetaData rsMeta = rs.getMetaData();
      while(rs.next()) {
        int colCount = rsMeta.getColumnCount();
        for (int i = 1; i <= colCount; i++) {
          System.out.println("Column: " + rsMeta.getColumnName(i) + ", value=" + rs.getString(i));
        }
      }

    } catch (Exception e) {
      System.out.println("Connection failed...");
      e.printStackTrace();
    }
    finally {
      try {
        System.out.println("Closing connection...");
        statement.close();
        connection.close();
        System.exit(0);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
