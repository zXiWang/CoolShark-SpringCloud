package com.xiwang.csmall.stock.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiwang.csmall.stock.mapper")
public class MybatisPlusConfiguration {
    public MybatisPlusConfiguration() {

    }
}
