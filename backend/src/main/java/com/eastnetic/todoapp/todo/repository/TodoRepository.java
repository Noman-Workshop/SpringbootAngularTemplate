package com.eastnetic.todoapp.todo.repository;

import com.eastnetic.todoapp.todo.domain.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	@Query ("select t from Todo t where t.userId = :userId")
	Page<Todo> findAllByUserId(@Param ("userId") Long userId, Pageable pageable);
	
	@Query ("select t from Todo t where t.id = :id and t.userId = :userId")
	Optional<Todo> findByIdAndUserId(@Param ("id") Long id, @Param ("userId") Long userId);
	
	Page<Todo> findAll(Specification<Todo> specification, Pageable pagination);
}