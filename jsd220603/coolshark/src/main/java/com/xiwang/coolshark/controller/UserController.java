package com.xiwang.coolshark.controller;

import com.xiwang.coolshark.entity.User;
import com.xiwang.coolshark.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;


    @RequestMapping("/login")
    public int login(@RequestBody User user, HttpServletResponse response, HttpSession session) {
        User u;
        if (user.getUsername().trim().isEmpty()) {
            return 1;
        }
        if ((u = userMapper.selectByUsername(user.getUsername())) == null) {
            return 2;
        } else if (!u.getPassword().equals(user.getPassword())) {
            return 3;
        } else if (user.getRem()) {
            Cookie c1 = new Cookie("username", user.getUsername());
            Cookie c2 = new Cookie("password", user.getPassword());
            response.addCookie(c1);
            response.addCookie(c2);

        }
        session.setAttribute("user", u);
        return 4;

    }


    @RequestMapping("/currentUser")
    public User currentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }


    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("user");
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
