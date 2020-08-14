package com.jie.controller;

import com.jie.model.Comments;
import com.jie.model.User;
import com.jie.result.Result;
import com.jie.result.ResultFactory;
import com.jie.service.CommentsService;
import com.jie.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jie Pu
 */
@RestController
@RequestMapping("/comment")
public class CommentsController {

    @Autowired
    CommentsService commentsService;

    @Autowired
    private UserService userService;

    // Get request, list all comments and usernames by blog
    @RequestMapping(value = "/{aid}/blog", method = RequestMethod.GET)
    public List<Map> getCommentsByBlog(@PathVariable("aid") int aid) {
        if(0 != aid){
            List<Comments> comments = commentsService.listByArticle(aid);
            List<Map> result = new ArrayList<>();
            comments.forEach(comment -> {
                Map map = new HashMap();
                Integer uid = comment.getUid();
                map.put("id", comment.getId());
                map.put("content", comment.getContent());
                map.put("uid", uid);
                User user = userService.getById(uid);
                map.put("username", null != user ? user.getUsername() : "user");
                result.add(map);
            });
            return result;
        } else {
            return null;
        }
    }

    //Post request, add comment
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result create(@RequestBody Comments comments){
        Integer uid = (Integer) SecurityUtils.getSubject().getSession().getAttribute("uid");
        comments.setUid(uid);
        commentsService.add(comments);
        return ResultFactory.SuccessResult("Comment successful");
    }
}

