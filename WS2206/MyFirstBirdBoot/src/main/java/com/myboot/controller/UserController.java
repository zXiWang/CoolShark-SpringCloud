package com.myboot.controller;

import com.webserver.annotatiob.Controller;
import com.webserver.annotatiob.RequestMapping;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

@Controller
public class UserController {
    @RequestMapping("/regUser")
    public void reg(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始处理注册......");
    }
}
