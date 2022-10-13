package com.xiwang.csmall.passport.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.debug("创建Bean方法定义的对象:authenticationManagerBean");
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //白名单
        String[] urls = {
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs",
                "/admin/login",
//                "/admin/list"
        };
        //禁用-防止伪造跨域请求
        http.csrf().disable();
        http.authorizeRequests()// 对请求执行认证与授权
                .antMatchers(urls)// 匹配某些请求路径
                .permitAll()// （对此前匹配的请求路径）不需要通过认证即允许访问
                .anyRequest()// 除以上配置过的请求路径以外的所有请求路径
                .authenticated();// 要求是已经通过认证的
//                .and()
//                .formLogin();//开启表单验证,即未通过验证时,将重定向到登录表单
    }
}
