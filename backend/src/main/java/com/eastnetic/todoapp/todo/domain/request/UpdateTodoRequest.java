package com.eastnetic.todoapp.todo.domain.request;

import com.eastnetic.todoapp.todo.domain.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

/**
 * A create dto for the {@link Todo} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTodoRequest implements Serializable {
	
	@NotNull
	@Positive
	private Long id;
	
	@NotBlank
	@Length (min = 5, max = 256)
	private String name;
	
	@Length (max = 1024)
	private String description;
	
	@NotNull
	private Date dueDate;

	@NotNull
	private Boolean isImportant;
	
	@NotBlank
	private String status;
}
