package com.xiwang.csmall.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiwang.csmall.product.pojo.entity.AttributeTemplate;
import com.xiwang.csmall.product.pojo.vo.AttributeTemplateListItemVO;
import com.xiwang.csmall.product.pojo.vo.AttributeTemplateNormalVO;
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


    int insert(AttributeTemplate attributeTemplate);

    int insertBatch(List<AttributeTemplate> attributeTemplateList);

    /**
     * 根据id删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    int updateById(AttributeTemplate attributeTemplate);

    int count();

    AttributeTemplateNormalVO getNormalById(Long id);

    List<AttributeTemplateListItemVO> list();

    List<AttributeTemplate> selectAll();

    /**
     * 根据名称查询数据
     *
     * @param name 属性模板名称
     * @return 影响行数
     */
    int countByName(String name);
}
