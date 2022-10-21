package com.xiwang.csmall.product.service.impl;

import com.xiwang.csmall.product.ex.ServiceException;
import com.xiwang.csmall.product.mapper.BrandCategoryMapper;
import com.xiwang.csmall.product.mapper.CategoryAttributeTemplateMapper;
import com.xiwang.csmall.product.mapper.CategoryMapper;
import com.xiwang.csmall.product.mapper.SpuMapper;
import com.xiwang.csmall.product.pojo.dto.CategoryAddNewDTO;
import com.xiwang.csmall.product.pojo.entity.Category;
import com.xiwang.csmall.product.pojo.vo.CategoryListItemVO;
import com.xiwang.csmall.product.pojo.vo.CategoryNormalVO;
import com.xiwang.csmall.product.service.CategoryService;
import com.xiwang.csmall.product.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类别(Category)表服务实现类
 *
 * @author 夕妄
 * @since 2022-09-26 15:02:46
 */
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandCategoryMapper brandCategoryMapper;
    @Autowired
    private CategoryAttributeTemplateMapper categoryAttributeTemplateMapper;
    @Autowired
    private SpuMapper spuMapper;

    public CategoryServiceImpl() {
        log.info("创建业务对象：CategoryServiceImpl");
    }


    @Override
    public void addNew(CategoryAddNewDTO categoryAddNewDTO) {
        if (categoryMapper.countByName(categoryAddNewDTO.getName()) != 0) {
            String message = "添加类别失败!名称重复!";
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        Category category = new Category();
        BeanUtils.copyProperties(categoryAddNewDTO, category);
        categoryMapper.insert(category);
    }

    @Override
    public void delete(Long id) {
        CategoryNormalVO categoryNormalVO=categoryMapper.getNormalById(id);
        if (categoryMapper.getNormalById(id) == null) {
            String message = "删除失败!不存在此类别!";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        // 检查当前尝试删除的类别是否存在子级类别：判断以上查询结果的isParent是否为1
        if (categoryNormalVO.getIsParent() == 1) {
            // 是：当前尝试删除的类别“是父级类别”（包含子级），抛出异常（ERR_CONFLICT）
            String message = "删除类别失败，尝试删除的类别仍包含子级类别！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 如果此类别关联了品牌，则不允许删除
        {
            int count = brandCategoryMapper.countByCategory(id);
            if (count > 0) {
                String message = "删除类别失败，当前类别仍关联了类别！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_DELETE, message);
            }
        }

        // 如果此类别关联了属性模板，则不允许删除
        {
            int count = categoryAttributeTemplateMapper.countByCategory(id);
            if (count > 0) {
                String message = "删除类别失败，当前类别仍关联了属性模板！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_DELETE, message);
            }
        }

        // 如果此类别关联了SPU，则不允许删除
        {
            int count = spuMapper.countByCategory(id);
            if (count > 0) {
                String message = "删除类别失败，当前类别仍关联了商品！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERR_DELETE, message);
            }
        }

        // 调用Mapper对象的【根据id删除】执行删除，并获取返回值
        int rows = categoryMapper.deleteById(id);
        // 判断返回值是否不为1
        if (rows != 1) {
            // 是：抛出异常（ERR_DELETE）
            String message = "删除类别失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }

        // ====== 如果这是父级类别中的最后一个子级，则将父级的isParent改为0 =====
        // 从当前尝试删除的类别对象中取出parentId
        Long parentId = categoryNormalVO.getParentId();
        // 判断当前类别是否不为1级类别，即parentId不为0
        if (parentId != 0) {
            // 调用Mapper对象的countByParentId(parentId)进行统计
            int count = categoryMapper.countByParentId(parentId);
            // 判断统计结果是否为0
            if (count == 0) {
                // 创建新的Category对象，用于更新父级，此Category对象中需要封装：id（parentId），isParent（0）
                Category parentCategory = new Category();
                parentCategory.setId(parentId);
                parentCategory.setIsParent(0);
                // 调用Mapper对象的【更新】功能，执行修改数据，并获取返回值
                rows = categoryMapper.updateById(parentCategory);
                // 判断返回值是否不为1
                if (rows != 1) {
                    // 是：抛出异常（ERR_UPDATE）
                    String message = "删除类别失败，服务器忙，请稍后再尝试！";
                    log.warn(message);
                    throw new ServiceException(ServiceCode.ERR_UPDATE, message);
                }
            }
        }
        categoryMapper.deleteById(id);
    }

    @Override
    public List<CategoryListItemVO> list() {
        return categoryMapper.list();
    }

    @Override
    public List<CategoryListItemVO> listByParentId(Long templateId) {
        return categoryMapper.listByParentId(templateId);
    }
}
