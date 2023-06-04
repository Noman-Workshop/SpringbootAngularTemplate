package com.eastnetic.todoapp.todo.service;

import com.eastnetic.todoapp.todo.domain.request.CreateTodoRequest;
import com.eastnetic.todoapp.todo.domain.request.GetTodoRequest;
import com.eastnetic.todoapp.todo.domain.request.UpdateTodoRequest;
import com.eastnetic.todoapp.common.domain.response.PagedResponse;
import com.eastnetic.todoapp.todo.domain.response.TodoResponse;

public interface TodoService {
	
	TodoResponse create(CreateTodoRequest todo);
	
	TodoResponse get(Long id);
	
	PagedResponse<TodoResponse> getAll(GetTodoRequest filterTodoRequest);
	
	TodoResponse update(UpdateTodoRequest todo);
	
	void delete(Long id);
	
}
