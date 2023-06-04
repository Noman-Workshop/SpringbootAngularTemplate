package com.eastnetic.todoapp.auth.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
	SIGNED_IN_SUCCESSFULLY("signed in successfully"),
	TOKENS_REFRESHED_SUCCESSFULLY("tokens refreshed successfully"),
	SIGNED_OUT_SUCCESSFULLY("signed out successfully"),
	SIGNED_OUT_ALL_SUCCESSFULLY("signed out from all devices successfully"),
	INVALID_ACCESS_TOKEN("invalid access token"),
	INVALID_REFRESH_TOKEN("invalid refresh token"),
	REFRESH_TOKEN_ALREADY_USED("refresh token already used")
	;
	private final String message;
}
