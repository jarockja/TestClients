import io.jsonwebtoken.*;

import java.util.Date;

public class JWTTestClient {

  /**
   *
   * {
   *   "typ": "JWT",
   *   "alg": "HS256"
   * }
   *
   * {
   *   "iss": "Portal",
   *   "sub": "kundenNummer",
   *   "exp": 1497354638,
   *   "iat": 1497350754,
   *   "jti": "id123456",
   *   "lie": "1572003090"
   * }
   */
  private static final String key = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrdW5kZW5OdW1tZXIiLCJpc3MiOiJQb3J0YWwiLCJpYXQiOjE0OTczNTgxNjEsImV4cCI6MTQ5NzM1ODI4MSwianRpIjoiaWQxMjM0NTYiLCJsaWUiOiIxNTcyMDAzMDkwIn0.TtBpCZqbwqpWb3aKqLUPjuePPwUpGh86Wf-hDzM2KyA";

  private static final long EXPIRATION_TIME = 2 * 1000; // 2 sec
  private static final String JTI = "id123456";
  private static final String INIT_SECRET = "RadioPaloma";
  private static final String CLAIM_LIEGENSCHAFT = "prp";
  private static final String CLAIM_KUNDE_NR = "cno";
  private static final String CLAIM_BACK_URL = "pbu";
  private static final String CLAIM_EMAIL = "eml";
  private static final String CLAIM_SIMULATION_MODE = "smm";
  private static final String CLAIM_PORTAL_TRACKING = "trk";

  public static void main(String[] args) {
    //parseTokens();
    //createTokens("http://localhost:4200/");
    //createTokens("http://10.49.139.176:8180/ide/");
    //createProdToken("https://ide.ista.com/ide/", "6946019362", false); // ABRE-Prod

    //createProdToken("https://ide1.ista.com/ide/", "1680003021", false); // Cactus-Prod
    //createProdToken("https://ide.ista.com/ide/", "1680002556", false); // Cactus-Prod

    //createProdToken("https://ide1.ista.com/ide/", "4110650120", false);
    //createProdToken("https://ide-test.ista.com/ide/", "6946019362", false);
    System.out.print("Cactus-Test-LG: ");
    createProdToken("https://ide.ista.com/ide/", "3900000994", false);

    System.out.print("ABRE-Test-LG: ");
    createProdToken("https://ide.ista.com/ide/", "6946019362", false);

    //createProdToken("https://ide.ista.com/ide/", "6946019362", false);
    //createProdToken("http://localhost:4200/", "1572003090", false);
    //createProdToken("http://10.49.170.14:8020/ide/", "6946019362", false);
    //createProdToken("http://10.49.139.242:8010/", "4110650120", false);
    // createProdToken("8220079113");
    //createPreProdToken("6143070687");
  }

  private static void parseTokens() {
    // String token = getToken("1572003090", "2297222", null, true);
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c3IiOiJwb3J0ZW5kaSIsInBycCI6IjY5NDYwMTkzNjIiLCJjbm8iOiI3MDUxODcwIiwiYmNrIjoiaHR0cDovL3d3dy5pc3RhLXdlYnBvcnRhbC5kZTo4MC9pc3RhLWxhbmRsb3JkcG9ydGxldC9sYW5kbG9yZC92aWV3L3Byb3BlcnRpZXMvcHJvcGVydGllcy5qc2YiLCJpYXQiOjE1MDkzNTM5MjEsImV4cCI6MTUwOTM1Mzk1MX0.srP1U1dWNAo4ufSUiYOWsS2rY0lFrjrXQDL-NUiC51F1kg_n0nfKATPCEM6orNqhqoNvij_Vm12zyySDCO3s1g";
    try {
      Thread.sleep(2100L);
      parseToken(token);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      parseToken2(token);
    } catch (ExpiredJwtException e) {
      e.printStackTrace();
    }
  }

  private static void createProdToken(String baseUrl, String lgNummer, boolean simulation) {
    Date expiration = new Date(System.currentTimeMillis() + (50 * 1000));
    createAndPrintToken(lgNummer, baseUrl, "2297222", expiration, simulation);
  }

  private static void createPreProdToken(String lgNummer, boolean simulation) {
    Date expiration = new Date(System.currentTimeMillis() + (30 * 1000));
    createAndPrintToken(lgNummer, "https://ide.ista.com/ide/", "2297222", expiration, simulation);
  }

  private static void createTokens(String baseUrl) {
    createAndPrintToken("1572003090", baseUrl, "2297222", null, true);
    createAndPrintToken("1533218950", baseUrl, "2297222", null, true);
    createAndPrintToken("1544400980", baseUrl, "2297222", null, true);
    createAndPrintToken("1580402999", baseUrl, "2297222", null, true);
    createAndPrintToken("1510142710", baseUrl, "2297222", null, true);
    createAndPrintToken("1540600291", baseUrl, "2297222", null, true);
    createAndPrintToken("1562618690", baseUrl, "2297222", null, true);
    createAndPrintToken("1578707243", baseUrl, "2297222", null, true);
    createAndPrintToken("1587008684", baseUrl, "7747534", null, true);
    createAndPrintToken("1583903441", baseUrl, "7250681", null, true);
    createAndPrintToken("1561112947", baseUrl, "2277496", null, true);
    createAndPrintToken("0684902296", baseUrl, "7530160", null, true);
    createAndPrintToken("0769800336", baseUrl, "7037390", null, true);
    System.out.println();
    createAndPrintToken("4114203047", baseUrl, "7473357", null, true);
    createAndPrintToken("4114203330", baseUrl, "7642486", null, true);
    createAndPrintToken("8220001904", baseUrl, "7201698", null, true);
    createAndPrintToken("8220001980", baseUrl, "7321517", null, true);
    createAndPrintToken("8200002688", baseUrl, "7745640", null, true);
    createAndPrintToken("8220008577", baseUrl, "6018637", null, true);
    createAndPrintToken("8220020607", baseUrl, "3147804", null, true);
    System.out.println();
    createAndPrintToken("3050019186", baseUrl, "3147804", null, true);
    createAndPrintToken("3050076660", baseUrl, "3147804", null, true);
    createAndPrintToken("3050007633", baseUrl, "3147804", null, true);
    createAndPrintToken("4890300230", baseUrl, "3147804", null, true);
    createAndPrintToken("3050300055", baseUrl, "3147804", null, true);
    System.out.println();
    createAndPrintToken("6143053111", baseUrl, "3147804", null, true);
    createAndPrintToken("6143302928", baseUrl, "3147804", null, true);
    createAndPrintToken("6143301921", baseUrl, "7268610", null, true);
    createAndPrintToken("7921700312", baseUrl, "7268610", null, true);
    System.out.println();
    System.out.println();
    createAndPrintToken("6143070440", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070458", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070466", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070474", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070482", baseUrl, "7268610", null, true);
    System.out.println();
    createAndPrintToken("6143070490", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070504", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070512", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070520", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070539", baseUrl, "7268610", null, true);
    System.out.println();
    createAndPrintToken("6143070547", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070555", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070563", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070571", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070580", baseUrl, "7268610", null, true);
    System.out.println();
    createAndPrintToken("6143070598", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070601", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070610", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070628", baseUrl, "7268610", null, true);
    createAndPrintToken("6143070636", baseUrl, "7268610", null, true);
    System.out.println();
    System.out.println();
    createAndPrintToken("4110020886", baseUrl, "7268610", null, true);
    createAndPrintToken("7035306859", baseUrl, "7268610", null, true);
    createAndPrintToken("6142009933", baseUrl, "7268610", null, true);
    System.out.println();
    System.out.println();
    createAndPrintToken("6143070687", baseUrl, "7268610", null, true);
  }

  private static void createAndPrintToken(String lgNummer, String urlPrefix, String kundenNummer, Date expiration, boolean simulation) {
    System.out.println(lgNummer);
    System.out.println(urlPrefix + "login?Authorization=" + getToken(lgNummer, kundenNummer, expiration, false, simulation));
    System.out.println();
  }

  static String getToken(String lgNummer, String kundenNummer, Date expiration, boolean logToConsole, boolean simulation) {
    JwtBuilder builder = Jwts.builder()
      .setHeaderParam("typ", "JWT")
      .setIssuer("Portal")
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setId(JTI)
      .claim(CLAIM_LIEGENSCHAFT, lgNummer)
      .claim(CLAIM_KUNDE_NR, kundenNummer)
      .claim(CLAIM_BACK_URL, "http://de.icp.test")
      .claim(CLAIM_EMAIL, "jacek.jarocki@ista.com")
      .claim(CLAIM_SIMULATION_MODE, "" + simulation)
      .claim(CLAIM_PORTAL_TRACKING, "true")
      .signWith(SignatureAlgorithm.HS512, INIT_SECRET.getBytes());
    if (expiration != null) {
      builder.setExpiration(expiration);
    }
    String jwtStr = builder.compact();

    if (logToConsole) {
      System.out.println("JWT: " + jwtStr);
    }
    return jwtStr;
  }

  private static void parseToken(String jwtStr) {
    Jws<Claims> jws = Jwts.parser().setSigningKey(INIT_SECRET).parseClaimsJws(jwtStr);
    System.out.println("Typ: " + jws.getHeader().getType());
    System.out.println(" id: " + jws.getBody().getId());
    System.out.println("iat: " + jws.getBody().getIssuedAt());
    System.out.println("exp: " + jws.getBody().getExpiration());
    System.out.println("iss: " + jws.getBody().getIssuer());
    System.out.println("jti: " + jws.getBody().getSubject());
    System.out.println(CLAIM_LIEGENSCHAFT + ": " + jws.getBody().get(CLAIM_LIEGENSCHAFT));
    System.out.println(CLAIM_KUNDE_NR + ": " + jws.getBody().get(CLAIM_KUNDE_NR));
    System.out.println(CLAIM_BACK_URL + ": " + jws.getBody().get(CLAIM_BACK_URL));
  }

  private static void parseToken2(String jwtStr) {
    String withoutSignature = jwtStr.substring(0, jwtStr.lastIndexOf('.')+1);
    Jwt<Header,Claims> jws = Jwts.parser().parseClaimsJwt(withoutSignature);

    System.out.println("Typ: " + jws.getHeader().getType());
    System.out.println(" id: " + jws.getBody().getId());
    System.out.println("iat: " + jws.getBody().getIssuedAt());
    System.out.println("exp: " + jws.getBody().getExpiration());
    System.out.println("iss: " + jws.getBody().getIssuer());
    System.out.println("jti: " + jws.getBody().getSubject());
    System.out.println(CLAIM_LIEGENSCHAFT + ": " + jws.getBody().get(CLAIM_LIEGENSCHAFT));
    System.out.println(CLAIM_KUNDE_NR + ": " + jws.getBody().get(CLAIM_KUNDE_NR));
    System.out.println(CLAIM_BACK_URL + ": " + jws.getBody().get(CLAIM_BACK_URL));
  }

  private static void parseTokenFuerThomas(String jwtStr) {
    Jws<Claims> jws = Jwts.parser().setSigningKey(INIT_SECRET.getBytes()).parseClaimsJws(jwtStr);
    System.out.println("Typ: " + jws.getHeader().getType());
    System.out.println("Alg: " + jws.getHeader().getAlgorithm());
    System.out.println(" id: " + jws.getBody().getId());
    System.out.println("iat: " + jws.getBody().getIssuedAt());
    System.out.println("exp: " + jws.getBody().getExpiration());
    System.out.println("iss: " + jws.getBody().getIssuer());
    System.out.println("jti: " + jws.getBody().getSubject());
    System.out.println(CLAIM_LIEGENSCHAFT + ": " + jws.getBody().get(CLAIM_LIEGENSCHAFT));
    System.out.println(CLAIM_KUNDE_NR + ": " + jws.getBody().get(CLAIM_KUNDE_NR));
    System.out.println(CLAIM_BACK_URL + ": " + jws.getBody().get(CLAIM_BACK_URL));
  }

}
