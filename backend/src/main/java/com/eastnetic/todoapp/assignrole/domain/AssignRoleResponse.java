package com.eastnetic.todoapp.assignrole.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;

//@AllArgsConstructor
@Getter
public class AssignRoleResponse {
    //private Long userId;
    private String RespMessage;
//    public AssignRoleResponse(Long userId){
//        this.userId = userId;
//    }
    public AssignRoleResponse(String RespMessage){
        this.RespMessage = RespMessage;
    }
}
