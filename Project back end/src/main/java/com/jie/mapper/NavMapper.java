package com.jie.mapper;

import com.jie.model.Nav;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface NavMapper extends JpaRepository<Nav, Integer> {

    // Query all parent id
    List<Nav> findAllByParentId(int parentId);
}
