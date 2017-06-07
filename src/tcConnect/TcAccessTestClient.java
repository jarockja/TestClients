package tcConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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

  private static Logger log = Logger.getGlobal();

  public static void main(String[] args) {
    Connection connection = null;
    Statement statement = null;
    try {
      String connectUrl = url_prefix +
        " /C:" + TcaJdbc.JdbcDriver.CON_TCPIP + // Verbindung mit TCPIP
        " /H:" + jdbcHost + // Verbindung mit Host IP-Adresse
        " /P:" + jdbcPort + // Verbindung mit Port
        " /L:N" + // Verbindung ohne Anmeldung
        " /T:N" + // NO Trace
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

      long start = System.currentTimeMillis();
      ResultSet rs = statement.executeQuery("select CDREES.SA820.SA820_001N, CDREES.SA820.SA820_002N, CDREES.SA820.SA820_003, CDREES.SA820.SA820_UUID"
        + " from CDREES.SA820 Where CDREES.SA820.SA820_001N = " + lgNummer + "820 AND CDREES.SA820.SA820_003 <> 'S'");
      long end = System.currentTimeMillis();
      System.out.println("Query-exec took: " + (end - start) + " ms...");

      List<AbrUUID> list = new ArrayList<>();

      while(rs.next()) {
        while (rs.next()){
          AbrUUID uuid = new AbrUUID();
          uuid.setDatei("SA820");
          uuid.setUUID(rs.getString("SA820_UUID"));
          uuid.setKey1(rs.getString("SA820_001N"));
          uuid.setKey2(rs.getString("SA820_002N"));
          uuid.setKey3(rs.getString("SA820_003"));
          log.log(Level.INFO, "Adding new Row to list...");
          list.add(uuid);
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
  static class AbrUUID {
    private String datei;
    private String UUID;
    private String key1;
    private String key2;
    private String key3;

    public String getDatei() {
      return datei;
    }

    public void setDatei(String datei) {
      this.datei = datei;
    }

    public String getUUID() {
      return UUID;
    }

    public void setUUID(String UUID) {
      this.UUID = UUID;
    }

    public String getKey1() {
      return key1;
    }

    public void setKey1(String key1) {
      this.key1 = key1;
    }

    public String getKey2() {
      return key2;
    }

    public void setKey2(String key2) {
      this.key2 = key2;
    }

    public String getKey3() {
      return key3;
    }

    public void setKey3(String key3) {
      this.key3 = key3;
    }
  }
}
