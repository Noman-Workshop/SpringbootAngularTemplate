package com.eastnetic.todoapp.user.domain.request;

import com.eastnetic.todoapp.common.domain.request.PaginationRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRequest {
    private PaginationRequest pagination;
    private UserFilterRequest filters;
}
