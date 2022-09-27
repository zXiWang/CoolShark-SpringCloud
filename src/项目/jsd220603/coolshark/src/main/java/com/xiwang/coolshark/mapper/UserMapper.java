package com.xiwang.coolshark.mapper;


import com.xiwang.coolshark.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user values (null,#{username},#{password},#{nickname})")
    void insert(User user);

    @Select("select * from user")
    List<User> select();

    @Delete(("delete from user where id=#{id}"))
    void deleteById(int id);


    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);


}
