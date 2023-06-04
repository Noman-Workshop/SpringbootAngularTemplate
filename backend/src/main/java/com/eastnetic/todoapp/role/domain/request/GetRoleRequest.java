package com.eastnetic.todoapp.role.domain.request;

import com.eastnetic.todoapp.common.domain.request.PaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoleRequest {

    private PaginationRequest pagination;
    private RoleFilterRequest filters;
}
