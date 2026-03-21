///* ═══════════════════════════════════════
//            JwtUtil.java
//═══════════════════════════════════════ */
//package com.portfolio.config;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class JwtUtil2 {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private long expiration;
//
//    private Key signingKey() {
//        return Keys.hmacShaKeyFor(secret.getBytes());
//    }
//    
//    
//    
//    
//    
//    ///////////////////////////////////////////////////////////////////////
//    /* ═══════════════════════════════════════
//    JwtUtil.java (Fixed)
//═══════════════════════════════════════ */
//
//public String generateToken(String email, List<String> roles) {
//return Jwts.builder()
//    .setSubject(email)
//    .claim("roles", roles) // ✅ correct key
//    .setIssuedAt(new Date())
//    .setExpiration(new Date(System.currentTimeMillis() + expiration))
//    .signWith(signingKey())
//    .compact();
//}
//
////✅ FIXED
//public List<String> extractRoles(String token) {
//return parseClaims(token).get("roles", List.class);
//}
//
//public String extractEmail(String token) {
//return parseClaims(token).getSubject();
//}
//    ////////////////////////////////////////////////////////////////////
////    //  old
////    public String generateToken(String email) {
////        return Jwts.builder()
////                .setSubject(email)
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + expiration))
////                .signWith(signingKey())
////                .compact();
////    }
//    
//// // ADD
////    public String generateToken(String email, String role) {
////        return Jwts.builder()
////                .setSubject(email)
////                .claim("role", role) // ADD ROLE CLAIM
////                .setIssuedAt(new Date())
////                .setExpiration(new Date(System.currentTimeMillis() + expiration))
////                .signWith(signingKey())
////                .compact();
////    }
////    
//// // ADD 
////    public String extractRole(String token) {
////        return parseClaims(token).get("role", String.class);
////    }
////    
////    public String extractEmail(String token) {
////        return parseClaims(token).getSubject();
////    }
//
//    public boolean isTokenValid(String token) {
//        try {
//            Date expiry = parseClaims(token).getExpiration();
//            return expiry.after(new Date());
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
//
//    private Claims parseClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(signingKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}