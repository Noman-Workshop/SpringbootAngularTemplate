package com.eastnetic.todoapp.menu.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.menu.constant.Message.MENU_UPDATE_FAILED;
import static com.eastnetic.todoapp.role.constant.Message.ROLE_UPDATE_FAILED;

public class MenuUpdateFailedException extends RESTException {
    public MenuUpdateFailedException(){
        super(MENU_UPDATE_FAILED.getMessage());
    }
}
