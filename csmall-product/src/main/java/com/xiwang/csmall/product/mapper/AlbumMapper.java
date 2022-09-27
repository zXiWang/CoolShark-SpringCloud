package com.xiwang.csmall.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Album;
import com.xiwang.csmall.product.pojo.vo.AlbumListItemVO;
import com.xiwang.csmall.product.pojo.vo.AlbumNormalVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理相册数据的Mapper接口
 *
 * @author 夕妄
 * @version 1.0.0
 */

@Repository
public interface AlbumMapper extends BaseMapper<Album> {


    int insert(Album album);

    int insertBatch(List<Album> albumList);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    int updateById(Album album);

    int count();

    AlbumNormalVO getNormalById(Long id);

    List<AlbumListItemVO> list();

    int countByName(String name);

//    List<Album> selectAll();


}
