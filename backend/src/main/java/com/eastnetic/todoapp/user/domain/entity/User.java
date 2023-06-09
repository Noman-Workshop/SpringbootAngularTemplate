package com.eastnetic.todoapp.user.domain.entity;

import com.eastnetic.todoapp.common.domain.entities.BaseEntity;
import com.eastnetic.todoapp.role.domain.entity.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "users", schema = "public")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String email;
	
	@Column (nullable = false)
	private String firstName;
	
	@Column (nullable = false)
	private String lastName;
	
	@Column (nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "users_id"),
			inverseJoinColumns = @JoinColumn(name = "roles_id")
	)
	private Set<Role> user_roles = new HashSet<>();
}
