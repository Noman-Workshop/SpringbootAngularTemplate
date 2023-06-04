package com.eastnetic.todoapp.user.domain.mapper;

import com.eastnetic.todoapp.user.domain.entity.User;
import com.eastnetic.todoapp.user.domain.request.CreateUserRequest;
import com.eastnetic.todoapp.user.domain.request.UpdateUserRequest;
import com.eastnetic.todoapp.user.domain.response.UserDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper (componentModel = "spring")
public interface UserMapper {
	
	User toEntity(CreateUserRequest createTodoRequest);
	
	User toEntity(UserDetailsResponse todo);
	
	UserDetailsResponse toDto(User todo);
	
	void updateEntity(UpdateUserRequest request, @MappingTarget User entity);
}
