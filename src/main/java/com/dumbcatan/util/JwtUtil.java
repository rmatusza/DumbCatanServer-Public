package com.dumbcatan.util;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dumbcatan.entity.MyUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
	private String SECRET_KEY = "secret";
	
	// this method utilizes the extract claim method to get the username
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	// this method utilizes the extract claim method to get the expiration
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	
	// lets you extract the claims that were originally added into the token when
	// it was created
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date(System.currentTimeMillis()));
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	// creates a JWT based off of the the userDetails object. Specifically the 
	// username of the user
	// the claims are gonna be an object of any other info that you want to add into the jwt
	// here we are leaving it empty
	public String generateToken(MyUserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", userDetails.getUsername());
		claims.put("password", userDetails.getPassword());
		claims.put("userId", userDetails.getId());
		return createToken(claims, userDetails.getUsername());
	}
	
	// this method will call the jwt api added in pom.xml
	// the subject is the person who is being authenticated, in this case it's the username
	// that's being passed in for that parameter
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
