package com.lib.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class ValidateToken {
    public static boolean validateToken(String jwt, String SECRET_KEY) {
        boolean result = false;

        try{
            Claims claims= Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY.getBytes())
                    .parseClaimsJws(jwt)
                    .getBody();
            result = !claims.getExpiration().before(new Date());

        }catch (ExpiredJwtException e){
            throw e;
        }catch (ExpiredJwtException e){
            return result;
        }
        return result;
    }
}
