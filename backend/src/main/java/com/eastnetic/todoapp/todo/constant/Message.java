package com.eastnetic.todoapp.todo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
	
	TODO_CREATED_SUCCESSFULLY("todo created successfully"),
	TODO_RETRIEVED_SUCCESSFULLY("todo retrieved successfully"),
	TODOS_RETRIEVED_SUCCESSFULLY("todos retrieved successfully"),
	TODO_UPDATED_SUCCESSFULLY("todo updated successfully"),
	TODO_DELETED_SUCCESSFULLY("todo deleted successfully"),
	TODO_CREATE_FAILED("failed to create todo"),
	TODO_NOT_FOUND("todo with id %d not found"),
	TODO_DELETE_FAILED("failed to delete todo"),
	TODO_UPDATE_FAILED("failed to update todo");
	
	private final String message;
}
