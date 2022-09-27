package com.xiwang.coolshark.mapper;

import com.xiwang.coolshark.entity.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Insert("insert into news values" +
            "(null,#{source},#{title},#{url},#{content},0)")
    void insert(News news);

    @Select("select id,title,source,content,url from news")
    List<News> selectForIndex();

    @Select("select id,title,source,content,url,view_count from news")
    List<News> select();

    @Delete(("delete from news where id=#{id}"))
    void deleteById(Integer id);


    @Select("select id,title,source,url,content from news where id=#{id}")
    News selectById(Integer id);

    @Update("update news set title=#{title},source=#{source}" +
            ",url=#{url},content=#{content} where id=#{id}")
    void update(News news);

    @Select("select id,title,source,content,url from news where id=#{id}")
    News selectByIdForUpdate(Integer id);
}
