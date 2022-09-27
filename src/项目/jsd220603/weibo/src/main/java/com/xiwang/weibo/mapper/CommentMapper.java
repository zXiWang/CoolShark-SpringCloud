package com.xiwang.weibo.mapper;




import com.xiwang.weibo.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {


    @Insert("insert into comment values (null,#{content},#{nickname},#{weiboId})")
    void insert(Comment comment);

    @Select("select id,content,nickname from comment order by created desc")
    List<Comment> select();

    @Delete(("delete from comment where id=#{id}"))
    void deleteById(Integer id);

    @Select("select id,content,nickname from comment where id=#{id}")
    Comment selectById(Integer id);

    @Select("select id,content,nickname from comment where weibo_id=#{id}")
    List<Comment> selectByWeiboId(Integer id);
}
