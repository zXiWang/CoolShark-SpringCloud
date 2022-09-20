package com.xiwang.coolshark.controller;

import com.xiwang.coolshark.entity.Product;
import com.xiwang.coolshark.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${dirPath}")
    private String dirPath;

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("/insert")
    public int insert(@RequestBody Product product) {
        product.setCreated(new Date());
        productMapper.insert(product);
        return 1;
    }

    @RequestMapping("/select")
    public List<Product> select() {
        return productMapper.select();
    }

    @RequestMapping("/selectForIndex")
    public List<Product> selectForIndex() {
        return productMapper.selectForIndex();
    }

    @RequestMapping("/selectByCategoryId")
    public List<Product> selectByCategoryId(Integer id) {
        return productMapper.selectByCategoryId(id);
    }

    @RequestMapping("/selectByWd")
    public List<Product> selectByWd(String wd) {
        return productMapper.selectByWd(wd);
    }

    @RequestMapping("/selectById")
    public Product selectById(Integer id, HttpSession session) {
        String info = (String) session.getAttribute("view" + id);
        if (info == null) {
            productMapper.updateViewCountById(id);
            session.setAttribute("view" + id, "isVisible");
        }

        return productMapper.selectById(id);
    }

    @RequestMapping("/selectByIdForUpdate")
    public Product selectByIdForUpdate(Integer id) {
        return productMapper.selectByIdForUpdate(id);
    }

    @RequestMapping("/update")
    public int update(@RequestBody Product product) {
        productMapper.update(product);
        return 1;
    }

    @RequestMapping("/selectTop")
    public List<Product> selectTop() {
        return productMapper.selectTop();
    }

    @RequestMapping("/delete")
    public void delete(Integer id, String url) {
        new File(dirPath + url).delete();
        productMapper.deleteById(id);
    }

    @RequestMapping("/remove")
    public void remove(String url) {
        new File(dirPath + url).delete();
    }

}
