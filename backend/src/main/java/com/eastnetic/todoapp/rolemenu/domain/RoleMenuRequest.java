package com.eastnetic.todoapp.rolemenu.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the user sign-in request
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuRequest implements Serializable {
    private Long roleId;
    private List<Long> menuIds;
}
