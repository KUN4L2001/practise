package com.myapp.practise.util;

import com.myapp.practise.config.JWTConfigProps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.*;
import javax.crypto.SecretKey;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JWTUtil {

  private final JWTConfigProps jwtConfigProps;

  public String generateToken(UserDetails userDetails) {
    List<String> roles = new ArrayList<>();
    for (GrantedAuthority authority : userDetails.getAuthorities()) {
      roles.add(authority.getAuthority());
    }
    Map<String, List<String>> claims = new HashMap<>();
    claims.put("claims", roles);

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + jwtConfigProps.getValidity() * 1000))
        .signWith(SignatureAlgorithm.HS512, getKey())
        .compact();
  }

  private SecretKey getKey() {
    byte[] bytes = jwtConfigProps.getSecretKey().getBytes();
    return Keys.hmacShaKeyFor(bytes);
  }

  public String getUsernameFromToken(String token) {
    return getAllClaims(token).getSubject();
  }

  public boolean isTokenExpired(String token) {
    Date exp = getAllClaims(token).getExpiration();
    return exp.before(new Date());
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private Claims getAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
  }
}
