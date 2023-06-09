package com.eastnetic.todoapp.rolemenu.mapper;
import com.eastnetic.todoapp.rolemenu.domain.RoleMenu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMenuMapper {
    RoleMenu reqToEntity(Long roleId, Long menuId);
}
