package com.jie.controller;

import com.jie.model.Role;
import com.jie.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jie Pu
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    // Get request, get all roles
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public List<Role> listRoles() {
        return roleService.listAll();
    }
}

