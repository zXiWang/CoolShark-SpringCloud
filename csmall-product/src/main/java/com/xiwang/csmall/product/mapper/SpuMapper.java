package com.xiwang.csmall.product.mapper;

import com.xiwang.csmall.product.pojo.entity.Spu;
import com.xiwang.csmall.product.pojo.vo.SpuNormalVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SPU（Standard Product Unit）(Spu)表数据库访问层
 *
 * @author 夕妄
 * @since 2022-09-26 09:20:51
 */
@Repository
public interface SpuMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SpuNormalVO getNormalById(Long id);

    /**
     * 查询指定行数据
     *
     * @param Spu      查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<Spu> list(Spu Spu, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @return 总行数
     */
    int count();

    /**
     * 新增数据
     *
     * @param Spu 实例对象
     * @return 影响行数
     */
    int insert(Spu Spu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Spu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Spu> entities);


    /**
     * 修改数据
     *
     * @param Spu 实例对象
     * @return 影响行数
     */
    int updateById(Spu Spu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    int countByBrand(Long id);

    int countByAlbum(Long id);

    int countByCategory(Long id);
}

