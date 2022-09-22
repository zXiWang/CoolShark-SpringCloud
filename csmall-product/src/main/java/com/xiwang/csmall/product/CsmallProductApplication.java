package com.xiwang.csmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xiwang.csmall.product.mapper")
@SpringBootApplication
public class CsmallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsmallProductApplication.class, args);
    }


}

