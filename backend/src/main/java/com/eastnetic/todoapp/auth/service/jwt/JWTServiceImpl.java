package com.eastnetic.todoapp.auth.service.jwt;

import com.eastnetic.todoapp.auth.domain.entity.RefreshToken;
import com.eastnetic.todoapp.auth.domain.entity.UserDetailsImpl;
import com.eastnetic.todoapp.auth.exception.InvalidAccessTokenException;
import com.eastnetic.todoapp.auth.exception.InvalidRefreshTokenException;
import com.eastnetic.todoapp.auth.exception.RefreshTokenAlreadyUsedException;
import com.eastnetic.todoapp.auth.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Optional;

@Service
public class JWTServiceImpl implements JWTService {
	
	private final RefreshTokenRepository refreshTokenRepository;
	
	private final JWTConfiguration jwtConfiguration;
	
	public JWTServiceImpl(RefreshTokenRepository refreshTokenRepository, JWTConfiguration jwtConfiguration) {
		this.refreshTokenRepository = refreshTokenRepository;
		this.jwtConfiguration = jwtConfiguration;
	}
	
	@Override
	public String generateAccessToken(String refreshToken) {
		Optional<RefreshToken> optionalRefreshToken = Optional.empty();
		try {
			optionalRefreshToken = validateRefreshToken(refreshToken);
		} catch (RefreshTokenAlreadyUsedException ignored) {
			invalidateRefreshTokenChain(refreshToken);
		}
		
		if (optionalRefreshToken.isEmpty()) {
			throw new InvalidRefreshTokenException();
		}
		
		return createAccessToken(optionalRefreshToken.get()
		                                             .getToken());
	}
	
	@Override
	public String generateRefreshToken(UserDetails userPrincipal) {
		var refreshToken = createRefreshToken((UserDetailsImpl) userPrincipal);
		refreshTokenRepository.save(refreshToken);
		return refreshToken.getToken();
	}
	
	@Override
	public String refreshTokens(String refreshToken) {
		Optional<RefreshToken> optionalRefreshToken = Optional.empty();
		try {
			optionalRefreshToken = validateRefreshToken(refreshToken);
		} catch (RefreshTokenAlreadyUsedException ignored) {
			invalidateRefreshTokenChain(refreshToken);
		}
		
		if (optionalRefreshToken.isEmpty()) {
			throw new InvalidRefreshTokenException();
		}
		
		RefreshToken oldRefreshToken = optionalRefreshToken.get();
		RefreshToken newRefreshToken = createRefreshToken(oldRefreshToken.getToken());
		
		oldRefreshToken.setSuccessor(newRefreshToken.getToken());
		// FIXME: Use transactional redis to avoid data inconsistency
		refreshTokenRepository.save(oldRefreshToken);
		refreshTokenRepository.save(newRefreshToken);
		
		return newRefreshToken.getToken();
	}
	
	@Override
	public boolean validateAccessToken(String accessToken) {
		try {
			parseJWT(accessToken, jwtConfiguration.getAccessTokenKey());
			return true;
		} catch (JwtException ignored) {
			return false;
		}
	}
	
	@Override
	public Jws<Claims> extractClaims(String token) {
		try {
			return parseJWT(token, jwtConfiguration.getAccessTokenKey());
		} catch (JwtException ignored) {
			return null;
		}
	}
	
	@Override
	public void invalidateRefreshTokenChain(String token) {
		var optionalRefreshToken = refreshTokenRepository.findById(token);
		if (optionalRefreshToken.isEmpty()) {
			return;
		}
		
		// TODO: Test the correctness of the algorithm
		var refreshToken = optionalRefreshToken.get();
		refreshTokenRepository.delete(refreshToken);
		while (refreshToken.getSuccessor() != null) {
			var successor = refreshTokenRepository.findById(refreshToken.getSuccessor());
			if (successor.isPresent()) {
				refreshToken = successor.get();
				refreshTokenRepository.deleteById(refreshToken.getToken());
			}
		}
	}
	
	@Override
	public int getRefreshTokenExpiration() {
		return jwtConfiguration.getRefreshTokenExpiration();
	}
	
	private Optional<RefreshToken> validateRefreshToken(String token) throws RefreshTokenAlreadyUsedException {
		try {
			parseJWT(token, jwtConfiguration.getRefreshTokenKey());
			
			var refreshToken = refreshTokenRepository.findById(token);
			if (refreshToken.isEmpty()) {
				return Optional.empty();
			}
			
			RefreshToken storedToken = refreshToken.get();
			boolean used = storedToken.isUsed();
			if (used) {
				throw new RefreshTokenAlreadyUsedException();
			}
			
			return Optional.of(storedToken);
		} catch (JwtException e) {
			return Optional.empty();
		}
	}
	
	private Jws<Claims> parseJWT(String token, Key signingKey) {
		try {
			return Jwts.parserBuilder()
			           .setSigningKey(signingKey)
			           .build()
			           .parseClaimsJws(token);
		} catch (ExpiredJwtException e) {
			throw new InvalidAccessTokenException("Access token expired");
		} catch (JwtException e) {
			throw new InvalidAccessTokenException();
		}
	}
	
	private RefreshToken createRefreshToken(UserDetailsImpl userDetails) {
		
		var tokenString = Jwts.builder()
		                      .signWith(jwtConfiguration.getRefreshTokenKey())
		                      .setSubject(userDetails.getUsername())
		                      .claim("role",
		                             userDetails.getAuthorities())
		                      .setIssuer(userDetails.getUsername())
		                      .setIssuedAt(new java.util.Date())
		                      .setExpiration(new java.util.Date(System.currentTimeMillis() + jwtConfiguration.getRefreshTokenExpiration() * 1000L))
		                      .compact();
		
		return RefreshToken.builder()
		                   .token(tokenString)
		                   .email(userDetails.getUsername())
		                   .expiration((long) jwtConfiguration.getRefreshTokenExpiration())
		                   .build();
	}
	
	private RefreshToken createRefreshToken(String refreshToken) {
		var claims = parseJWT(refreshToken, jwtConfiguration.getRefreshTokenKey());
		var tokenString = Jwts.builder()
		                      .signWith(jwtConfiguration.getRefreshTokenKey())
		                      .setClaims(claims.getBody())
		                      .setIssuer(claims.getSignature())
		                      .setIssuedAt(new java.util.Date())
		                      .setExpiration(new java.util.Date(System.currentTimeMillis() + jwtConfiguration.getRefreshTokenExpiration() * 1000L))
		                      .compact();
		
		return RefreshToken.builder()
		                   .token(tokenString)
		                   .email(claims.getBody()
		                                .getSubject())
		                   .expiration((long) jwtConfiguration.getRefreshTokenExpiration())
		                   .build();
		
	}
	
	private String createAccessToken(String refreshToken) {
		var claims = parseJWT(refreshToken, jwtConfiguration.getRefreshTokenKey());
		return Jwts.builder()
		           .signWith(jwtConfiguration.getAccessTokenKey())
		           .setClaims(claims.getBody())
		           .setIssuedAt(new java.util.Date())
		           .setExpiration(new java.util.Date(System.currentTimeMillis() + jwtConfiguration.getAccessTokenExpiration() * 1000L))
		           .compact();
	}
	
}
