package com.eastnetic.todoapp.assignrole.repository;
import com.eastnetic.todoapp.assignrole.domain.Rolesassigned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignRoleRepository extends JpaRepository<Rolesassigned, Long> {
    void deleteAllByUserId(Long userId);
}
