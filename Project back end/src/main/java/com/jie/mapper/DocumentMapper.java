package com.jie.mapper;

import com.jie.model.Category;
import com.jie.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Jie Pu
 */

public interface DocumentMapper extends JpaRepository<Document, Integer> {

    // Query all document based on the category
    List<Document> findByCategory(Category category);

    // uid as a condition to query data
    @Query(value = "select * from document where uid=:uid", nativeQuery = true)
    Page<Document> findByCurrentUidNotAdmin(@Param("uid") Integer uid, Pageable pageable);

}
