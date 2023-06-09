package com.eastnetic.todoapp.rolemenu.exception;

import com.eastnetic.todoapp.common.exception.RESTException;

import static com.eastnetic.todoapp.rolemenu.constant.Message.MENU_IS_INVALID_OR_ALREADY_ASSIGNED;

public class MenuAssignUnsuccessful extends RESTException {
    public MenuAssignUnsuccessful(){
        super(MENU_IS_INVALID_OR_ALREADY_ASSIGNED.getMessage());
    }
}
