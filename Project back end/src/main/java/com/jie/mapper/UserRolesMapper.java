package com.jie.mapper;

import com.jie.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface UserRolesMapper extends JpaRepository<UserRoles, Integer> {

    // Query all user roles id
    List<UserRoles> findAllByUid(int uid);

    // Query all user roles id and delete
    void deleteAllByUid(int uid);

}
