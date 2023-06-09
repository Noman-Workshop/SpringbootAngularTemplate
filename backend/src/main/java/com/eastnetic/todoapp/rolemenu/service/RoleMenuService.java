package com.eastnetic.todoapp.rolemenu.service;

import com.eastnetic.todoapp.assignrole.domain.AssignRoleRequest;
import com.eastnetic.todoapp.assignrole.domain.AssignRoleResponse;
import com.eastnetic.todoapp.rolemenu.domain.RoleMenuRequest;
import com.eastnetic.todoapp.rolemenu.domain.RoleMenuResponse;

import javax.transaction.Transactional;

public interface RoleMenuService {

    @Transactional(rollbackOn = Exception.class)
    RoleMenuResponse assign(RoleMenuRequest roleMenuRequest);

    RoleMenuResponse update(RoleMenuRequest roleMenuRequest);

    @Transactional(rollbackOn = Exception.class)
    void delete(Long roleId);
}
