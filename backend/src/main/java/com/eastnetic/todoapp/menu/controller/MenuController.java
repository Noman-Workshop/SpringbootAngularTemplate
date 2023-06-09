package com.eastnetic.todoapp.menu.controller;

import com.eastnetic.todoapp.common.domain.response.ApiResponse;
import com.eastnetic.todoapp.common.util.ResponseUtil;
import com.eastnetic.todoapp.menu.domain.*;
import com.eastnetic.todoapp.menu.service.MenuService;
import com.eastnetic.todoapp.role.domain.request.UpdateRoleRequest;
import com.eastnetic.todoapp.role.domain.response.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eastnetic.todoapp.menu.constant.Message.MENU_DELETED_SUCCESSFULLY;
import static com.eastnetic.todoapp.role.constant.Message.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/viewall")
    public ResponseEntity<ApiResponse<MenuResponse>> getallmenu() {

        MenuResponse response = menuService.getallmenu();
        return ResponseUtil.createResponse(HttpStatus.OK, response.getRespMessage(),response);
    }

    @PostMapping("/view")
    public ResponseEntity<ApiResponse<MenuResponse>> getmenu(@Valid @RequestBody MenuRequest menuRequest) {

        MenuResponse response = menuService.getmenu(menuRequest);
        return ResponseUtil.createResponse(HttpStatus.OK, response.getRespMessage(),response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<MenuCreateResponse>> createmenu(@Valid @RequestBody MenuCreateRequest menuCreateRequest) {
        MenuCreateResponse response = menuService.createmenu(menuCreateRequest);
        return ResponseUtil.createResponse(HttpStatus.CREATED, response.getRespMessage(),null);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<MenuCreateResponse>> update(@Valid @RequestBody MenuUpdateRequest menuUpdateRequest){
        MenuResponse response = menuService.update(menuUpdateRequest);
        return ResponseUtil.createResponse(HttpStatus.OK, response.getRespMessage(), null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id){
        menuService.delete(id);
        return ResponseUtil.createResponse(HttpStatus.OK, MENU_DELETED_SUCCESSFULLY.getMessage(), null);
    }
}
