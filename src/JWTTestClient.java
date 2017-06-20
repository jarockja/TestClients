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

  private static final long EXPIRATION_TIME = 30 * 1000; // 30 sec
  private static final String SUBJECT = "kundenNummer";
  private static final String JTI = "id123456";
  private static final String INIT_SECRET = "RadioPaloma";
  private static final String CLAIM_LIEGENSCHAFT = "prp";
  private static final String CLAIM_KUNDE_NR = "cno";
  private static final String CLAIM_BACK_URL = "bck";


  public static void main(String[] args) {

    //System.out.println(getToken("1533218950", "2297222", true));

    createAndPrintToken("1572003090", "2297222");
    createAndPrintToken("1533218950", "2297222");
    createAndPrintToken("1544400980", "2297222");
    createAndPrintToken("1580402999", "2297222");
    createAndPrintToken("1510142710", "2297222");
    createAndPrintToken("1540600291", "2297222");
    createAndPrintToken("1562618690", "2297222");
    createAndPrintToken("1578707243", "2297222");
    createAndPrintToken("1587008684", "7747534");
    createAndPrintToken("1583903441", "7250681");
    createAndPrintToken("1561112947", "2277496");
    System.out.println();
    createAndPrintToken("4114203047", "7473357");
    createAndPrintToken("4114203330", "7642486");
    createAndPrintToken("8220001904", "7201698");
    createAndPrintToken("8220001980", "7321517");
    createAndPrintToken("8200002688", "7745640");
    createAndPrintToken("8220008577", "6018637");
    createAndPrintToken("8220020607", "3147804");
  }

  private static void createAndPrintToken(String lgNummer, String kundenNummer) {
    System.out.println(lgNummer + "\r\n/login?Authorization=" + getToken(lgNummer, kundenNummer, false));
    System.out.println();
  }


  public static String getToken(String lgNummer, String kundenNummer, boolean logToConsole) {
    String jwtStr = Jwts.builder()
      .setHeaderParam("typ", "JWT")
      .setSubject(SUBJECT)
      .setIssuer("Portal")
      .setIssuedAt(new Date(System.currentTimeMillis()))
      //.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .setId(JTI)
      .claim(CLAIM_LIEGENSCHAFT, lgNummer)
      .claim(CLAIM_KUNDE_NR, kundenNummer)
      .claim(CLAIM_BACK_URL, "/")
      .signWith(SignatureAlgorithm.HS256, INIT_SECRET)
      .compact();

    if (logToConsole) {
      System.out.println("JWT: " + jwtStr);

      Jws<Claims> jws = Jwts.parser().setSigningKey(INIT_SECRET).parseClaimsJws(jwtStr);
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

    return jwtStr;
  }
}
