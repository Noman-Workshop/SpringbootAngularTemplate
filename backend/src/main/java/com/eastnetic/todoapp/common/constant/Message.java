package com.eastnetic.todoapp.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
	GENERIC_ERROR_RESPONSE("cannot perform the requested operation");
	
	private final String message;
}
