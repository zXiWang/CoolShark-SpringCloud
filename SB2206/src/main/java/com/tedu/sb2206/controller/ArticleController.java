package com.tedu.sb2206.controller;

import com.tedu.sb2206.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {

    private static File articleDir;

    static {
        articleDir = new File("testfile/articles");
        if (!articleDir.exists()) {
            articleDir.mkdirs();
        }
    }

    @RequestMapping("/writeArticle")
    public void createArticle(HttpServletRequest request, HttpServletResponse response) {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        File file = new File(articleDir, title + ".obj");

        if (author == null || author.isEmpty() || title == null || title.isEmpty() ||
                content == null || content.isEmpty()) {
            try {
                response.sendRedirect("/article_fail.html");
            } catch (IOException e) {
                e.printStackTrace();

            }
            return;
        }
        Article article = new Article(author, title, content);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(article);
            response.sendRedirect("/article_success.html");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("/articleList")
    public void showArticle(HttpServletRequest request, HttpServletResponse response) {

        List<Article> articleList = new ArrayList<>();
        File[] files = articleDir.listFiles(f -> f.getName().endsWith(".obj"));
        if (files.length <= 0) {
            try {
                response.sendRedirect("/article_null.html");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (File file : files) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            ) {

                Article article = (Article) ois.readObject();
                articleList.add(article);
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
            pw.println("<title>文章列表</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<center>");
            pw.println("<h1>文章列表</h1>");
            pw.println("<table border=\"1\">");
            pw.println("<tr>");
            pw.println("<td>作者</td>");
            pw.println("<td>标题</td>");
            pw.println("<td>内容</td>");
            pw.println("<td>操作</td>");
            pw.println("</tr>");
            for (Article article : articleList) {
                pw.println("<tr>");
                pw.println("<td>" + article.getAuthor() + "</td>");
                pw.println("<td>" + article.getTitle() + "</td>");
                pw.println("<td>" + article.getContent() + "</td>");
                pw.println("<td><a href='/deleteArticle?title=" + article.getTitle() + "'>删除</a></td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            pw.println("</body>");
            pw.println("</html>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteArticle")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("开始处理......");
        String title = request.getParameter("title");
        System.out.println("要删除的文章标题" + title);
        File file = new File(articleDir, title + ".obj");
        file.delete();
        try {
            response.sendRedirect("/articleList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
