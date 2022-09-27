package com.xiwang.csmall.product.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiwang.csmall.product.mapper")
public class MybatisPlusConfiguration {
    public MybatisPlusConfiguration() {

    }
}
