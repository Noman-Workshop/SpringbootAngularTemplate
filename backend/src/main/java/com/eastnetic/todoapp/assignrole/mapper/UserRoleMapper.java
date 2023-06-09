package com.eastnetic.todoapp.assignrole.mapper;
import com.eastnetic.todoapp.assignrole.domain.Rolesassigned;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    Rolesassigned reqToEntity(Long userId, Long roleId);
}
