package com.webserver.controller;

import com.webserver.annotatiob.Controller;
import com.webserver.annotatiob.RequestMapping;
import com.webserver.entity.User;
import com.webserver.http.HttpServletRequest;
import com.webserver.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理和用户相关的业务类
 */
@Controller
public class UserController {
    private static File userDir;

    static {
        userDir = new File("./users");
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
    }


    @RequestMapping("/loginUser")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        //1
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("/login_info_error.html");
            return;
        }

        //2
        //根据登录用户的用户名去users目录下寻找该用户信息
        File file = new File(userDir, username + ".obj");
        if (file.exists()) {//文件存在则说明该用户存在(用户名输入正确)
            //反序列化文件中该用户曾经的注册信息
            try (
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ) {
                User user = (User) ois.readObject();
                //比较登录密码和注册时输入的密码是否一致
                // a = "abc123"    b = "AbC123"
                //a.equals(b) ==> false
                //a.equalsIgnoreCase(b) ==> true
                if (user.getPassword().equals(password)) {
                    //登录成功
                    response.sendRedirect("/login_success.html");
                    return;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        //登录失败
        response.sendRedirect("/login_fail.html");
    }

    @RequestMapping("/userList")
    public void userList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始处理动态页面.......");
        List<User> userList = new ArrayList<User>();
        File[] subs = userDir.listFiles(f -> f.getName().endsWith(".obj"));
        for (File file : subs) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                User user = (User) ois.readObject();
                userList.add(user);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try{
        System.out.println(userList);
        PrintWriter pw = new PrintWriter("./userList.html");
        pw.println("<!DOCTYPE html>");
        pw.println("<html lang=\"en\">");
        pw.println("<head>");
        pw.println("<meta charset=\"UTF-8\">");
        pw.println("<title>用户列表</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<center>");
        pw.println("<h1>用户列表</h1>");
        pw.println("<table border=\"1\">");
        pw.println("<tr>");
        pw.println("<td>用户名</td>");
        pw.println("<td>密码</td>");
        pw.println("<td>昵称</td>");
        pw.println("<td>年龄</td>");
        pw.println("<td>操作</td>");
        pw.println("</tr>");
        for (User user : userList) {
            pw.println("<tr>");
            pw.println("<td>" + user.getUsername() + "</td>");
            pw.println("<td>" + user.getPassword() + "</td>");
            pw.println("<td>" + user.getNickname() + "</td>");
            pw.println("<td>" + user.getAge() + "</td>");
            pw.println("<td><a href='/deleteUser?username=" + user.getUsername() + "'>删除</a></td>");
            pw.println("</tr>");
        }
        pw.println("</table>");
        pw.println("<p>" + userList + "</p>");
        pw.println("</center>");
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        response.setContentFile(new File("./userList.html"));
    }

    @RequestMapping("/regUser")
    public void reg(HttpServletRequest request, HttpServletResponse response) {
        //1获取表单信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println(username + "," + password + "," + nickname + "," + ageStr);

        if (username == null || username.isEmpty() || password == null || password.isEmpty() ||
                nickname == null || nickname.isEmpty() || ageStr == null || ageStr.isEmpty() ||
                !ageStr.matches("[0-9]+")) {
            response.sendRedirect("/reg_info_error.html");
            return;
        }

        int age = Integer.parseInt(ageStr);
        User user = new User(username, password, nickname, age);

        File file = new File(userDir, username + ".obj");
        if (file.exists()) {//文件已经存在说明是重复用户
            response.sendRedirect("/have_user.html");
            return;
        }

        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/reg_success.html");

    }
}
