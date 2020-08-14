package com.jie.service;

import com.jie.model.BlogArticle;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface BlogArticleService {

    // Get all articles
    List<BlogArticle> findAll();

    // Get all articles and operate data pagination
    Page getAll(int page, int size);

    // Only display information uploaded by users themselves
    Page getAllByUser(int page, int size);

    // Get articles' id
    BlogArticle findById(int id);

    // Create or update articles
    void addOrUpdate(BlogArticle blogArticle);

    // Delete article
    void delete(int id);

    // Fuzzy query articles based on keywords
    List<BlogArticle> Search(String keywords);

    // Get the article id, if id does not exist return default value
    BlogArticle getId(int id);

}
