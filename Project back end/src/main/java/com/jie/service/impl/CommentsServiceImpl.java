package com.jie.service.impl;

import com.jie.mapper.CommentsMapper;
import com.jie.model.BlogArticle;
import com.jie.model.Comments;
import com.jie.service.BlogArticleService;
import com.jie.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jie Pu
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommentsMapper commentsMapper;

    @Autowired
    BlogArticleService blogArticleService;

    // Get all comments of the article according to its id
    @Override
    public List<Comments> listByArticle(int aid) {
        BlogArticle blogArticle = blogArticleService.getId(aid);
        return commentsMapper.findByBlogArticle(blogArticle);
    }

    // Create comments
    @Override
    public void add(Comments comments) {
        commentsMapper.save(comments);
    }
}
