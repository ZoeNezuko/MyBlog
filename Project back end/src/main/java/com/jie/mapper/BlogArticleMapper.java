package com.jie.mapper;

import com.jie.model.BlogArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface BlogArticleMapper extends JpaRepository<BlogArticle, Integer> {

    // Fuzzy query by title
    List<BlogArticle> findByTitleLike(String keyword);

    // uid as a condition to query data
    @Query(value = "select * from blog_article where uid=:uid", nativeQuery = true)
    Page<BlogArticle> findByCurrentUidNotAdmin(@Param("uid") Integer uid, Pageable pageable);
}
