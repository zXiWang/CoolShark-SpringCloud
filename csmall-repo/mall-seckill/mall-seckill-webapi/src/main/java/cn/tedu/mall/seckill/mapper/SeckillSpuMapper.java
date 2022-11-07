package cn.tedu.mall.seckill.mapper;

import cn.tedu.mall.pojo.seckill.model.SeckillSpu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeckillSpuMapper {

    List<SeckillSpu> findSeckillSpus();
}
