package com.eastnetic.todoapp.user.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.user.constant.Message.USER_UPDATE_FAILED;

public class UserUpdateFailedException extends RESTException {
	
	public UserUpdateFailedException() {
		super(USER_UPDATE_FAILED.getMessage());
	}
	
}
