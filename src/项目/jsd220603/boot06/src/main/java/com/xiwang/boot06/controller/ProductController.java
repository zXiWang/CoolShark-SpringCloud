package com.xiwang.boot06.controller;


import com.xiwang.boot06.entity.Product;
import com.xiwang.boot06.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductMapper productMapper;

    @RequestMapping("/selectById")
    public Product selectById(int id) {

        return productMapper.selectById(id);
    }

    @RequestMapping("/insert")
    public int reg(@RequestBody Product product) {
        Product u;
        if (product.getTitle().trim().isEmpty()) {
            return 1;
        }
        if ((u = productMapper.selectByTitle(product.getTitle())) != null) {

            return 2;
        }
        productMapper.insert(product);
        return 3;
    }

    @RequestMapping("/update")
    public int update(@RequestBody Product product) {
        Product u;

        if (product.getTitle().trim().isEmpty()) {
            return 1;
        }
        productMapper.update(product);
        return 3;
    }

    @RequestMapping("/check")
    public int check(String title) {
        Product u;
        if (title.trim().isEmpty()) {
            return 1;
        } else if ((u = productMapper.selectByTitle(title)) != null) {
            return 2;
        }
        return 3;
    }

    @RequestMapping("/select")
    public List<Product> select() {
        return productMapper.select();
    }

    @RequestMapping("/del")
    public int del(Integer id) {
        productMapper.deleteById(id);
        if (productMapper.selectById(id) != null) {
            return 1;
        }
        return 2;
    }
}
