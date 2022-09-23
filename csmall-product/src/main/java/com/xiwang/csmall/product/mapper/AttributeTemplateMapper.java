package com.xiwang.csmall.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiwang.csmall.product.pojo.entity.AttributeTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 处理属性模板数据的Mapper接口
 *
 * @author 夕妄
 * @version 1.0.0
 */
@Repository
public interface AttributeTemplateMapper extends BaseMapper<AttributeTemplate> {


    List<AttributeTemplate> selectAll();
}
