package com.jie.service;

import com.jie.model.Category;


/**
 * @author Jie Pu
 */
public interface CategoryService {

    // Get the category id, if id does not exist return default value
    Category getId(int id);

}
