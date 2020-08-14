package com.jie.service.impl;

import com.jie.mapper.UserMapper;
import com.jie.mapper.UserRolesMapper;
import com.jie.model.Role;
import com.jie.model.User;
import com.jie.model.UserRoles;
import com.jie.service.RoleService;
import com.jie.service.UserRolesService;
import com.jie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jie Pu
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRolesMapper userRolesMapper;

    @Autowired
    UserRolesService userRolesService;

    @Lazy //if no lazy annotation Shiro bean will report an error
    @Autowired
    RoleService roleService;

    // Check if the user exists
    @Override
    public boolean isExist(String username) {
        User user = userMapper.findByUsername(username);
        return null != user;
    }

    // Get all users
    @Override
    public User getByName(String username) {
        return userMapper.findByUsername(username);
    }

    // Create new user
    @Override
    public int register(User user) {
        boolean exist = isExist(user.getUsername());

        // Check if the user exists
        if (exist) {
            return 1;
        }

        // Return number from LoginController
        userMapper.save(user);

        // Save user id and role id
        UserRoles userRoles = new UserRoles();
        userRoles.setUid(user.getId());
        userRoles.setRid(3);
        userRolesMapper.save(userRoles);
        return 2;
    }

    // Get all users information and bring the role id
    @Override
    public List<User> getAllUser() {
        List<User> users = userMapper.list();
        List<Role> roles;
        //Traverse all users and roles
        for (User user : users) {
            roles = roleService.listRolesByUser(user.getUsername());
            user.setRoles(roles);
        }
        return users;
    }

    // Get users by specific username
    @Override
    public User findByName(String username) {
        return userMapper.findByUsername(username);
    }

    // Update user role information
    @Override
    public void editUser(User user) {
        User userInDB = userMapper.findByUsername(user.getUsername());
        userRolesService.saveRoleChanges(userInDB.getId(), user.getRoles());
    }

    // Update user status
    @Override
    public void userStatusChanged(User user) {
        User userInDB = userMapper.findByUsername(user.getUsername());
        userInDB.setEnable(user.isEnable());
        userMapper.save(userInDB);
    }

    // Reset user password
    @Override
    public void resetPassword(User user) {
        User userInDB = userMapper.findByUsername(user.getUsername());
        userInDB.setPassword(user.getPassword());
        userMapper.save(userInDB);
    }

    // Get user id
    @Override
    public User getById(Integer uid) {
        if (null != uid) {
            User user = userMapper.getOne(uid);
            return user;
        }
        return null;
    }
}
