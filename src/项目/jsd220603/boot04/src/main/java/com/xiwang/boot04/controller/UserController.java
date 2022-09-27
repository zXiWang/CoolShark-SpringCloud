package com.xiwang.boot04.controller;


import com.xiwang.boot04.entity.User;
import com.xiwang.boot04.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/reg")
    public String insert(User user) {
        User u;
        if ((u = userMapper.selectByUsername(user.getUsername())) != null) {

            return "用户名已存在!";
        }
        userMapper.insert(user);
        return "添加完成!<a href='/'>返回首页</a>";
    }

    @RequestMapping("/login")
    public String login(User user) {
        User u;
        if ((u = userMapper.selectByUsername(user.getUsername())) != null) {
            if (u.getPassword().equals(user.getPassword())) {
                return "登录成功!<a href='/'>返回首页</a>";
            } else {
                return "用户名或密码错误!";
            }
        }

        return "用户名不存在!";
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        userMapper.deleteById(id);
        return "删除完成!<a href='/'>返回首页</a>";
    }

    @RequestMapping("/select")
    public String select() {
        List<User> users = userMapper.select();
        String html = "<table border='1'>";
        html += "<caption>商品列表</caption>";
        html += "<tr><th>id</th><th>商品标题</th><th>价格</th><th>库存</th><th colspan='2'>操作</th></tr>";
        for (User p : users) {
            html += "<tr>";
            html += "<td>" + p.getId() + "</td>";
            html += "<td>" + p.getUsername() + "</td>";
            html += "<td>" + p.getPassword() + "</td>";
            html += "<td>" + p.getNickname() + "</td>";
            html += "<td><a href='/delete?id=" + p.getId() + "'>删除</a></td>";
            html += "<td><a href='/update.html?id=" + p.getId() + "'>修改</a></td>";
            html += "</tr>";
        }
        html += "</table>";
        return html;
    }

    @RequestMapping("/update")
    public String update(User user) {
        userMapper.update(user);
        return "修改完成! <a href='/select'>返回列表页面</a>";
    }
}
