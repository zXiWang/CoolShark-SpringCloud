package com.xiwang.csmall.product.service;


import com.xiwang.csmall.product.pojo.entity.Attribute;

/**
 * 属性(Attribute)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:45
 */
public interface AttributeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Attribute getNormalById(Long id);


    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    Attribute insert(Attribute attribute);

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    Attribute updateById(Attribute attribute);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
