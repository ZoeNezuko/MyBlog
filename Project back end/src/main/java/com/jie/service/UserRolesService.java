package com.jie.service;

import com.jie.model.Role;
import com.jie.model.UserRoles;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface UserRolesService {

    // Get all user id
    List<UserRoles> listAllByUid(int uid);

    // Update role change
    void saveRoleChanges(int uid, List<Role> roles);

}
