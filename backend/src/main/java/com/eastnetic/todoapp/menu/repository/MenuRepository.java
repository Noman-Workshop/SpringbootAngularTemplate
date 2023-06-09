package com.eastnetic.todoapp.menu.repository;

import com.eastnetic.todoapp.menu.domain.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuEntity,Long>, JpaSpecificationExecutor<MenuEntity> {
    Optional<MenuEntity> findById(Long id);
    List<MenuEntity>findAll();
    List<MenuEntity>findAllById(Long id);
    MenuEntity findByName(String name);
    List<MenuEntity> findAllByParentIdIsNull();

    @Query(value= "select menu_id from role_menu inner join user_role on role_menu.role_id = user_role.role_id where user_role.user_id = :userId", nativeQuery = true)
    List<Long>findDistinctByUserId(@Param("userId") Long userId);


}
