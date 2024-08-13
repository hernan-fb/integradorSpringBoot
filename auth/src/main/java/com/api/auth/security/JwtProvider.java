package com.api.auth.security;

import com.api.auth.dto.RequestDto;
import com.api.auth.model.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtProvider {

    private Key secret;

    @Autowired
    private RouteValidation routeValidation;

    @Value("${durationToken}")
    private int durationToken;

    @PostConstruct
    protected void init() { // crea una cadena de bytes para hacer la firma de los métodos
        byte[] apiKeySecretB = new byte[64]; //64bytes 500caracteres aprox
        new SecureRandom().nextBytes(apiKeySecretB); // crea bytes aleatorios y seguros
        secret = Keys.hmacShaKeyFor(apiKeySecretB); // inicializar
    }

    public String getUserNameFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception e) {
            log.error("Bad token" + e.getMessage());
            return "bad token";

        }
    }
    public String createToken(AuthUser user) {
        //Json web token tiene cabecera, información e info encriptada. La info se llama claims.
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        LocalDateTime now = LocalDateTime.now();

        return Jwts
                .builder() // inicializa construcción del token
                .claims(claims)
                .subject(user.getUserName()) // añadir info al token
                .issuedAt(this.convertToLocalDateTimeToDate(now)) //fecha de inicio del token
                .expiration(this.convertToLocalDateTimeToDate(now.plusHours(durationToken)))
                .signWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .compact();
    }
    private Date convertToLocalDateTimeToDate(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private boolean isAdmin(String token) {
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role").equals("admin");
    }

    public boolean validate(String token, RequestDto requestDto) {
        try {
            Jwts
                    .parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getEncoded()))
                    .build()
                    .parseSignedClaims(token);
        } catch (Exception e) {
            return false;
        }
        // if (!isAdmin(token) && routeValidation.isAdmin(requestDto)) {
        //    return false;
        // }
        return isAdmin(token) || !routeValidation.isAdmin(requestDto);
    }
}
