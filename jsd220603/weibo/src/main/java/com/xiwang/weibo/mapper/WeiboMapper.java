package com.xiwang.weibo.mapper;


import com.xiwang.weibo.entity.Weibo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeiboMapper {


    @Insert("insert into weibo values (null,#{content},#{created},#{urls},#{userId},#{nickname})")
    void insert(Weibo weibo);

    @Select("select id,content,urls,nickname from weibo order by created desc")
    List<Weibo> select();

    @Delete(("delete from weibo where id=#{id}"))
    void deleteById(Integer id);

    @Select("select id,content,urls,nickname,created from weibo where id=#{id}")
    Weibo selectById(Integer id);
}
