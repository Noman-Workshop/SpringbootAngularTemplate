package com.eastnetic.todoapp.todo.domain.entity;

import com.eastnetic.todoapp.common.domain.entities.BaseEntity;
import com.eastnetic.todoapp.user.domain.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "todos", schema = "public")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo extends BaseEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Date dueDate;
	
	@Column (nullable = false)
	private Long status;
	
	@Column (nullable = false)
	private Long userId;

	@Column (nullable = false)
	private Boolean isImportant;
}
