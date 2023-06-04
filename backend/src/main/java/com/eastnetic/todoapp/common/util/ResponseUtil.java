package com.eastnetic.todoapp.common.util;

import com.eastnetic.todoapp.common.domain.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
	
	public static <T> ResponseEntity<ApiResponse<T>> createResponse(HttpStatus status, String message, T response) {
		return ResponseEntity.status(status)
		                     .body(createResponseBody(message, response));
	}
	
	public static <T> ResponseEntity<ApiResponse<T>> createResponse(HttpStatus status,
	                                                                String cookie,
	                                                                String message,
	                                                                T response) {
		return ResponseEntity.status(status)
		                     .header("Set-Cookie", cookie)
		                     .body(createResponseBody(message, response));
	}
	
	private static <T> ApiResponse<T> createResponseBody(String message, T response) {
		return ApiResponse.<T>builder()
		                  .message(message)
		                  .data(response)
		                  .build();
	}
}
