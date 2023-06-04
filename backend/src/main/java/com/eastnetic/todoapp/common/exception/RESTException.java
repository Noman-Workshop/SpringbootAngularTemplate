package com.eastnetic.todoapp.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Getter
public class RESTException extends RestClientException {
	
	private final Map<String, ?> payload;
	protected HttpStatus status = HttpStatus.BAD_REQUEST;
	
	public RESTException(String message) {
		super(message);
		this.payload = null;
	}
	
	public RESTException(String message, Map<String, ?> payload) {
		super(message);
		this.payload = payload;
	}
	
	@Builder
	public RESTException(String message, Map<String, ?> payload, HttpStatus status) {
		super(message);
		this.payload = payload;
		this.status = status == null ? this.status : status;
	}
	
}
