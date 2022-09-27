package com.xiwang.coolshark.mapper;


import com.xiwang.coolshark.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category")
    List<Category> select();

    @Delete("delete from category where id=#{id}")
    void delete(int id);

    @Insert("insert into category values (null,#{name})")
    void insert(String name);

    @Update("update category set name=#{name} where id=#{id}")
    void update(Category category);
}
