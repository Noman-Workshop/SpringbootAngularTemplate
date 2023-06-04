package com.eastnetic.todoapp.auth.exception;

import com.eastnetic.todoapp.common.exception.RESTException;
import org.springframework.http.HttpStatus;

import static com.eastnetic.todoapp.auth.constant.Message.INVALID_ACCESS_TOKEN;

public class InvalidAccessTokenException extends RESTException {
	
	{
		status = HttpStatus.UNAUTHORIZED;
	}
	
	public InvalidAccessTokenException() {
		super(INVALID_ACCESS_TOKEN.getMessage());
	}
	
	public InvalidAccessTokenException(String message) {
		super(message);
	}
	
}
