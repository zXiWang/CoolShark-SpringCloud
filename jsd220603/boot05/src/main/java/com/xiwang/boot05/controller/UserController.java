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


    @RequestMapping("/login")
    public int login(@RequestBody User user) {
        User u;
        if (user.getUsername().trim().isEmpty()) {
            return 1;
        }
        if ((u = userMapper.selectByUsername(user.getUsername())) == null) {
            return 2;
        } else if (!u.getPassword().equals(user.getPassword())) {
            return 3;
        }
        return 4;
    }

    @RequestMapping("/reg")
    public int reg(@RequestBody User user) {
        User u;
        if (user.getUsername().trim().isEmpty()) {
            return 1;
        }
        if ((u = userMapper.selectByUsername(user.getUsername())) != null) {

            return 2;
        }
        userMapper.insert(user);
        return 3;
    }

    @RequestMapping("/check")
    public int check(String username) {
        User u;
        if (username.trim().isEmpty()) {
            return 1;
        } else if ((u = userMapper.selectByUsername(username)) != null) {
            return 2;
        }
        return 3;
    }
}
