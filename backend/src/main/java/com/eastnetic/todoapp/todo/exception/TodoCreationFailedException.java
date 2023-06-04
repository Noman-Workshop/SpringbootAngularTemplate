package com.eastnetic.todoapp.todo.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.todo.constant.Message.TODO_CREATE_FAILED;

public class TodoCreationFailedException extends RESTException {
	
	public TodoCreationFailedException() {
		super(TODO_CREATE_FAILED.getMessage());
	}
}
