package com.eastnetic.todoapp.auth.service.jwt;

import io.jsonwebtoken.security.Keys;
import liquibase.pro.packaged.G;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Configuration
class JWTConfiguration {
	
	@Value ("${security.jwt.access-token.secret}")
	String accessTokenSecret;
	
	@Getter
	@Value ("${security.jwt.access-token.expiration}")
	private int accessTokenExpiration;
	
	@Value ("${security.jwt.refresh-token.secret}")
	private String refreshTokenSecret;
	
	@Getter
	@Value ("${security.jwt.refresh-token.expiration}")
	private int refreshTokenExpiration;
	
	Key getAccessTokenKey() {
		return Keys.hmacShaKeyFor(this.accessTokenSecret.getBytes(StandardCharsets.UTF_8));
	}
	
	Key getRefreshTokenKey() {
		return Keys.hmacShaKeyFor(this.refreshTokenSecret.getBytes(StandardCharsets.UTF_8));
	}
	
}
