package com.jie.service.impl;

import com.jie.mapper.NavMapper;
import com.jie.model.Nav;
import com.jie.model.NavRoles;
import com.jie.model.User;
import com.jie.model.UserRoles;
import com.jie.service.NavRolesService;
import com.jie.service.NavService;
import com.jie.service.UserRolesService;
import com.jie.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jie Pu
 */
@Service
public class NavServiceImpl implements NavService {

    @Autowired
    UserService userService;

    @Autowired
    UserRolesService userRolesService;

    @Autowired
    NavRolesService navRolesService;

    @Autowired
    NavMapper navMapper;

    // Get current user and render the corresponding accessible nav
    @Override
    public List<Nav> getNavsByCurrentUser() {
        // Get the current user from the database
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);

        // Get the id list of all roles corresponding to the current user
        List<Integer> rids = userRolesService.listAllByUid(user.getId())
                .stream().map(UserRoles::getRid).collect(Collectors.toList());

        // Query all the navigation corresponding to these roles
        List<Integer> navIds = navRolesService.findAllByRid(rids)
                .stream().map(NavRoles::getNid).collect(Collectors.toList());

        List<Nav> navs = navMapper.findAllById(navIds).stream().distinct().collect(Collectors.toList());

        // Adjust the structure of the nav
        handleNavs(navs);
        return navs;
    }

    // Adjust the Structure of the nav
    @Override
    public void handleNavs(List<Nav> navs) {
        // Traverse the nav, query all children according to the parent id, and put them in the children attribute
        navs.forEach(nav -> {
            List<Nav> children = getAllByParentId(nav.getId());
            nav.setChildren(children);
        });
        // lambda expression
        navs.removeIf(nav -> nav.getParentId() != 0);
    }

    // Get all parent id
    @Override
    public List<Nav> getAllByParentId(int parentId) {
        return navMapper.findAllByParentId(parentId);
    }

}
