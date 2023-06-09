package com.eastnetic.todoapp.assignrole.service;

import com.eastnetic.todoapp.assignrole.domain.AssignRoleRequest;
import com.eastnetic.todoapp.assignrole.domain.AssignRoleResponse;

import javax.transaction.Transactional;

public interface AssignRoleService {

    @Transactional(rollbackOn = Exception.class)
    AssignRoleResponse assign(AssignRoleRequest assignRoleRequest);

    AssignRoleResponse update(AssignRoleRequest assignRoleRequest);
    @Transactional(rollbackOn = Exception.class)
    void delete(Long userId);
}
