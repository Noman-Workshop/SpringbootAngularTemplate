package com.eastnetic.todoapp.rolemenu.controller;
import com.eastnetic.todoapp.common.controller.BaseController;
import com.eastnetic.todoapp.common.domain.response.ApiResponse;
import com.eastnetic.todoapp.common.util.ResponseUtil;
import com.eastnetic.todoapp.rolemenu.domain.RoleMenuRequest;
import com.eastnetic.todoapp.rolemenu.service.RoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eastnetic.todoapp.todo.constant.Message.TODO_DELETED_SUCCESSFULLY;

@RestController
@RequestMapping ("/rolemenu")
@RequiredArgsConstructor
public class RoleMenuController extends BaseController {

    private final RoleMenuService roleMenuService;

    @PostMapping ("/assign")
    public ResponseEntity<ApiResponse<String>> assign(@Valid @RequestBody RoleMenuRequest roleMenuRequest) {

        String tokens=roleMenuService.assign(roleMenuRequest).getRespMessage();
        return ResponseUtil.createResponse(HttpStatus.OK,
                tokens, "");
    }

    @PostMapping ("/update")
    public ResponseEntity<ApiResponse<String>> update(@Valid @RequestBody RoleMenuRequest roleMenuRequest) {

        String tokens = roleMenuService.update(roleMenuRequest).getRespMessage();
        return ResponseUtil.createResponse(HttpStatus.OK,
                tokens, "");
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        roleMenuService.delete(id);
        return ResponseUtil.createResponse(HttpStatus.OK, TODO_DELETED_SUCCESSFULLY.getMessage(), null);
    }

}

