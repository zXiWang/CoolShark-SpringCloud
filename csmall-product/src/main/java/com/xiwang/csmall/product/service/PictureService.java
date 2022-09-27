package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.entity.Picture;
import com.xiwang.csmall.product.pojo.vo.PictureNormalVO;

/**
 * 图片(Picture)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface PictureService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PictureNormalVO getNormalById(Long id);


    /**
     * 新增数据
     *
     * @param picture 实例对象
     * @return 实例对象
     */
    Picture insert(Picture picture);

    /**
     * 修改数据
     *
     * @param picture 实例对象
     * @return 实例对象
     */
    PictureNormalVO updateById(Picture picture);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
