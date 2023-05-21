package com.eastnetic.todoapp.common.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude (JsonInclude.Include.NON_NULL)
public class ApiResponse <T> implements Serializable {
	
	private String message;
	private T data;
}