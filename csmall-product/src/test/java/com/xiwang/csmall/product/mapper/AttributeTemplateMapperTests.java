package com.xiwang.csmall.product.mapper;


import com.xiwang.csmall.product.pojo.entity.AttributeTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AttributeTemplateMapperTests {

    @Autowired
    private AttributeTemplateMapper mapper;
    private AttributeTemplate attributeTemplate = new AttributeTemplate();

    @Test
    public void insertTest() {
        attributeTemplate.setName("真是醉了");
        attributeTemplate.setPinyin("zhenshizuile");
        mapper.insert(attributeTemplate);
        selectAll();
    }

    @Test
    public void testInsertBatch() {
        List<AttributeTemplate> attributeTemplateList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            AttributeTemplate attributeTemplate = new AttributeTemplate();
            attributeTemplate.setName(""+i);
            attributeTemplate.setPinyin(""+i);
            attributeTemplate.setSort(99);
            attributeTemplateList.add(attributeTemplate);
        }
        int count=mapper.insertBatch(attributeTemplateList);
        System.out.println("受影响的行数"+count);
    }

    @Test
    public void selectAll() {
        List<AttributeTemplate> templates = mapper.selectAll();
        templates.forEach(System.out::println);
    }

    @Test
    public void delete() {
        mapper.deleteById(2);
    }
}
