package com.eastnetic.todoapp.auth.exception;

import com.eastnetic.todoapp.common.exception.RESTException;
import org.springframework.http.HttpStatus;

import static com.eastnetic.todoapp.auth.constant.Message.INVALID_REFRESH_TOKEN;

public class InvalidRefreshTokenException extends RESTException {
	
	{
		status = HttpStatus.UNAUTHORIZED;
	}
	
	public InvalidRefreshTokenException() {
		super(INVALID_REFRESH_TOKEN.getMessage());
	}
}
