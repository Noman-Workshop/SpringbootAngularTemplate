package com.eastnetic.todoapp.user.domain.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFilterRequest {
    private String name;
}
