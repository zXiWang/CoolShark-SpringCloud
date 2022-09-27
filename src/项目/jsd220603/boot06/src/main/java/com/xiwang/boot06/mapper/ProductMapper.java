package com.xiwang.boot06.mapper;




import com.xiwang.boot06.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("insert into product values (null,#{title},#{price},#{num})")
    void insert(Product product);

    @Select("select * from product")
    List<Product> select();

    @Delete(("delete from product where id=#{id}"))
    void deleteById(int id);

    @Update("update product set price=#{price},title=#{title},num=#{num} where id=#{id}")
    void update(Product product);

    @Select("select id from product where title=#{title}")
    Product selectByTitle(String title);

    @Select("select * from product where id=#{id}")
    Product selectById(int id);
}
