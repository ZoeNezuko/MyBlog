package com.jie.service;

import com.jie.model.Comments;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface CommentsService {

    // Get all comments of the article according to its id
    List<Comments> listByArticle(int aid);

    // Create comments
    void add(Comments comments);

}
