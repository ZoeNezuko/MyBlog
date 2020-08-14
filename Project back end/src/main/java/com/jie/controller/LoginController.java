package com.jie.controller;

import com.jie.result.Result;
import com.jie.model.User;
import com.jie.result.ResultFactory;
import com.jie.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jie Pu
 */
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    // Post request, user login request
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User requestUser) {

        String username = requestUser.getUsername();

        // Get current user
        Subject subject = SecurityUtils.getSubject();

        // Encapsulate the user login data
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());

        // Set cookie manage object remember me
        usernamePasswordToken.setRememberMe(true);

        // Determine user login
        try {
            subject.login(usernamePasswordToken);
            User user = userService.getByName(username);
            if (!user.isEnable()) {
                return ResultFactory.FailResult("This user has been disabled");
            }
            return ResultFactory.SuccessResult(username);
        } catch (UnknownAccountException e) {
            return ResultFactory.FailResult("Account does not exist");
        } catch (IncorrectCredentialsException e) {
            return ResultFactory.FailResult("Incorrect password");
        }
    }

    // Get request, user logout request
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout(); // Use ctrl+alt+b to view the shiro framework implementation interface in intellij IDEA
        return ResultFactory.SuccessResult("logout ");
    }

    // Post request, user register request
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {
        int status = userService.register(user);
        switch (status) {
            case 1:
                return ResultFactory.FailResult("Username already exists, please change username");
            case 2:
                return ResultFactory.SuccessResult("Registration success");
        }
        return ResultFactory.FailResult("unknown error");
    }

    // Get request, Match the authentication interface in the front-end router.beforeEach to verify user information
    @RequestMapping(value = "/authentication", method = RequestMethod.GET)
    public String authentication() {
        return "Successful verification";
    }
}
