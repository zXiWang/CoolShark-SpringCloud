package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Album;
import com.xiwang.csmall.product.mapper.AlbumMapper;
import com.xiwang.csmall.product.pojo.vo.AlbumNormalVO;
import com.xiwang.csmall.product.service.AlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 相册(Album)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:43
 */
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumMapper albumMapper;

    @Override
    public void addNew(AlbumAddNewDTO albumAddNewDTO) {
        if(albumMapper.countByName(albumAddNewDTO.getName())!=0){
         throw new RuntimeException();
        }
        Album album=new Album();
        album.setName(albumAddNewDTO.getName());
        album.setDescription(albumAddNewDTO.getDescription());
        album.setSort(albumAddNewDTO.getSort());
        albumMapper.insert(album);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AlbumNormalVO getNormalById(Long id) {
        return this.albumMapper.getNormalById(id);
    }



    /**
     * 新增数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    @Override
    public Album insert(Album album) {
        this.albumMapper.insert(album);
        return album;
    }

    /**
     * 修改数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    @Override
    public AlbumNormalVO updateById(Album album) {
        this.albumMapper.updateById(album);
        return this.getNormalById(album.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.albumMapper.deleteById(id) > 0;
    }
}
