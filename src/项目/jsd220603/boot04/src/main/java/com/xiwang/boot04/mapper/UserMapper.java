package com.xiwang.boot04.mapper;


import com.xiwang.boot04.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user values (null,#{username},#{password},#{nickname})")
    void insert(User user);

    @Select("select * from user")
    List<User> select();

    @Delete(("delete from user where id=#{id}"))
    void deleteById(int id);

    @Update("update user set price=#{price},title=#{title},num=#{num} where id=#{id}")
    void update(User user);

    @Select("select password from user where username=#{username}")
    User selectByUsername(String username);
}
