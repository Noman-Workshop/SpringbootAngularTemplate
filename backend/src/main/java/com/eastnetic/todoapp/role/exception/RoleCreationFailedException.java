package com.eastnetic.todoapp.role.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.role.constant.Message.ROLE_CREATE_FAILED;

public class RoleCreationFailedException extends RESTException {
    public RoleCreationFailedException(){
        super(ROLE_CREATE_FAILED.getMessage());
    }
}
