package org.studentservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${secrets.jwt.secret-key}")
    private String secretKey;

    JwtService () {}


    private SecretKey getKeys () {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor( keyBytes );
    }

    // extract username from token
    public String extractUsername (String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // extract claims from token
    private <T> T extractClaims (String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims( token );
        return claimsResolver.apply( claims );
    }

    // extract all claims from token
    public Claims extractAllClaims (String token) {
        return Jwts.parser()
                .verifyWith(getKeys())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long extractUserId (String bearerToken) {
        String token = bearerToken.split(" ")[1];

        Claims claims = extractAllClaims( token );
        return claims.get("id", Long.class);
    }

    // validate token
    public boolean validateToken (String token) {
        try {
            Jwts.parser()
                    .verifyWith(getKeys())
                    .build()
                    .parseSignedClaims(token);

            return !isTokenExpired( token );
        }
        catch (JwtException e) {
            return false;
        }
    }

    // check if token is expired
    private boolean isTokenExpired (String token) {
        return extractExpiration(token).before(new Date());
    }

    // extract expiration
    private Date extractExpiration (String token) {
        return extractClaims(token, Claims::getExpiration);
    }

}
