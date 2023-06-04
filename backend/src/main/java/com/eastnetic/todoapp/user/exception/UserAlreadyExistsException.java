package com.eastnetic.todoapp.user.exception;

import com.eastnetic.todoapp.common.exception.RESTException;
import org.springframework.http.HttpStatus;

import static com.eastnetic.todoapp.user.constant.Message.USER_EMAIL_ALREADY_EXISTS;

public class UserAlreadyExistsException extends RESTException {
	
	{
		status = HttpStatus.CONFLICT;
	}
	
	public UserAlreadyExistsException(String email) {
		super(String.format(USER_EMAIL_ALREADY_EXISTS.getMessage(), email));
	}
}
