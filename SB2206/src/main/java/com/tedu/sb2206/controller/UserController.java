package com.tedu.sb2206.controller;

import com.tedu.sb2206.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/loginUser")
    @ResponseBody
    public void loginUser(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            File file = new File(userDir, username + ".obj");
            if (!file.exists()) {
                System.out.println("用户不存在");
                response.sendRedirect("/login.html");
                return;
            }
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            ObjectInputStream ois = new ObjectInputStream(in);
            if ((user = (User) ois.readObject()) != null) {
                if (username.equals("") || password.equals("")) {
                    System.out.println("用户名或密码为空");
                    response.sendRedirect("/login.html");
                    return;
                } else if (!username.equals(user.getUsername()) || !password.equals(user.getPassword())) {
                    System.out.println("用户名或密码错误!");
                    response.sendRedirect("/login.html");
                    return;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("登陆成功!");
        try {
            response.sendRedirect("/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    public Result login(String name, String password) {
//
//        User user = null;
//        try {
//            InputStream in = new BufferedInputStream(new FileInputStream(new File(userDir, name + ".obj")));
//            ObjectInputStream ois = new ObjectInputStream(in);
//            if ((user = (User) ois.readObject()) != null) {
//                if (name.equals("") || password.equals("")) {
//                    return new Result("500", "用户名和密码不能为空!", null);
//                } else if (!name.equals(user.getUsername()) || !password.equals(user.getPassword())) {
//                    return new Result("502", "用户名或密码错误!", null);
//                }
//            } else {
//                return new Result("501", "用户不存在!", null);
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return new Result("200", "登陆成功!", null);
//    }

    @RequestMapping("/regUser")
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
            try {
                response.sendRedirect("/reg_info_error.html");
            } catch (IOException e) {
                e.printStackTrace();

            }
            return;
        }
        int age = Integer.parseInt(ageStr);
        User user = new User(username, password, nickname, age);
        File file = new File(userDir, username + ".obj");
        if (file.exists()) {
            try {
                response.sendRedirect("/have_user.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {

            /*
                让浏览器重定向到指定的路径查看处理结果页面
                这里的路径"/reg_success.html"是发送给浏览器让其理解的.
                所以浏览器还是当这里的"/"为抽象路径中第一个"/"的位置
                相当于浏览器会根据该路径请求:
                http://localhost:8080/reg_success.html
             */
            response.sendRedirect("/reg_success.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
