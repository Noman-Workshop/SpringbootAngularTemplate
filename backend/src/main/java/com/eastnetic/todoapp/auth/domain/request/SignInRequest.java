package com.eastnetic.todoapp.auth.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the user sign-in request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest implements Serializable {
	
	private String email;
	private String password;
}