package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.mapper.PictureMapper;
import com.xiwang.csmall.product.pojo.entity.Picture;
import com.xiwang.csmall.product.pojo.vo.PictureNormalVO;
import com.xiwang.csmall.product.service.PictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 图片(Picture)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService {
    @Resource
    private PictureMapper pictureMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PictureNormalVO getNormalById(Long id) {
        return this.pictureMapper.getNormalById(id);
    }


    /**
     * 新增数据
     *
     * @param picture 实例对象
     * @return 实例对象
     */
    @Override
    public Picture insert(Picture picture) {
        this.pictureMapper.insert(picture);
        return picture;
    }

    /**
     * 修改数据
     *
     * @param picture 实例对象
     * @return 实例对象
     */
    @Override
    public PictureNormalVO updateById(Picture picture) {
        this.pictureMapper.updateById(picture);
        return this.getNormalById(picture.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.pictureMapper.deleteById(id) > 0;
    }
}
