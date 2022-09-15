package com.xiwang.weibo.controller;

import com.xiwang.weibo.entity.User;
import com.xiwang.weibo.entity.Weibo;
import com.xiwang.weibo.mapper.WeiboMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class WeiboController {

    @Autowired
    WeiboMapper weiboMapper;

    @RequestMapping("/weibo/insert")
    public int insert(@RequestBody Weibo weibo, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return 2;
        }
        if (weibo.getContent().isEmpty() && weibo.getUrls().isEmpty()) {
            return 3;
        }
        weibo.setCreated(new Date());

        weibo.setNickname(user.getNickname());
        weibo.setUserId(user.getId());
        System.out.println(weibo);
        weiboMapper.insert(weibo);
        return 1;
    }

    @RequestMapping("/weibo/select")
    public List<Weibo> select() {
        return weiboMapper.select();
    }

    @RequestMapping("/weibo/selectById")
    public Weibo selectById(Integer id) {
        return weiboMapper.selectById(id);
    }

    @RequestMapping("/weibo/del")
    public Integer delete(Integer id) {

        weiboMapper.deleteById(id);
        if (weiboMapper.selectById(id) != null) {
            return 1;
        }
        return 2;
    }



}
