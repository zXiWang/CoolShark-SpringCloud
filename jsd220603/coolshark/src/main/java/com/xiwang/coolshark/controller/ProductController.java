package com.xiwang.coolshark.controller;

import com.xiwang.coolshark.entity.Product;
import com.xiwang.coolshark.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("/insert")
    public int insert(@RequestBody Product product) {
        product.setCreated(new Date());
        productMapper.insert(product);
        return 1;
    }

    @RequestMapping("/select")
    public List<Product> select(){
        return productMapper.select();
    }

    @RequestMapping("/delete")
    public void delete(Integer id){
        productMapper.delete(id);
    }
}
