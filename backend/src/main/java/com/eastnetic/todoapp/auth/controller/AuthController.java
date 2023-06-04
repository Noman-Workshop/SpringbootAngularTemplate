package com.eastnetic.todoapp.auth.controller;

import com.eastnetic.todoapp.auth.domain.request.SignInRequest;
import com.eastnetic.todoapp.auth.domain.response.AuthResponse;
import com.eastnetic.todoapp.auth.service.AuthService;
import com.eastnetic.todoapp.common.controller.BaseController;
import com.eastnetic.todoapp.common.domain.response.ApiResponse;
import com.eastnetic.todoapp.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eastnetic.todoapp.auth.constant.Message.*;

@RestController
@RequestMapping ("/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {
	
	private final AuthService authService;
	
	@PostMapping ("/signin")
	public ResponseEntity<ApiResponse<String>> signIn(@Valid @RequestBody SignInRequest signInRequest) {
		AuthResponse tokens = authService.signIn(signInRequest);
		return ResponseUtil.createResponse(HttpStatus.OK,
		                                   authService.getRefreshTokenCookie(tokens.getRefreshToken()),
		                                   SIGNED_IN_SUCCESSFULLY.getMessage(),
		                                   tokens.getAccessToken());
	}
	
	@PostMapping ("/refresh")
	public ResponseEntity<ApiResponse<String>> refresh(@CookieValue (value = "refreshToken") String refreshToken) {
		AuthResponse tokens = authService.refreshTokens(refreshToken);
		return ResponseUtil.createResponse(HttpStatus.OK,
		                                   authService.getRefreshTokenCookie(tokens.getRefreshToken()),
		                                   TOKENS_REFRESHED_SUCCESSFULLY.getMessage(),
		                                   tokens.getAccessToken());
	}
	
	@PostMapping ("/signout")
	public ResponseEntity<ApiResponse<String>> signOut(@CookieValue (value = "refreshToken") String refreshToken) {
		authService.signOut(refreshToken);
		return ResponseUtil.createResponse(HttpStatus.OK,
		                                   authService.getEmptyCookie(),
		                                   SIGNED_OUT_SUCCESSFULLY.getMessage(),
		                                   null);
	}
	
}
