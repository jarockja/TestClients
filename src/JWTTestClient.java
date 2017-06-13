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

  private static final long EXPIRATION_TIME = 2 * 60 * 1000; // 2 min
  private static final String SUBJECT = "kundenNummer";
  private static final String JTI = "id123456";
  private static final String LG_NUMMER = "1572003090";
  private static final String SECRET = "RadioPaloma";


  public static void main(String[] args) {
    String jwtStr = Jwts.builder()
      .setHeaderParam("typ", "JWT")
      .setSubject(SUBJECT)
      .setIssuer("Portal")
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .setId(JTI)
      .claim("lie", LG_NUMMER)
      .signWith(SignatureAlgorithm.HS256, SECRET)
      .compact();

    System.out.println("JWT: " + jwtStr);

    Jws<Claims> jws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtStr);
    System.out.println("Typ: " + jws.getHeader().getType());
    System.out.println("Alg: " + jws.getHeader().getAlgorithm());
    System.out.println(" id: " + jws.getBody().getId());
    System.out.println("iat: " + jws.getBody().getIssuedAt());
    System.out.println("exp: " + jws.getBody().getExpiration());
    System.out.println("iss: " + jws.getBody().getIssuer());
    System.out.println("jti: " + jws.getBody().getSubject());
    System.out.println("lie: " + jws.getBody().get("lie"));

  }
}
