package com.xiwang.csmall.cart.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiwang.csmall.cart.mapper")
public class MybatisPlusConfiguration {
    public MybatisPlusConfiguration() {

    }
}
