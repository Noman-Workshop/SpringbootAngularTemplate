package com.eastnetic.todoapp.todo.controller;

import com.eastnetic.todoapp.common.domain.response.ApiResponse;
import com.eastnetic.todoapp.common.util.ResponseUtil;
import com.eastnetic.todoapp.todo.domain.request.CreateTodoRequest;
import com.eastnetic.todoapp.todo.domain.request.GetTodoRequest;
import com.eastnetic.todoapp.todo.domain.request.UpdateTodoRequest;
import com.eastnetic.todoapp.common.domain.response.PagedResponse;
import com.eastnetic.todoapp.todo.domain.response.TodoResponse;
import com.eastnetic.todoapp.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eastnetic.todoapp.todo.constant.Message.*;

@RestController
@RequestMapping ("/todo")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService todoService;
	
	@PostMapping ("/create")
	public ResponseEntity<ApiResponse<TodoResponse>> create(@Valid @RequestBody CreateTodoRequest todoRequest) {
		TodoResponse response = todoService.create(todoRequest);
		return ResponseUtil.createResponse(HttpStatus.CREATED,
		                                   TODO_CREATED_SUCCESSFULLY.getMessage(),
		                                   response);
	}
	
	@GetMapping ("/get/{id}")
	public ResponseEntity<ApiResponse<TodoResponse>> get(@PathVariable Long id) {
		
		TodoResponse response = todoService.get(id);
		return ResponseUtil.createResponse(HttpStatus.OK, TODO_RETRIEVED_SUCCESSFULLY.getMessage(), response);
	}
	
	@PostMapping ("/get/all")
	public ResponseEntity<ApiResponse<PagedResponse<TodoResponse>>> getAll(@Valid @RequestBody GetTodoRequest getTodoRequest) {
		PagedResponse<TodoResponse> todosDetailsResponse = todoService.getAll(getTodoRequest);
		return ResponseUtil.createResponse(HttpStatus.OK,
		                                   TODOS_RETRIEVED_SUCCESSFULLY.getMessage(), todosDetailsResponse);
	}
	
	@PutMapping ("/update")
	public ResponseEntity<ApiResponse<TodoResponse>> update(@Valid @RequestBody UpdateTodoRequest todoRequest) {
		TodoResponse response = todoService.update(todoRequest);
		
		return ResponseUtil.createResponse(HttpStatus.OK, TODO_UPDATED_SUCCESSFULLY.getMessage(), response);
	}
	
	@DeleteMapping ("/delete/{id}")
	public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
		todoService.delete(id);
		return ResponseUtil.createResponse(HttpStatus.OK, TODO_DELETED_SUCCESSFULLY.getMessage(), null);
	}
}
