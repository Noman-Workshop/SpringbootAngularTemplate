package com.eastnetic.todoapp.role.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.role.constant.Message.ROLE_UPDATE_FAILED;

public class RoleUpdateFailedException extends RESTException {
    public RoleUpdateFailedException(){
        super(ROLE_UPDATE_FAILED.getMessage());
    }
}
