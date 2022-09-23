package com.xiwang.csmall.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiwang.csmall.product.pojo.entity.Album;
import com.xiwang.csmall.product.pojo.vo.AlbumNormalVO;
import org.apache.ibatis.annotations.Param;
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
    List<Album> selectAll();

    int insert(@Param("album") Album album);

    AlbumNormalVO getNormalById(Long id);

}
