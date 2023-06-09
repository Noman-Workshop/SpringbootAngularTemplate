package com.eastnetic.todoapp.rolemenu.repository;
import com.eastnetic.todoapp.assignrole.domain.Rolesassigned;
import com.eastnetic.todoapp.menu.domain.MenuEntity;
import com.eastnetic.todoapp.rolemenu.domain.RoleMenu;
import com.eastnetic.todoapp.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {
    void deleteAllByRoleId(Long roleId);
}
