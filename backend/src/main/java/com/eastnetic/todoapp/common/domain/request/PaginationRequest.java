package com.eastnetic.todoapp.common.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {
	
	private int page;
	private int size;
	private String[] sorts;
}
