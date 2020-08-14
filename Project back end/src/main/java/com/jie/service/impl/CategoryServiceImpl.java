package com.jie.service.impl;

import com.jie.mapper.CategoryMapper;
import com.jie.model.Category;
import com.jie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jie Pu
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    // Get the category id, if id does not exist return default value
    @Override
    public Category getId(int id) {
        // If category id is found, return id and not return null
        Category category = categoryMapper.findById(id).orElse(null);
        return category;
    }

}
