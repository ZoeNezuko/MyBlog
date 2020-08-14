package com.jie.mapper;

import com.jie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Jie Pu
 */

public interface UserMapper extends JpaRepository<User, Integer> {

    // Query username
    User findByUsername(String username);

    // Query all users information
    @Query(value = "select id, username, password, email, enable from user", nativeQuery = true)
    List<User> list();
}
