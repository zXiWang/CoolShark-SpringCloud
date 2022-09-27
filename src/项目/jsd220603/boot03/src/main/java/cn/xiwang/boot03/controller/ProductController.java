package cn.xiwang.boot03.controller;

import cn.xiwang.boot03.entity.Product;
import cn.xiwang.boot03.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @RequestMapping("/insert")
    public String insert(Product product) {
        productMapper.insert(product);
        return "添加完成!<a href='/'>返回首页</a>";
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        productMapper.deleteById(id);
        return "删除完成!<a href='/'>返回首页</a>";
    }

    @RequestMapping("/select")
    public String select() {
        List<Product> products = productMapper.select();
        String html = "<table border='1'>";
        html += "<caption>商品列表</caption>";
        html += "<tr><th>id</th><th>商品标题</th><th>价格</th><th>库存</th><th colspan='2'>操作</th></tr>";
        for (Product p : products) {
            html += "<tr>";
            html += "<td>" + p.getId() + "</td>";
            html += "<td>" + p.getTitle() + "</td>";
            html += "<td>" + p.getPrice() + "</td>";
            html += "<td>" + p.getNum() + "</td>";
            html += "<td><a href='/delete?id=" + p.getId() + "'>删除</a></td>";
            html += "<td><a href='/update.html?id=" + p.getId() + "'>修改</a></td>";
            html += "</tr>";
        }
        html += "</table>";
        return html;
    }

    @RequestMapping("/update")
    public String update(Product product) {
        productMapper.update(product);
        return "修改完成! <a href='/select'>返回列表页面</a>";
    }
}
