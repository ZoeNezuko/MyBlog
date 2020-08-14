package com.jie.service;

import com.jie.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jie Pu
 */
@Repository
public interface UserService {

    // Check if the user exists
    boolean isExist(String username);

    // Get all users
    User getByName(String username);

    // Create new user
    int register(User user);

    // Get all users information and bring the roles id(rid)
    List<User> getAllUser();

    // Get users by specific username
    User findByName(String username);

    // Update user role information
    void editUser(User user);

    // Update user status
    void userStatusChanged(User user);

    // Reset user password
    void resetPassword(User user);

    // Get user id
    User getById(Integer uid);
}
