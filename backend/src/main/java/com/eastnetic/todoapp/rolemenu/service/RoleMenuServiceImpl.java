package com.eastnetic.todoapp.rolemenu.service;

import com.eastnetic.todoapp.role.domain.entity.Role;
import com.eastnetic.todoapp.role.repository.RoleRepository;
import com.eastnetic.todoapp.rolemenu.domain.RoleMenu;
import com.eastnetic.todoapp.rolemenu.domain.RoleMenuRequest;
import com.eastnetic.todoapp.rolemenu.domain.RoleMenuResponse;
import com.eastnetic.todoapp.rolemenu.exception.MenuAssignUnsuccessful;
import com.eastnetic.todoapp.rolemenu.mapper.RoleMenuMapper;
import com.eastnetic.todoapp.rolemenu.repository.RoleMenuRepository;
import com.eastnetic.todoapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.eastnetic.todoapp.rolemenu.constant.Message.*;

@Service
@RequiredArgsConstructor
public class RoleMenuServiceImpl implements RoleMenuService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleMenuRepository roleMenuRepository;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public RoleMenuResponse assign(RoleMenuRequest roleMenuRequest) {

        Optional<Role> roleOptional = roleRepository.findById(roleMenuRequest.getRoleId());
        if(roleOptional.isPresent()) {
            try {
                List<RoleMenu> rolemenuList = new ArrayList<>();
                roleMenuRequest.getMenuIds().forEach(menuId -> {
                    try{
                        RoleMenu roleMenu=roleMenuMapper.reqToEntity(roleMenuRequest.getRoleId(),menuId);
                        rolemenuList.add(roleMenu);
                    }
                    catch (Exception e) {
                        throw new MenuAssignUnsuccessful();
                    }
                });
                roleMenuRepository.saveAll(rolemenuList);
                return new RoleMenuResponse(ASSIGNED_MENU_SAVED_SUCCESSFUL.getMessage());
            }
            catch (Exception e) {
                return new RoleMenuResponse(MENU_IS_INVALID_OR_ALREADY_ASSIGNED.getMessage());
            }
        }
        return new RoleMenuResponse(ROLE_IS_INVALID.getMessage());
    }

    @Override
    public RoleMenuResponse update(RoleMenuRequest roleMenuRequest) {

        delete(roleMenuRequest.getRoleId());
        try {
            assign(roleMenuRequest);
        }
        catch (Exception e) {
            throw new MenuAssignUnsuccessful();
        }
        return new RoleMenuResponse(ASSIGNED_MENU_SAVED_SUCCESSFUL.getMessage());
    }

    @Override
    @Transactional
    public void delete(Long roleId) {
        try {
            roleMenuRepository.deleteAllByRoleId(roleId);
        }
        catch (Exception e){
            throw e;
        }
    }
}
