package com.eastnetic.todoapp.todo.domain.response;

import com.eastnetic.todoapp.todo.domain.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link Todo} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponse implements Serializable {
	
	private Date createdAt;
	private Date updatedAt;
	private Long id;
	private String name;
	private String description;
	private Date dueDate;
	private String status;
	private Boolean isImportant;
}
