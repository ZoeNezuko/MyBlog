package com.jie.service.impl;

import com.jie.mapper.DocumentMapper;
import com.jie.model.Category;
import com.jie.model.Document;
import com.jie.service.CategoryService;
import com.jie.service.DocumentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jie Pu
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentMapper documentMapper;

    @Autowired
    CategoryService categoryService;

    // Get all documents
    @Override
    public List<Document> findAll() {
        return documentMapper.findAll();
    }

    // Get all documents and operate data pagination
    @Override
    public Page getAll(int page, int size) {
        // Sort descending by id
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return documentMapper.findAll(PageRequest.of(page, size, sort));
    }

    // Only display information uploaded by users themselves
    @Override
    public Page<Document> getAllByUser(int page, int size) {
        // Sort ascending by id
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        // Set uid, set the current user get data
        Integer uid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("uid");
        if (uid == 1) {
            Page<Document> pageResult = documentMapper.findAll(pageRequest);
            return pageResult;
        } else {
            Page<Document> result = documentMapper.findByCurrentUidNotAdmin(uid, pageRequest);
            return result;
        }
    }

    // Get documents by id
    @Override
    public Document findById(int id) {
        return documentMapper.findById(id).get();
    }

    // Create or update documents
    @Override
    public void addOrUpdate(Document document) {
        Integer uid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("uid");
        document.setUid(uid);
        documentMapper.save(document);
    }

    // Delete documents according to id
    @Override
    public void delete(int id) {
        documentMapper.deleteById(id);
    }

    // Search documents by category
    @Override
    public List<Document> listByCategory(int cid) {
        Category category = categoryService.getId(cid);
        return documentMapper.findByCategory(category);
    }

}
