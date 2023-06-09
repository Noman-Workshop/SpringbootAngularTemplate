package com.eastnetic.todoapp.menu.service;

import com.eastnetic.todoapp.common.service.BaseService;
import com.eastnetic.todoapp.menu.domain.*;
import com.eastnetic.todoapp.menu.exception.MenuDeleteFailedException;
import com.eastnetic.todoapp.menu.mapper.MenuMapper;
import com.eastnetic.todoapp.menu.repository.MenuRepository;
import com.eastnetic.todoapp.role.domain.entity.Role;
import com.eastnetic.todoapp.role.domain.request.UpdateRoleRequest;
import com.eastnetic.todoapp.role.domain.response.RoleResponse;
import com.eastnetic.todoapp.role.exception.RoleDeleteFailedException;
import com.eastnetic.todoapp.role.exception.RoleUpdateFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.eastnetic.todoapp.menu.constant.Message.*;
import static org.hibernate.id.IdentifierGeneratorHelper.get;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends BaseService implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;
    @Override
    public MenuResponse getallmenu(){
        List<MenuEntity> menu =  menuRepository.findAllByParentIdIsNull();
        return new MenuResponse(MENU_FOUND.getMessage(), menu);
    }

    @Override
    public MenuResponse getmenu(MenuRequest menuRequest) {
        System.out.println("whatsdfsfsdfsdfsdfsdf");
        System.out.println(menuRequest.getId());
        List<Long> menuidlist =  menuRepository.findDistinctByUserId(menuRequest.getId());
        List<MenuEntity>menu = new ArrayList<>();
        menuidlist.forEach(menuid-> {
            System.out.println(menuid);
            MenuEntity menuEntity= menuRepository.findById(menuid).get();
            menu.add(menuEntity);
        });
        if (menu == null ) {
            return new MenuResponse(MENU_NOT_FOUND.getMessage(),null);
        }
        return new MenuResponse(MENU_FOUND.getMessage(), menu);
    }


    @Override
    public MenuCreateResponse createmenu(MenuCreateRequest menuCreateRequest) {
        MenuEntity menu= menuMapper.createToEntity(menuCreateRequest);

        MenuEntity checkmenuexist = menuRepository.findByName(menuCreateRequest.getName());
        if (checkmenuexist == null ) {
            try {
                menuRepository.save(menu);
                return new MenuCreateResponse(NEW_MENU_CREATED_SUCCESSFULLY.getMessage());
            } catch (Exception e) {
                // Handle the exception and return an appropriate response or error message
                return new MenuCreateResponse(NEW_MENU_CREATION_FAILED.getMessage() + e.getMessage());
            }
        }
        return new MenuCreateResponse(MENU_NAME_ALREADY_USED.getMessage());
    }

    @Override
    public MenuResponse update(MenuUpdateRequest menuUpdateRequest) {
        Optional<MenuEntity> checkmenuexist = menuRepository.findById(menuUpdateRequest.getId());
        if (checkmenuexist.isPresent()) {
            MenuEntity menuEntity = checkmenuexist.get();
            try{
                menuMapper.updateToEntity(menuEntity,menuUpdateRequest);
                menuRepository.save(menuEntity);
                return new MenuResponse(MENU_UPDATED_SUCCESSFULLY.getMessage(),null);
            }
            catch (Exception e) {
                //throw new RoleUpdateFailedException();
                return new MenuResponse(MENU_UPDATE_FAILED.getMessage(),null);
            }
        }
        else {
            return new MenuResponse(INVALID_MENU_ID.getMessage(),null);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            menuRepository.deleteById(id);
        } catch (Exception e){
            throw new MenuDeleteFailedException();
        }
    }

}
