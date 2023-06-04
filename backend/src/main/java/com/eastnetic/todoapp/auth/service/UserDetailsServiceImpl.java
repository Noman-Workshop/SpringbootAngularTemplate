package com.eastnetic.todoapp.auth.service;

import com.eastnetic.todoapp.auth.domain.entity.UserDetailsImpl;
import com.eastnetic.todoapp.user.domain.mapper.UserMapper;
import com.eastnetic.todoapp.user.exception.UserNotFoundException;
import com.eastnetic.todoapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserService userService;
	private final UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			var user = userService.getByEmail(username);
			return new UserDetailsImpl(userMapper.toEntity(user));
		} catch (UserNotFoundException exception) {
			throw new UsernameNotFoundException(exception.getMessage());
		}
	}
}
