package com.eastnetic.todoapp.role.controller;

import com.eastnetic.todoapp.common.domain.response.ApiResponse;
import com.eastnetic.todoapp.common.domain.response.PagedResponse;
import com.eastnetic.todoapp.common.util.ResponseUtil;
import com.eastnetic.todoapp.role.domain.request.CreateRoleRequest;
import com.eastnetic.todoapp.role.domain.request.GetRoleRequest;
import com.eastnetic.todoapp.role.domain.request.UpdateRoleRequest;
import com.eastnetic.todoapp.role.domain.response.RoleResponse;
import com.eastnetic.todoapp.role.service.RoleService;
import com.eastnetic.todoapp.todo.domain.response.TodoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eastnetic.todoapp.role.constant.Message.*;
import static com.eastnetic.todoapp.todo.constant.Message.TODO_RETRIEVED_SUCCESSFULLY;


@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<RoleResponse>> create(@Valid @RequestBody CreateRoleRequest roleRequest) {
        RoleResponse response = roleService.create(roleRequest);
        return ResponseUtil.createResponse(HttpStatus.CREATED, ROLE_CREATED_SUCCESSFULLY.getMessage(),response);
    }

    @GetMapping ("/get/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> get(@PathVariable Long id) {

        RoleResponse response = roleService.get(id);
        return ResponseUtil.createResponse(HttpStatus.OK, ROLE_RETRIEVED_SUCCESSFULLY.getMessage(), response);
    }

    @PostMapping("/all")
    public ResponseEntity<ApiResponse<PagedResponse<RoleResponse>>> getAll(@Valid @RequestBody GetRoleRequest getRoleRequest) {
        PagedResponse<RoleResponse> roleDetailsResponse = roleService.getAll(getRoleRequest);
        return ResponseUtil.createResponse(HttpStatus.OK, ROLES_RETRIEVED_SUCCESSFULLY.getMessage(), roleDetailsResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<RoleResponse>> update(@Valid @RequestBody UpdateRoleRequest updateRole){
        RoleResponse response= roleService.update(updateRole);

        return ResponseUtil.createResponse(HttpStatus.OK, ROLE_UPDATED_SUCCESSFULLY.getMessage(), response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id){
        roleService.delete(id);
        return ResponseUtil.createResponse(HttpStatus.OK, ROLE_DELETED_SUCCESSFULLY.getMessage(), null);
    }
}
