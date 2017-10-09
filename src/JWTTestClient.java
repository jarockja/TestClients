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
  private static final String CLAIM_BACK_URL = "bck";


  public static void main(String[] args) {
    //parseTokens();
    createTokens();
    //createProdToken("6143070687");
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
    Date expiration = new Date(System.currentTimeMillis() + (30 * 1000));
    createAndPrintToken(lgNummer, "https://ide.ista.com/ide/", "2297222", expiration);
  }

  private static void createTokens() {
    createAndPrintToken("1572003090", "http://localhost:4200/", "2297222", null);
    createAndPrintToken("1533218950", "http://localhost:4200/", "2297222", null);
    createAndPrintToken("1544400980", "http://localhost:4200/", "2297222", null);
    createAndPrintToken("1580402999", "http://localhost:4200/", "2297222", null);
    createAndPrintToken("1510142710", "http://localhost:4200/", "2297222", null);
    createAndPrintToken("1540600291", "http://localhost:4200/", "2297222", null);
    createAndPrintToken("1562618690", "http://localhost:4200/", "2297222", null);
    createAndPrintToken("1578707243", "http://localhost:4200/", "2297222", null);
    createAndPrintToken("1587008684", "http://localhost:4200/", "7747534", null);
    createAndPrintToken("1583903441", "http://localhost:4200/", "7250681", null);
    createAndPrintToken("1561112947", "http://localhost:4200/", "2277496", null);
    createAndPrintToken("0684902296", "http://localhost:4200/", "7530160", null);
    createAndPrintToken("0769800336", "http://localhost:4200/", "7037390", null);
    System.out.println();
    createAndPrintToken("4114203047", "http://localhost:4200/", "7473357", null);
    createAndPrintToken("4114203330", "http://localhost:4200/", "7642486", null);
    createAndPrintToken("8220001904", "http://localhost:4200/", "7201698", null);
    createAndPrintToken("8220001980", "http://localhost:4200/", "7321517", null);
    createAndPrintToken("8200002688", "http://localhost:4200/", "7745640", null);
    createAndPrintToken("8220008577", "http://localhost:4200/", "6018637", null);
    createAndPrintToken("8220020607", "http://localhost:4200/", "3147804", null);
    System.out.println();
    createAndPrintToken("3050019186", "http://localhost:4200/", "3147804", null);
    createAndPrintToken("3050076660", "http://localhost:4200/", "3147804", null);
    createAndPrintToken("3050007633", "http://localhost:4200/", "3147804", null);
    createAndPrintToken("4890300230", "http://localhost:4200/", "3147804", null);
    createAndPrintToken("3050300055", "http://localhost:4200/", "3147804", null);
    System.out.println();
    createAndPrintToken("6143053111", "http://localhost:4200/", "3147804", null);
    createAndPrintToken("6143302928", "http://localhost:4200/", "3147804", null);
    createAndPrintToken("6143301921", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("7921700312", "http://localhost:4200/", "7268610", null);
    System.out.println();
    System.out.println();
    createAndPrintToken("6143070440", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070458", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070466", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070474", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070482", "http://localhost:4200/", "7268610", null);
    System.out.println();
    createAndPrintToken("6143070490", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070504", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070512", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070520", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070539", "http://localhost:4200/", "7268610", null);
    System.out.println();
    createAndPrintToken("6143070547", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070555", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070563", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070571", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070580", "http://localhost:4200/", "7268610", null);
    System.out.println();
    createAndPrintToken("6143070598", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070601", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070610", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070628", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6143070636", "http://localhost:4200/", "7268610", null);
    System.out.println();
    System.out.println();
    createAndPrintToken("4110020886", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("7035306859", "http://localhost:4200/", "7268610", null);
    createAndPrintToken("6142009933", "http://localhost:4200/", "7268610", null);
    System.out.println();
    System.out.println();
    createAndPrintToken("6143070687", "http://localhost:4200/", "7268610", null);
  }

  private static void createAndPrintToken(String lgNummer, String urlPrefix, String kundenNummer, Date expiration) {
    System.out.println(lgNummer);
    System.out.println(urlPrefix + "login?Authorization=" + getToken(lgNummer, kundenNummer, expiration, false));
    System.out.println();
  }

  static String getToken(String lgNummer, String kundenNummer, Date expiration, boolean logToConsole) {
    JwtBuilder builder = Jwts.builder()
      .setHeaderParam("typ", "JWT")
      .setIssuer("Portal")
      .setIssuedAt(new Date(System.currentTimeMillis()))
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
