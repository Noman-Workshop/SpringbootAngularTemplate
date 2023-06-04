package com.eastnetic.todoapp.user.domain.response;

import com.eastnetic.todoapp.user.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link User} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse implements Serializable {
	
	private Date createdAt;
	private Date updatedAt;
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	
	@JsonIgnore
	private String password;
}