package com.webserver.controller;

import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.*;

public class UserController {

    private static File userDir;

    static {
        userDir = new File("testfile/users");
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }


    public void login(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            File file = new File(userDir, username + ".obj");
            if (!file.exists()) {
                System.out.println("用户不存在");
                response.sendRedirect("/login_fail.html");
                return;
            }
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            ObjectInputStream ois = new ObjectInputStream(in);
            if ((user = (User) ois.readObject()) != null) {
                if (username.equals("") || password.equals("")) {
                    System.out.println("用户名或密码为空");
                    response.sendRedirect("/login_info_error.html");
                    return;
                } else if (!username.equals(user.getUsername()) || !password.equals(user.getPassword())) {
                    System.out.println("用户名或密码错误!");
                    response.sendRedirect("/login_fail.html");
                    return;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("登陆成功!");
        response.sendRedirect("/index.html");
    }

    public void reg(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("处理注册中......");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println("username=" + username + " password=" + password +
                " nickname=" + nickname + " age=" + ageStr);

        if (username == null || username.isEmpty() || password == null || password.isEmpty() ||
                nickname == null || nickname.isEmpty() ||
                ageStr == null || ageStr.isEmpty() || !ageStr.matches("[0-9]+")) {
            response.sendRedirect("/reg_info_error.html");
            return;
        }
        int age = Integer.parseInt(ageStr);
        User user = new User(username, password, nickname, age);
        File file = new File(userDir, username + ".obj");
        if (file.exists()) {
            response.sendRedirect("/have_user.html");
            return;
        }
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/reg_success.html");

    }
}
