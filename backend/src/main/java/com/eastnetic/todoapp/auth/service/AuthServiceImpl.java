package com.eastnetic.todoapp.auth.service;

import com.eastnetic.todoapp.auth.domain.entity.UserDetailsImpl;
import com.eastnetic.todoapp.auth.domain.request.SignInRequest;
import com.eastnetic.todoapp.auth.domain.response.AuthResponse;
import com.eastnetic.todoapp.auth.service.jwt.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final AuthenticationManager authenticationManager;
	
	private final JWTService jwtService;
	
	@Override
	public AuthResponse signIn(SignInRequest signInRequest) {
		var authToken = new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword());
		var authenticatedUser = authenticationManager.authenticate(authToken);
		String refreshToken = jwtService.generateRefreshToken((UserDetailsImpl) authenticatedUser.getPrincipal());
		String accessToken = jwtService.generateAccessToken(refreshToken);
		
		return new AuthResponse(accessToken, refreshToken);
	}
	
	@Override
	public AuthResponse refreshTokens(String refreshToken) {
		var newRefreshToken = jwtService.refreshTokens(refreshToken);
		var accessToken = jwtService.generateAccessToken(newRefreshToken);
		return new AuthResponse(accessToken, newRefreshToken);
	}
	
	@Override
	public void signOut(String refreshToken) {
		jwtService.invalidateRefreshTokenChain(refreshToken);
	}
	
	@Override
	public String getRefreshTokenCookie(String refreshToken) {
		return ResponseCookie.from("refreshToken", refreshToken)
		                     .httpOnly(true)
		                     .path("/")
		                     .maxAge(Duration.of(jwtService.getRefreshTokenExpiration(), ChronoUnit.SECONDS))
		                     .sameSite("Strict")
		                     .build()
		                     .toString();
	}
	
	@Override
	public String getEmptyCookie() {
		return ResponseCookie.from("refreshToken", "")
		                     .httpOnly(true)
		                     .path("/")
		                     .maxAge(0)
		                     .sameSite("Strict")
		                     .build()
		                     .toString();
	}
}
