package com.eastnetic.todoapp.common.controller;

import com.eastnetic.todoapp.auth.domain.entity.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {
	
	public UserDetailsImpl getCurrentUser() {
		return (UserDetailsImpl) SecurityContextHolder.getContext()
		                                              .getAuthentication()
		                                              .getPrincipal();
	}
}
