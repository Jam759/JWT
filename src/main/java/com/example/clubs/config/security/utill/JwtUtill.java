package com.example.clubs.config.security.utill;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Signature;
import java.util.Date;

public class JwtUtill {

    private final String SECRET_KET = "my_secret_key";
    private final long EXPIRATION_TIME = 86400000; //1d

    public String generatedToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KET)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KET).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }

}
