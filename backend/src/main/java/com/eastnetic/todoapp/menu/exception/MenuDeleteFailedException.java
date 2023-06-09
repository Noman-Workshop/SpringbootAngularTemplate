package com.eastnetic.todoapp.menu.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.menu.constant.Message.MENU_DELETE_FAILED;
import static com.eastnetic.todoapp.role.constant.Message.ROLE_DELETE_FAILED;

public class MenuDeleteFailedException extends RESTException {
    public MenuDeleteFailedException(){
        super(MENU_DELETE_FAILED.getMessage());
    }
}
