package com.xiwang.csmall.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiwang.csmall.order.mapper")
public class MybatisPlusConfiguration {
    public MybatisPlusConfiguration() {

    }
}
