package com.xiwang.csmall.product;


import com.xiwang.csmall.product.mapper.AlbumMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsmallProductApplicationTests {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AlbumMapper albumMapper;

    @Test
    void contextLoads() {
    }



//    @Test
//    public void AdminSelect() {
//        System.out.println("测试开始......");
//        List<Admin> aaList = adminMapper.selectList(null);
//        aaList.forEach(System.out::println);
//    }
//
//    @Test
//    public void AdminInsert() {
//        System.out.println("测试开始......");
//        Admin Admin = new Admin();
//        Admin.setUsername("张三");
//        adminMapper.insert(Admin);
//        List<Admin> aaList = adminMapper.selectList(null);
//        aaList.forEach(System.out::println);
//    }

}
