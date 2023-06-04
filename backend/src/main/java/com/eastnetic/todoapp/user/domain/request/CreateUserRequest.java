package com.eastnetic.todoapp.user.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for user sign-up request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest implements Serializable {
	
	@NotBlank
	@Email (regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
	private String email;
	
	@NotBlank
	@Length (min = 3, max = 20)
	private String firstName;
	
	@NotBlank
	@Length (min = 3, max = 20)
	private String lastName;
	
	@NotBlank
	@Length (min = 8, max = 20)
	private String password;
}
