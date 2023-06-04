package com.eastnetic.todoapp.user.service;

import com.eastnetic.todoapp.auth.domain.entity.UserDetailsImpl;
import com.eastnetic.todoapp.common.service.BaseService;
import com.eastnetic.todoapp.user.domain.mapper.UserMapper;
import com.eastnetic.todoapp.user.domain.request.CreateUserRequest;
import com.eastnetic.todoapp.user.domain.entity.User;
import com.eastnetic.todoapp.user.domain.request.UpdateUserRequest;
import com.eastnetic.todoapp.user.domain.response.UserDetailsResponse;
import com.eastnetic.todoapp.user.exception.UserAlreadyExistsException;
import com.eastnetic.todoapp.user.exception.UserCreationFailedException;
import com.eastnetic.todoapp.user.exception.UserNotFoundException;
import com.eastnetic.todoapp.user.exception.UserUpdateFailedException;
import com.eastnetic.todoapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetailsResponse create(CreateUserRequest createUserRequest) {
		// check if user already exists
		try {
			UserDetailsResponse existingUser = getByEmail(createUserRequest.getEmail());
			throw new UserAlreadyExistsException(existingUser.getEmail());
		} catch (UserNotFoundException ignored) {
			// user does not exist, continue
		}
		
		try {
			User entity = userMapper.toEntity(createUserRequest);
			// TODO: Can we move this to the mapper?
			entity.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
			User user = userRepository.save(entity);
			return userMapper.toDto(user);
		} catch (Exception ignored) {
			throw new UserCreationFailedException();
		}
	}
	
	@Override
	public UserDetailsResponse get(Long id) {
		UserDetailsImpl user = getCurrentUser();
		if (!user.getId()
		         .equals(id)) {
			throw new UserNotFoundException(id);
		}
		
		return userRepository.findById(id)
		                     .map(userMapper::toDto)
		                     .orElseThrow(() -> new UserNotFoundException(id));
	}
	
	@Override
	public UserDetailsResponse getByEmail(String email) {
		return userRepository.findByEmail(email)
		                     .map(userMapper::toDto)
		                     .orElseThrow(() -> new UserNotFoundException(email));
	}
	
	@Override
	public UserDetailsResponse update(UpdateUserRequest updateUserRequest) {
		
		// check if the current user is updating their own profile
		if (!updateUserRequest.getId()
		                      .equals(getCurrentUser().getId())) {
			throw new UserNotFoundException(updateUserRequest.getId());
		}
		
		// check if user exists first
		UserDetailsResponse existingUser = get(updateUserRequest.getId());
		
		// check if email is being updated
		if (!existingUser.getEmail()
		                 .equals(updateUserRequest.getEmail())) {
			// check if new email is already taken
			Optional<User> optionalUser = userRepository.findByEmail(updateUserRequest.getEmail());
			if (optionalUser.isPresent()) {
				throw new UserAlreadyExistsException(updateUserRequest.getEmail());
			}
		}
		
		User updatedUser = userMapper.toEntity(existingUser);
		userMapper.updateEntity(updateUserRequest, updatedUser);
		
		try {
			User user = userRepository.save(updatedUser);
			return userMapper.toDto(user);
		} catch (Exception ignored) {
			throw new UserUpdateFailedException();
		}
	}
}
