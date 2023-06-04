package com.eastnetic.todoapp.todo.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoFilterRequest {
	
	private String name;
	private String status;
	private Date dueDate;
	private Boolean isImportant;
}
