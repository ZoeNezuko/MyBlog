package com.jie.service;

import com.jie.model.Nav;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface NavService {

    // Get current user and render the corresponding accessible nav
    List<Nav> getNavsByCurrentUser();

    // Adjust the Structure of the nav
    void handleNavs(List<Nav> navs);

    // Get all parent id
    List<Nav> getAllByParentId(int parentId);

}
