package com.eastnetic.todoapp.user.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.user.constant.Message.USER_CREATE_FAILED;

public class UserCreationFailedException extends RESTException {
	
	public UserCreationFailedException() {
		super(USER_CREATE_FAILED.getMessage());
	}
}
