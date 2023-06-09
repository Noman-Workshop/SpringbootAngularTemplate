package com.eastnetic.todoapp.menu.mapper;
import com.eastnetic.todoapp.menu.domain.MenuCreateRequest;
import com.eastnetic.todoapp.menu.domain.MenuEntity;
import com.eastnetic.todoapp.menu.domain.MenuResponse;
import com.eastnetic.todoapp.menu.domain.MenuUpdateRequest;
import com.eastnetic.todoapp.role.domain.entity.Role;
import com.eastnetic.todoapp.role.domain.request.UpdateRoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    //public abstract MenuResponse EntityToResponse(List<MenuEntity>menu);

    MenuEntity createToEntity(MenuCreateRequest menuCreateRequest);

//    void updateToEntity(@MappingTarget MenuEntity menuEntity, MenuUpdateRequest menuUpdateRequest);
    void updateToEntity(@MappingTarget MenuEntity menuEntity,MenuUpdateRequest menuUpdateRequest);
}
