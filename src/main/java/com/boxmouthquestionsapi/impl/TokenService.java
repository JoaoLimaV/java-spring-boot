package com.boxmouthquestionsapi.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.boxmouthquestionsapi.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		System.out.println(secret);
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
							  .withIssuer("auth-api")
							  .withSubject(user.getEmail())
							  .withClaim("id", user.getId())
							  .withExpiresAt(genExpirationDate())
							  .sign(algorithm);
			return token;
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error while generating token", exception);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					  .withIssuer("auth-api")
					  .build()
					  .verify(token)
					  .getSubject();
		} catch (JWTCreationException exception) {
			System.out.println("Token");
			throw new RuntimeException("invalid token");
		}
	}
	
	public String getUserIdFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "").trim();
        
        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token);

        Claims claims = jws.getBody();
        
		return (String) claims.get("id");
	}
	
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
