package com.jie.service.impl;

import com.jie.mapper.BlogArticleMapper;
import com.jie.model.BlogArticle;
import com.jie.service.BlogArticleService;
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
public class BlogArticleServiceImpl implements BlogArticleService {

    @Autowired
    BlogArticleMapper blogArticleMapper;

    //     // Get all articles
    @Override
    public List<BlogArticle> findAll() {
        return blogArticleMapper.findAll();
    }

    // Get all articles and operate data pagination
    @Override
    public Page getAll(int page, int size) {
        // Sort descending by id
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return blogArticleMapper.findAll(PageRequest.of(page, size, sort));
    }

    // Only display information uploaded by users themselves
    @Override
    public Page<BlogArticle> getAllByUser(int page, int size) {
        // Sort ascending by id
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        // Set uid, set the current user get data
        Integer uid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("uid");
        if (uid == 1) {
            Page<BlogArticle> pageResult = blogArticleMapper.findAll(pageRequest);
            return pageResult;
        } else {
            Page<BlogArticle> result = blogArticleMapper.findByCurrentUidNotAdmin(uid, pageRequest);
            return result;
        }
    }

    // Get articles' id
    @Override
    public BlogArticle findById(int id) {
        return blogArticleMapper.findById(id).get();
    }

    // Create or update articles
    @Override
    public void addOrUpdate(BlogArticle blogArticle) {
        Integer uid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("uid");
        blogArticle.setUid(uid);
        blogArticleMapper.save(blogArticle);
    }

    // Delete article
    @Override
    public void delete(int id) {
        blogArticleMapper.deleteById(id);
    }

    // Fuzzy query articles based on keywords
    @Override
    public List<BlogArticle> Search(String keywords) {
        return blogArticleMapper.findByTitleLike('%' + keywords + '%');
    }

    // Get the article id, if id does not exist return default value
    @Override
    public BlogArticle getId(int id) {
        BlogArticle blogArticle = blogArticleMapper.findById(id).orElse(null);
        return blogArticle;
    }

}
