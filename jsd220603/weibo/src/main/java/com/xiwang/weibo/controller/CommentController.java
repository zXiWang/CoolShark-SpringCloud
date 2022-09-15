package com.xiwang.weibo.controller;

import com.xiwang.weibo.entity.Comment;
import com.xiwang.weibo.entity.User;
import com.xiwang.weibo.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentMapper commentMapper;
    @RequestMapping("/comment/insert")
    public int insert(@RequestBody Comment comment, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return 2;
        }
        comment.setNickname(user.getNickname());
        System.out.println(comment);
        commentMapper.insert(comment);
        return 1;
    }

    @RequestMapping("/comment/selectByWeiboId")
    public List<Comment> selectByWeiboId(Integer id) {
        return commentMapper.selectByWeiboId(id);
    }

    @RequestMapping("/comment/del")
    public Integer delete(Integer id) {

        commentMapper.deleteById(id);
        if (commentMapper.selectById(id) != null) {
            return 1;
        }
        return 2;
    }
}
