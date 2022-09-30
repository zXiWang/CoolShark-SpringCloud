package com.xiwang.csmall.passport.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xiwang.csmall.passport.mapper")
public class MybatisPlusConfiguration {
    public MybatisPlusConfiguration() {

    }
}
