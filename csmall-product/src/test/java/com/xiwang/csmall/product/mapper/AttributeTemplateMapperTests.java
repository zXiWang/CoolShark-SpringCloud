package com.xiwang.csmall.product.mapper;


import com.xiwang.csmall.product.pojo.entity.AttributeTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AttributeTemplateMapperTests {

    @Autowired
    private AttributeTemplateMapper mapper;
    private AttributeTemplate attributeTemplate=new AttributeTemplate();

    @Test
    public void insertTest() {
        attributeTemplate.setName("真是醉了");
        attributeTemplate.setPinyin("zhenshizuile");
        mapper.insert(attributeTemplate);
        selectAll();
    }

    @Test
    public void selectAll(){
        List<AttributeTemplate> templates=mapper.selectAll();
        templates.forEach(System.out::println);
    }

    @Test
    public void delete(){
        mapper.deleteById(2);
    }
}
