package com.jie.mapper;

import com.jie.model.NavRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface NavRolesMapper extends JpaRepository<NavRoles, Integer> {

    // Query role id returns different id according to role
    List<NavRoles> findAllByRidIn(List<Integer> rids);

}
