package com.xiwang.boot07.mapper;

import com.xiwang.boot07.entity.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper {


    @Insert("insert into image values (null,#{intro},#{urls})")
    void insert(Image image);

    @Select("select * from image")
    List<Image> select();

    @Delete(("delete from image where id=#{id}"))
    void deleteById(Integer id);

    @Select("select * from image where id=#{id}")
    Image selectById(Integer id);
}
