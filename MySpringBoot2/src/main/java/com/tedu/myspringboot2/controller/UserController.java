package com.tedu.myspringboot2.controller;

import com.tedu.myspringboot2.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @RequestMapping("/regUser")
    public void reg(User user, HttpServletResponse response) {
        System.out.println(user);
    }
}
