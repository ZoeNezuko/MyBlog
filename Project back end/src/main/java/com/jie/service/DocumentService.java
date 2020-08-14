package com.jie.service;

import com.jie.model.Document;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Jie Pu
 */
public interface DocumentService {

    // Get all documents
    List<Document> findAll();

    // Get all documents and operate data pagination
    Page getAll(int page, int size);

    // Only display information uploaded by users themselves
    Page getAllByUser(int page, int size);

    // Get documents' id
    Document findById(int id);

    // Create or update documents
    void addOrUpdate(Document document);

    // Delete documents according to id
    void delete(int id);

    // Search documents by category
    List<Document> listByCategory(int cid);

}
