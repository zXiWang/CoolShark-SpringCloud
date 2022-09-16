package com.xiwang.coolshark.mapper;

import com.xiwang.coolshark.entity.Banner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BannerMapper {

    @Select("select id,url from banner")
    List<Banner> select();


    Banner selectById(Integer id);

    @Delete("delete from banner where id=#{id}")
    void deleteById(Integer id);

    @Insert("insert into banner values(null,#{url})")
    void insert(String url);
@Select("select * from banner where url=#{url}")
    Banner selectByUrl(String url);
}
