package com.xiwang.csmall.product.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiwang.csmall.product.pojo.entity.Album;
import com.xiwang.csmall.product.pojo.vo.AlbumNormalVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class AlbumMapperTests {

    @Autowired
    private AlbumMapper mapper;


    @Test
    public void List(){
        List<?> list=mapper.list();
        list.forEach(System.out::println);
//        QueryWrapper query = new QueryWrapper<>();
//        query.select("id", "name", "description", "sort");
//        List<?> list=mapper.selectMaps(query);
//        list.forEach(System.out::println);
    }

    @Test
    public void SelectMaps() {
        QueryWrapper<Album> albumQueryWrapper=new QueryWrapper<Album>();
        albumQueryWrapper.select("id","name", "description","sort");
        List<Map<String,Object>> albums= mapper.selectMaps(albumQueryWrapper);
        albums.forEach(System.out::println);
    }
    @Test
    public void getNormalById(){
        AlbumNormalVO normal=mapper.getNormalById(1L);
        System.out.println(normal);
    }

    @Test
    public void count(){
//        Long count=mapper.selectCount(null);
//        System.out.println("数据的数量为:"+count);
        mapper.count();
    }

    @Test
    public void testAlbumUpdate() {
//        UpdateWrapper<Album> albumUpdateWrapper=new UpdateWrapper<Album>();
//
//        albumUpdateWrapper
//                .like("name", "牛")
//                .set("description", "好牛啊");
//
//        int count=mapper.update(null,albumUpdateWrapper);
//        System.out.println(count);
//        SelectMaps();
        Album album=new Album();
        album.setId(1L);
        album.setName("牛逼坏了");

        mapper.updateById(album);
    }

    @Test
    public void insert(){
        Album album = new Album();
        album.setName("牛逼");
        album.setDescription("牛");
        album.setSort(1);
        mapper.insert(album);
        System.out.println(album.getId());
        SelectMaps();
    }

    @Test
    public void testInsertBatch() {
        List<Album> albumList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Album album = new Album();
            album.setName(""+i);
            album.setDescription(""+i);
            album.setSort(99);
            albumList.add(album);
        }
        int count=mapper.insertBatch(albumList);
        System.out.println("受影响的行数"+count);
    }

    @Test
    public void deleteById(){
        mapper.deleteById(3);
        SelectMaps();
    }

}
