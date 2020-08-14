package com.jie.mapper;

import com.jie.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jie Pu
 */
public interface CategoryMapper extends JpaRepository<Category,Integer> {

}
