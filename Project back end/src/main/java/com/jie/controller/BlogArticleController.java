package com.jie.controller;

import com.jie.model.BlogArticle;
import com.jie.result.Result;
import com.jie.result.ResultFactory;
import com.jie.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jie Pu
 */
@RestController
@RequestMapping("/blog")
public class BlogArticleController {

    @Autowired
    BlogArticleService blogArticleService;

    // Get request, get all blog and pagination
    @RequestMapping(value = "/{size}/{page}", method = RequestMethod.GET)
    public Page list(@PathVariable("size") int size, @PathVariable("page") int page) {
        return blogArticleService.getAll(page - 1, size);
    }

    // Get request, only get information uploaded by users themselves
    @RequestMapping(value = "/user/{size}/{page}", method = RequestMethod.GET)
    public Page getByUser(@PathVariable("size") int size, @PathVariable("page") int page) {
        return blogArticleService.getAllByUser(page - 1, size);
    }

    // Get request, get the id of all blog
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BlogArticle findById(@PathVariable("id") int id) {
        return blogArticleService.findById(id);
    }

    // Post request, create or update blog
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result addOrUpdate(@RequestBody BlogArticle blogArticle) {
        blogArticleService.addOrUpdate(blogArticle);
        return ResultFactory.SuccessResult("Post success");
    }

    // Post request, delete blog according to id
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody BlogArticle blogArticle) {
        blogArticleService.delete(blogArticle.getId());
        return ResultFactory.SuccessResult("delete success");
    }

    // Post request, use keywords to search blog
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<BlogArticle> searchResult(@RequestParam("keywords") String keywords) {
        if ("".equals(keywords)) {
            return blogArticleService.findAll();
        } else {
            return blogArticleService.Search(keywords);
        }
    }
}

