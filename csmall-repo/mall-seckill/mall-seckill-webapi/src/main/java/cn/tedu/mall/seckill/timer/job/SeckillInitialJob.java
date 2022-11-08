package cn.tedu.mall.seckill.timer.job;

import cn.tedu.mall.pojo.seckill.model.SeckillSku;
import cn.tedu.mall.pojo.seckill.model.SeckillSpu;
import cn.tedu.mall.seckill.mapper.SeckillSkuMapper;
import cn.tedu.mall.seckill.mapper.SeckillSpuMapper;
import cn.tedu.mall.seckill.utils.SeckillCacheUtils;
import com.alibaba.nacos.client.naming.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SeckillInitialJob implements Job {

    //
    @Autowired
    private SeckillSkuMapper skuMapper;

    // 需要查询秒杀spu相关信息的mapper
    @Autowired
    private SeckillSpuMapper spuMapper;

    // 操作Redis的对象
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 当前方法是执行缓存预热工作的
        // 本方法运行的实际是秒杀开始前5分钟,所以要获取分钟后进行秒杀的所有商品
        LocalDateTime time = LocalDateTime.now().plusMinutes(5);
        // 查询这个时间所有进行秒杀的商品
        List<SeckillSpu> seckillSpus = spuMapper.findSeckillSpusByTime(time);
        for (SeckillSpu spu : seckillSpus) {
            List<SeckillSku> seckillSkus = skuMapper.findSeckillSkusBySpuId(spu.getSpuId());
            // 再次遍历seckillSkus,这个集合其中的对象包含库存信息
            for (SeckillSku sku : seckillSkus) {
                log.info("开始将{}号sku商品的库存预热到Redis", sku.getSkuId());
                // 要操作Redis,先确定保存值用的key
                String skuStockKey = SeckillCacheUtils.getStockKey(sku.getSkuId());
                if (redisTemplate.hasKey(skuStockKey)) {
                    // 如果key已经存在,说明缓存过了,直接跳过
                    log.info("{}好sku商品已经缓存过了", sku.getSkuId());
                } else {
                    // 如果key不存在,就要将我们sku对象的库存数保存在Redis中
                    stringRedisTemplate.boundValueOps(skuStockKey)
                            .set(sku.getSeckillStock() + "",
                                    10 * 60 * 1000 + RandomUtils.nextInt(10000),
                                    TimeUnit.MILLISECONDS);
                    log.info("{}号sku商品库存数成功进入缓存!", sku.getSkuId());
                }
            }
            // 循环结束,完成当前正在遍历的spu对应的所有sku库存数的缓存
            // 下面开始缓存预热spu对应的随机码
            // 随机码会在用户提交订单时,进行验证,不提供正确随机码的用户不能生成订单
            String randCodeKey = SeckillCacheUtils.getRandCodeKey(spu.getSpuId());
            // 判断当前随机码key是否已经在redis中
            if (redisTemplate.hasKey(randCodeKey)) {
                // 如果已经存在这个key,不需要做操作
                int randCode = (int) redisTemplate.boundValueOps(randCodeKey).get();
                log.info("{}号spu商品的随机码已经缓存过了,值为:{}",
                        spu.getSpuId(), randCode);
            } else {
                // 如果这个key没有保存过,就生成随机码保存在Redis
                // 生成随机码的范围自定,这里使用100000-999999
                int randCode = RandomUtils.nextInt(900000) + 100000;
                redisTemplate.boundValueOps(randCodeKey)
                        .set(randCode, 10 * 60 * 1000 + RandomUtils.nextInt(10000),
                                TimeUnit.MILLISECONDS);
                log.info("spuId为{}号的随机码生成成功!值为:{}",
                        spu.getSpuId(), randCode);
            }

        }
    }
}
