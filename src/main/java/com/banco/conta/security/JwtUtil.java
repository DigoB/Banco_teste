package com.banco.conta.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public String gerarToken(String email) {
        return Jwts.builder()
        .setSubject(email)
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
    }

	public boolean validToken(String token) {
        Claims claims = getClaims(token);
        if(claims != null){
            String username = claims.getSubject();
            Date dataDeExpiracao = claims.getExpiration();
            Date agora = new Date(System.currentTimeMillis());

            if(username != null && dataDeExpiracao != null && agora.before(dataDeExpiracao)){
                return true;

            }
        }
        return false;
    }

	private Claims getClaims(String token) {
        try{
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }
        catch(Exception e) {
            return null;
        }

    }

    public String getusername(String token) {
        Claims claims = getClaims(token);
        if(claims != null) {
            return claims.getSubject();
        }
        
        return null;
	}
}
