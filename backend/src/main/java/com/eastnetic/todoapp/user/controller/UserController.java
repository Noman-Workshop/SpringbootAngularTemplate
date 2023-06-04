package com.eastnetic.todoapp.user.controller;

import com.eastnetic.todoapp.common.controller.BaseController;
import com.eastnetic.todoapp.common.domain.response.ApiResponse;
import com.eastnetic.todoapp.common.util.ResponseUtil;
import com.eastnetic.todoapp.user.domain.request.CreateUserRequest;
import com.eastnetic.todoapp.user.domain.request.UpdateUserRequest;
import com.eastnetic.todoapp.user.domain.response.UserDetailsResponse;
import com.eastnetic.todoapp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eastnetic.todoapp.user.constant.Message.*;

@RestController
@RequestMapping ("/user")
@RequiredArgsConstructor
public class UserController extends BaseController {
	
	private final UserService userService;
	
	@PostMapping ("/signup")
	public ResponseEntity<ApiResponse<UserDetailsResponse>> signup(@Valid @RequestBody CreateUserRequest createUserRequest) {
		UserDetailsResponse response = userService.create(createUserRequest);
		return ResponseUtil.createResponse(HttpStatus.CREATED, USER_CREATED_SUCCESSFULLY.getMessage(), response);
	}
	
	@GetMapping ("/account")
	public ResponseEntity<ApiResponse<UserDetailsResponse>> getAccount() {
		UserDetailsResponse response = userService.get(getCurrentUser().getId());
		return ResponseUtil.createResponse(HttpStatus.OK, USER_RETRIEVED_SUCCESSFULLY.getMessage(), response);
	}
	
	@PutMapping ("/update")
	public ResponseEntity<ApiResponse<UserDetailsResponse>> update(@Valid @RequestBody UpdateUserRequest updateuserRequest) {
		UserDetailsResponse response = userService.update(updateuserRequest);
		return ResponseUtil.createResponse(HttpStatus.OK, USER_UPDATED_SUCCESSFULLY.getMessage(), response);
	}
}
