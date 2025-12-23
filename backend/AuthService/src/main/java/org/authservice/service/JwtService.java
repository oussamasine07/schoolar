package org.authservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.authservice.exception.JwtKeyGenerationException;
import org.authservice.model.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private String secretKey;

    JwtService () {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        }
        catch (NoSuchAlgorithmException e) {
            throw new JwtKeyGenerationException("invalid secret key", e);
        }
    }


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

    // validate token
    public boolean validateToken (String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return (userDetails.getUsername().equals( username ) && !isTokenExpired(token));
    }

    // check if token is expired
    private boolean isTokenExpired (String token) {
        return extractExpiration(token).before(new Date());
    }

    // extract expiration
    private Date extractExpiration (String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    // generate jwt
    public String generateJwtToken (AppUser appUser) {
        // generate claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", appUser.getFirstName());
        claims.put("lastName", appUser.getLastName());
        claims.put("email", appUser.getEmail());
        claims.put("role", appUser.getRole());

        // build token
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(appUser.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 60 * 40))
                .and()
                .signWith(getKeys())
                .compact();
    }

}
















