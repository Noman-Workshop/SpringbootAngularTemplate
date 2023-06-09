package com.eastnetic.todoapp.assignrole.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the user sign-in request
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleRequest implements Serializable {
    private Long userId;
    private List<Long> roleIds;
}
