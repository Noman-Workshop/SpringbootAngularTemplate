package com.eastnetic.todoapp.role.service;

import com.eastnetic.todoapp.common.domain.request.PaginationRequest;
import com.eastnetic.todoapp.common.domain.response.PagedResponse;
import com.eastnetic.todoapp.common.service.BaseService;
import com.eastnetic.todoapp.common.util.PageUtil;
import com.eastnetic.todoapp.role.domain.entity.Role;
import com.eastnetic.todoapp.role.domain.request.CreateRoleRequest;
import com.eastnetic.todoapp.role.domain.request.GetRoleRequest;
import com.eastnetic.todoapp.role.domain.request.UpdateRoleRequest;
import com.eastnetic.todoapp.role.domain.response.RoleResponse;
import com.eastnetic.todoapp.role.exception.RoleCreationFailedException;
import com.eastnetic.todoapp.role.exception.RoleDeleteFailedException;
import com.eastnetic.todoapp.role.exception.RoleNotFoundException;
import com.eastnetic.todoapp.role.exception.RoleUpdateFailedException;
import com.eastnetic.todoapp.role.mapper.RoleMapper;
import com.eastnetic.todoapp.role.repository.RoleRepository;
import com.eastnetic.todoapp.role.repository.Specification.RoleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseService implements RoleService {

    private final RoleRepository roleRepo;
    private final RoleMapper roleMapper;


    @Override
    public RoleResponse create(CreateRoleRequest createroleRequest) {

        Role role= roleMapper.mapToEntity(createroleRequest);

        try{
            return roleMapper.maptoResponse(roleRepo.save(role));
        } catch (Exception e){
            throw new RoleCreationFailedException();
        }
    }

    @Override
    public PagedResponse<RoleResponse> getAll(GetRoleRequest filterRoleRequest) {

        PaginationRequest paginationRequest = filterRoleRequest.getPagination();
        Sort sort= PageUtil.getSort(paginationRequest.getSorts());
        PageRequest pagination = PageRequest.of(paginationRequest.getPage()-1,paginationRequest.getSize(), sort);

        Page<Role> roles= roleRepo.findAll(RoleSpecification.criteriaFilter(filterRoleRequest.getFilters()), pagination);

        return PageUtil.paginate(roles,roleMapper::maptoResponse);
    }

    @Override
    public RoleResponse get(Long id) {
        return roleRepo.findById(id)
                .map(roleMapper::maptoResponse)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    @Override
    public RoleResponse update(UpdateRoleRequest updateRoleRequest) {
        Long id= updateRoleRequest.getId();
        RoleResponse response=get(id);

        Role updatedRole= roleMapper.responsetoEntity(response);

        roleMapper.updateEntity(updateRoleRequest,updatedRole);

        try{
            return roleMapper.maptoResponse(roleRepo.save(updatedRole));
        }catch (Exception e){
            throw new RoleUpdateFailedException();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            roleRepo.deleteById(id);
        } catch (Exception e){
            throw new RoleDeleteFailedException();
        }

    }
}
