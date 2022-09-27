package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlbumServiceImplTest {

    @Autowired
    private AlbumService service;
    @Test
    void getNormalById() {
        service.getNormalById(2L);
    }


    @Test
    void addNew() {
        AlbumAddNewDTO obj = new AlbumAddNewDTO();
        obj.setName("牛牛");
        obj.setDescription("真的牛");
        obj.setSort(99);
        try{
            service.addNew(obj);
            System.out.println("添加相册成功!");
        }catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void delete() {
        try{
            service.delete(1L);
            System.out.println("删除相册成功!");
        }catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}