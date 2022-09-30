package com.xiwang.csmall.passport.mapper;

import com.xiwang.csmall.passport.pojo.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class AdminMapperTest {

    @Autowired
    private AdminMapper mapper;

    @Test
    void insert() {
        Admin obj = new Admin();
        obj.setNickname("admin");
        obj.setUsername("admin");
        obj.setPassword("admin");
        obj.setPhone("123");
        obj.setEmail("admin@example.com");

        int count = mapper.insert(obj);
        log.debug("插入的数据: {}", obj, count);
    }

    @Test
    void countByUsername() {
        mapper.countByUsername("admin");
    }

    @Test
    void countByPhone() {
        mapper.countByPhone("123");
    }

    @Test
    void countByEmail() {
        mapper.countByEmail("admin@example.com");
    }
}