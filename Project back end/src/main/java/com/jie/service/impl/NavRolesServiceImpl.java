package com.jie.service.impl;

import com.jie.mapper.NavRolesMapper;
import com.jie.model.NavRoles;
import com.jie.service.NavRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jie Pu
 */
@Service
public class NavRolesServiceImpl implements NavRolesService {

    @Autowired
    NavRolesMapper navRolesMapper;

    // Get all role ids and return different role ids according to the role
    @Override
    public List<NavRoles> findAllByRid(List<Integer> rids) {
        return navRolesMapper.findAllByRidIn(rids);
    }

}
