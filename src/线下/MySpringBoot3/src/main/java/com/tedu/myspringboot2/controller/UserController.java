package com.tedu.myspringboot2.controller;

import com.tedu.myspringboot2.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class UserController {
    private static File userDir;

    static {
        userDir = new File("testfile/users");
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }

    @RequestMapping("/loginUser")
    public void login(User user, HttpServletResponse response) {
        if (user.getUsername().isEmpty() || user.getUsername() == null ||
                user.getPassword().isEmpty() || user.getPassword() == null) {
            try {
                response.sendRedirect("/login_info_error.html");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(userDir, user.getUsername() + ".obj");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file
                    )
            )) {
                User userFile = (User) ois.readObject();
                if (userFile.getPassword().equals(user.getPassword())) {
                    response.sendRedirect("/login_success.html");
                    return;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            response.sendRedirect("/login_fail.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/regUser")
    public void reg(User user, HttpServletResponse response) {
        System.out.println(user);
        String username = user.getUsername();
        String password = user.getPassword();
        String nickname = user.getNickname();
        int age = user.getAge();
        if (username == null || username.isEmpty() || password == null || password.isEmpty() ||
                nickname == null || nickname.isEmpty() || age == 0 || String.valueOf(age).isEmpty() ||
                !String.valueOf(age).matches("[0-9]+")) {
            try {
                response.sendRedirect("/reg_info_error.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        File file = new File(userDir, username + ".obj");
        if (file.exists()) {//文件已经存在说明是重复用户
            try {
                response.sendRedirect("/have_user.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(user);
            response.sendRedirect("/reg_success.html");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
