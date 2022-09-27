package com.xiwang.coolshark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ServletComponentScan
@SpringBootApplication
public class CoolsharkApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(CoolsharkApplication.class, args);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
                .maxAge(3600);
    }
}
