package com.xiwang.csmall.product.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xiwang.csmall.product.pojo.entity.Album;
import com.xiwang.csmall.product.pojo.vo.AlbumNormalVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class AlbumMapperTests {

    @Autowired
    private AlbumMapper mapper;

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
        Long count=mapper.selectCount(null);
        System.out.println("数据的数量为:"+count);
    }

    @Test
    public void AlbumUpdate() {
        UpdateWrapper<Album> albumUpdateWrapper=new UpdateWrapper<Album>();

        albumUpdateWrapper
                .like("name", "牛")
                .set("description", "好牛啊")
                .setSql("sort ='5'");

        Album album=new Album();
        int count=mapper.update(album,albumUpdateWrapper);
        System.out.println(count);
        SelectMaps();
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
    public void delete(){
        mapper.deleteById(2);
        SelectMaps();
    }

}
