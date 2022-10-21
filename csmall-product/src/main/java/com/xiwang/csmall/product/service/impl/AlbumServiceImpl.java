package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.AlbumMapper;
import com.xiwang.csmall.product.mapper.PictureMapper;
import com.xiwang.csmall.product.mapper.SpuMapper;
import com.xiwang.csmall.product.pojo.dto.AlbumAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Album;
import com.xiwang.csmall.product.pojo.vo.AlbumListItemVO;
import com.xiwang.csmall.product.pojo.vo.AlbumNormalVO;
import com.xiwang.csmall.product.service.AlbumService;
import com.xiwang.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 相册(Album)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:43
 */
@Slf4j
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumMapper albumMapper;

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    SpuMapper spuMapper;

    @Override
    public List<AlbumListItemVO> list() {
        return albumMapper.list();
    }

    @Override
    public void addNew(AlbumAddNewDTO albumAddNewDTO) {
        if (albumMapper.countByName(albumAddNewDTO.getName()) != 0) {
            String message = "添加相册失败，尝试添加的相册名称已经被占用！";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        Album album = new Album();
        BeanUtils.copyProperties(albumAddNewDTO, album);
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
     */
    @Override
    public void delete(Long id) {
        log.debug("开始处理[删除相册]过程,处理id为: {}", id);
        AlbumNormalVO queryResult = albumMapper.getNormalById(id);
        if (queryResult == null) {
            String message = "删除相册失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        int rows = albumMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除相册失败,服务器未响应!";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }

        // 检查此相册是否关联了图片
        {
            int count = pictureMapper.countByAlbum(id);
            if (count > 0) {
                String message = "删除相册失败！当前相册中仍有图片！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }

        // 检查此相册是否关联了SPU
        {
            int count = spuMapper.countByAlbum(id);
            if (count > 0) {
                String message = "删除相册失败！当前相册仍关联了商品！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
            }
        }
    }
}
