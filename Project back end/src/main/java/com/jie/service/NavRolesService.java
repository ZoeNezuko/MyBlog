package com.jie.service;

import com.jie.model.NavRoles;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface NavRolesService {

    // Get all role ids and return different role ids according to the role
    List<NavRoles> findAllByRid(List<Integer> rids);

}
