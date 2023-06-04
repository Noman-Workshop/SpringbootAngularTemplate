package com.eastnetic.todoapp.auth.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.auth.constant.Message.REFRESH_TOKEN_ALREADY_USED;

public class RefreshTokenAlreadyUsedException extends RESTException {
	
	public RefreshTokenAlreadyUsedException() {
		super(REFRESH_TOKEN_ALREADY_USED.getMessage());
	}
}
