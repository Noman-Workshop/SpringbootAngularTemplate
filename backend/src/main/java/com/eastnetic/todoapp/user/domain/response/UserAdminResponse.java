package com.eastnetic.todoapp.user.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
