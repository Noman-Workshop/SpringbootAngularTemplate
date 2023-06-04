package com.eastnetic.todoapp.todo.exception;

import com.eastnetic.todoapp.common.exception.RESTException;
import org.springframework.http.HttpStatus;

import static com.eastnetic.todoapp.todo.constant.Message.TODO_NOT_FOUND;

public class TodoNotFoundException extends RESTException {
	
	{
		status = HttpStatus.NOT_FOUND;
	}
	
	public TodoNotFoundException(Long id) {
		super(String.format(TODO_NOT_FOUND.getMessage(), id));
	}
}
