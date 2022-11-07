package cn.tedu.mall.seckill.service.impl;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import cn.tedu.mall.pojo.seckill.model.SeckillSpu;
import cn.tedu.mall.pojo.seckill.vo.SeckillSpuDetailSimpleVO;
import cn.tedu.mall.pojo.seckill.vo.SeckillSpuVO;
import cn.tedu.mall.product.service.seckill.IForSeckillSpuService;
import cn.tedu.mall.seckill.mapper.SeckillSpuMapper;
import cn.tedu.mall.seckill.service.ISeckillSpuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SeckillSpuServiceImpl implements ISeckillSpuService {
    // 装配查询秒杀Spu列表的Mapper
    @Autowired
    private SeckillSpuMapper seckillSpuMapper;

    // 查询方法的返回值泛型为SeckillSpuVO,其中包含很多普通spu表中的信息
    @DubboReference
    private IForSeckillSpuService dubboSeckillSpuService;

    /**
     * 分页查询秒杀商品信息
     *
     * @param page     当前页数
     * @param pageSize 每页条数
     * @return 常规spu信息和秒杀spu信息
     */
    @Override
    public JsonPage<SeckillSpuVO> listSeckillSpus(Integer page, Integer pageSize) {
        // 设置分页条件,准备分页查询
        PageHelper.startPage(page, pageSize);
        // 进行查询获得分页数据
        List<SeckillSpu> seckillSpus = seckillSpuMapper.findSeckillSpus();
        // 实例化一个SeckillSpuVO泛型的集合,以备赋值和最后的返回
        List<SeckillSpuVO> seckillSpuVOs = new ArrayList<>();
        // 遍历从数据库中查询出的所有秒杀商品列表
        for (SeckillSpu seckillSpu : seckillSpus) {
            // 获得秒杀商品的spuId
            Long spuId = seckillSpu.getSpuId();
            SpuStandardVO spuStandardVO = dubboSeckillSpuService.getSpuById(spuId);
            // 秒杀信息在seckillSpu中,常规信息在spuStandardVO中
            SeckillSpuVO seckillSpuVO = new SeckillSpuVO();
            BeanUtils.copyProperties(spuStandardVO, seckillSpuVO);
            // 秒杀信息要单独赋值,因为不全是同名属性
            seckillSpuVO.setSeckillListPrice(seckillSpu.getListPrice());
            seckillSpuVO.setStartTime(seckillSpu.getStartTime());
            seckillSpuVO.setEndTime(seckillSpu.getEndTime());

            seckillSpuVOs.add(seckillSpuVO);
        }
        return JsonPage.restPage(new PageInfo<>(seckillSpuVOs));
    }

    @Override
    public SeckillSpuVO getSeckillSpu(Long spuId) {
        return null;
    }

    @Override
    public SeckillSpuDetailSimpleVO getSeckillSpuDetail(Long spuId) {
        return null;
    }
}
