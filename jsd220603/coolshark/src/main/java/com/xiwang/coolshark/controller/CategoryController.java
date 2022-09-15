package com.xiwang.coolshark.controller;

import com.xiwang.coolshark.entity.Category;
import com.xiwang.coolshark.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryMapper categoryMapper;

    @RequestMapping("/select")
    public List<Category> select() {
        return categoryMapper.select();
    }

    @RequestMapping("/delete")
    public void delete(int id) {
        categoryMapper.delete(id);
    }

    @RequestMapping("/insert")
    public List<Category> insert(String name){
        categoryMapper.insert(name);
        return categoryMapper.select();
    }

    @RequestMapping("/update")
    public void update(@RequestBody Category category){
        categoryMapper.update(category);
    }
}

