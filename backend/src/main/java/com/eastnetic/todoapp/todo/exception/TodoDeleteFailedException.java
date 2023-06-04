package com.eastnetic.todoapp.todo.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.todo.constant.Message.TODO_DELETE_FAILED;

public class TodoDeleteFailedException extends RESTException {
	
	public TodoDeleteFailedException() {
		super(TODO_DELETE_FAILED.getMessage());
	}
	
}
