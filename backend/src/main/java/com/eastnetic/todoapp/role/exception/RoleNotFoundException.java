package com.eastnetic.todoapp.role.exception;

import com.eastnetic.todoapp.common.exception.RESTException;
import org.springframework.http.HttpStatus;

import static com.eastnetic.todoapp.role.constant.Message.ROLE_NOT_FOUND;

public class RoleNotFoundException extends RESTException {
    {
        status= HttpStatus.NOT_FOUND;
    }

    public RoleNotFoundException(Long id){
        super(String.format(ROLE_NOT_FOUND.getMessage(),id));
    }
}
