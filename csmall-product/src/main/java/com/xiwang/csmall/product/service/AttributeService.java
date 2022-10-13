package com.xiwang.csmall.product.service;


import com.xiwang.csmall.product.pojo.dto.AttributeAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Attribute;
import com.xiwang.csmall.product.pojo.vo.AttributeListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 属性(Attribute)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:45
 */
public interface AttributeService {

    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    @Transactional
    Attribute insert(Attribute attribute);

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     */
    @Transactional
    void updateById(Attribute attribute);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Transactional
    void delete(Long id);

    @Transactional
    void addNew(AttributeAddNewDTO attributeAddNewDTO);

    List<AttributeListItemVO> list();
}
