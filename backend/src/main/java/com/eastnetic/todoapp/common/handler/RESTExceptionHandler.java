package com.eastnetic.todoapp.common.handler;

import com.eastnetic.todoapp.common.domain.response.ApiResponse;
import com.eastnetic.todoapp.common.exception.RESTException;
import com.eastnetic.todoapp.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.eastnetic.todoapp.common.constant.Message.GENERIC_ERROR_RESPONSE;

@RestControllerAdvice
public class RESTExceptionHandler {
	
	@ExceptionHandler (Exception.class)
	public ResponseEntity<ApiResponse<String>> handleAllExceptions(Exception ignored) {
		return ResponseUtil.createResponse(HttpStatus.I_AM_A_TEAPOT, GENERIC_ERROR_RESPONSE.getMessage(), null);
	}
	
	@ExceptionHandler (RESTException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleRESTException(RESTException exception) {
		Map<String, String> payload = exception.getPayload() == null ? null :
				exception.getPayload()
				         .entrySet()
				         .stream()
				         .collect(HashMap::new,
				                  (map, entry) -> map.put(entry.getKey(),
				                                          entry.getValue()
				                                               .toString()),
				                  Map::putAll);
		
		return ResponseUtil.createResponse(exception.getStatus(),
		                                   exception.getMessage(),
		                                   payload);
	}
	
}
