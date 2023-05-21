package com.eastnetic.todoapp.todo.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Status {
	TODO(101, "todo"),
	DOING(102, "doing"),
	COMPLETED(201, "completed"),
	PAUSE_HOLD(202, "pause and hold"),
	FAILED(401, "failed")
	;
	
	private final long code;
	private final String name;
	
	Status(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public static String fromCode(long code) {
		return Arrays.stream(Status.values())
		             .filter(status -> status.getCode() == code)
		             .findFirst()
		             .orElseThrow(() -> {
			             throw new IllegalArgumentException("invalid status code: " + code);
		             })
		             .getName()
		             .toUpperCase();
	}
	
	public static long fromName(String name) {
		return Arrays.stream(Status.values())
		             .filter(status -> status.getName()
		                                     .equalsIgnoreCase(name))
		             .findFirst()
		             .orElseThrow(() -> {
			             throw new IllegalArgumentException("invalid status name: " + name);
		             })
		             .getCode();
	}
}
