package com.xiwang.csmall.passport.secrity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security调用了loadUserByUsername()方法，参数：{}", s);
        // 暂时使用模拟数据来处理登录认证，假设正确的用户名和密码分别是root和123456
        if ("root".equals(s)) {
            UserDetails userDetails = User.builder()
                    .username("root")
                    .password("$2a$10$nO7GEum8P27F8S0EGEHryel7m89opm/AMdaqMBk.qdsdIpE/SWFwe")
                    .accountExpired(false)
                    .accountLocked(false)
                    .disabled(false)
                    .authorities("这是一个山寨的权限标识") // 权限，注意，此方法的参数不可以为null，在不处理权限之前，可以写一个随意的字符串值
                    .build();
            log.debug("即将向Spring Security返回UserDetails对象：{}", userDetails);
            return userDetails;
        }
        log.debug("此用户名【{}】不存在，即将向Spring Security返回为null的UserDetails值", s);
        return null;
    }

}
