package com.jie.service;

import com.jie.model.Role;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface RoleService {

    // Get all roles
    List<Role> listAll();

    // Get all roles and bring the users information
    List<Role> listRolesByUser(String username);

}
