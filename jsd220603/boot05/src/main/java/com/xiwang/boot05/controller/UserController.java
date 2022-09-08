package com.xiwang.boot05.controller;

import com.xiwang.boot05.entity.User;
import com.xiwang.boot05.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/reg")
    public String reg(@RequestBody User user) {
        User u;
        if ((u = userMapper.selectByUsername(user.getUsername())) != null) {

            return "用户名已存在!";
        }
        userMapper.insert(user);
        return "添加完成!<a href='/'>返回首页</a>";
    }
    @RequestMapping("/check")
    public int check(String username) {
        User u;
        if(username.trim().isEmpty()){
            return 1;
        }else if((u = userMapper.selectByUsername(username)) != null){
            return 2;
        }
        return 3;
    }
}
