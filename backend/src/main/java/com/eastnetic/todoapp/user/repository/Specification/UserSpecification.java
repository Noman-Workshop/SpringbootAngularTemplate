package com.eastnetic.todoapp.user.repository.Specification;

import com.eastnetic.todoapp.user.domain.entity.User;
import com.eastnetic.todoapp.user.domain.entity.User_;
import com.eastnetic.todoapp.user.domain.request.UserFilterRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import static com.eastnetic.todoapp.common.util.SpecificationUtils.wildcardsAndLower;

public interface UserSpecification {
    static Specification<User> criteriaFilter(UserFilterRequest userFilterRequest){
        return (root,query,criteriaBuilder) -> {

            var predicates = criteriaBuilder.conjunction();

            if(!ObjectUtils.isEmpty(userFilterRequest.getName())){
                predicates= criteriaBuilder.and(predicates,

                        criteriaBuilder.or(
                                criteriaBuilder.like(criteriaBuilder.lower(root.get(User_.FIRST_NAME)),wildcardsAndLower(userFilterRequest.getName())),
                                criteriaBuilder.like(criteriaBuilder.lower(root.get(User_.LAST_NAME)),wildcardsAndLower(userFilterRequest.getName()))
                        ));
            }
            return predicates;
        };
    }
}
