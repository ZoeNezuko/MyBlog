package com.jie.controller;

import com.jie.model.User;
import com.jie.result.Result;
import com.jie.result.ResultFactory;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jie Pu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    // Get request, get all users
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public List<User> listUser() {
        return userService.getAllUser();
    }

    // Get request, get specific username
    @RequestMapping(value = "/my/{username}", method = RequestMethod.GET)
    public User findUser(@PathVariable("username") String username) {
        return userService.findByName(username);
    }

    // Put request, update user
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Result editUser(@RequestBody User user) {
        userService.editUser(user);
        return ResultFactory.SuccessResult("Successfully modify user information");
    }

    // Put request, update user status
    @RequestMapping(value = "/status", method = RequestMethod.PUT)
    public Result updateUserStatus(@RequestBody User user) {
        userService.userStatusChanged(user);
        return ResultFactory.SuccessResult("User status update successfully");
    }

    // Put request, reset user password
    @RequestMapping(value = "/repassword", method = RequestMethod.PUT)
    public Result resetPassword(@RequestBody User user) {
        userService.resetPassword(user);
        return ResultFactory.SuccessResult("Password reset successfully");
    }
}

