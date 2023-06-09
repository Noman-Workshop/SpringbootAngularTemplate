package com.eastnetic.todoapp.rolemenu.constant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
    ASSIGNED_MENU_SAVED_SUCCESSFUL("menu assign successfully"),
    ASSIGNED_MENU_SAVED_UNSUCCESSFUL("menu assign unsuccessful"),
    ROLE_ALREADY_WAS_ASSIGNED_THAT_MENU("role already was assigned that menu"),
    MENU_IS_INVALID("menu is invalid"),
    ROLE_IS_INVALID("role is invalid"),
    MENU_IS_INVALID_OR_ALREADY_ASSIGNED("menu is invalid or already was assigned that menu");
    private final String message;
}

