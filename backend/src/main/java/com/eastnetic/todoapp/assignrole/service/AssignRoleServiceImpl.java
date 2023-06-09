package com.eastnetic.todoapp.assignrole.service;

import com.eastnetic.todoapp.assignrole.domain.AssignRoleRequest;
import com.eastnetic.todoapp.assignrole.domain.AssignRoleResponse;
import com.eastnetic.todoapp.assignrole.domain.Rolesassigned;
import com.eastnetic.todoapp.assignrole.exception.RoleAssignedFailedException;
import com.eastnetic.todoapp.assignrole.exception.RoleInvalidException;
import com.eastnetic.todoapp.assignrole.mapper.UserRoleMapper;
import com.eastnetic.todoapp.assignrole.repository.AssignRoleRepository;
import com.eastnetic.todoapp.role.exception.RoleCreationFailedException;
import com.eastnetic.todoapp.role.exception.RoleDeleteFailedException;
import com.eastnetic.todoapp.todo.domain.response.TodoResponse;
import com.eastnetic.todoapp.todo.exception.TodoDeleteFailedException;
import com.eastnetic.todoapp.user.domain.entity.User;
import com.eastnetic.todoapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.eastnetic.todoapp.assignrole.constant.Message.*;
import static org.hibernate.id.IdentifierGeneratorHelper.get;

@Service
@RequiredArgsConstructor
public class AssignRoleServiceImpl implements AssignRoleService {
    private final UserRepository userRepository;
    private final AssignRoleRepository assignRoleRepository;
    private final UserRoleMapper userRoleMapper;

    @Override
    public AssignRoleResponse assign(AssignRoleRequest assignRoleRequest) {

        Optional<User> userOptional = userRepository.findById(assignRoleRequest.getUserId());
        if(userOptional.isPresent()) {
            try {
                List<Rolesassigned> rolesassignedList = new ArrayList<>();
                assignRoleRequest.getRoleIds().forEach(roleId -> {
                    try{
                        Rolesassigned rolesassigned = userRoleMapper.reqToEntity(assignRoleRequest.getUserId(),roleId);
                        rolesassignedList.add(rolesassigned);
                    }
                    catch (Exception e) {
                        throw new RoleAssignedFailedException();
                    }
                });
                assignRoleRepository.saveAll(rolesassignedList);
                return new AssignRoleResponse(ASSIGNED_ROLE_SAVED_SUCCESSFUL.getMessage());
            }
            catch (Exception e) {
                return new AssignRoleResponse(ROLE_IS_INVALID_OR_ALREADY_ASSIGNED.getMessage());
            }
        }
        return new AssignRoleResponse(USER_IS_INVALID.getMessage());
    }

    @Override
    public AssignRoleResponse update(AssignRoleRequest assignRoleRequest) {

        Optional<User> userOptional = userRepository.findById(assignRoleRequest.getUserId());
        if(userOptional.isPresent()) {
            List<Rolesassigned> rolesassignedList = new ArrayList<>();
            assignRoleRequest.getRoleIds().forEach(roleId -> {
                try {
                    Rolesassigned rolesassigned = new Rolesassigned();
                    rolesassigned.setUserId(assignRoleRequest.getUserId());
                    rolesassigned.setRoleId(roleId);
                    assignRoleRepository.save(rolesassigned);
                }
                catch (DataIntegrityViolationException e) {
                    if (e.getCause() instanceof ConstraintViolationException) {
                        ConstraintViolationException constraintViolationException = (ConstraintViolationException) e.getCause();
                        if (constraintViolationException.getConstraintName().equals("unique_user_role")) {
                            //

                        }
                        else if (constraintViolationException.getConstraintName().equals("user_role_role_id_fkey")) {
                            throw new RoleInvalidException();
                        }
                    }
                }
                catch (Exception e) {

                }
            });
            return new AssignRoleResponse(ASSIGNED_ROLE_SAVED_SUCCESSFUL.getMessage());
        }
        return new AssignRoleResponse(USER_IS_INVALID.getMessage());
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        try {
            assignRoleRepository.deleteAllByUserId(userId);
        }
        catch (Exception e){
            throw e;
        }
    }
}
