package com.jie.mapper;

import com.jie.model.BlogArticle;
import com.jie.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface CommentsMapper extends JpaRepository<Comments, Integer> {

    //Query all comments based on the article
    List<Comments> findByBlogArticle(BlogArticle blogArticle);
}
