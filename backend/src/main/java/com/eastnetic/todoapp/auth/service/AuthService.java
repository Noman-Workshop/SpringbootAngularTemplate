package com.eastnetic.todoapp.auth.service;

import com.eastnetic.todoapp.auth.domain.request.SignInRequest;
import com.eastnetic.todoapp.auth.domain.response.AuthResponse;

import java.util.List;

public interface AuthService {
	
	AuthResponse signIn(SignInRequest signInRequest);
	
	AuthResponse refreshTokens(String refreshToken);
	
	void signOut(String refreshToken);
	
	String getRefreshTokenCookie(String refreshToken);
	
	String getEmptyCookie();
}
