package com.xiwang.csmall.product.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiwang.csmall.product.pojo.entity.Brand;
import com.xiwang.csmall.product.pojo.vo.BrandNormalVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BrandMapperTests {

    @Autowired
    private BrandMapper mapper;


    @Test
    public void SelectMaps() {
        QueryWrapper<Brand> objectQueryWrapper = new QueryWrapper<Brand>();
        objectQueryWrapper.select("id", "name", "description", "sort");
        List<Map<String, Object>> objects = mapper.selectMaps(objectQueryWrapper);
        objects.forEach(System.out::println);
    }

    @Test
    public void getNormalById() {
        BrandNormalVO normal = mapper.getNormalById(1L);
        System.out.println(normal);
    }

    @Test
    public void count() {
        Long count = mapper.selectCount(null);
        System.out.println("数据的数量为:" + count);
    }

    @Test
    public void testBrandUpdate() {
        UpdateWrapper<Brand> objectUpdateWrapper = new UpdateWrapper<Brand>();

        objectUpdateWrapper
                .like("name", "牛")
                .set("description", "好牛啊")
                .setSql("sort ='5'");

        Brand object = new Brand();
        int count = mapper.update(object, objectUpdateWrapper);
        System.out.println(count);
        SelectMaps();
    }

    @Test
    public void insert() {
        Brand object = new Brand();
        object.setName("牛逼");
        object.setDescription("牛");
        object.setSort(1);
        mapper.insert(object);
        System.out.println(object.getId());
        SelectMaps();
    }

    @Test
    public void deleteByIds() {


        Long[] ids = new Long[]{17l, 18l};
//        int count = mapper.deleteBatchIds(Arrays.asList(ids));,
        int count = mapper.deleteByIds(ids);
        System.out.println("受影响的行数:" + count);
    }

    @Test
    void testInsert() {
        Brand object = new Brand();
        object.setName("牛牛");
        object.setDescription("牛炸了");
        object.setSort(99);
        mapper.insert(object);
    }

    @Test
    void insertBatch() {
        List<Brand> brandList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Brand brand = new Brand();
            brand.setName("" + i);
            brand.setDescription("" + i);
            brand.setSort(99);
            brandList.add(brand);
        }
        int count = mapper.insertBatch(brandList);
    }

    @Test
    void deleteById() {
        mapper.deleteById(3);
        SelectMaps();
    }


    @Test
    void testDeleteByIds() {
        Long[] ids = {1L, 2L};
        mapper.deleteByIds(ids);
    }

    @Test
    void updateById() {
        Brand object = new Brand();
        object.setId(4L);
        object.setName("牛逼");
        object.setDescription("牛逼");
        object.setSort(99);
        mapper.updateById(object);
    }

    @Test
    void testCount() {
        int count = mapper.count();
    }

    @Test
    void testGetNormalById() {
        mapper.getNormalById(1L);
    }

    @Test
    void list() {
        mapper.list();
    }
}
