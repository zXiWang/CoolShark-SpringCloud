package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.pojo.dto.CategoryAddNewDTO;
import com.xiwang.csmall.product.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;
    @Test
    void addNew() {
        CategoryAddNewDTO data = new CategoryAddNewDTO();
        data.setName("category");
        data.setKeywords("牛");
        categoryService.addNew(data);
    }

    @Test
    void delete() {
        categoryService.delete(2L);
    }
}