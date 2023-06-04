package com.eastnetic.todoapp.role.repository.Specification;

import com.eastnetic.todoapp.role.domain.entity.Role;
import com.eastnetic.todoapp.role.domain.entity.Role_;
import com.eastnetic.todoapp.role.domain.request.RoleFilterRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import static com.eastnetic.todoapp.common.util.SpecificationUtils.wildcardsAndLower;

public interface RoleSpecification {
    static Specification<Role> criteriaFilter(RoleFilterRequest roleFilterRequest) {
        return (root, query, criteriaBuilder) -> {

            var predicates = criteriaBuilder.conjunction();

            if (!ObjectUtils.isEmpty(roleFilterRequest.getName())) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(Role_.NAME)),wildcardsAndLower(roleFilterRequest.getName())));
            }
            return predicates;
        };
    }
}
