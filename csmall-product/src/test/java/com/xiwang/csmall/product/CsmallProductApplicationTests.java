package com.xiwang.csmall.product;

import com.xiwang.csmall.product.entity.AmsAdmin;
import com.xiwang.csmall.product.mapper.AmsAdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CsmallProductApplicationTests {

    @Autowired
    private AmsAdminMapper amsAdminMapper;

    @Test
    void contextLoads() {
    }


    @Test
    public void amsAdminSelect() {
        System.out.println("测试开始......");
        List<AmsAdmin> aaList = amsAdminMapper.selectList(null);
        aaList.forEach(System.out::println);
    }
    @Test
    public void amsAdminInsert() {
        System.out.println("测试开始......");
        AmsAdmin amsAdmin=new AmsAdmin();
        amsAdmin.setUsername("张三");
        amsAdminMapper.insert(amsAdmin);
        List<AmsAdmin> aaList = amsAdminMapper.selectList(null);
        aaList.forEach(System.out::println);
    }

}
