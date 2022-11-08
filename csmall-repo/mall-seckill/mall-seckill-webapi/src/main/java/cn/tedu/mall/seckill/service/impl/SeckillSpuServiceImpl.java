package cn.tedu.mall.seckill.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.product.vo.SpuDetailStandardVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import cn.tedu.mall.pojo.seckill.model.SeckillSpu;
import cn.tedu.mall.pojo.seckill.vo.SeckillSpuDetailSimpleVO;
import cn.tedu.mall.pojo.seckill.vo.SeckillSpuVO;
import cn.tedu.mall.product.service.seckill.IForSeckillSpuService;
import cn.tedu.mall.seckill.mapper.SeckillSpuMapper;
import cn.tedu.mall.seckill.service.ISeckillSpuService;
import cn.tedu.mall.seckill.utils.SeckillCacheUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


    /**
     * 根据spuId查询spu详情(返回值包含常规信息和秒杀信息以及随机码)
     *
     * @param spuId
     * @return
     */
    @Override
    public SeckillSpuVO getSeckillSpu(Long spuId) {
        // 在最终完整版的代码中,要在这里添加布隆过滤器的判断
        String seckillSpuKey = SeckillCacheUtils.getSeckillSpuVOKey(spuId);
        SeckillSpuVO seckillSpuVO = null;
        if (redisTemplate.hasKey(seckillSpuKey)) {
            seckillSpuVO = (SeckillSpuVO) redisTemplate
                    .boundValueOps(seckillSpuKey).get();
        } else {
            SeckillSpu seckillSpu = seckillSpuMapper.findSeckillSpuById(spuId);
            if (seckillSpu == null) {
                throw new CoolSharkServiceException(ResponseCode.NOT_FOUND
                        , "您访问的商品不存在!");
            }
            // 到此为止,已查询出了spu的秒杀商品信息,下面还要获取常规商品信息
            // dubbo调用product牟凯的方法获取spu常规信息
            SpuStandardVO spuStandardVO = dubboSeckillSpuService.getSpuById(spuId);
            seckillSpuVO = new SeckillSpuVO();
            BeanUtils.copyProperties(spuStandardVO, seckillSpuVO);
            seckillSpuVO.setSeckillListPrice(seckillSpu.getListPrice());
            seckillSpuVO.setStartTime(seckillSpu.getStartTime());
            seckillSpuVO.setEndTime(seckillSpu.getEndTime());

            redisTemplate.boundValueOps(seckillSpuKey).set(
                    seckillSpuVO,
                    10 * 60 * 1000 + RandomUtils.nextInt(10000),
                    TimeUnit.MILLISECONDS
            );
        }
        // 除了url属性外,所有属性都被赋值了
        // 判断秒杀时间,如果不在时间段内,不赋值,只能看不能卖
        LocalDateTime nowTime = LocalDateTime.now();
        // 因为是秒杀高并发状态,所以尽量不连接数据库
        if (seckillSpuVO.getStartTime().isBefore(nowTime) &&
                nowTime.isBefore(seckillSpuVO.getEndTime())) {
            String randCodeKey = SeckillCacheUtils.getRandCodeKey(spuId);
            String randCode = redisTemplate.boundValueOps(randCodeKey).get() + "";
            // 将随机码赋值到url
            seckillSpuVO.setUrl("/seckill/" + randCode);
            log.info("赋值随机码:{}", randCode);
        }
        return seckillSpuVO;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    public static final String SECKILL_SPU_DETAIL_VO_PREFIX = "seckill:spu:detail:vo:";

    // 秒杀spu信息对应的spuDetail
    @Override
    public SeckillSpuDetailSimpleVO getSeckillSpuDetail(Long spuId) {
        String seckillSpuDetailKey = SECKILL_SPU_DETAIL_VO_PREFIX + spuId;
        // 先声明一个返回值类型的对象,设值为null即可
        // 因为后面判断无论Redis中有没有这个key,都需要使用这个对象
        SeckillSpuDetailSimpleVO simpleVO = null;
        if (redisTemplate.hasKey(seckillSpuDetailKey)) {
            // 如果key已经存在,直接从redis中获取即可
            simpleVO = (SeckillSpuDetailSimpleVO) redisTemplate
                    .boundValueOps(seckillSpuDetailKey).get();
        } else {
            // 如果Redis中没有这个key,就要从数据库中查询
            // 使用Dubbo调用product模块的方法查询即可
            SpuDetailStandardVO spuDetailStandardVO =
                    dubboSeckillSpuService.getSpuDetailById(spuId);
            // 实例化SeckillSpuDetailSimpleVO对象
            simpleVO = new SeckillSpuDetailSimpleVO();
            BeanUtils.copyProperties(spuDetailStandardVO, simpleVO);
            // simpleVO赋值完成后,将它保存到Redis中,以便后续请求获取
            redisTemplate.boundValueOps(seckillSpuDetailKey)
                    .set(simpleVO, 10 * 60 * 1000 + RandomUtils.nextInt(10000),
                            TimeUnit.MILLISECONDS);
        }
        return simpleVO;
    }
}
