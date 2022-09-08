package com.example.boot02.controller;

import com.example.boot02.entity.Product;
import com.example.boot02.utils.DBUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class ProductController {
    @RequestMapping("/insert")
    public String insert(Product product) {
        System.out.println("product: " + product);
        try (Connection conn = DBUtils.getConn()) {
            PreparedStatement ps = conn.prepareStatement("insert into product values (null,?,?,?)");
            ps.setString(1, product.getTitle());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getNum());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "添加完成!<a href='/'>返回首页</a>";
    }

    @RequestMapping("/select")
    public String select() {
        ArrayList<Product> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                Double price = rs.getDouble(3);
                int num = rs.getInt(4);
                list.add(new Product(id, title, price, num));
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        String html = "<table border='1'>";
        html += "<caption>商品列表</caption>";
        html += "<tr><th>id</th><th>商品标题</th><th>价格</th><th>库存</th><th>操作</th></tr>";
        for (Product p : list) {
            html += "<tr>";
            html += "<td>" + p.getId() + "</td>";
            html += "<td>" + p.getTitle() + "</td>";
            html += "<td>" + p.getPrice() + "</td>";
            html += "<td>" + p.getNum() + "</td>";
            html += "<td><a href='/delete?id=" + p.getId() + "'>删除</a></td>";
            html += "</tr>";
        }
        html += "</table>";
        return html;
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        System.out.println("id = " + id);
        try (Connection conn = DBUtils.getConn()) {
            String sql = "delete from product where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "删除完成!<a href='/select'>返回列表页面</a>";
    }

    @RequestMapping("/update")
    public String update(Product product) {
        System.out.println("product=" + product);
        try (Connection conn = DBUtils.getConn()) {


            PreparedStatement ps = conn.prepareStatement("select * from product where id=?");
            ps.setInt(1,product.getId());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return "修改失败!没有此商品<a href='/select'>返回列表页面</a>";
            }
            ps = conn.prepareStatement("update product set title=?,price=?,num=? where id=?");

            ps.setString(1, product.getTitle());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getNum());
            ps.setInt(4, product.getId());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "修改完成! <a href='/select'>返回列表页面</a>";
    }
}
