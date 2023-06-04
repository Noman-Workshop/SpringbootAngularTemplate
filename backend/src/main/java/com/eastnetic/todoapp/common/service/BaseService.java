package com.eastnetic.todoapp.common.service;

import com.eastnetic.todoapp.auth.domain.entity.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseService {
	
	public UserDetailsImpl getCurrentUser() {
		return (UserDetailsImpl) SecurityContextHolder.getContext()
		                                              .getAuthentication()
		                                              .getPrincipal();
	}
}
