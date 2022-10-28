package com.xiwang.search.repository;

import com.xiwang.search.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void addOne() {
        Item item = new Item();
        item.setId(1L)
                .setTitle("测试数据001的标题")
                .setCategory("Test")
                .setBrand("Test")
                .setPrice(99.99)
                .setImgPath("/1.jpg");
        itemRepository.save(item);
        System.out.println("ok");
    }

    @Test
    void getOne() {
        Optional<Item> optional = itemRepository.findById(1L);
        Item item = optional.get();
        System.out.println("item: " + item);
    }
}