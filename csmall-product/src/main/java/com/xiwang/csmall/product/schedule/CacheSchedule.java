package com.xiwang.csmall.product.schedule;

import com.xiwang.csmall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 处理缓存的计划任务类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Component
public class CacheSchedule {

    @Autowired
    BrandService brandService;

    public CacheSchedule() {
        log.debug("创建计划任务类对象：CacheSchedule");
    }

    // 关于@Scheduled注解的属性配置：
    // fixedRate：每间隔多少毫秒执行一次
    // fixedDelay：上次执行结束后，过多少毫秒执行一次
    // cron：使用一个字符串，其中包含6~7个值，每个值之间使用1个空格进行分隔
    // >> 在cron的字符串的各值分别表示：秒 分 时 日 月 周（星期） [年]
    // >> 例如：cron = "56 34 12 2 1 ? 2035"，则表示2035年1月2日12:34:56将执行此计划任务，无论这一天是星期几
    // >> 以上各值都可以使用通配符，使用星号（*）则表示任意值，使用问号（?）表示不关心具体值，并且，问号只能用于“周（星期）”和“日”这2个位置
    // >> 以上各值，可以使用“x/x”格式的值，例如，分钟对应的值使用“1/5”，则表示当分钟值为1的那一刻开始执行，往后每间隔5分钟执行一次
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void rebuildCache() {
        log.debug("开始执行处理缓存的计划任务……");
        brandService.rebuildCache();
        log.debug("处理缓存的计划任务执行完成！");
    }

}
