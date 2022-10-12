package com.xiwang.csmall.passport.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.debug("创建@Bean方法定义的对象：PasswordEncoder");
        return new BCryptPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance(); // 无操作的密码编码器，即：不会执行加密处理

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //白名单
        String[] urls = {
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs"
        };
        //禁用-防止伪造跨域请求
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(urls)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();//开启表单验证,即未通过验证时,将重定向到登录表单
    }
}
