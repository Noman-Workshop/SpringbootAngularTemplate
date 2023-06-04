package com.eastnetic.todoapp.todo.domain.request;

import com.eastnetic.todoapp.common.domain.request.PaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTodoRequest {
	
	private PaginationRequest pagination;
	private TodoFilterRequest filters;
}
