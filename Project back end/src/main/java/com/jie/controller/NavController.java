package com.jie.controller;


import com.jie.model.Nav;
import com.jie.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jie Pu
 */
@RestController
public class NavController {

    @Autowired
    NavService navService;

    // Get request, get all admin navs
    @RequestMapping(value = "/nav", method = RequestMethod.GET)
    public List<Nav> navs() {
        return navService.getNavsByCurrentUser();
    }
}

