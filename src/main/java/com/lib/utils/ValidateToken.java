//package com.lib.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//
//public class ValidateToken {
//    public static boolean validateToken(String jwt, String SECRET_KEY) {
//        boolean result = false;
//
//        try{
//            Key key= Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
//            Claims claims= Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(jwt)
//                    .getBody();
//            result = !claims.getExpiration().before(new Date());
//
//        }catch (ExpiredJwtException e){
//            throw e;
//        }catch (Exception e){
//            return result;
//        }
//        return result;
//    }
//}
