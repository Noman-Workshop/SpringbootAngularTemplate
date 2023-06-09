package com.eastnetic.todoapp.assignrole.constant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
    ASSIGNED_ROLE_SAVED_SUCCESSFUL("role assign successfully"),
    ASSIGNED_ROLE_SAVED_UNSUCCESSFUL("role assign unsuccessful"),
    USER_ALREADY_WAS_ASSIGNED_THAT_ROLE("user already was assigned that role"),
    ROLE_IS_INVALID("role is invalid"),
    USER_IS_INVALID("user is invalid"),
    ROLE_IS_INVALID_OR_ALREADY_ASSIGNED("role is invalid or already was assigned that role");
    private final String message;
}

