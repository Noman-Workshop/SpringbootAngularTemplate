package com.eastnetic.todoapp.todo.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.todo.constant.Message.TODO_UPDATE_FAILED;

public class TodoUpdateFailedException extends RESTException {
	
	public TodoUpdateFailedException() {
		super(TODO_UPDATE_FAILED.getMessage());
	}
	
}
