package com.eastnetic.todoapp.todo.mapper;

import com.eastnetic.todoapp.todo.enums.Status;
import com.eastnetic.todoapp.todo.domain.entity.Todo;
import com.eastnetic.todoapp.todo.domain.request.CreateTodoRequest;
import com.eastnetic.todoapp.todo.domain.request.UpdateTodoRequest;
import com.eastnetic.todoapp.todo.domain.response.TodoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper (componentModel = "spring")
public abstract class TodoMapper {
	
	public abstract Todo toEntity(CreateTodoRequest createTodoRequest);
	
	@Mapping (target = "status", expression = "java(this.fromName(todo.getStatus()))")
	public abstract Todo toEntity(TodoResponse todo);
	
	@Mapping (target = "status", expression = "java(this.fromCode(todo.getStatus()))")
	public abstract TodoResponse toDto(Todo todo);
	
	@Mapping (target = "status", expression = "java(this.fromName(request.getStatus()))")
	public abstract void updateEntity(UpdateTodoRequest request, @MappingTarget Todo entity);
	
	protected String fromCode(long status) {
		return Status.fromCode(status);
	}
	
	protected long fromName(String status) {
		return Status.fromName(status);
	}
}
