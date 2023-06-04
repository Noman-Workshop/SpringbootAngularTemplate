package com.eastnetic.todoapp.role.service;

import com.eastnetic.todoapp.common.domain.response.PagedResponse;
import com.eastnetic.todoapp.role.domain.request.CreateRoleRequest;
import com.eastnetic.todoapp.role.domain.request.GetRoleRequest;
import com.eastnetic.todoapp.role.domain.request.UpdateRoleRequest;
import com.eastnetic.todoapp.role.domain.response.RoleResponse;

public interface RoleService {
    RoleResponse create(CreateRoleRequest createroleRequest);
    PagedResponse<RoleResponse> getAll(GetRoleRequest filterRoleRequest);
    RoleResponse update(UpdateRoleRequest updateRoleRequest);
    void delete(Long id);

    RoleResponse get(Long id);
}
