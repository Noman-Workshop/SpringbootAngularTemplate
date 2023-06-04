package com.eastnetic.todoapp.role.mapper;

import com.eastnetic.todoapp.role.domain.entity.Role;
import com.eastnetic.todoapp.role.domain.request.CreateRoleRequest;
import com.eastnetic.todoapp.role.domain.request.UpdateRoleRequest;
import com.eastnetic.todoapp.role.domain.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role mapToEntity(CreateRoleRequest request);

    RoleResponse maptoResponse(Role role);

    void updateEntity(UpdateRoleRequest request,@MappingTarget Role entity);

    //Role toEntity(UpdateRoleRequest request);
    Role responsetoEntity(RoleResponse response);
}
