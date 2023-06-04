package com.eastnetic.todoapp.user.exception;

import com.eastnetic.todoapp.common.exception.RESTException;
import org.springframework.http.HttpStatus;

import static com.eastnetic.todoapp.user.constant.Message.USER_EMAIL_NOT_FOUND;
import static com.eastnetic.todoapp.user.constant.Message.USER_ID_NOT_FOUND;

public class UserNotFoundException extends RESTException {
	
	{
		status = HttpStatus.NOT_FOUND;
	}
	
	public UserNotFoundException(Long id) {
		super(String.format(USER_ID_NOT_FOUND.getMessage(), id));
	}
	
	public UserNotFoundException(String email) {
		super(String.format(USER_EMAIL_NOT_FOUND.getMessage(), email));
	}
}
