package com.eastnetic.todoapp.user.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest implements Serializable {
	
	@NotNull
	@Positive
	private Long id;
	
	@NotBlank
	@Email (regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
	private String email;
	
	@NotBlank
	@Length (min = 3, max = 20)
	private String firstName;
	
	@NotBlank
	@Length (min = 3, max = 20)
	private String lastName;
}
