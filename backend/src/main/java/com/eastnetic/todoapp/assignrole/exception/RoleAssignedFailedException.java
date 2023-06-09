package com.eastnetic.todoapp.assignrole.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.assignrole.constant.Message.ROLE_IS_INVALID;
import static com.eastnetic.todoapp.assignrole.constant.Message.ROLE_IS_INVALID_OR_ALREADY_ASSIGNED;

public class RoleAssignedFailedException extends RESTException {
    public RoleAssignedFailedException(){
        super(ROLE_IS_INVALID_OR_ALREADY_ASSIGNED.getMessage());
    }
}

