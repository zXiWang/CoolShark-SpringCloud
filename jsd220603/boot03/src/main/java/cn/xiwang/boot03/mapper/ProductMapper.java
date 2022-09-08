package cn.xiwang.boot03.mapper;

import cn.xiwang.boot03.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

    @Insert("insert into product values (null,#{title},#{price},#{num})")
    void insert(Product product);
}
