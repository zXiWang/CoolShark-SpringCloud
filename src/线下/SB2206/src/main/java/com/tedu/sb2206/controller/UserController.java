package com.tedu.sb2206.controller;

import com.tedu.sb2206.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

            response.sendRedirect("/reg_success.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/userList")
    public void userList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始......................");
        List<User> userList = new ArrayList<>();
        File[] files = userDir.listFiles(f -> f.getName().endsWith(".obj"));
        for (File file : files) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                User user = (User) ois.readObject();
                userList.add(user);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter pw = response.getWriter();
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

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println(userList);
    }

    @RequestMapping("/deleteUser")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始处理......");
        String username = request.getParameter("username");
        System.out.println("要删除的用户" + username);
        File file = new File(userDir, username + ".obj");
        file.delete();
        try {
            response.sendRedirect("/userList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
