package com.jie.service.impl;

import com.jie.mapper.UserRolesMapper;
import com.jie.model.Role;
import com.jie.model.UserRoles;
import com.jie.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jie Pu
 */
@Service
public class UserRolesServiceImpl implements UserRolesService {

    @Autowired
    UserRolesMapper userRolesMapper;

    // Get all user id
    @Override
    public List<UserRoles> listAllByUid(int uid) {
        return userRolesMapper.findAllByUid(uid);
    }

    // Update role change
    @Transactional
    @Override
    public void saveRoleChanges(int uid, List<Role> roles) {
        // first, delete all uid
        userRolesMapper.deleteAllByUid(uid);

        List<UserRoles> userRoles = new ArrayList<>();
        // Then insert according to the newly passed data
        roles.forEach(role -> {
            UserRoles userRole = new UserRoles();
            userRole.setUid(uid);
            userRole.setRid(role.getId());
            userRoles.add(userRole);
        });
        userRolesMapper.saveAll(userRoles);
    }
}
