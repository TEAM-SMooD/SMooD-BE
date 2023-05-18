package yeinyeonha.SMooD.oauth.token;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class AuthToken {

    @Getter
    private final String token;
    private final Key key;

    private static final String AUTHORITIES_KEY = "role";

    AuthToken(String id, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, expiry);
    }

    AuthToken(String id, String role, Date expiry, Key key) {
        this.key = key;
        this.token = createAuthToken(id, role, expiry);
    }
    //리프레시 토큰용
    private String createAuthToken(String id, Date expiry) {
        String str = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(id)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return str;
    }
    //그냥 토큰용
    private String createAuthToken(String id, String role, Date expiry) {
        String str = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(id)
                .claim(AUTHORITIES_KEY, role)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return str;

    }

    public boolean validate() {
        return this.getTokenClaims() != null;
    }

    public Claims getTokenClaims() {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SecurityException e) {
//            log.info("Invalid JWT signature.");
        } catch (MalformedJwtException e) {
//            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
//            log.info("JWT token compact of handler are invalid.");
        }
        return null;
    }

    public Claims getExpiredTokenClaims() {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
        return null;
    }
}

