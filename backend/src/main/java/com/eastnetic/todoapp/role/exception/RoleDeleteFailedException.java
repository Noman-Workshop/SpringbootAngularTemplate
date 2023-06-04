package com.eastnetic.todoapp.role.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.role.constant.Message.ROLE_DELETE_FAILED;

public class RoleDeleteFailedException extends RESTException {
    public RoleDeleteFailedException(){
        super(ROLE_DELETE_FAILED.getMessage());
    }
}
