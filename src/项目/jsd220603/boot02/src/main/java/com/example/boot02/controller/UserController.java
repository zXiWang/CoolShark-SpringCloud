package com.example.boot02.controller;

import com.example.boot02.entity.User;
import com.example.boot02.utils.DBUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class UserController {
    @RequestMapping("/reg")
    public String reg(User user) {
        try (Connection conn = DBUtils.getConn()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM user where username=?");
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "用户已存在!";
            }
            ps = conn.prepareStatement("insert into user values (null,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "注册成功!";
    }

    @RequestMapping("/login")
    public String login(User user) {
        try (Connection conn = DBUtils.getConn()) {
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM user where username=?");
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return "用户不存在!";
            }
            ps = conn.prepareStatement("select id from user where username=? and password=?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            int count = ps.executeUpdate();
            if (count <= 0) {
                return "用户名或密码错误!";
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "登录成功!";
    }

}
