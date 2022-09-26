package com.xiwang.csmall.product.mapper;

import com.xiwang.csmall.product.pojo.entity.Attribute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AttributeMapperTest {


    @Autowired
    private AttributeMapper mapper;

    @Test
    void queryById() {
        mapper.queryById(2L);
    }

    @Test
    void count() {
    }

    @Test
    void insert() {
        Attribute object = new Attribute();
        object.setName("value");
        object.setType(1);
        object.setDescription("牛逼");
        mapper.insert(object);
        System.out.println(object.getId());
    }

    @Test
    void insertBatch() {
        List<Attribute> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Attribute object = new Attribute();
            object.setName("" + i);
            object.setType(i);
            object.setDescription("" + i);
            list.add(object);


        }

    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteByIdS() {
    }

    @Test
    void queryAllByLimit() {
        Attribute object = new Attribute();
        Pageable pageable = new Pageable() {
            @Override
            public int getNumberOfPages() {
                return 1;
            }

            @Override
            public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
                return null;
            }

            @Override
            public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
                return null;
            }
        };
        Object o = new Object();

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("offset", 1);
        map.put("pageSize", 2);
        mapper.queryAllByLimit(object, pageable);
    }
}