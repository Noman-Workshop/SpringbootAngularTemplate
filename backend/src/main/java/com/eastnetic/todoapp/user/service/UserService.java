package com.eastnetic.todoapp.user.service;

import com.eastnetic.todoapp.user.domain.request.CreateUserRequest;
import com.eastnetic.todoapp.user.domain.request.UpdateUserRequest;
import com.eastnetic.todoapp.user.domain.response.UserDetailsResponse;

public interface UserService {
	
	UserDetailsResponse create(CreateUserRequest user);
	
	UserDetailsResponse get(Long id);
	
	UserDetailsResponse getByEmail(String email);
	
	UserDetailsResponse update(UpdateUserRequest user);
}
