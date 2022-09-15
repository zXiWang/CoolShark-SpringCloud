package com.xiwang.coolshark.mapper;

import com.xiwang.coolshark.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("insert into product values" +
            "(null,#{title},#{url},#{price},#{oldPrice},#{saleCount},#{num},0,#{created},#{categoryId})")
    void insert(Product product);

    @Select("select * from product")
    List<Product> select();

    @Delete(("delete from product where id=#{id}"))
    void delete(Integer id);
}
