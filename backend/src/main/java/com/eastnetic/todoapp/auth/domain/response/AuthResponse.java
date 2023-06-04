package com.eastnetic.todoapp.auth.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponse {
	private String accessToken;
	private String refreshToken;
}
