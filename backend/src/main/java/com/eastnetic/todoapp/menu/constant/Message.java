package com.eastnetic.todoapp.menu.constant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {

    MENU_FOUND("Menu found"),
    MENU_NOT_FOUND("Menu not found"),
    NEW_MENU_CREATED_SUCCESSFULLY("menu created successfully"),
    NEW_MENU_CREATION_FAILED("menu creation failed"),
    MENU_NAME_ALREADY_USED("menu name already used"),
    MENU_UPDATED_SUCCESSFULLY("Menu updated successfully"),
    INVALID_MENU_ID("Invalid Menu Id"),
    MENU_UPDATE_FAILED("menu update failed"),
    MENU_DELETE_FAILED("menu delete failed"),
    MENU_DELETED_SUCCESSFULLY("menu deleted successfully");
    private final String message;
}

