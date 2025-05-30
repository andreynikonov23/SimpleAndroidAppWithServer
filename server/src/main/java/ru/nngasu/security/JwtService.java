package ru.nngasu.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.nngasu.models.User;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtService {
    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;

    public JwtService(@Value("${jwt.secret.access}") String jwtAccessSecret, @Value("${jwt.secret.refresh}") String jwtRefreshSecret) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }

    public String generateAccessToken(User user) {
        LocalDateTime now = LocalDateTime.now();
        Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
        Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .claim("authority", user.getRole().getAuthority())
                .claim("fullName", String.format("%s %s", user.getFirstname(), user.getLastname()))
                .compact();
    }

    public String generateRefreshToken(User user) {
        LocalDateTime now = LocalDateTime.now();
        Instant accessExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(accessExpiration)
                .signWith(jwtRefreshSecret)
                .claim("name", String.format("%s %s", user.getFirstname(), user.getLastname()))
                .claim("role", user.getRole())
                .compact();
    }

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }

    private boolean validateToken(String token, Key secret) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("Token is not valid");
        }
        return false;
    }

    public Claims getAccessClaims(String accessToken) {
        return getClaims(accessToken, jwtAccessSecret);
    }

    public Claims getRefreshClaims(String refreshToken) {
        return getClaims(refreshToken, jwtRefreshSecret);
    }

    private Claims getClaims(String token, Key secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
