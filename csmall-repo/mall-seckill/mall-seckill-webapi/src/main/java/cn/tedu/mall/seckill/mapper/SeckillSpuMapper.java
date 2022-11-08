package cn.tedu.mall.seckill.mapper;

import cn.tedu.mall.pojo.seckill.model.SeckillSpu;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeckillSpuMapper {

    List<SeckillSpu> findSeckillSpus();

    List<SeckillSpu> findSeckillSpusByTime(LocalDateTime time);

    SeckillSpu findSeckillSpuById(Long spuId);

    // 布隆过滤器用:查询获得所有秒杀商品的spuId数组
    Long[] findAllSeckillSpuIds();
}
