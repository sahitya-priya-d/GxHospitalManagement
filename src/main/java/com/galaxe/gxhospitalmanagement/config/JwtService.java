package com.galaxe.gxhospitalmanagement.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "bSV98iZpndmg8E559G4ttzS0HjRBmej5MJa9HuwUkZJbcXeMAvrYfthSsNigKuNh5IzBceixsrKNySDLmo6UjkZ2SEx+Z1aMvdGEjeX7k+5wlBJ0pAQUXZz3np/TLsE4F4ayubSHXl+ICAIN2g851i3b74XMDBjsnleUEy/hTChHnbYv4L7CL+v5CsM/Ip874ezOukw+oay4ten6q4AWwNxItT9QG3TmdeBCa3bfWQ25YULdaG1LTLycbaZcL962OjS4nGoIhbMxT+GRM9IE8AoC0i0jSf7EpC4h49eHcRn200CTmKxAeN4KWOW+hSDm4U0cyrGrEe82yMQ3UBHFvvSCOB1Qkg3GFjIybJnvVlA";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);

	}

	public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());

	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
