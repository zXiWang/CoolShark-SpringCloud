package com.xiwang.csmall.product.service;

import com.xiwang.csmall.product.pojo.dto.BrandAddNewDTO;
import com.xiwang.csmall.product.pojo.vo.BrandListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 品牌(Brand)表服务接口
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
public interface BrandService {

    @Transactional
    void addNew(BrandAddNewDTO brandAddNewDTO);

    @Transactional
    void delete(Long id);

    List<BrandListItemVO> list();


    @Transactional
    void setEnabled(Long id);

    @Transactional
    void setDisabled(Long id);
}
