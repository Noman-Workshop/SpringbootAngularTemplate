package com.eastnetic.todoapp.assignrole.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.assignrole.constant.Message.ROLE_IS_INVALID;
import static com.eastnetic.todoapp.role.constant.Message.ROLE_CREATE_FAILED;

public class RoleInvalidException extends RESTException {
    public RoleInvalidException(){
        super(ROLE_IS_INVALID.getMessage());
    }
}
