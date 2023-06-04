package com.eastnetic.todoapp.todo.service;

import com.eastnetic.todoapp.auth.domain.entity.UserDetailsImpl;
import com.eastnetic.todoapp.common.domain.request.PaginationRequest;
import com.eastnetic.todoapp.common.service.BaseService;
import com.eastnetic.todoapp.common.util.PageUtil;
import com.eastnetic.todoapp.todo.enums.Status;
import com.eastnetic.todoapp.todo.domain.entity.Todo;
import com.eastnetic.todoapp.todo.domain.request.CreateTodoRequest;
import com.eastnetic.todoapp.todo.domain.request.GetTodoRequest;
import com.eastnetic.todoapp.todo.domain.request.UpdateTodoRequest;
import com.eastnetic.todoapp.common.domain.response.PagedResponse;
import com.eastnetic.todoapp.todo.domain.response.TodoResponse;
import com.eastnetic.todoapp.todo.exception.TodoCreationFailedException;
import com.eastnetic.todoapp.todo.exception.TodoDeleteFailedException;
import com.eastnetic.todoapp.todo.exception.TodoNotFoundException;
import com.eastnetic.todoapp.todo.exception.TodoUpdateFailedException;
import com.eastnetic.todoapp.todo.mapper.TodoMapper;
import com.eastnetic.todoapp.todo.repository.TodoRepository;
import com.eastnetic.todoapp.todo.repository.specifications.TodoSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl extends BaseService implements TodoService {
	
	private final TodoRepository todoRepository;
	private final TodoMapper todoMapper;
	
	@Override
	public TodoResponse create(CreateTodoRequest createTodoRequest) {
		UserDetailsImpl user = getCurrentUser();
		
		Todo todo = todoMapper.toEntity(createTodoRequest);
		todo.setStatus(Status.TODO.getCode());
		todo.setIsImportant(false);
		todo.setUserId(user.getId());
		
		try {
			return todoMapper.toDto(todoRepository.save(todo));
		} catch (Exception e) {
			throw new TodoCreationFailedException();
		}
	}
	
	@Override
	public TodoResponse get(Long id) {
		UserDetailsImpl user = getCurrentUser();
		return todoRepository.findByIdAndUserId(id, user.getId())
		                     .map(todoMapper::toDto)
		                     .orElseThrow(() -> new TodoNotFoundException(id));
	}
	
	@Override
	public PagedResponse<TodoResponse> getAll(GetTodoRequest filterTodoRequest) {
		UserDetailsImpl user = getCurrentUser();
		
		PaginationRequest paginationRequest = filterTodoRequest.getPagination();
		Sort sort = PageUtil.getSort(paginationRequest.getSorts());
		PageRequest pagination = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize(), sort);
		
		Specification<Todo> attributeFilter = TodoSpecifications.criteriaFilter(user.getId(),
		                                                                        filterTodoRequest.getFilters());
		
		Page<Todo> todos = todoRepository.findAll(attributeFilter, pagination);
		return PageUtil.paginate(todos, todoMapper::toDto);
	}
	
	@Override
	public TodoResponse update(UpdateTodoRequest todoRequest) {
		TodoResponse todo = get(todoRequest.getId());
		Todo updatedTodo = todoMapper.toEntity(todo);
		todoMapper.updateEntity(todoRequest, updatedTodo);
		updatedTodo.setUserId(getCurrentUser().getId());
		try {
			return todoMapper.toDto(todoRepository.save(updatedTodo));
		} catch (Exception e) {
			throw new TodoUpdateFailedException();
		}
	}
	
	@Override
	public void delete(Long id) {
		TodoResponse todo = get(id);
		try {
			todoRepository.delete(todoMapper.toEntity(todo));
		} catch (Exception e) {
			throw new TodoDeleteFailedException();
		}
	}
}
