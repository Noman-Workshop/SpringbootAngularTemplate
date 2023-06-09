package com.eastnetic.todoapp.assignrole.controller;
import com.eastnetic.todoapp.assignrole.domain.AssignRoleRequest;
import com.eastnetic.todoapp.assignrole.domain.AssignRoleResponse;
import com.eastnetic.todoapp.assignrole.service.AssignRoleService;
import com.eastnetic.todoapp.auth.domain.request.SignInRequest;
import com.eastnetic.todoapp.auth.domain.response.AuthResponse;
import com.eastnetic.todoapp.auth.service.AuthService;
import com.eastnetic.todoapp.common.controller.BaseController;
import com.eastnetic.todoapp.common.domain.response.ApiResponse;
import com.eastnetic.todoapp.common.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eastnetic.todoapp.assignrole.constant.Message.*;
import static com.eastnetic.todoapp.auth.constant.Message.SIGNED_IN_SUCCESSFULLY;
import static com.eastnetic.todoapp.todo.constant.Message.TODO_DELETED_SUCCESSFULLY;

@RestController
@RequestMapping ("/userrole")
@RequiredArgsConstructor
public class AssignRoleController extends BaseController {

    private final AssignRoleService assignRoleService;

    @PostMapping ("/assign")
    public ResponseEntity<ApiResponse<String>> assign(@Valid @RequestBody AssignRoleRequest assignRoleRequest) {

        String tokens=assignRoleService.assign(assignRoleRequest).getRespMessage();
        return ResponseUtil.createResponse(HttpStatus.OK,
                tokens, "");
    }

    @PostMapping ("/update")
    public ResponseEntity<ApiResponse<String>> update(@Valid @RequestBody AssignRoleRequest assignRoleRequest) {

        String tokens = assignRoleService.update(assignRoleRequest).getRespMessage();
        return ResponseUtil.createResponse(HttpStatus.OK,
                tokens, "");
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        assignRoleService.delete(id);
        return ResponseUtil.createResponse(HttpStatus.OK, TODO_DELETED_SUCCESSFULLY.getMessage(), null);
    }

}

