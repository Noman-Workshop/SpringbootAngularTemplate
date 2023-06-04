package com.eastnetic.todoapp.role.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum Message {

    ROLE_CREATED_SUCCESSFULLY("role created successfully"),
    ROLE_RETRIEVED_SUCCESSFULLY("role retrieved successfully"),
    ROLES_RETRIEVED_SUCCESSFULLY("roles retrieved successfully"),
    ROLE_UPDATED_SUCCESSFULLY("role updated successfully"),
    ROLE_DELETED_SUCCESSFULLY("role deleted successfully"),
    ROLE_CREATE_FAILED("failed to create role"),
    ROLE_NOT_FOUND("role with id %d not found"),
    ROLE_DELETE_FAILED("failed to delete role"),
    ROLE_UPDATE_FAILED("failed to update role");

    private final String message;
}


