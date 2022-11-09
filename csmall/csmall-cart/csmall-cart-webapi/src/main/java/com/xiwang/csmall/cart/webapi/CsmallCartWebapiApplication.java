package com.xiwang.csmall.cart.webapi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDubbo
@SpringBootApplication
// 启动SpringBoot对kafka的支持
@EnableKafka
// 为了测试kafka收发消息
// 我们利用SpringBoot自带的定时任务工具,周期性的向kafka发送消息
// 明确下面的注解和kafka没有必然的联系
@EnableScheduling
public class CsmallCartWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsmallCartWebapiApplication.class, args);
    }

}
