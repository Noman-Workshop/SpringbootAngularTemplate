package com.eastnetic.todoapp.user.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
	USER_CREATED_SUCCESSFULLY("user created successfully"),
	USER_RETRIEVED_SUCCESSFULLY("user data retrieved successfully"),
	USER_UPDATED_SUCCESSFULLY("user data updated successfully"),
	USER_EMAIL_ALREADY_EXISTS("user with email %s already exists"),
	USER_CREATE_FAILED("failed to create user"),
	USER_ID_NOT_FOUND("user with id %d not found"),
	USER_EMAIL_NOT_FOUND("user with email %s not found"),
	USER_UPDATE_FAILED("failed to update user");
	
	private final String message;
}
