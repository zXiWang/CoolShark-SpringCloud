package com.xiwang.coolshark.mapper;

import com.xiwang.coolshark.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("insert into product values" +
            "(null,#{title},#{url},#{price},#{oldPrice},#{saleCount},#{num},0,#{created},#{categoryId})")
    void insert(Product product);

    @Select("select id,title,price,old_price,sale_count,url from product")
    List<Product> selectForIndex();

    @Select("select id,title,price,old_price,sale_count,url from product")
    List<Product> select();

    @Delete(("delete from product where id=#{id}"))
    void deleteById(Integer id);

    @Select("select title,sale_count from product order by sale_count desc limit 0,6")
    List<Product> selectTop();

    @Select("select id,title,price,old_price,sale_count,url from product where category_id=#{id}")
    List<Product> selectByCategoryId(Integer id);

    @Select("select id,title,price,old_price,sale_count,url from product where title like concat('%',#{title},'%')")
    List<Product> selectByWd(String wd);

    @Select("select id,title,price,old_price,sale_count,url,view_Count,created from product where id=#{id}")
    Product selectById(Integer id);

    @Update("update product set title=#{title},price=#{price},old_price=#{oldPrice}" +
            ",sale_count=#{saleCount},url=#{url},view_count=#{viewCount},created=#{created} where id=#{id}")
    void update(Product product);

    @Update("update product set view_count=view_count+1 where id=#{id}")
    void updateViewCountById(Integer id);
}
