package com.eastnetic.todoapp.menu.service;

import com.eastnetic.todoapp.menu.domain.*;
import com.eastnetic.todoapp.role.domain.request.UpdateRoleRequest;
import com.eastnetic.todoapp.role.domain.response.RoleResponse;

import javax.validation.Valid;
import java.util.List;

public interface MenuService {
    MenuResponse getmenu(MenuRequest menuRoleRequest);
//    List<MenuEntity> getChildMenus(MenuEntity parentMenu);

    MenuCreateResponse createmenu(@Valid MenuCreateRequest menuEntity);
    MenuResponse update(MenuUpdateRequest menuUpdateRequest);
    void delete(Long id);

    MenuResponse getallmenu();
}
