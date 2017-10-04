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
  private static final String SUBJECT = "kundenNummer";
  private static final String JTI = "id123456";
  private static final String INIT_SECRET = "RadioPaloma";
  private static final String CLAIM_LIEGENSCHAFT = "prp";
  private static final String CLAIM_KUNDE_NR = "cno";
  private static final String CLAIM_BACK_URL = "bck";


  public static void main(String[] args) {
    //parseTokens();
    //createTokens();
    createProdToken("1572003090");
  }

  private static void parseTokens() {
    String token = getToken("1572003090", "2297222", null, true);
    //String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c3IiOiJhZG1pbiIsInBycCI6IjY5NDYzNDA2OTEiLCJjbm8iOiIzMDAwMTIwIiwiYmNrIjoiaHR0cDovL2RlLmljcC50ZXN0OjgwL2lzdGEtbGFuZGxvcmRwb3J0bGV0L2xhbmRsb3JkL3ZpZXcvcHJvcGVydGllcy9wcm9wZXJ0aWVzLmpzZiIsImlhdCI6MTUwNDI2MDMwMTQ2OH0.jmRuKfYsiF9utMaQjEYAWFC2TNduUbTRUdA3ErJigqFTSWJvC_8mEBApVv6yYksBFVnelYPISLAT23WShxN9Gg";
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

  private static void createProdToken(String lgNummer) {
    Date expiration = new Date(System.currentTimeMillis() + (25 * 1000));
    createAndPrintToken(lgNummer, "2297222", expiration);
  }

  private static void createTokens() {
    createAndPrintToken("1572003090", "2297222", null);
    createAndPrintToken("1533218950", "2297222", null);
    createAndPrintToken("1544400980", "2297222", null);
    createAndPrintToken("1580402999", "2297222", null);
    createAndPrintToken("1510142710", "2297222", null);
    createAndPrintToken("1540600291", "2297222", null);
    createAndPrintToken("1562618690", "2297222", null);
    createAndPrintToken("1578707243", "2297222", null);
    createAndPrintToken("1587008684", "7747534", null);
    createAndPrintToken("1583903441", "7250681", null);
    createAndPrintToken("1561112947", "2277496", null);
    createAndPrintToken("0684902296", "7530160", null);
    createAndPrintToken("0769800336", "7037390", null);
    System.out.println();
    createAndPrintToken("4114203047", "7473357", null);
    createAndPrintToken("4114203330", "7642486", null);
    createAndPrintToken("8220001904", "7201698", null);
    createAndPrintToken("8220001980", "7321517", null);
    createAndPrintToken("8200002688", "7745640", null);
    createAndPrintToken("8220008577", "6018637", null);
    createAndPrintToken("8220020607", "3147804", null);
    System.out.println();
    createAndPrintToken("3050019186", "3147804", null);
    createAndPrintToken("3050076660", "3147804", null);
    createAndPrintToken("3050007633", "3147804", null);
    createAndPrintToken("4890300230", "3147804", null);
    createAndPrintToken("3050300055", "3147804", null);
    System.out.println();
    createAndPrintToken("6143053111", "3147804", null);
    createAndPrintToken("6143302928", "3147804", null);
    createAndPrintToken("6143301921", "7268610", null);
    createAndPrintToken("7921700312", "7268610", null);
    System.out.println();
    System.out.println();
    createAndPrintToken("6143070440", "7268610", null);
    createAndPrintToken("6143070458", "7268610", null);
    createAndPrintToken("6143070466", "7268610", null);
    createAndPrintToken("6143070474", "7268610", null);
    createAndPrintToken("6143070482", "7268610", null);
    System.out.println();
    createAndPrintToken("6143070490", "7268610", null);
    createAndPrintToken("6143070504", "7268610", null);
    createAndPrintToken("6143070512", "7268610", null);
    createAndPrintToken("6143070520", "7268610", null);
    createAndPrintToken("6143070539", "7268610", null);
    System.out.println();
    createAndPrintToken("6143070547", "7268610", null);
    createAndPrintToken("6143070555", "7268610", null);
    createAndPrintToken("6143070563", "7268610", null);
    createAndPrintToken("6143070571", "7268610", null);
    createAndPrintToken("6143070580", "7268610", null);
    System.out.println();
    createAndPrintToken("6143070598", "7268610", null);
    createAndPrintToken("6143070601", "7268610", null);
    createAndPrintToken("6143070610", "7268610", null);
    createAndPrintToken("6143070628", "7268610", null);
    createAndPrintToken("6143070636", "7268610", null);
    System.out.println();
    System.out.println();
    createAndPrintToken("4110020886", "7268610", null);
    createAndPrintToken("7035306859", "7268610", null);
    createAndPrintToken("6142009933", "7268610", null);
  }

  private static void createAndPrintToken(String lgNummer, String kundenNummer, Date expiration) {
    System.out.println(lgNummer + "\r\nlogin?Authorization=" + getToken(lgNummer, kundenNummer, expiration, false));
    System.out.println();
  }

  static String getToken(String lgNummer, String kundenNummer, Date expiration, boolean logToConsole) {
    JwtBuilder builder = Jwts.builder()
      .setHeaderParam("typ", "JWT")
      .setSubject(SUBJECT)
      .setIssuer("Portal")
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .setId(JTI)
      .claim(CLAIM_LIEGENSCHAFT, lgNummer)
      .claim(CLAIM_KUNDE_NR, kundenNummer)
      .claim(CLAIM_BACK_URL, "/ide/")
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
