package com.eastnetic.todoapp.auth.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
	
	String generateAccessToken(String refreshToken);
	
	String generateRefreshToken(UserDetails userDetails);
	
	String refreshTokens(String refreshToken);
	
	// FIXME: Need refactoring to combine validation and parsing
	boolean validateAccessToken(String accessToken);
	
	Jws<Claims> extractClaims(String token);
	
	void invalidateRefreshTokenChain(String token);
	
	int getRefreshTokenExpiration();
}
