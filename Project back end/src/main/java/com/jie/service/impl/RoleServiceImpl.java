package com.jie.service.impl;

import com.jie.mapper.RoleMapper;
import com.jie.model.Role;
import com.jie.model.UserRoles;
import com.jie.service.RoleService;
import com.jie.service.UserRolesService;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jie Pu
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    UserService userService;

    @Autowired
    UserRolesService userRolesService;

    @Autowired
    RoleMapper roleMapper;

    // Get all roles
    @Override
    public List<Role> listAll() {
        return roleMapper.findAll();
    }

    // Get all roles and bring the users information
    @Override
    public List<Role> listRolesByUser(String username) {
        int uid = userService.getByName(username).getId();
        List<Integer> rids = userRolesService.listAllByUid(uid)
                .stream().map(UserRoles::getRid).collect(Collectors.toList());
        return roleMapper.findAllById(rids);
    }

}
