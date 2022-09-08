package cn.xiwang.boot03.controller;

import cn.xiwang.boot03.entity.Product;
import cn.xiwang.boot03.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @RequestMapping("/insert")
    public String insert(Product product) {
        System.out.println("product" + product);
        return "添加完成!<a href='/'>返回首页</a>";
    }
}
