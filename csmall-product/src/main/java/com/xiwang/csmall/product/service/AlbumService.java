package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Album;
import com.xiwang.csmall.product.pojo.vo.AlbumNormalVO;

/**
 * 相册(Album)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:44
 */
public interface AlbumService {

    /**
     * 新增数据
     *
     * @param albumAddNewDTO 实例对象
     */
    void addNew(AlbumAddNewDTO albumAddNewDTO);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AlbumNormalVO getNormalById(Long id);

    /**
     * 新增数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    Album insert(Album album);

    /**
     * 修改数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    AlbumNormalVO updateById(Album album);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    void delete(Long id);

}
